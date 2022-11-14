package com.module3.photosocial.model;

public class PhotoTag {
    private int idPhoto;
    private int idTag;

    public PhotoTag(int idPhoto, int idTag) {
        this.idPhoto = idPhoto;
        this.idTag = idTag;
    }

    public PhotoTag() {
    }

    public int getIdPhoto() {
        return idPhoto;
    }

    public void setIdPhoto(int idPhoto) {
        this.idPhoto = idPhoto;
    }

    public int getIdTag() {
        return idTag;
    }

    public void setIdTag(int idTag) {
        this.idTag = idTag;
    }
}
