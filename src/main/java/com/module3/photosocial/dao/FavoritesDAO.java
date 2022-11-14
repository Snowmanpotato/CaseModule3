package com.module3.photosocial.dao;

import com.module3.photosocial.model.Favorites;
import com.module3.photosocial.model.PhotoTag;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FavoritesDAO extends DatabaseContext implements IFavoritesDAO {

    private static final String INSERT_FAVORITES_DAO = "INSERT INTO favorites (user_userId) VALUES (?);";
    private static final String DELETE_FAVORITES = "DELETE FROM favorites WHERE (idfavorites = ?);";

    private Favorites getFavoritesFromResultSet(ResultSet rs) throws SQLException {
        int idFavorites = rs.getInt("idPhoto");
        int userId = rs.getInt("id_tag");
        Favorites favorites = new Favorites(idFavorites, userId);
        return favorites;
    }

    @Override
    public void insertFavorites(Favorites favorites) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(INSERT_FAVORITES_DAO);
            preparedStatement.setInt(1, favorites.getUserId());
            preparedStatement.execute();
            connection.close();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public boolean deleteFavoritesById(int id) throws SQLException {
        boolean check = false;
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FAVORITES);
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
