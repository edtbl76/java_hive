package GenericTypeArrays;

public class GenericArrayExample<T> {

    // This is ok
    public T[] notYetInstantiatedArray;

    // causes compiler error. Can't create a generic array of T
    //public T[] array = new T[5];

}
