package BehavioralDesignPatterns.ChainOfResponsibility.ApproverExample_1;

public class ChainOfResponsibilityDemo {

    public static void main(String[] args) {
        Director kubrik = new Director();
        VP biden = new VP();
        CEO ed = new CEO();

        kubrik.setSuccessor(biden);
        biden.setSuccessor(ed);

        /*
            An interesting note about Chain of Responsibility
            - the initial handler is all that the client sees.
            - The client doesn't care who approved the request, just that it got approved.
         */
        Request request = new Request(RequestType.CONFERENCE, 500);
        kubrik.handleRequest(request);

        request = new Request(RequestType.PURCHASE, 1000);
        kubrik.handleRequest(request);

        request = new Request(RequestType.PURCHASE, 2000);
        kubrik.handleRequest(request);
    }
}
