package StructuralDesignPatterns.Composite.CompositeException_2;

public class CompositeMenuDemo {

    public static void main(String[] args) {

        Menu mainMenu = new Menu("Main", "/main");
        Menu claimsSubMenu = new Menu("Claims", "/claims");

        MenuItem safetyMenuItem = new MenuItem("Safety", "/safety");
        MenuItem personalClaimsMenu = new MenuItem("Personal Claim", "/personalClaims");

        mainMenu.add(safetyMenuItem);
        mainMenu.add(claimsSubMenu);
        claimsSubMenu.add(personalClaimsMenu);

        System.out.println(mainMenu.toString());

    }
}
