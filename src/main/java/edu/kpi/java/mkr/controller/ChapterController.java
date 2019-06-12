package edu.kpi.java.mkr.controller;

import edu.kpi.java.mkr.controller.DTO.ChapterDetailsDTO;
import edu.kpi.java.mkr.controller.DTO.FullChapterDTO;
import edu.kpi.java.mkr.service.ChapterService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/chapter")
public class ChapterController {

    private ChapterService chapterService;


    @GetMapping
    public List<ChapterDetailsDTO> getChaptersByBook(@RequestParam("book_id") Number bookId){
        return chapterService.getChaptersByBook(bookId).stream().map(ChapterDetailsDTO::from).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public FullChapterDTO getChapterWithText(@PathVariable("id") Number id){
        return FullChapterDTO.from(chapterService.getChapterWithText(id));
    }

    @GetMapping("/current")
    public FullChapterDTO getCurrentChapter(@RequestParam("book_id") Number bookId){
        return FullChapterDTO.from(chapterService.getCurrentChapter(bookId));
    }
}
