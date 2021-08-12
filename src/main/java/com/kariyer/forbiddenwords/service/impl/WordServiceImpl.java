package com.kariyer.forbiddenwords.service.impl;

import com.kariyer.forbiddenwords.StringUtil;
import com.kariyer.forbiddenwords.model.Word;
import com.kariyer.forbiddenwords.repository.WordRepository;
import com.kariyer.forbiddenwords.service.WordService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class WordServiceImpl implements WordService {

    private final WordRepository wordRepository;

    public WordServiceImpl(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    @Override
    @Cacheable(value = "words", key = "#word.id")
    public Word saveWord(Word word) {
        return wordRepository.save(word);
    }

    @Override
    @CachePut(key = "#word.id", value = "words")
    public Word updateWord(Word word) {
        return wordRepository.save(word);
    }

    @Override
    @CacheEvict(key = "#word", value = "words")
    public void deleteWordById(String word) {
        wordRepository.deleteById(word);
    }

    @Override
    @Cacheable(key = "#keyString", value = "words")
    public Word searchWord(String keyString) {
        return wordRepository.findById(keyString).orElse(null);
    }

    @Override
    public List<Word> getAllWordList() {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(wordRepository.findAll().iterator(), Spliterator.ORDERED), false)
                .collect(Collectors.toList());
    }

    @Override
    public Boolean isForbiddenWordExist(String description) {
        Date date = new Date();
        String[] wordList = StringUtil.splitParagraphToWords(description);
        Set<String> wordSet = new HashSet<>(Arrays.asList(wordList));
        if (!CollectionUtils.isEmpty(wordSet))
            for (String word : wordList) {
                if (Objects.nonNull(searchWord(word)))
                    return Boolean.TRUE;
            }
        return Boolean.FALSE;
    }

    @Override
    @Cacheable(value = "words", key = "#word.id")
    public void invalidateCache(Word word) {
        wordRepository.save(word);
    }
}
