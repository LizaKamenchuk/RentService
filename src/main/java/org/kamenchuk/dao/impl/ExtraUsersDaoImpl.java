package org.kamenchuk.dao.impl;

import org.kamenchuk.dao.ExtraUsersDao;
import org.kamenchuk.dao.config.ConnectionPool;
import org.kamenchuk.dao.config.ConnectionProxy;
import org.kamenchuk.models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExtraUsersDaoImpl implements ExtraUsersDao<User> {
    User user;
    private final String extraUsersDataID = "id",
            NAME = "name",
            LASTNAME = "lastname",
            DATEOFBIRTH = "dateOfBirth",
            DRIVINGLICENSE = "drivingLicense",
            PHONE = "phone",
            REGISTERDATE = "registerDate";
    private final String UPDATE = "UPDATE extraUsersData SET " +
            NAME + " = " + "'" + user.getName() + "'" +
            LASTNAME + " = " + "'" + user.getLastname() + "'" +
            DATEOFBIRTH + " = " + "'" + user.getDateOfBirth() + "'" +
            DRIVINGLICENSE + " = " + "'" + user.getDrivingLicense() + "'" +
            PHONE + " = " + "'" + user.getPhone() + "'" +
            "WHERE " + extraUsersDataID + " = " + "'" + user.getIdExtraUsersData() + "'";
    private final String DELETE = "DELETE FROM extraUsersData WHERE " + extraUsersDataID + " = " + user.getIdExtraUsersData();

    @Override
    public void update(User entity, String[] params) throws SQLException {
        ConnectionProxy connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement ps = connection.prepareStatement(UPDATE);
        ResultSet rs = ps.executeQuery(UPDATE);
    }

    @Override
    public void delete(User entity) throws SQLException {
        ConnectionProxy connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement ps = connection.prepareStatement(DELETE);
        ResultSet rs = ps.executeQuery(DELETE);
    }
}
