package com.global.commtech.test.anagramfinder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AnagramCollector {

    private WordCollection wordCollection;

    private int processingWordsOfLength;

    @Autowired
    public AnagramCollector(WordCollection wordCollection){
        this.wordCollection = wordCollection;
        this.processingWordsOfLength = -1;
    }

    /**
     * Collect all words that are anagrams together into a comma separated string
     * @param nextWord the next word to process
     */
    public void collect(String nextWord) {
        if (processingWordsOfLength != nextWord.length()) {
            flush();
            processingWordsOfLength = nextWord.length();
        }
        wordCollection.addWord(nextWord);
    }

    /**
     * Flush the collector of any remaining data
     */
    public void flush() {
        for (List groupedWords : wordCollection.getGroupedWords()) {
            System.out.println(groupedWords.stream().collect(Collectors.joining(",")));
        }
        wordCollection.clear();
    }
}