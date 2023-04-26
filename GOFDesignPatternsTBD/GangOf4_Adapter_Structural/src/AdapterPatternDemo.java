public class AdapterPatternDemo {

    public static void main(String[] args) {
        AudioPlayer audioPlayer = new AudioPlayer();

        audioPlayer.play("mp3", "ceremony.mp3");
        audioPlayer.play("mp4", "change of seasons.mp4");
        audioPlayer.play("vlc", "friends.vlc");
        audioPlayer.play("avi", "suck my kiss.avi");
    }
}
