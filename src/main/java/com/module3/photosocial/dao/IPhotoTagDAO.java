package com.module3.photosocial.dao;

import com.module3.photosocial.model.Access;
import com.module3.photosocial.model.PhotoTag;

import java.sql.SQLException;
import java.util.List;

public interface IPhotoTagDAO {
    void insertPhotoTag(PhotoTag photoTag);
    Access selectPhotoTag(int id);
    List<Access> listPhotoTag();
    boolean updatePhotoTag(PhotoTag photoTag);
     boolean deletePhotoTagById(int idPhoto, int idTag) throws SQLException;
}
