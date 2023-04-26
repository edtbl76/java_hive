class Football extends Game {

    @Override
    void endPlay() {
        System.out.println("Football game is over!");
    }

    @Override
    void initialize() {
        System.out.println("Football game Initialized! Begin!");
    }

    @Override
    void startPlay() {
        System.out.println("Football game on!!!");
    }
}
