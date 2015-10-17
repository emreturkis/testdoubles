package co.elron.userservice;

import org.joda.time.DateTime;

import co.elron.userservice.TimeSource;

public class StubTimeSource implements TimeSource {

	@Override
	public DateTime currentTime() {
		return new DateTime(2011, 12, 25, 12, 0, 0, 0);
	}

}
