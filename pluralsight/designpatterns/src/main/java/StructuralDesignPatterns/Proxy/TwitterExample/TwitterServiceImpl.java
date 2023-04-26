package StructuralDesignPatterns.Proxy.TwitterExample;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.util.List;

public class TwitterServiceImpl implements TwitterService {

    private static final String TWITTER_CONSUMER_KEY = "vlXRUGPnq1HNQJ2TJlymAaNFN";
    private static final String TWITTER_SECRET_KEY = "DOVeFcdQb3W3rT5DRmWhHC3YpjSny5HiQtCyi7mt5NDFfhtazS";
    private static final String TWITTER_ACCESS_TOKEN = "184933426-1d9ntbYCnjZbq0ywE3aHP6hGQquriUlBN9aoZnKD";
    private static final String TWITTER_ACCESS_TOKEN_SECRET = "VEr5Z35MlMQuidoPnLn8GoEPTB2QqrW8eHHvYPeBpj5VG";


    @Override
    public String getTimeline(String screenName) {

        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
        configurationBuilder
                .setDebugEnabled(true)
                .setOAuthConsumerKey(TWITTER_CONSUMER_KEY)
                .setOAuthConsumerSecret(TWITTER_SECRET_KEY)
                .setOAuthAccessToken(TWITTER_ACCESS_TOKEN)
                .setOAuthAccessTokenSecret(TWITTER_ACCESS_TOKEN_SECRET);

        TwitterFactory twitterFactory = new TwitterFactory(configurationBuilder.build());
        Twitter twitter = twitterFactory.getInstance();

        StringBuilder stringBuilder = new StringBuilder();
        try {
            Query query = new Query(screenName);
            QueryResult result;
            do {
                result = twitter.search(query);
                List<Status> statuses = twitter.getHomeTimeline();
                statuses.forEach(status -> stringBuilder
                        .append("@")
                        .append(status.getUser().getScreenName())
                        .append(" - ")
                        .append(status.getText())
                        .append("\n"));
            } while ((query = result.nextQuery()) !=null);
        } catch (TwitterException e) {
            e.printStackTrace();
            System.out.println("Failed to get tweets: " + e.getMessage());
        }
        return stringBuilder.toString();
    }

    @Override
    public void postToTimeline(String screenName, String message) {
        // Not Gonna Take It
    }
}
