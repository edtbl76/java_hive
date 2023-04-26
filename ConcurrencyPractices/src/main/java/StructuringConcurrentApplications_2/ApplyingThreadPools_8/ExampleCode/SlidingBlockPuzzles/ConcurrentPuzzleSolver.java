package StructuringConcurrentApplications_2.ApplyingThreadPools_8.ExampleCode.SlidingBlockPuzzles;

import StructuringConcurrentApplications_2.ApplyingThreadPools_8.ExampleCode.SlidingBlockPuzzles.SequentialPuzzleSolver.Node;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;

public class ConcurrentPuzzleSolver<P, M> {

    private final Puzzle<P, M> puzzle;
    private final ExecutorService executor;

    /*
        We use a ConcurrentHashMap instead of a Set to track visited positions in this version because it might be
        accessed from different Threads.

            Using ConcurrentHashMap
                - provides thread safety
                - avoids inherent race condition in conditionally updating a shared collection by using

                    putIfAbsent() (which atomically adds positions rather than the non-atomic check-and-set pattern)
     */
    private final ConcurrentHashMap<P, Boolean> seen;

    /*
        This is a Result-Bearing Latch.
     */
    final ValueLatch<Node<P, M>> solution = new ValueLatch<>();

    public ConcurrentPuzzleSolver(Puzzle<P, M> puzzle, ExecutorService executor, ConcurrentHashMap<P, Boolean> seen) {
        this.puzzle = puzzle;
        this.executor = executor;
        this.seen = seen;
    }

    public List<M> solve() throws InterruptedException {
        try {
            P position = puzzle.initialPosition();
            executor.execute(newTask(position, null, null));

            // block until a solution is found
            Node<P, M> solutionNode = solution.getValue();
            return (solutionNode == null) ? null : solutionNode.asMoveList();
        } finally {
            executor.shutdown();
        }
    }

    private Runnable newTask(P position, M move, Node<P, M> node) {
        return new SolverTask(position, move, node);
    }

    class SolverTask extends Node<P,M> implements Runnable {
        private final P position;

        public SolverTask(P position, M move, Node<P, M> node) {
            super(position, move, node);
            this.position = position;
        }

        /*
         *  Most of the logic in the inner class is done here.
         *
         */
        @Override
        public void run() {

            /*
                STEP 1:
                    - Evaluation of the set of next possible positoins
                        - solution.isSet() means that we've solved the puzzle
                            - This is our ValueLatch (Result-Bearing Latch)

                        - seen.putIfAbsent(position, true) is only non-null when we're in a loop (i.e. encountering
                        the same positions).

                NOTE:
                - If we don't track the positions we have encountered, then it is possible for this algorithm to
                loop infinitely.

                    In the sequential version, these positions are tracked in a Set.

                    In this version, we use ConcurrentHashMap seen.
             */
            if (solution.isSet() || seen.putIfAbsent(position, true) != null) {
                // already solved or seen this position
                return;
            }

            /*
                STEP 2:
                    A: if puzzle.isGoal(position), we've solved it so we can set the value to THIS task.
                    B: else, we iterate through each legal move from this position and submit all of the possibilities
                       to the Executor.
             */
            if (puzzle.isGoal(position)) {
                solution.setValue(this);
            } else {
                for (M move : puzzle.legalMoves(position)) {
                    /*
                        Here we are using the internal WORK QUEUE of the Thread Pool managed by the Executor to
                        hold the state of the search.

                        If you look at SequentialPuzzleSolver, we manage this via the call stack directly.
                     */
                    executor.execute(newTask(puzzle.move(position, move), move, this));
                }
            }
        }
    }
}
