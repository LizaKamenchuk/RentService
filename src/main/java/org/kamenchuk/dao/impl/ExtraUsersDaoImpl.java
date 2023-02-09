package org.kamenchuk.dao.impl;

import org.kamenchuk.dao.ExtraUsersDao;
import org.kamenchuk.dto.ExtraUsersDataDto;
import org.kamenchuk.models.ExtraUsersData;

public class ExtraUsersDaoImpl implements ExtraUsersDao{
    private final String extraUsersDataID = "id",
            NAME = "name",
            LASTNAME = "lastname",
            DATEOFBIRTH = "dateOfBirth",
            DRIVINGLICENSE = "drivingLicense",
            PHONE = "phone",
            REGISTERDATE = "registerDate";
    private final String UPDATE = "UPDATE extraUsersData SET "+
            NAME + " = ?" +", "+
            LASTNAME + " = ?" + ", " +
            DATEOFBIRTH + " = ?" + ", " +
            DRIVINGLICENSE + " = ?" + ", " +
            PHONE + " = ?" + ", "+
            "WHERE " + extraUsersDataID + " = ?";
    private final String DELETE = "DELETE FROM extraUsersData WHERE " + extraUsersDataID + " = ?" ;
    private final String INSERT = "INSERT INTO extraUsersData ("+
            NAME + " = ?" +", "+
            LASTNAME + " = ?" + ", " +
            DATEOFBIRTH + " = ?" + ", " +
            DRIVINGLICENSE + " = ?" + ", " +
            PHONE + " = ?" + ", "+
            REGISTERDATE + " = ?" +
            "VALUE (?,?,?,?,?,?)";


    @Override
    public void save(ExtraUsersData extraUsersData){

    }

    @Override
    public void update(ExtraUsersDataDto entity) {

    }

    @Override
    public void delete(ExtraUsersDataDto entity) {

    }

}
