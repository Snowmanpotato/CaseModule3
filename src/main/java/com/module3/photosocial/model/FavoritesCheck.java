package com.module3.photosocial.model;

public class FavoritesCheck {
  private int idFavoritesCheck;
  private int idFavorites;
  private int idPhoto;

    public FavoritesCheck(int idFavoritesCheck, int idFavorites, int idPhoto) {
        this.idFavoritesCheck = idFavoritesCheck;
        this.idFavorites = idFavorites;
        this.idPhoto = idPhoto;
    }

    public FavoritesCheck() {
    }

    public int getIdFavoritesCheck() {
        return idFavoritesCheck;
    }

    public void setIdFavoritesCheck(int idFavoritesCheck) {
        this.idFavoritesCheck = idFavoritesCheck;
    }

    public int getIdFavorites() {
        return idFavorites;
    }

    public void setIdFavorites(int idFavorites) {
        this.idFavorites = idFavorites;
    }

    public int getIdPhoto() {
        return idPhoto;
    }

    public void setIdPhoto(int idPhoto) {
        this.idPhoto = idPhoto;
    }
}
