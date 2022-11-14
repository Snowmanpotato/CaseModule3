package com.module3.photosocial.dao;

import com.module3.photosocial.model.Access;
import com.module3.photosocial.model.PhotoTag;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PhotoTagDAO extends DatabaseContext implements IPhotoTagDAO {

    private static final String INSERT_PHOTO_TAG = "INSERT INTO photo_tag (idPhoto, id_tag) VALUES (?, ?);";
    private static final String DELETE_PHOTO_TAG = "DELETE FROM photo_tag WHERE (idPhoto = ?) and (id_tag = ?);";


    private PhotoTag getPhotoTagFromResultSet(ResultSet rs) throws SQLException {
        int idPhoto = rs.getInt("idPhoto");
        int idTag = rs.getInt("id_tag");
        PhotoTag photoTag = new PhotoTag(idPhoto, idTag);
        return photoTag;
    }

    @Override
    public void insertPhotoTag(PhotoTag photoTag) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        try{
           preparedStatement= connection.prepareStatement(INSERT_PHOTO_TAG);
           preparedStatement.setInt(1, photoTag.getIdPhoto());
           preparedStatement.setInt(2, photoTag.getIdTag());
           preparedStatement.execute();
           connection.close();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public Access selectPhotoTag(int id) {
        return null;
    }

    @Override
    public List<Access> listPhotoTag() {
        return null;
    }

    @Override
    public boolean updatePhotoTag(PhotoTag photoTag) {
        return false;
    }

    @Override
    public boolean deletePhotoTagById(int idPhoto, int idTag) throws SQLException {
        boolean check = false;
        Connection connection= getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PHOTO_TAG);
        try {
            preparedStatement.setInt(1, idPhoto);
            preparedStatement.setInt(2, idTag);
            check =  preparedStatement.executeUpdate() > 0;
            connection.close();
        }catch (SQLException e){
            printSQLException(e);
        }
        return check;
    }
}
