public class Language {

    protected String name;
    protected int numSpeakers;
    protected String regionsSpoken;
    protected String wordOrder;

    public Language(String name, int numSpeakers, String regionsSpoken, String wordOrder) {
        this.name = name;
        this.numSpeakers = numSpeakers;
        this.regionsSpoken = regionsSpoken;
        this.wordOrder = wordOrder;
    }

    public void getInfo() {
        System.out.println(name + " is spoken by " + numSpeakers + " people mainly in " + regionsSpoken + ".");
        System.out.println("The language follows the word order: " + wordOrder);
    }

    public static void main(String[] args) {
        Language language = new Language(
                "Danish", 10000000, "Europe", "subject-verb-object");
        language.getInfo();

        Mayan mocho = new Mayan("Mocho'", 30);
        mocho.getInfo();

        SinoTibetan chinese = new SinoTibetan("Chinese", 1000000000);
        SinoTibetan notChinese = new SinoTibetan("Burmese", 10);
        chinese.getInfo();
        notChinese.getInfo();

    }
}
