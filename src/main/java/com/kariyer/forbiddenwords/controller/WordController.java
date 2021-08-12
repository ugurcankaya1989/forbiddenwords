package com.kariyer.forbiddenwords.controller;

import com.kariyer.forbiddenwords.model.Word;
import com.kariyer.forbiddenwords.service.ParseFile;
import com.kariyer.forbiddenwords.service.WordService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/forbidden")
public class WordController {

    private final WordService wordService;
    private final ParseFile parseFile;

    public WordController(WordService wordService, ParseFile parseFile) {
        this.wordService = wordService;
        this.parseFile = parseFile;
    }

    @GetMapping("/find-by-word")
    public Word findById(@RequestParam("word") String word) {
        return wordService.searchWord(word);
    }

    @GetMapping("/get-all-word")
    public List<Word> getAllWordList() {
        return wordService.getAllWordList();
    }

    @GetMapping("/invalidate-word-cache")
    public Boolean invalidateWordCache() {
        List<Word> wordList;
        boolean result = false;
        try {
            wordList = parseFile.getForbiddenWordList();
            wordList.stream().forEach(w -> wordService.invalidateCache(w));
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @PostMapping("/save-word")
    public Word saveWord(@RequestBody Word word) {
        return wordService.saveWord(word);
    }

    @PutMapping("/update-word")
    public Word updateWord(@RequestBody Word word) {
        return wordService.updateWord(word);
    }

    @DeleteMapping("/delete-by-word")
    public void deleteByWord(@RequestParam("word") String word) {
        wordService.deleteWordById(word);
    }

    @PostMapping("/is-forbidden-word-exist")
    public Boolean isForbiddenWordExist(@RequestBody String description) {
        return wordService.isForbiddenWordExist(description);
    }

}
