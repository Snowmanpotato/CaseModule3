package com.module3.photosocial.dao;

import com.module3.photosocial.model.Access;

import java.sql.SQLException;
import java.util.List;

public interface IAccessDAO {
    void insertAccess(Access access);
    Access selectAccess(int id);
    List<Access> listAccess();
    boolean updateAccess(Access access);
    boolean deleteAccessById(int id) throws SQLException;

}
