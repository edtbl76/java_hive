package StructuralDesignPatterns.Proxy.TwitterExample;

public class TwitterRealDemo {

    public static void main(String[] args) {
        TwitterService service = (TwitterService)SecurityProxy.newInstance(new TwitterServiceImpl());

        System.out.println(service.getTimeline("edmangini76"));
    }
}
