package com.global.commtech.test.anagramfinder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class WordCollectionTest {

    WordCollection wordCollection;

    @BeforeEach
    public void setup() {
        wordCollection = new WordCollection();
    }

    @Test
    void testCollect_oneWordCollected() {
        wordCollection.addWord("foo");

        var expectedListOfGroupedWords = new ArrayList();
        expectedListOfGroupedWords.add(Arrays.asList("foo"));

        assertEquals(expectedListOfGroupedWords, wordCollection.getGroupedWords());
    }

    @Test
    void testCollect_multipleWordsNoAnagramsCollected() {
        var words = Arrays.asList("foo", "bar", "hello", "world");

        var expectedListOfGroupedWords = new ArrayList();
        expectedListOfGroupedWords.add(Arrays.asList("foo"));
        expectedListOfGroupedWords.add(Arrays.asList("bar"));
        expectedListOfGroupedWords.add(Arrays.asList("hello"));
        expectedListOfGroupedWords.add(Arrays.asList("world"));


        for (String word : words) {
            wordCollection.addWord(word);
        }

        assertThat(wordCollection.getGroupedWords())
                .hasSize(expectedListOfGroupedWords.size())
                .containsAll(expectedListOfGroupedWords);
    }

    @Test
    void testCollect_multipleWordsWithAnagramsCollected() {
        var words = Arrays.asList("bar", "rab", "arb", "bare", "bear", "brae", "hello");

        var expectedListOfGroupedWords = new ArrayList();
        expectedListOfGroupedWords.add(Arrays.asList("bar", "rab", "arb"));
        expectedListOfGroupedWords.add(Arrays.asList("bare", "bear", "brae"));
        expectedListOfGroupedWords.add(Arrays.asList("hello"));


        for (String word : words) {
            wordCollection.addWord(word);
        }

        assertThat(wordCollection.getGroupedWords())
                .hasSize(expectedListOfGroupedWords.size())
                .containsAll(expectedListOfGroupedWords);
    }

    @Test
    void testGenerateKey() {
        assertEquals("dehllloorw", wordCollection.generateKey("helloworld"));
    }

    @Test
    void testGenerateKey_emptyInput() {
        assertEquals("", wordCollection.generateKey(""));
    }

    @Test
    void testClear() {
        wordCollection.addWord("foo");

        var expectedListOfGroupedWords = new ArrayList();
        expectedListOfGroupedWords.add(Arrays.asList("foo"));

        assertEquals(expectedListOfGroupedWords, wordCollection.getGroupedWords());

        wordCollection.clear();
        assertEquals(0, wordCollection.getGroupedWords().size());

    }
}