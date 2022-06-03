package eu.glowacki.utp.assignment10.repositories.test;

import eu.glowacki.utp.assignment10.repositories.UserRepository;
import org.junit.Assert;
import org.junit.Test;

import eu.glowacki.utp.assignment10.UnimplementedException;
import eu.glowacki.utp.assignment10.dtos.UserDTO;
import eu.glowacki.utp.assignment10.repositories.IUserRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public final class UserRepositoryTest extends RepositoryTestBase<UserDTO, IUserRepository> {

	@Test
	public void add() {
		UserDTO user = new UserDTO(1, "dima", "dima20");
		_repository.add(user);
		assertTrue(_repository.exists(user));
	}

	@Test
	public void update() {
		UserDTO user = new UserDTO(1, "dima", "dima20");
		_repository.add(user);
		user.setLogin("dmitriy");
		user.setPassword("dmitriy20");
		_repository.update(user);

		String sql = "select * from utp10.users;";
		PreparedStatement select = null;
		ResultSet result = null;
		try {
			select = _repository.getConnection()
					.prepareStatement(sql);
			result = select.executeQuery();

			result.next();
			assertEquals(user.getLogin(), result.getString("user_login"));
			assertEquals(user.getPassword(), result.getString("user_password"));

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void exists() {
		UserDTO user1 = new UserDTO(1, "dima", "dima20");
		UserDTO user2 = new UserDTO(2, "travis", "travisscott");
		_repository.add(user1);

		assertTrue(_repository.exists(user1));
		assertFalse(_repository.exists(user2));
	}

	@Test
	public void addOrUpdate() {
		UserDTO user1 = new UserDTO(1, "dima", "dima20");
		UserDTO user2 = new UserDTO(2, "travis", "travisscott");

		_repository.add(user1);
		user1.setLogin("dmitriy");
		user1.setPassword("dmitriy20");

		_repository.addOrUpdate(user1);
		_repository.addOrUpdate(user2);

		assertTrue(_repository.exists(user1));
		assertTrue(_repository.exists(user2));
	}

	@Test
	public void delete() {
		UserDTO user = new UserDTO(1, "dima", "dima20");
		_repository.add(user);
		assertTrue(_repository.exists(user));

		_repository.delete(user);
		assertFalse(_repository.exists(user));
	}

	@Test
	public void findById() {
		UserDTO user = new UserDTO(1, "dima", "dima20");
		_repository.add(user);
		assertEquals(user, _repository.findById(user.getId()));
		assertNull(_repository.findById(2));
	}

	@Test
	public void findByName() {
		UserDTO user1 = new UserDTO(1, "dima", "dima20");
		UserDTO user2 = new UserDTO(2, "diana", "dianadiana123");
		_repository.add(user1);
		_repository.add(user2);

		List<UserDTO> usersFound = _repository.findByName("di");
		assertTrue(usersFound.contains(user1));
		assertTrue(usersFound.contains(user2));
	}

	@Override
	protected IUserRepository Create() {
		return new UserRepository("jdbc:postgresql://localhost:5432/postgres",
				"postgres", "postgres");
	}
}