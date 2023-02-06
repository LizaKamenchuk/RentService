package org.kamenchuk.dao.impl;

import org.kamenchuk.dao.UserDao;
import org.kamenchuk.dao.config.ConnectionPool;
import org.kamenchuk.dao.config.ConnectionProxy;
import org.kamenchuk.dto.CreateUserDto;
import org.kamenchuk.dto.UserDto;
import org.kamenchuk.mapper.UserMapper;
import org.kamenchuk.models.Role;
import org.kamenchuk.models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao<UserDto> {

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
            + DATE_OF_BIRTH + ", " + DRIVING_LICENSE + ", " + PHONE + ", " + REGISTER_DATE + ", idRole" +
            "FROM users " +
            "LEFT JOIN extraUsersData ON " + EXTRA_USERS_ID_IN_USERS + " = " + EXTRA_USERS_ID +
            "JOIN roles ON " + ROLE_ID_IN_USERS + " = " + ROLE_ID +
            "WHERE " + ID + " = ?";
    private final String GET_ALL = "SELECT " + ID + ", " + LOGIN + ", " + PASSWORD + ", "
            + ROLE + ", " + EXTRA_USERS_ID + ", " + NAME + ", " + LASTNAME + ", "
            + DATE_OF_BIRTH + ", " + DRIVING_LICENSE + ", " + PHONE + ", " + REGISTER_DATE + ", idRole" +
            " FROM users " +
            "LEFT JOIN extraUsersData ON " + EXTRA_USERS_ID_IN_USERS + " = " + EXTRA_USERS_ID +
            " JOIN roles ON " + ROLE_ID_IN_USERS + " = " + ROLE_ID;
    private final String UPDATE = "UPDATE users SET login =  ? , password = ? WHERE users.id = ?";
    private final String DELETE = "DELETE FROM users WHERE users.id = ?";
    private final String INSERT = "INSERT INTO users (login,password,idRole) VALUES(?,?,?)";

    //TODO переделать интерфейсы
    //TODO обработать исключения

    @Override
    public void insert(UserDto entity) throws SQLException {
        System.out.println("ggggg");
    }

    @Override
    public void update(UserDto user) throws SQLException {
    }

    @Override
    public UserDto get(Long id) {
        UserMapper mapper = new UserMapper();
        try (ConnectionProxy connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(GET_BY_ID);
             ResultSet rs = ps.executeQuery()) {
            ps.setLong(1, id);
            UserDto user = mapper.toUserDto(buildUser(rs));
            return user;
            //TODO null check
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<UserDto> getAll() {
        UserMapper mapper = new UserMapper();
        List<UserDto> users = new ArrayList<>();
        try (ConnectionProxy connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(GET_ALL);
        ) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                users.add(mapper.toUserDto(buildUser(rs)));
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void update(CreateUserDto user) {
        try (ConnectionProxy connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(UPDATE);
             ResultSet rs = ps.executeQuery()) {
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            ps.setLong(3, user.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(UserDto user) {
        try (ConnectionProxy connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(DELETE);
             ResultSet rs = ps.executeQuery()) {
            ps.setLong(1, user.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void insert(CreateUserDto user) {
        try (ConnectionProxy connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(INSERT)) {
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            ps.setInt(3, user.getRole().getId());
            int rs = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private User buildUser(ResultSet rs) throws SQLException {
//        if (rs == null){
//            throw new NullPointerException();
//        }
        Role role = new Role();
        role.setId(rs.getInt("idRole"));
        role.setRole(rs.getString("role"));
        return User.builder()
                .id(rs.getLong("id"))
                .login(rs.getString("login"))
                .password(rs.getString("password"))
                .role(role)
//                .idExtraUsersData(rs.getLong("idExtraUsersData"))
                .name(rs.getString("name"))
                .lastname(rs.getString("lastname"))
                .dateOfBirth(rs.getDate("dateOfBirth"))
                .drivingLicense(rs.getString("drivingLicense"))
                .phone(rs.getString("phone"))
                .registerDate(rs.getDate("registerDate"))
                .build();

    }
}

