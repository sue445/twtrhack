package net.sue445;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class GreetingBotTest{

	private GreetingBot greetingBot = new GreetingBot();


	@Test
	public void okaeri() throws Exception {
		String actual = greetingBot.okaeri();
		assertThat(actual, is("おかえり RT @sue445: 借り暮らしのただいまってぃ"));
	}

}
