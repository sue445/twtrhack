package net.sue445;

import static org.easymock.EasyMock.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.Iterator;

import org.easymock.IAnswer;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.User;

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

		private boolean isUpdateStatusCalled = false;

		@Before
		public void setUp() throws Exception{
			User mockUser = createMock(User.class);
			expect(mockUser.getScreenName()).andReturn("sue445");

			final Status mockStatus = createMock(Status.class);
			expect(mockStatus.getText()).andReturn("借り暮らしのただいまってぃ");
			expect(mockStatus.getUser()).andReturn(mockUser);

			@SuppressWarnings("unchecked")
			ResponseList<Status> mockTimelineList = createMock(ResponseList.class);

			Iterator<Status> it = new Iterator<Status>() {
				private int count = 0;

				@Override
				public void remove() {
					throw new UnsupportedOperationException();
				}

				@Override
				public Status next() {
					return mockStatus;
				}

				@Override
				public boolean hasNext() {
					if(count == 0){
						return true;
					}
					return false;
				}
			};
			expect(mockTimelineList.iterator()).andReturn(it);

			Twitter mockTwitter = createMock(Twitter.class);
			expect(mockTwitter.getHomeTimeline()).andReturn(mockTimelineList);

			IAnswer<? extends Status> answer = new IAnswer<Status>() {
				@Override
				public Status answer() throws Throwable {
					isUpdateStatusCalled = true;
					return null;
				}
			};
			expect(mockTwitter.updateStatus("おかえり RT @sue445: 借り暮らしのただいまってぃ")).andAnswer(answer);

			greetingBot.setTwitter(mockTwitter);

			replay(mockTwitter, mockTimelineList, mockStatus, mockUser);
		}

		@Test
		public void okaeri() throws Exception {
			String actual = greetingBot.okaeri();
			assertThat(actual, is("おかえり RT @sue445: 借り暮らしのただいまってぃ"));
			assertThat(isUpdateStatusCalled, is(true));
		}
	}
}
