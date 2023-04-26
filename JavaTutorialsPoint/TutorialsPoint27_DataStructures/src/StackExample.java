import java.util.EmptyStackException;
import java.util.Stack;

@SuppressWarnings("unchecked")
public class StackExample {

    private static void showpush(Stack st, int a){
        st.push(a);
        System.out.println("push(" + a + ")");
        System.out.println("stack: " + st);
    }

    private static void showpop(Stack st){
        System.out.print("pop-> ");
        Integer a  = (Integer)st.pop();
        System.out.println(a);
        System.out.println("stack: " + st);
    }

    public static void main(String[] args) {
        Stack st = new Stack();
        System.out.println("stack: " + st);

        showpush(st, 42);
        showpush(st, 66);
        showpush(st, 99);

        showpop(st);
        showpop(st);
        showpop(st);

        try {
            showpop(st);
        } catch (EmptyStackException e) {
            e.printStackTrace();
        }
    }

}