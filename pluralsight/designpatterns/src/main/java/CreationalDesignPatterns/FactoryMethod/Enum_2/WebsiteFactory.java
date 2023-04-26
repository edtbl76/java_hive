package CreationalDesignPatterns.FactoryMethod.Enum_2;

public class WebsiteFactory {

    /*
        Factory instantiates the siteTypes (doesn't have to be noargs constructor)

     */
    public static Website getWebsite(WebsiteType websiteType) {

        switch(websiteType) {
            case BLOG : {
                return new Blog();
            }
            case SHOP : {
                return new Shop();
            }

            default: {
                return null;
            }
        }
    }
}
