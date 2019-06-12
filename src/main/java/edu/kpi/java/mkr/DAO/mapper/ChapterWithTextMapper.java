package edu.kpi.java.mkr.DAO.mapper;

import edu.kpi.java.mkr.model.Book;
import edu.kpi.java.mkr.model.Chapter;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ChapterWithTextMapper extends ChapterMapper{
    @Override
    public Chapter mapRow(ResultSet resultSet, int i) throws SQLException {
        Chapter c = super.mapRow(resultSet, i);
        c.setChapterText(resultSet.getString(5));
        return c;
    }
}
