package org.kamenchuk.dao.impl;

import org.kamenchuk.dao.UserDao;
import org.kamenchuk.dao.config.ConnectionPool;
import org.kamenchuk.dao.config.ConnectionProxy;
import org.kamenchuk.models.Role;
import org.kamenchuk.models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao<User> {

    //TODO написать в нормальной форме
    //TODO переделать запрос UPDATE с ?
    //TODO сделать сервисы
    //TODO сделать в main что-то типо интерфейса, чтобы проверить сервисы
    //TODO List<> везде с параметрами
    private final String ID = "users.id",
            LOGIN = "users.login",
            PASSWORD = "users.password",
            ROLE_ID_IN_USERS = "users.idRole",
            EXTRA_USERS_ID_IN_USERS = "users.idExtraUsersdata",
            EXTRA_USERS_ID = "extraUsersData.id",
            NAME = "extraUsersData.name",
            LASTNAME = "extraUsersData.lastname",
            DATE_OF_BIRTH = "extraUsersData.dateOfBirth",
            DRIVING_LICENSE = "extraUsersData.drivingLicense",
            PHONE = "extraUsersData.phone",
            REGISTER_DATE = "extraUsersData.registerDate",
            ROLE = "roles.role",
            ROLE_ID = "roles.id";
    private final String GET_BY_ID = "SELECT " + ID + ", " + LOGIN + ", " + PASSWORD + ", "
            + ROLE + ", " + EXTRA_USERS_ID + ", " + NAME + ", " + LASTNAME + ", "
            + DATE_OF_BIRTH + ", " + DRIVING_LICENSE + ", " + PHONE + ", " + REGISTER_DATE +
            "FROM users " +
            "LEFT JOIN extraUsersData ON " + EXTRA_USERS_ID_IN_USERS + " = " + EXTRA_USERS_ID +
            "JOIN roles ON " + ROLE_ID_IN_USERS + " = " + ROLE_ID +
            "WHERE " + ID + " = ?";
    private final String GET_ALL = "SELECT " + ID + ", " + LOGIN + ", " + PASSWORD + ", "
            + ROLE + ", " + EXTRA_USERS_ID + ", " + NAME + ", " + LASTNAME + ", "
            + DATE_OF_BIRTH + ", " + DRIVING_LICENSE + ", " + PHONE + ", " + REGISTER_DATE +
            "FROM users " +
            "LEFT JOIN extraUsersData ON "+EXTRA_USERS_ID_IN_USERS+" = " + EXTRA_USERS_ID +
            "JOIN roles ON "+ROLE_ID_IN_USERS+ " = "+ROLE_ID;
    private final String UPDATE = "UPDATE users SET login =  ? , password = ? WHERE users.id = ?";
    private final String DELETE = "DELETE FROM users WHERE users.id = ?";
    private final String INSERT = "INSERT INTO users (login,password,role) VALUES(?,?,?)";

    private ArrayList<User> users = new ArrayList<>();

    //TODO убрать optional
    //TODO обработать исключения
    @Override
    public User get(Long id) {
        try (ConnectionProxy connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(GET_BY_ID);
             ResultSet rs = ps.executeQuery(GET_BY_ID)) {
            ps.setLong(1, id);
            User user = buildUser(rs);
            return user;
            //TODO null check
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getAll() {
        try (ConnectionProxy connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(GET_ALL);
             ResultSet rs = ps.executeQuery(GET_ALL)) {
            while (rs.next()) {
                users.add(buildUser(rs));
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(User user, String[] params) {
        try (ConnectionProxy connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(UPDATE);
             ResultSet rs = ps.executeQuery(UPDATE)) {
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            ps.setLong(3, user.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(User user) {
        try (ConnectionProxy connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(DELETE);
             ResultSet rs = ps.executeQuery(DELETE)) {
            ps.setLong(1, user.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insert(User user) {
        try (ConnectionProxy connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(INSERT);
             ResultSet rs = ps.executeQuery(INSERT)) {
            ps.setString(1,user.getLogin());
            ps.setString(2,user.getPassword());
            ps.setInt(3,user.getRole().getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private User buildUser(ResultSet rs) {
        try{
            Role role = new Role();
            role.setId(rs.getInt("idRole"));
            role.setRole(rs.getString("role"));
            if(rs.equals(0)) throw new NullPointerException();
            return User.builder()
                    .id(rs.getLong("id"))
                    .login(rs.getString("login"))
                    .password(rs.getString("password"))
                    .role(role)
                    .idExtraUsersData(rs.getLong("idExtraUsersData"))
                    .name(rs.getString("name"))
                    .lastname(rs.getString("lastname"))
                    .dateOfBirth(rs.getDate("dateOfBirth"))
                    .drivingLicense(rs.getString("drivingLicense"))
                    .phone(rs.getString("phone"))
                    .registerDate(rs.getDate("registerDate"))
                    .build();
        } catch (SQLException e) {
            throw new NullPointerException();
        }
    }
}

