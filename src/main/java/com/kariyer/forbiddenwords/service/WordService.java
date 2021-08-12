package com.kariyer.forbiddenwords.service;

import com.kariyer.forbiddenwords.model.Word;

import java.util.List;

public interface WordService {
    Word saveWord(Word word);
    Word updateWord(Word word);
    void deleteWordById(String word);
    Word searchWord(String word);
    List<Word> getAllWordList();
    Boolean isForbiddenWordExist(String description);
    void invalidateCache(Word word);
}
