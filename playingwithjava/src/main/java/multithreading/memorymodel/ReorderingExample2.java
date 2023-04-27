package multithreading.memorymodel;


import utils.Generated;

@Generated
@SuppressWarnings("all")
public class ReorderingExample2 {

    @Generated
    static class MyFavorite {
        int thing;
    }

    // Shared Data
    MyFavorite myFavorite1;
    MyFavorite myFavorite2;

    void method1() {
        MyFavorite read1 = myFavorite1;
        int read2 = read1.thing;
        MyFavorite read3 = myFavorite2;
        int read4 = read3.thing;
        int read5 = read1.thing;
    }

    void method2() {
        MyFavorite read6 = myFavorite1;
        read6.thing = 3;
    }
}
