package eu.glowacki.utp.assignment10.repositories.test;

import eu.glowacki.utp.assignment10.dtos.UserDTO;
import eu.glowacki.utp.assignment10.repositories.GroupRepository;
import eu.glowacki.utp.assignment10.repositories.UserRepository;
import org.junit.Assert;
import org.junit.Test;

import eu.glowacki.utp.assignment10.UnimplementedException;
import eu.glowacki.utp.assignment10.dtos.GroupDTO;
import eu.glowacki.utp.assignment10.repositories.IGroupRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class GroupRepositoryTest extends RepositoryTestBase<GroupDTO, IGroupRepository> {

	@Test
	public void add() {
		GroupDTO group = new GroupDTO(1, "group1", "this is group 1");
		_repository.add(group);
		assertTrue(_repository.exists(group));

	}

	@Test
	public void	exists() {
		GroupDTO group1 = new GroupDTO(1, "group1", "this is group 1");
		GroupDTO group2 = new GroupDTO(2, "group2", "this is group 2");
		_repository.add(group1);

		assertTrue(_repository.exists(group1));
		assertFalse(_repository.exists(group2));
	}

	@Test
	public void update() {
		GroupDTO group = new GroupDTO(1, "group1", "this is group1");
		_repository.add(group);
		group.setName("first group");
		group.setDescription("this is first group");
		_repository.update(group);

		String sql = "select * from utp10.groups;";
		PreparedStatement select = null;
		ResultSet result = null;
		try {
			select = _repository.getConnection()
					.prepareStatement(sql);
			result = select.executeQuery();

			result.next();
			assertEquals(group.getName(), result.getString("group_name"));
			assertEquals(group.getDescription(), result.getString("group_description"));

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void addOrUpdate() {
		GroupDTO group1 = new GroupDTO(1, "group1", "this is group1");
		GroupDTO group2 = new GroupDTO(2, "group2", "this is group2");

		_repository.add(group1);
		group1.setName("first group");
		group1.setDescription("this is first group");

		_repository.addOrUpdate(group1);
		_repository.addOrUpdate(group2);

		assertTrue(_repository.exists(group1));
		assertTrue(_repository.exists(group2));
	}

	@Test
	public void delete() {
		GroupDTO group = new GroupDTO(1, "group1", "this is group1");
		_repository.add(group);
		assertTrue(_repository.exists(group));
		_repository.delete(group);
		assertFalse(_repository.exists(group));
	}

	@Test
	public void findById() {
		GroupDTO group = new GroupDTO(1, "group1", "this is group1");
		_repository.add(group);
		assertEquals(group, _repository.findById(group.getId()));
		assertNull(_repository.findById(2));
	}
	
	@Test
	public void findByName() {
		GroupDTO group1 = new GroupDTO(1, "group1", "this is group1");
		GroupDTO group2 = new GroupDTO(2, "group2", "this is group2");

		_repository.add(group1);
		_repository.add(group2);

		List<GroupDTO> groupsFound = _repository.findByName("group");
		assertTrue(groupsFound.contains(group1));
		assertTrue(groupsFound.contains(group2));
	}

	@Override
	protected IGroupRepository Create() {
		return new GroupRepository("jdbc:postgresql://localhost:5432/postgres",
				"postgres", "postgres");
	}
}