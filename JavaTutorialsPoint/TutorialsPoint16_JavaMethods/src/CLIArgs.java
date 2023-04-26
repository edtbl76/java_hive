public class CLIArgs {

    // execute this from a shell like so:
    // java CLIArgs this is a command line 200 - 100
    public static void main(String[] args) {
        for(int i = 0; i< args.length; i++) {
            System.out.println("args[" + i + "]: " + args[i]);
        }
    }
}
