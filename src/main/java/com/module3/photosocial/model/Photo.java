package com.module3.photosocial.model;

public class Photo {
    private int idPhoto;
    private String urlPhoto;
    private String photoName;
    private int upLoadBy;
    private String timeCreatePhoto;
    private int view;

    public Photo(int idPhoto, String urlPhoto, String photoName, int upLoadBy, String timeCreatePhoto, int view) {
        this.idPhoto = idPhoto;
        this.urlPhoto = urlPhoto;
        this.photoName = photoName;
        this.upLoadBy = upLoadBy;
        this.timeCreatePhoto = timeCreatePhoto;
        this.view = view;
    }

    public Photo() {
    }

    public int getIdPhoto() {
        return idPhoto;
    }

    public void setIdPhoto(int idPhoto) {
        this.idPhoto = idPhoto;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public int getUpLoadBy() {
        return upLoadBy;
    }

    public void setUpLoadBy(int upLoadBy) {
        this.upLoadBy = upLoadBy;
    }

    public String getTimeCreatePhoto() {
        return timeCreatePhoto;
    }

    public void setTimeCreatePhoto(String timeCreatePhoto) {
        this.timeCreatePhoto = timeCreatePhoto;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }
}
