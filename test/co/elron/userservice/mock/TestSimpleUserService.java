package co.elron.userservice.mock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import co.elron.userservice.AuditLog;
import co.elron.userservice.SimpleUserService;
import co.elron.userservice.TimeSource;
import co.elron.userservice.User;
import co.elron.userservice.UserStore;

@RunWith(MockitoJUnitRunner.class)
public class TestSimpleUserService {

	@Mock
	AuditLog audit;
	@Mock
	UserStore store;
	@Mock
	TimeSource source;
	@InjectMocks
	SimpleUserService service;

	@Test
	public void testNewUserServiceReturnsNull() {
		User user = service.find("Ahmet");
		assertNull(user);
	}

	@Test
	public void testRegisteredUserIsLogged() {
		String name = "Ayse";
		service.register(name);
		verify(audit).log(eq("user"), eq("register"), eq(name));
	}

	@Test
	public void testDuplicateRegisteredUserIsLogged() {
		String name = "Ayse";
		doReturn(Arrays.asList(new User(name, null))).when(store).findAll();
		service.register(name);
		verify(audit).log(eq("user"), eq("duplicateregister"), eq(name));
	}

	@Test
	public void testAddedUserIsFound() {
		String name = "Ahmet";
		DateTime time = new DateTime(2015, 10, 17, 0, 0, 0);
		//
		when(source.currentTime()).thenReturn(time);
		//
		service.register(name);
		doReturn(Arrays.asList(new User(name, time))).when(store).findAll();
		User user = service.find(name);
		//
		assertEquals(1, service.users().size());
		assertEquals("Names are not equal !", name, user.getUsername());
		assertEquals("Dates are not equal !", time, user.getCreationTime());
	}

}
