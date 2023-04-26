package StructuringConcurrentApplications_2.ApplyingThreadPools_8.ExampleCode.SlidingBlockPuzzles;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

public class PuzzleSolver<P, M> extends ConcurrentPuzzleSolver<P, M> {

    private final AtomicInteger TASK_COUNT = new AtomicInteger(0);

    public PuzzleSolver(Puzzle<P, M> puzzle, ExecutorService executor, ConcurrentHashMap<P, Boolean> seen) {
        super(puzzle, executor, seen);
    }

    protected Runnable newTask(P position, M move, SequentialPuzzleSolver.Node<P, M> node) {
        return new CountingSolverTask(position, move, node);
    }

    private class CountingSolverTask extends SolverTask {
        public CountingSolverTask(P position, M move, SequentialPuzzleSolver.Node<P, M> node) {
            super(position, move, node);
            TASK_COUNT.incrementAndGet();
        }

        @Override
        public void run() {
            try {
                super.run();
            } finally {
                if (TASK_COUNT.decrementAndGet() == 0) {
                    solution.setValue(null);
                }
            }
        }
    }
}
