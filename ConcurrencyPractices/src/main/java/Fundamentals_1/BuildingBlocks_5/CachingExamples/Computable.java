package Fundamentals_1.BuildingBlocks_5.CachingExamples;

/*
    Interface describes a single abstract function
    - compute
        - takes input of type A
        - returns result of type V
 */
public interface Computable<A, V> {
    V compute(A argument) throws InterruptedException;
}
