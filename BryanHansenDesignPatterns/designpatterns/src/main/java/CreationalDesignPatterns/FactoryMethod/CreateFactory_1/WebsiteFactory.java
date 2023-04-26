package CreationalDesignPatterns.FactoryMethod.CreateFactory_1;

public class WebsiteFactory {

    /*
        Factory instantiates the siteTypes (doesn't have to be noargs constructor)

     */
    public static Website getWebsite(String siteType) {

        switch(siteType) {
            case "blog" : {
                return new Blog();
            }
            case "shop" : {
                return new Shop();
            }

            default: {
                return null;
            }
        }
    }
}
