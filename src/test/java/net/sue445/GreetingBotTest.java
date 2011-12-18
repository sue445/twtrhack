package net.sue445;

import static org.easymock.EasyMock.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import twitter4j.Twitter;

@RunWith(Enclosed.class)
public class GreetingBotTest{


	public static class WhenNotFound{
		private GreetingBot greetingBot = new GreetingBot();

		@Before
		public void setUp(){
			greetingBot.setTwitter(GreetingBot.createTwitter());
		}

		@Test
		public void okaeri() throws Exception {
			String actual = greetingBot.okaeri();
			assertThat(actual, is(""));
		}
	}

	public static class WhenFound{
		private GreetingBot greetingBot = new GreetingBot();

		@Before
		public void setUp(){
			Twitter twitter = createMock(Twitter.class);
			greetingBot.setTwitter(twitter);
		}

		@Test
		public void okaeri() throws Exception {
			String actual = greetingBot.okaeri();
			assertThat(actual, is("おかえり RT @sue445: 借り暮らしのただいまってぃ"));
		}
	}
}
