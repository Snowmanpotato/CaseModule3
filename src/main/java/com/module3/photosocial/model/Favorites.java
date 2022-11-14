package com.module3.photosocial.model;

public class Favorites {
  private int idFavorites;
  private int userId;

    public Favorites(int idFavorites, int userId) {
        this.idFavorites = idFavorites;
        this.userId = userId;
    }

    public Favorites() {
    }

    public int getIdFavorites() {
        return idFavorites;
    }

    public void setIdFavorites(int idFavorites) {
        this.idFavorites = idFavorites;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserUserId(int userId) {
        this.userId = userId;
    }
}
