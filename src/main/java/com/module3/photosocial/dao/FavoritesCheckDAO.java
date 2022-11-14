package com.module3.photosocial.dao;

import com.module3.photosocial.model.Favorites;
import com.module3.photosocial.model.FavoritesCheck;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class FavoritesCheckDAO extends DatabaseContext implements IFavoritesCheckDAO {

    private static final String INSERT_FAVORITES_CHECK_DAO = "INSERT INTO favorites_check (idfavorites, idphoto) VALUES (?, ?);";
    private static final String DELETE_FAVORITES_CHECK_DAO = "DELETE FROM favorites_check WHERE (idfavorites_check = ?);";

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
