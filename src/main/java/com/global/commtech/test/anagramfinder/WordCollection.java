package com.global.commtech.test.anagramfinder;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class WordCollection {

    private Map<String, List<String>> anagramMap = new HashMap<>();

    public void addWord(String word) {
        String anagramKey = generateKey(word);

        if (! anagramMap.containsKey(anagramKey)) {
            anagramMap.put(anagramKey, new ArrayList());
        }
        List groupedWords = anagramMap.get(anagramKey);
        groupedWords.add(word);
    }

    public List<List<String>> getGroupedWords() {
        return new ArrayList(anagramMap.values());
    }

    protected String generateKey(String word) {
        char[] wordChars = word.toCharArray();
        Arrays.sort(wordChars);
        return String.valueOf(wordChars);
    }

    public void clear() {
        anagramMap.clear();
    }
}
