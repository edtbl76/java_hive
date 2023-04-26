package ClassAndInterface;

public interface DemoInterface<T1, T2> {
    T2 doOperation(T1 t);
    T1 doReverse(T2 t2);
}

class DerivedDemoClass implements DemoInterface<String, Integer> {

    public Integer doOperation(String t) {
        return Integer.parseInt(t);
    }

    @Override
    public String doReverse(Integer integer) {
        return String.valueOf(integer);
    }
}
