package ExceptionHandling;

public class DBException {

    public static class BadExecution extends BaseException {
        public BadExecution(String msg) {
            super(msg);
        }
    }

    public static class NoData extends BaseException {
        public NoData(String msg) {
            super(msg);
        }
    }

    public static class MoreData extends BaseException {
        public MoreData(String msg) {
            super(msg);
        }
    }

    public static class InvalidParam extends BaseException {
        public InvalidParam(String msg) {
            super(msg);
        }
    }

}
