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

public class PhotoDAO extends DatabaseContext implements IPhotoDAO{
    private static final String SELECT_ALL_PHOTO = "SELECT * FROM photo;";
    private static final String SELECT_PHOTO_BY_ID = "SELECT * FROM photo WHERE idPhoto = ?;";
    private static final String SELECT_PHOTO_BY_NAME = "SELECT * FROM photo WHERE photoName LIKE ?;";
    private static final String SELECT_PHOTO_BY_ID_TAG = "SELECT photo.idPhoto, urlPhoto, photoName, timeCreatePhoto, iduser, view FROM photo \n" +
            "JOIN photo_tag ON  photo.idPhoto = photo_tag.idPhoto WHERE photo_tag.id_tag = ?;";
    private static final String INSERT_PHOTO = "INSERT INTO photo ( urlPhoto, photoName, iduser) VALUES (?, ?, ?);";
    private static final String DELETE_PHOTO = "DELETE FROM photo WHERE (idPhoto = ?);";
    private static final String LIST_PHOTO_USER_UPLOAD = "SELECT idPhoto, urlPhoto, photoName, timeCreatePhoto, iduser, view " +
            "FROM photo JOIN user on  photo.iduser = user.userId where user.userId = ?;";

    private Photo getPhotoFromResultSet(ResultSet rs) throws SQLException {
        int idPhoto = rs.getInt("idPhoto");
        String urlPhoto = rs.getString("urlPhoto");
        String photoName = rs.getString("photoName");
        String timeCreatePhoto = rs.getString("timeCreatePhoto");
        int upLoadBy = rs.getInt("iduser");
        int view = rs.getInt("view");
        Photo photo = new Photo(idPhoto, urlPhoto, photoName, upLoadBy, timeCreatePhoto, view);
        return photo;
    }

    @Override
    public void insertPhoto(Photo photo) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement(INSERT_PHOTO);
            preparedStatement.setString(1, photo.getUrlPhoto());
            preparedStatement.setString(2, photo.getPhotoName());
            preparedStatement.setInt(3, photo.getUpLoadBy());
            preparedStatement.execute();
            connection.close();
            System.out.println(this.getClass() + " insertPhoto :" + preparedStatement);
        }catch (SQLException e){
            printSQLException(e);
        }
    }

    @Override
    public Photo findPhotoById(int id) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PHOTO_BY_ID);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            Photo photo = getPhotoFromResultSet(rs);
            System.out.println(this.getClass() + " findPhotoById: " + preparedStatement);
            connection.close();
            return photo;

        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
        return null;
    }

    @Override
    public Photo findPhotoByName(String photoName) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PHOTO_BY_NAME);
            preparedStatement.setString(1,"%"+ photoName+"%");

            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            Photo photo = getPhotoFromResultSet(rs);
            System.out.println(this.getClass() + " findPhotoByName: " + preparedStatement);
            connection.close();
            return photo;

        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
        return null;
    }

    public List<Photo> selectPhotoByIdTag(int idTag) {
        List<Photo> photoList = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PHOTO_BY_ID_TAG);
            preparedStatement.setInt(1,idTag);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Photo photo = getPhotoFromResultSet(rs);
                photoList.add(photo);
            }
            System.out.println(this.getClass() + " selectPhotoByIdTag: " + preparedStatement);
            connection.close();
            return photoList;

        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
        return null;
    }

    @Override
    public List<Photo> selectAllPhotos() {
        List<Photo> photoList = new ArrayList<>();
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PHOTO);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Photo photo = getPhotoFromResultSet(rs);
                photoList.add(photo);
            }
            System.out.println(this.getClass() + " selectAllPhotos: " + preparedStatement);
            connection.close();
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }

        return photoList;
    }

    @Override
    public List<Photo> selectPhotosPagging(int offset, int noOfRecords) {
        return null;
    }

    @Override
    public boolean deletePhotoById(int id) throws SQLException {
        boolean check = false;
        Connection connection= getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PHOTO);
        try {
            preparedStatement.setInt(1, id);
            check =  preparedStatement.executeUpdate() > 0;
            System.out.println(this.getClass() + " deletePhotoById: " + preparedStatement);
            connection.close();
        }catch (SQLException e){
            printSQLException(e);
        }
        return check;
    }

    @Override
    public boolean updatePhoto(Photo photo) throws SQLException {
        return false;
    }

    @Override
    public User photoUploadBy(Photo photo) {
        return null;
    }

    @Override
    public List<Photo> nullTagPhoto(List<Photo> photoList) {
        return null;
    }

    @Override
    public int totalPhotoFavorites(Photo photo) {
        return 0;
    }

    @Override
    public List<Tag> listPhotoTag(Photo photo) {
        return null;
    }

    @Override
    public List<Photo> photoUserUpload(int id) {
        List<Photo> photoList = new ArrayList<>();
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(LIST_PHOTO_USER_UPLOAD);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Photo photo = getPhotoFromResultSet(rs);
                photoList.add(photo);
            }
            System.out.println(this.getClass() + " photoUserUpload: " + preparedStatement);
            connection.close();
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }

        return photoList;
    }
}
