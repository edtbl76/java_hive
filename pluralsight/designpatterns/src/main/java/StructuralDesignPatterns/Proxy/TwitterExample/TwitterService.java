package StructuralDesignPatterns.Proxy.TwitterExample;

public interface TwitterService {
    String getTimeline(String screenName);
    void postToTimeline(String screenName, String message);
}
