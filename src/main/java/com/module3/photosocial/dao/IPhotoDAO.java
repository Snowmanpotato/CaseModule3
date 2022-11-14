package com.module3.photosocial.dao;

import com.module3.photosocial.model.Photo;
import com.module3.photosocial.model.Tag;
import com.module3.photosocial.model.User;

import java.sql.SQLException;
import java.util.List;

public interface IPhotoDAO {
    public void insertPhoto(Photo photo) throws SQLException; // thêm ảnh

    public Photo findPhotoById(int id); // tìm ảnh băng Id

    public Photo findPhotoByName(String photoName); // tìm ảnh bằng tên

    public List<Photo> selectAllPhotos(); // hiển thị danh sách ảnh

    public List<Photo> selectPhotosPagging(int offset, int noOfRecords); //phân trang

    public boolean deletePhotoById(int id) throws SQLException; // xóa ảnh

    public boolean updatePhoto(Photo photo) throws SQLException; // sửa ảnh

    User photoUploadBy(Photo photo); // ảnh được đăng bởi

    List<Photo> nullTagPhoto(List<Photo> photoList); // những ảnh không được gắn thẻ

    int totalPhotoFavorites(Photo photo); // tổng số lượt thích của bức ảnh

    List<Tag> listPhotoTag(Photo photo); // những thẻ được gắn vào ảnh

    List<Photo> photoUserUpload(int id);

    List<Photo> selectPhotoByIdTag(int idTag);
}
