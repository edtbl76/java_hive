class Chat {
    private boolean flag = false;

    synchronized void Question(String msg) {
        if (flag) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(msg);
        flag = true;
        notify();
    }

    synchronized void Answer(String msg) {
        if (!flag) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(msg);
        flag = false;
        notify();
    }
}

class ThreadOne implements Runnable {
    private final Chat m;
    private final String[] s1 = { "Hi", "How are you?", "I'm also doing fine!"};

    ThreadOne(Chat m1) {
        this.m = m1;
        new Thread(this, "Question").start();
    }

    public void run() {
        for (String s : s1) {
            m.Question(s);
        }
    }
}

class ThreadTwo implements Runnable {
    private final Chat m;
    private final String[] s2 = { "Hi", "I'm good, what about you?", "Great!"};

    ThreadTwo(Chat m2) {
        this.m = m2;
        new Thread(this, "Answer").start();
    }

    public void run() {
        for (String s : s2) {
            m.Answer(s);
        }
    }
}

public class InterthreadCommDemo {

    public static void main(String[] args) {
        Chat m = new Chat();
        new ThreadOne(m);
        new ThreadTwo(m);
    }
}
