package com.module3.photosocial.dao;

import com.module3.photosocial.model.Tag;

import java.sql.SQLException;
import java.util.List;

public interface ITagDAO {
    void insertTag(Tag tag);
    Tag selectTag(int id);
    List<Tag> listTag();
    boolean updateTag(Tag tag);
    boolean deleteTagById(int id) throws SQLException;
}
