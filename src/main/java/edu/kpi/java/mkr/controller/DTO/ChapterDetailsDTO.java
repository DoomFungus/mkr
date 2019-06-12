package edu.kpi.java.mkr.controller.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.kpi.java.mkr.model.Chapter;
import lombok.Data;

@Data
public class ChapterDetailsDTO {
    @JsonProperty("id")
    private int chapterId;
    @JsonProperty("name")
    private int chapterName;
    @JsonProperty("number")
    private int chapterNumber;
    @JsonProperty("book_id")
    private int bookId;

    public static ChapterDetailsDTO from(Chapter chapter){
        ChapterDetailsDTO res = new ChapterDetailsDTO();
        res.chapterId = chapter.getChapterId();
        res.chapterName = chapter.getChapterName();
        res.chapterNumber = chapter.getChapterNumber();
        res.bookId = chapter.getBook().getBookId();
        return res;
    }
}
