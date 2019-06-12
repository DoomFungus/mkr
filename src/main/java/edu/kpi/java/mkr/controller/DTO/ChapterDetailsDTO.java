package edu.kpi.java.mkr.controller.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.kpi.java.mkr.model.Chapter;
import lombok.Data;

@Data
public class ChapterDetailsDTO {
    @JsonProperty("id")
    private int chapterId;
    @JsonProperty("name")
    private String chapterName;
    @JsonProperty("number")
    private int chapterNumber;


    public static ChapterDetailsDTO from(Chapter chapter){
        ChapterDetailsDTO res = new ChapterDetailsDTO();
        res.chapterId = chapter.getChapterId();
        res.chapterName = chapter.getChapterName();
        res.chapterNumber = chapter.getChapterNumber();
        return res;
    }
}
