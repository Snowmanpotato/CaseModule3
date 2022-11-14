package com.module3.photosocial.dao;

import com.module3.photosocial.model.Access;
import com.module3.photosocial.model.FavoritesCheck;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AccessDAO extends DatabaseContext implements IAccessDAO {

    private static final String INSERT_ACCESS_DAO = "INSERT INTO access (access) VALUES (?);";
    private static final String DELETE_ACCESS_DAO = "DELETE FROM access WHERE (id = ?);";

    private Access getAccessFromResultSet(ResultSet rs) throws SQLException {
        int admin = rs.getInt("id");
        String value = rs.getString("access");
        Access access = new Access(admin, value);
        return access;
    }

    @Override
    public void insertAccess(Access access) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement(INSERT_ACCESS_DAO);
            preparedStatement.setString(1, access.getValue());
            connection.close();
        }catch (SQLException e){
            printSQLException(e);
        }
    }

    @Override
    public Access selectAccess(int id) {
        return null;
    }

    @Override
    public List<Access> listAccess() {
        return null;
    }

    @Override
    public boolean updateAccess(Access access) {
        return false;
    }

    @Override
    public boolean deleteAccessById(int id) throws SQLException {
        boolean check = false;
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ACCESS_DAO);
        try {
            preparedStatement.setInt(1, id);
            check = preparedStatement.executeUpdate() > 0;
            connection.close();

        } catch (
                SQLException e) {
            printSQLException(e);
        }
        return check;
    }
}
