package net.sue445;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

public class GreetingBotTest{

	private GreetingBot greetingBot = new GreetingBot();


	@Test
	public void okaeriNotFound() throws Exception {
		greetingBot.setTwitter(GreetingBot.createTwitter());
		String actual = greetingBot.okaeri();
		assertThat(actual, is(""));
	}

	@Ignore
	@Test
	public void okaeriFound() throws Exception {
		String actual = greetingBot.okaeri();
		assertThat(actual, is("おかえり RT @sue445: 借り暮らしのただいまってぃ"));
	}

}
