package net.sue445;

import java.util.List;

import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class GreetingBot {



	/**
	 * フォローしてる人が「ただいま」とツイートしたら「おかえり」と言う
	 * @return
	 * @throws TwitterException
	 */
	public String okaeri() throws TwitterException{
		Twitter twitter = createTwitter();
		Paging paging = new Paging(1, 100);
		List<Status> timelineList = twitter.getHomeTimeline(paging);

		for(Status status : timelineList){
			String tweet = status.getText();
			if(tweet.contains("ただいま")){
				String screenName = status.getUser().getScreenName();
				return "おかえり RT @" + screenName + ": " + tweet;
			}
		}
		return "";
	}

	private Twitter createTwitter() {
		ConfigurationBuilder builder = new ConfigurationBuilder();
		builder.setOAuthConsumerKey(TwitterOAuthConst.O_AUTH_CONSUMER_KEY);
		builder.setOAuthConsumerSecret(TwitterOAuthConst.O_AUTH_CONSUMER_SECRET);
		builder.setOAuthAccessToken(TwitterOAuthConst.O_AUTH_ACCESS_TOKEN);
		builder.setOAuthAccessTokenSecret(TwitterOAuthConst.O_AUTH_ACCESS_TOKEN_SECRET);

		TwitterFactory twitterFactory = new TwitterFactory(builder.build());
		Twitter twitter = twitterFactory.getInstance();
		return twitter;
	}

}
