package com.global.commtech.test.anagramfinder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class FileProcessorTest {
    private static final String TEST_FILE_PATH = "src/test/resources/testfiles/";

    FileProcessor fileProcessor;

    @Mock
    AnagramCollector collector;

    @BeforeEach
    public void setup() {
        fileProcessor = new FileProcessor();
    }

    @Test
    void testCollect_fileWithOneEntry() {
        var fileToProcess = new File(TEST_FILE_PATH + "oneEntry.txt");

        fileProcessor.process(fileToProcess, collector);

        verify(collector, times(1)).collect("first");
        verify(collector, times(1)).flush();
    }

    @Test
    void testCollect_fileWithFiveEntries() {
        var fileToProcess = new File(TEST_FILE_PATH + "fiveEntries.txt");

        fileProcessor.process(fileToProcess, collector);

        verify(collector, times(5)).collect(anyString());
        verify(collector, times(1)).flush();
    }

    @Test
    void testCollect_emptyFile() {
        var fileToProcess = new File(TEST_FILE_PATH + "emptyFile.txt");

        fileProcessor.process(fileToProcess, collector);

        verify(collector, times(0)).collect(anyString());
        verify(collector, times(1)).flush();
    }

    @Test
    void testCollect_missingFile() {
        var fileToProcess = new File(TEST_FILE_PATH + "missingFile.txt");

        final var exception = assertThrows(Exception.class, () ->  fileProcessor.process(fileToProcess, collector));
        assertThat(exception.getMessage()).isEqualTo("Error reading from file: missingFile.txt");

        verify(collector, times(0)).collect(anyString());
        verify(collector, times(0)).flush();
    }
}