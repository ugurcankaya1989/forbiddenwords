package com.kariyer.forbiddenwords;

import org.springframework.util.StringUtils;


public class StringUtil {

    public static String[] splitParagraphToWords(String paragraph) {
        if (StringUtils.hasLength(paragraph))
            return paragraph.split("\\W+");
        return new String[0];
    }
}
