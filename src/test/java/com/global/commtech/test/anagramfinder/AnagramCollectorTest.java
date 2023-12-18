package com.global.commtech.test.anagramfinder;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AnagramCollectorTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream standardOutCaptor = new ByteArrayOutputStream();

    @Mock
    WordCollection wordCollection;
    AnagramCollector anagramCollector;

    @BeforeEach
    public void setup() {

        anagramCollector = new AnagramCollector(wordCollection);

        System.setOut(new PrintStream(standardOutCaptor));
    }

    @AfterEach
    public void teardown() {
        System.setOut(standardOut);
    }

    @Test
    void testCollect_firstWord() {
        anagramCollector.collect("hello");
        verify(wordCollection, times(1)).addWord("hello");
        assertEquals("", standardOutCaptor.toString());
        verify(wordCollection, times(1)).clear();
    }

    @Test
    void testCollect_twoWordsTheSameLength() {
        anagramCollector.collect("hello");
        anagramCollector.collect("world");
        verify(wordCollection, times(1)).addWord("hello");
        verify(wordCollection, times(1)).addWord("world");
        assertEquals("", standardOutCaptor.toString());
        verify(wordCollection, times(1)).clear();
    }

    @Test
    void testCollect_twoWordsOfDifferentLengths() {
        when(wordCollection.getGroupedWords()).thenReturn(new ArrayList());
        anagramCollector.collect("foo");
        verify(wordCollection, times(1)).addWord("foo");
        assertEquals("", standardOutCaptor.toString());

        when(wordCollection.getGroupedWords()).thenReturn(Arrays.asList(Arrays.asList("foo")));
        anagramCollector.collect("world");

        verify(wordCollection, times(1)).addWord("world");
        assertEquals("foo\n", standardOutCaptor.toString());
        verify(wordCollection, times(2)).clear();
    }

    @Test
    void testFlush_withSomeWordsHavingBeenCollected() {
        var groupedWords = new ArrayList();
        groupedWords.add(Arrays.asList("foo","oof"));
        groupedWords.add(Arrays.asList("bar","rab"));
        when(wordCollection.getGroupedWords()).thenReturn(groupedWords);
        anagramCollector.flush();
        assertEquals("foo,oof\nbar,rab\n", standardOutCaptor.toString());
        verify(wordCollection, times(1)).clear();
    }

    @Test
    void testFlush_withNoWordsHavingBeenCollected() {
        when(wordCollection.getGroupedWords()).thenReturn(new ArrayList());
        anagramCollector.flush();
        assertEquals("", standardOutCaptor.toString());
        verify(wordCollection, times(1)).clear();
    }
}