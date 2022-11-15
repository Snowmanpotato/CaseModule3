package com.module3.photosocial.dao;

import com.module3.photosocial.model.Favorites;
import com.module3.photosocial.model.FavoritesCheck;
import com.module3.photosocial.model.Photo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FavoritesCheckDAO extends DatabaseContext implements IFavoritesCheckDAO {

    private static final String INSERT_FAVORITES_CHECK_DAO = "INSERT INTO favorites_check (idfavorites, idphoto) VALUES (?, ?);";
    private static final String DELETE_FAVORITES_CHECK_DAO = "DELETE FROM favorites_check WHERE (idfavorites_check = ?);";

    private static final String SELECT_PHOTO_BY_ID_USER="SELECT idphoto FROM favorites_check WHERE iduser = ?;";


    private FavoritesCheck getFavoritesCheckFromResultSet(ResultSet rs) throws SQLException {
        int idFavoritesCheck = rs.getInt("idfavorites_check");
        int idFavorites = rs.getInt("idfavorites");
        int idPhoto = rs.getInt("idphoto");
        FavoritesCheck favoritesCheck = new FavoritesCheck(idFavoritesCheck, idFavorites,idPhoto);
        return favoritesCheck;
    }

    @Override
    public void insertFavoritesCheck(FavoritesCheck favoritesCheck) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement(INSERT_FAVORITES_CHECK_DAO);
            preparedStatement.setInt(1, favoritesCheck.getIdFavorites());
            preparedStatement.setInt(2,favoritesCheck.getIdPhoto());
            preparedStatement.execute();
            connection.close();
        }catch (SQLException e){
            printSQLException(e);
        }
    }
    public List<Photo> listPhotoUserFavorites(int id){
        PhotoDAO photoDAO = new PhotoDAO();
        List<Photo> photoList = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement(SELECT_PHOTO_BY_ID_USER);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
               Photo photo = photoDAO.findPhotoById(resultSet.getInt("idphoto"));
                photoList.add(photo);
            }
            System.out.println(this.getClass() + " listPhotoUserFavorites: " + preparedStatement);
            connection.close();
        }catch (SQLException e){
            printSQLException(e);
        }
        return photoList;
    }


    @Override
    public FavoritesCheck selectFavoritesCheck(int id) {
        return null;
    }

    @Override
    public List<FavoritesCheck> listFavoritesCheck() {
        return null;
    }

    @Override
    public boolean updateFavoritesCheck(FavoritesCheck favoritesCheck) {
        return false;
    }

    @Override
    public boolean deleteFavoritesCheckById(int id) throws SQLException {
        boolean check = false;
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FAVORITES_CHECK_DAO);
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
