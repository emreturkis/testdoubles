package co.elron.userservice;

import org.joda.time.DateTime;

public interface TimeSource {
	DateTime currentTime();
}
