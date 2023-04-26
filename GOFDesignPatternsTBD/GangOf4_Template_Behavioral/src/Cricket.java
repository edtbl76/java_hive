class Cricket extends Game {

    @Override
    void endPlay() {
        System.out.println("Cricket game is over!");
    }

    @Override
    void initialize() {
        System.out.println("Cricket game Initialized! Begin!");
    }

    @Override
    void startPlay() {
        System.out.println("Cricket game on!!!");
    }
}
