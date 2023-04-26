class User {
    private final String name;

    String getName() {
        return name;
    }

    User(String name){
        this.name = name;
    }

    void sendMessage(String message) {
        ChatRoom.showMessage(this, message);
    }


}
