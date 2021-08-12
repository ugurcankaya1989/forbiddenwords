package com.kariyer.forbiddenwords.service;

import com.kariyer.forbiddenwords.model.Word;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
@Component
public class ParseFile {

    public List<Word> getForbiddenWordList() throws IOException {
        ClassPathResource resource = new ClassPathResource("forbiddenwords.txt");
        List<Word> wordList = new ArrayList<>();
        Files.lines(Path.of(resource.getURI().getPath())).forEach(e -> wordList.add(new Word(e, e)));
        return wordList;
    }

}
