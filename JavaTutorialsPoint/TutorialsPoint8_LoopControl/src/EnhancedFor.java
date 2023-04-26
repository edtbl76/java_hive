public class EnhancedFor {

    public static void main(String[] args) {
        int[] nums = { 1, 2, 3, 4, 5, };

        for( int x : nums ) {
            System.out.print( x );
            System.out.print( "," );
        }
        System.out.print( "\n" );

        String[] names = { "Larry", "Moe", "Curly" };

        for( String name : names) {
            System.out.print( name );
            System.out.print( "," );
        }
        System.out.print( "\n" );
    }
}
