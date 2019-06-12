package edu.kpi.java.mkr.controller.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.kpi.java.mkr.model.Chapter;

public class FullChapterDTO {
    @JsonProperty("id")
    private int chapterId;
    @JsonProperty("name")
    private String chapterName;
    @JsonProperty("number")
    private int chapterNumber;
    @JsonProperty("chapter_text")
    private String chapterText;

    public static FullChapterDTO from(Chapter chapter){
        FullChapterDTO res = new FullChapterDTO();
        res.chapterId = chapter.getChapterId();
        res.chapterName = chapter.getChapterName();
        res.chapterNumber = chapter.getChapterNumber();
        res.chapterText = chapter.getChapterText();
        return res;
    }
}
