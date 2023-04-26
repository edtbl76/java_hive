package StructuringConcurrentApplications_2.ApplyingThreadPools_8.ExampleCode.SlidingBlockPuzzles;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class SequentialPuzzleSolver<P, M> {

    private final Puzzle<P, M> puzzle;
    private final Set<P> seen = new HashSet<>();

    public SequentialPuzzleSolver(Puzzle<P, M> puzzle) {
        this.puzzle = puzzle;
    }

    /*
        Simple Solver method.
     */
    public List<M> solve() {

        // Set our initial position
        P position = puzzle.initialPosition();

        /*
            This returns a list of Moves returned from the search method.
            - we pass the initial puzzle state.
                - initial position,
                - no previous move
                - no previous node.
         */
        return search(new Node<>(position, null, null));
    }

    private List<M> search(Node<P, M> node) {

        /*
            Initial check:
            - have we seen this node before?
                - yes? return null, which means we didn't find a solution.
                - no? add this node to the Set of nodes to track it.

                Then proceed...
         */
        if (!seen.contains(node.position)) {
            seen.add(node.position);

            /*
                If this position is the target, then we're done!
                - call the asMoveList() method which returns the nose as the sequence of moves from initial position
                to the target.

                THIS IS THE WINNING SOLUTION.
             */
            if (puzzle.isGoal(node.position)) {
                return node.asMoveList();
            }

            /*
                If we hit this spot, then we've got to search the next node>
                - get the puzzle to calculate the legal moves from our current position
                - cycle through the list of legal modes:
                    - establish a new position after making the move
                    - create a node based on that position
                    - call search() recursively
                    - we only return a result if it isn't null.
                    - if it falls off the edge, then we'll unwind the loop w/ a set of nulls.

                This is a DEPTH First Search :)
             */
            for (M move : puzzle.legalMoves(node.position)) {
                P position = puzzle.move(node.position, move);
                Node<P, M> child = new Node<>(position, move, node);
                List<M> result = search(child);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }

    static class Node<P, M> {
        final P position;
        final M move;
        final Node<P, M> previous;

        public Node(P position, M move, Node<P, M> previous) {
            this.position = position;
            this.move = move;
            this.previous = previous;
        }

        List<M> asMoveList() {
            List<M> solution = new LinkedList<>();
            for (Node<P, M> node = this; node.move != null; node = node.previous) {
                solution.add(0, node.move);
            }
            return solution;
        }
    }
}
