import java.util.Locale;

public class SinoTibetan extends Language{

    public SinoTibetan(String name, int numSpeakers) {
        super(name, numSpeakers, "Asia", "subject-object-verb");
        if (this.name.toLowerCase(Locale.ROOT).contains("chinese")) {
            wordOrder = "subject-verb-object";
        }
    }


}
