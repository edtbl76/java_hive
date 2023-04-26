public class Continents {


    public static void main(String[] args) {

        int continent = 4;
        String msg;

        switch (continent) {
            case 1:
                msg = "North America: Mexico City, Mexico";
                break;
            case 2:
                msg = "South America: Sao Paulo, Brazil";
                break;
            case 3:
                msg = "Europe: Copenhagen, Denmark";
                break;
            case 4:
                msg = "Africa: Cairo, Egypt";
                break;
            case 5:
                msg = "Asia: Jakarta, Indonesia";
                break;
            case 6:
                msg = "Australia: Sydney, Australia";
                break;
            case 7:
                msg = "Antarctica: McMurdo Station, United States";
                break;
            default:
                msg = "Undefined Continent!";
                break;

        }
        System.out.println(msg);
    }
}
