package eu.glowacki.utp.assignment10.repositories;

import eu.glowacki.utp.assignment10.dtos.UserDTO;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserRepository implements IUserRepository{

    private Connection connection;
    private final String url;
    private final String user;
    private final String password;

    public UserRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public Connection getConnection() {
        return this.connection;
    }

    @Override
    public void add(UserDTO dto) {
        String sql = "insert into utp10.users(user_id, user_login, user_password) " +
                "values(?,?,?);";

        PreparedStatement addStatement = null;
        try {
            addStatement = connection
                    .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            addStatement.setInt(1, dto.getId());
            addStatement.setString(2, dto.getLogin());
            addStatement.setString(3, dto.getPassword());

            addStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(UserDTO dto) {
        String sql = "UPDATE utp10.users SET user_id = ?, user_login = ?, user_password = ?" +
                " WHERE user_id = ?;";

        PreparedStatement updateStatement = null;
        try {
            updateStatement = connection
                    .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            updateStatement.setInt(1, dto.getId());
            updateStatement.setString(2, dto.getLogin());
            updateStatement.setString(3, dto.getPassword());
            updateStatement.setInt(4, dto.getId());

            updateStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addOrUpdate(UserDTO dto) {
        if (exists(dto)) {
            update(dto);
        } else {
            add(dto);
        }
    }

    @Override
    public void delete(UserDTO dto) {
        String sql = "delete from utp10.users where user_id = ?";

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
    public UserDTO findById(int id) {

        String sql = "select * from utp10.users" +
                " where user_id = ?";

        PreparedStatement selectStatement = null;
        ResultSet result = null;
        UserDTO user = null;
        try {
            selectStatement = connection
                    .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            selectStatement.setInt(1, id);

            result = selectStatement.executeQuery();

            if (!result.next()) {
                return null;
            }

            user =  new UserDTO(result.getInt("user_id"),
                        result.getString("user_login"),
                        result.getString("user_password"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
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
    public int getCount() {
        return 0;
    } // what is it supposed to do?

    @Override
    public boolean exists(UserDTO dto) {
        String sql = "select exists(select 1 from utp10.users " +
                "where user_id = ?);";
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

    @Override
    public List<UserDTO> findByName(String username) {
        String sql = "select * from utp10.users " +
                "where user_login like '%" + username + "%';";
        PreparedStatement selectStatement = null;
        ResultSet result = null;
        List<UserDTO> users = null;


        try {
            selectStatement = connection
                    .prepareStatement(sql);

            result = selectStatement.executeQuery();

            if (!result.next()) {
                return null;
            }

            users = new LinkedList<>();

            do {
                users.add(
                        new UserDTO(result.getInt("user_id"),
                                result.getString("user_login"),
                                result.getString("user_password"))
                );

            } while(result.next());


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }
}
