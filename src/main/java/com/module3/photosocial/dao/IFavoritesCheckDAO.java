package com.module3.photosocial.dao;



import com.module3.photosocial.model.FavoritesCheck;

import java.sql.SQLException;
import java.util.List;

public interface IFavoritesCheckDAO {
    void insertFavoritesCheck(FavoritesCheck favoritesCheck);
    FavoritesCheck selectFavoritesCheck(int id);
    List<FavoritesCheck> listFavoritesCheck();
    boolean updateFavoritesCheck(FavoritesCheck favoritesCheck);
    boolean deleteFavoritesCheckById(int id) throws SQLException;
}
