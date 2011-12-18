package net.sue445;

import java.util.List;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class GreetingBot {

	private Twitter twitter;


	/**
	 * フォローしてる人が「ただいま」とツイートしたら「おかえり」と言う
	 * @return
	 * @throws TwitterException
	 */
	public String okaeri() throws TwitterException{
		List<Status> timelineList = twitter.getHomeTimeline();

		for(Status status : timelineList){
			String text = status.getText();
			if(text.contains("ただいま")){
				String screenName = status.getUser().getScreenName();
				String tweet = "おかえり RT @" + screenName + ": " + text;
				twitter.updateStatus(tweet);
				return tweet;
			}
		}
		return "";
	}

	/**
	 *
	 * @return
	 */
	public static Twitter createTwitter() {
		ConfigurationBuilder builder = new ConfigurationBuilder();
		builder.setOAuthConsumerKey(TwitterOAuthConst.O_AUTH_CONSUMER_KEY);
		builder.setOAuthConsumerSecret(TwitterOAuthConst.O_AUTH_CONSUMER_SECRET);
		builder.setOAuthAccessToken(TwitterOAuthConst.O_AUTH_ACCESS_TOKEN);
		builder.setOAuthAccessTokenSecret(TwitterOAuthConst.O_AUTH_ACCESS_TOKEN_SECRET);

		TwitterFactory twitterFactory = new TwitterFactory(builder.build());
		Twitter twitter = twitterFactory.getInstance();
		return twitter;
	}

	/**
	 * @param twitter セットする twitter
	 */
	public void setTwitter(Twitter twitter) {
		this.twitter = twitter;
	}

}
