package com.module3.photosocial.dao;

import com.module3.photosocial.model.Photo;
import com.module3.photosocial.model.Tag;
import com.module3.photosocial.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends DatabaseContext implements IUserDAO {

    private static final String SELECT_ALL_USER = "SELECT * FROM user;";
    private static final String SELECT_USER_BY_ID = "SELECT * FROM user WHERE userId = ?;";
    private static final String SELECT_USER_BY_NAME = "SELECT * FROM user WHERE userName LIKE ?;";
    private static final String SELECT_USER_BY_EMAIL = "SELECT * FROM user WHERE email LIKE ?;";
    private static final String INSERT_USER = "insert into user(userName, email, userPassword) values(?,?,?);";
    private static final String UPDATE_USER = "UPDATE `user` SET `userName` = ?, `email` = ?," +
            " `userPassword` = ?, `timeCreateUser` = ? WHERE (`userId` = ?);";

    private static final String PHOTO_USER_UPLOAD = "SELECT idPhoto, urlPhoto, photoName, timeCreatePhoto, iduser, view " +
            "FROM photo join user on  photo.iduser = user.userId where user.userId = ?;";
    private static final String DELETE_USER_BY_ID = "DELETE FROM user WHERE (userId = ?);";


    private User getUserFromResultSet(ResultSet rs) throws SQLException {
        int userId = rs.getInt("userId");
        String userName = rs.getString("userName");
        String userEmail = rs.getString("email");
        String userPassword = rs.getString("userPassword");
        String timeCreateUser = rs.getString("timeCreateUser");
        int access = rs.getInt("access");
        User user = new User(userId, userName, userEmail, userPassword, timeCreateUser, access);
        return user;
    }

    @Override
    public void insertUser(User user) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(INSERT_USER);
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getUserEmail());
            preparedStatement.setString(3, user.getUserPassword());
            preparedStatement.execute();
            connection.close();
            System.out.println(this.getClass() + " insertUser :" + preparedStatement);
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public User findUserById(int id) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            User user = getUserFromResultSet(rs);
            System.out.println(this.getClass() + " findUserById: " + preparedStatement);
            connection.close();
            return user;

        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
        return null;
    }

    @Override
    public User findUserByName(String userName) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_NAME);
            preparedStatement.setString(1,"%"+ userName+"%");

            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            User user = getUserFromResultSet(rs);
            System.out.println(this.getClass() + " findUserByName: " + preparedStatement);
            connection.close();
            return user;

        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
        return null;
    }

    public User findUserByEmail(String userEmail) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_EMAIL);
            preparedStatement.setString(1,"%"+ userEmail+"%");

            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            User user = getUserFromResultSet(rs);
            System.out.println(this.getClass() + " findUserByEmail: " + preparedStatement);
            connection.close();
            return user;

        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
        return null;
    }

    @Override
    public List<User> selectAllUsers() {
        List<User> listUser = new ArrayList<>();
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USER);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                User user = getUserFromResultSet(rs);
                listUser.add(user);
            }
            System.out.println(this.getClass() + " selectAllUsers: " + preparedStatement);
            connection.close();
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }

        return listUser;
    }

    @Override
    public List<User> selectUsersPagging(int offset, int noOfRecords) {
        return null;
    }

    @Override
    public boolean deleteUserById(int id) throws SQLException {
        boolean check = false;
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_BY_ID);
        try {
            preparedStatement.setInt(1, id);
            check = preparedStatement.executeUpdate() > 0;
            System.out.println(this.getClass() + " deleteUserById: " + preparedStatement);
            connection.close();
        } catch (SQLException e) {
            printSQLException(e);
        }
        return check;
    }

    @Override
    public boolean updateUser(User user) throws SQLException {
        Connection connection = getConnection();
        boolean check = false;
        try {
            PreparedStatement pr = connection.prepareStatement(UPDATE_USER);
            pr.setString(1, user.getUserName());
            pr.setString(2, user.getUserEmail());
            pr.setString(3, user.getUserPassword());
            pr.setString(4, user.getTimeCreateUser());
            pr.setInt(5,user.getUserId());
            System.out.println(this.getClass() + " updateUser: " + pr);
            check =  pr.executeUpdate() > 0;
            connection.close();
        } catch (SQLException e) {
            printSQLException(e);
        }
        return check;
    }

    @Override
    public List<Tag> userUsuallyUploadTagPhoto(User user) {
        return null;
    }
}
