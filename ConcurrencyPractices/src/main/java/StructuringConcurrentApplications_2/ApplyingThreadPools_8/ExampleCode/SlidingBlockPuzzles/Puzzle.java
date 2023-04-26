package StructuringConcurrentApplications_2.ApplyingThreadPools_8.ExampleCode.SlidingBlockPuzzles;

import java.util.Set;

public interface Puzzle<P, M> {

    P initialPosition();

    boolean isGoal(P position);

    Set<M> legalMoves(P position);

    P move(P position, M move);

}
