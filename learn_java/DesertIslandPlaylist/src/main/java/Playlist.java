import java.util.ArrayList;
import java.util.List;

public class Playlist {
    public static void main(String[] args) {
        List<String> desertIslandPlaylist = new ArrayList<>();

        desertIslandPlaylist.add("Love Bomb Baby");
        desertIslandPlaylist.add("Do Ya Really Want To Taste It?");
        desertIslandPlaylist.add("Monster");
        desertIslandPlaylist.add("Come On, Come On");
        desertIslandPlaylist.add("Home Sweet Home");
        desertIslandPlaylist.add("Outrun");
        desertIslandPlaylist.add("Pumped Up Kicks");

        System.out.println(desertIslandPlaylist);

        System.out.println(desertIslandPlaylist.size());

        desertIslandPlaylist.remove(4);
        System.out.println(desertIslandPlaylist.size());

        desertIslandPlaylist.remove(4);
        System.out.println(desertIslandPlaylist.size());
        System.out.println(desertIslandPlaylist);

        // Swap Songs
        int songA = desertIslandPlaylist.indexOf("Monster");
        int songB = desertIslandPlaylist.indexOf("Pumped Up Kicks");
        String temp = desertIslandPlaylist.get(songA);
        desertIslandPlaylist.set(songA, desertIslandPlaylist.get(songB));
        desertIslandPlaylist.set(songB, temp);
        System.out.println(desertIslandPlaylist);

    }
}
