package com.module3.photosocial.dao;

import com.module3.photosocial.model.Photo;
import com.module3.photosocial.model.Tag;
import com.module3.photosocial.model.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserDAO {
    public void insertUser(User user) throws SQLException;

    public User findUserById(int id);

    public User findUserByName(String userName);

    public List<User> selectAllUsers();

    public List<User> selectUsersPagging(int offset, int noOfRecords);

    public boolean deleteUserById(int id) throws SQLException;
    User findUserByEmail(String userEmail)throws SQLException;
    public boolean updateUser(User user) throws SQLException;




    List<Tag> userUsuallyUploadTagPhoto(User user);

}
