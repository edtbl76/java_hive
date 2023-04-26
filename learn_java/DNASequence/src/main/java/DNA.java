import java.util.List;

public class DNA {
    public static void main(String[] args) {

        String dnaStrand1 = "ATGCGATACGCTTGA";
        String dnaStrand2 = "ATGCGATACGTGA";
        String dnaStrand3 = "ATTAATATGTACTGA";

        String startCodon = "ATG";
        String stopCodon = "TGA";

        List<String> strands = List.of(dnaStrand1, dnaStrand2, dnaStrand3);

        strands.forEach(s -> {
            System.out.println("--- --- ---");
            // String Length
            System.out.println("Length: " + s.length());

            // Finding "start and stop codon" in string
            int startCodonIndex = s.indexOf(startCodon);
            int stopCodonIndex = s.indexOf(stopCodon);

            // Print them
            System.out.println("Start Codon Index: " + startCodonIndex);
            System.out.println("Stop Codon Index: " + stopCodonIndex);

            /*
                Check for Protein
                1.)  StartCodon is present
                2.)  StopCodon is present
                3.) The # of nucleotides between the codons should be a multiple of 3
             */
            if (startCodonIndex != -1 && stopCodonIndex != -1 && (stopCodonIndex - startCodonIndex) % 3 == 0) {
                // Codon is 3 nucleotides long, so we can find the protein
                // by creating a substring that starts w/ the startcodonindex, and finishes w/ stop + 3 (to include
                // the entire codon)
                String protein = s.substring(startCodonIndex, stopCodonIndex + 3);
                System.out.println("Protein: " + protein);
            } else {
                System.out.println("No Protein. So Sad.");
            }
        });


    }
}
