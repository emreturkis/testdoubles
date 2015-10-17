package co.elron.userservice;

import java.util.ArrayList;
import java.util.List;

import co.elron.userservice.User;
import co.elron.userservice.UserStore;

public class FakeUserStore implements UserStore {

	private final ArrayList<User> users = new ArrayList<User>();

	@Override
	public void store(User user) {
		users.add(user);
	}

	@Override
	public List<User> findAll() {
		return users;
	}

}
