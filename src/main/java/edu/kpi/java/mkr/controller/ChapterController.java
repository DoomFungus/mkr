package edu.kpi.java.mkr.controller;

import edu.kpi.java.mkr.controller.DTO.ChapterDetailsDTO;
import edu.kpi.java.mkr.controller.DTO.FullChapterDTO;
import edu.kpi.java.mkr.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/chapter")
public class ChapterController {

    private ChapterService chapterService;

    @Autowired
    public ChapterController(ChapterService chapterService) {
        this.chapterService = chapterService;
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping
    public List<ChapterDetailsDTO> getChaptersByBook(@RequestParam("book_id") Number bookId){
        return chapterService.getChaptersByBook(bookId).stream().map(ChapterDetailsDTO::from).collect(Collectors.toList());
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/{id}")
    public FullChapterDTO getChapterWithText(@PathVariable("id") Number id){
        return FullChapterDTO.from(chapterService.getChapterWithText(id));
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/current")
    public FullChapterDTO getCurrentChapter(@RequestParam("book_id") Number bookId){
        return FullChapterDTO.from(chapterService.getCurrentChapter(bookId));
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PostMapping("/current")
    public void setCurrentChapter(@RequestParam("chapter_id") Number chapterId){
        chapterService.setCurrentChapter(chapterId);
    }
}
