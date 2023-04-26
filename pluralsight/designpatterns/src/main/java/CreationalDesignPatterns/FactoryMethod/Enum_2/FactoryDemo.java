package CreationalDesignPatterns.FactoryMethod.Enum_2;

public class FactoryDemo {

    public static void main(String[] args) {

        /*
            Replace String literals w/ ENUMS
            - stronger, less brittle style of coding.
         */
        Website site = WebsiteFactory.getWebsite(WebsiteType.BLOG);
        System.out.println(site.getPages());
        site = WebsiteFactory.getWebsite(WebsiteType.SHOP);
        System.out.println(site.getPages());
    }
}
