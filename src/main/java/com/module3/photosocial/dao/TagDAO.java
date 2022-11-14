package com.module3.photosocial.dao;

import com.module3.photosocial.model.Photo;
import com.module3.photosocial.model.Tag;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TagDAO extends DatabaseContext implements ITagDAO {

    private static final String INSERT_TAG = "INSERT INTO tag (tagName) VALUES (?);";
    private static final String DELETE_TAG = "DELETE FROM tag WHERE (tagId = ?);";
    private static final String SELECT_ALL_TAG = "SELECT * FROM photo_social_media.tag;";
    private static final String SELECT_TAG_BY_ID = "SELECT * FROM photo_social_media.tag WHERE tagId = ?";

    private Tag getTagFromResultSet(ResultSet rs) throws SQLException {
        int tagId = rs.getInt("tagId");
        String tagName = rs.getString("tagName");
        Tag tag = new Tag(tagId, tagName);
        return tag;
    }

    @Override
    public void insertTag(Tag tag) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(INSERT_TAG);
            preparedStatement.setString(1, tag.getTagName());
            preparedStatement.execute();
            connection.close();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public Tag selectTag(int id) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TAG_BY_ID);
            preparedStatement.setInt(1, id);


            System.out.println(this.getClass() + " selectTag: " + preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            Tag tag = getTagFromResultSet(rs);
            connection.close();
            return tag;

        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
        return null;
    }

    @Override
    public List<Tag> listTag() {
        List<Tag> listTag = new ArrayList<>();
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TAG);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Tag tag = getTagFromResultSet(rs);
                listTag.add(tag);
            }
            System.out.println(this.getClass() + " listTag: " + preparedStatement);
            connection.close();
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
        return listTag;
    }

    @Override
    public boolean updateTag(Tag tag) {
        return false;
    }

    @Override
    public boolean deleteTagById(int id) throws SQLException {
        boolean check = false;
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_TAG);
        try {
            preparedStatement.setInt(1, id);
            check = preparedStatement.executeUpdate() > 0;
            System.out.println(this.getClass() + " deleteTagById: " + preparedStatement);
            connection.close();
        } catch (SQLException e) {
            printSQLException(e);
        }
        return check;
    }
}
