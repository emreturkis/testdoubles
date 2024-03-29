package co.elron.userservice;

import org.joda.time.DateTime;

public class User {

	private final String username;
	private final DateTime creationTime;

	public User(String username, DateTime creationTime) {
		this.username = username;
		this.creationTime = creationTime;
	}

	public String getUsername() {
		return username;
	}

	public DateTime getCreationTime() {
		return creationTime;
	}
}
