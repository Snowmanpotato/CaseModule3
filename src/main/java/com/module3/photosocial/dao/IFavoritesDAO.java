package com.module3.photosocial.dao;

import com.module3.photosocial.model.Favorites;

import java.sql.SQLException;

public interface IFavoritesDAO {
    void insertFavorites(Favorites favorites);

    boolean deleteFavoritesById(int id) throws SQLException;
}

