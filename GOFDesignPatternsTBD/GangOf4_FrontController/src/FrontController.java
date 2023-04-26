@SuppressWarnings("SameReturnValue")
class FrontController {

    private final Dispatcher dispatcher;

    public FrontController() {
        dispatcher = new Dispatcher();
    }

    private boolean isAuthenticUser(){
        System.out.println(("User is authenticated successfully"));
        return true;
    }

    private void trackRequest(String request) {
        System.out.println("Page requested: " + request);
    }

    public void dispatchRequest(String request) {
        // log
        trackRequest(request);

        //authN
        if(isAuthenticUser()){
            dispatcher.dispatch(request);
        }
    }
}
