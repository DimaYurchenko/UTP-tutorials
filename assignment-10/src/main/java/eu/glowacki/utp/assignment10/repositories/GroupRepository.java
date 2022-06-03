package eu.glowacki.utp.assignment10.repositories;

import eu.glowacki.utp.assignment10.dtos.GroupDTO;
import eu.glowacki.utp.assignment10.dtos.UserDTO;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class GroupRepository implements IGroupRepository{

    private Connection connection;
    private final String url;
    private final String user;
    private final String password;

    public GroupRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public List<GroupDTO> findByName(String name) {
        String sql = "select * from utp10.groups " +
                "where groups.group_name like '%" + name + "%';";
        PreparedStatement selectStatement = null;
        ResultSet result = null;
        List<GroupDTO> groups = null;


        try {
            selectStatement = connection
                    .prepareStatement(sql);

            result = selectStatement.executeQuery();

            if (!result.next()) {
                return null;
            }

            groups = new LinkedList<>();

            do {
                groups.add(
                        new GroupDTO(result.getInt("group_id"),
                                result.getString("group_name"),
                                result.getString("group_description"))
                );

            } while(result.next());


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return groups;
    }

    @Override
    public Connection getConnection() {
        return this.connection;
    }

    @Override
    public void add(GroupDTO dto) {
        String sql = "insert into utp10.groups(group_id, group_name, group_description) " +
                "values(?,?,?);";

        PreparedStatement addStatement = null;
        try {
            addStatement = connection
                    .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            addStatement.setInt(1, dto.getId());
            addStatement.setString(2, dto.getName());
            addStatement.setString(3, dto.getDescription());

            addStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(GroupDTO dto) {
        String sql = "UPDATE utp10.groups SET group_id = ?, group_name = ?, group_description = ?" +
                " WHERE group_id = ?;";

        PreparedStatement updateStatement = null;
        try {
            updateStatement = connection
                    .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            updateStatement.setInt(1, dto.getId());
            updateStatement.setString(2, dto.getName());
            updateStatement.setString(3, dto.getDescription());
            updateStatement.setInt(4, dto.getId());

            updateStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void addOrUpdate(GroupDTO dto) {
        if (exists(dto)) {
            update(dto);
        } else {
            add(dto);
        }

    }

    @Override
    public void delete(GroupDTO dto) {
        String sql = "delete from utp10.groups where group_id = ?";

        PreparedStatement deleteStatement = null;
        try {
            deleteStatement = connection
                    .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            deleteStatement.setInt(1, dto.getId());

            deleteStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public GroupDTO findById(int id) {
        String sql = "select * from utp10.groups" +
                " where group_id = ?";

        PreparedStatement selectStatement = null;
        ResultSet result = null;
        GroupDTO group = null;
        try {
            selectStatement = connection
                    .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            selectStatement.setInt(1, id);

            result = selectStatement.executeQuery();

            if (!result.next()) {
                return null;
            }

            group =  new GroupDTO(result.getInt("group_id"),
                    result.getString("group_name"),
                    result.getString("group_description"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return group;
    }

    @Override
    public void beginTransaction() {
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(url, user, password);
            this.connection.setAutoCommit(false);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void commitTransaction() {
        try {
            connection.commit();
        } catch (SQLException e) {
            rollbackTransaction();
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void rollbackTransaction() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int getCount() { // what is it supposed to do??
        return 0;
    }

    @Override
    public boolean exists(GroupDTO dto) {
        String sql = "select exists(select 1 from utp10.groups " +
                "where group_id = ?);";
        PreparedStatement existsStatement = null;
        ResultSet result = null;

        try {
            existsStatement = connection
                    .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            existsStatement.setInt(1, dto.getId());

            result = existsStatement.executeQuery();
            result.next();

            return result.getBoolean(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
