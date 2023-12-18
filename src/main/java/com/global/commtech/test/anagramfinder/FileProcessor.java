package com.global.commtech.test.anagramfinder;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

@Component
public class FileProcessor {

    public void process(File fileToProcess, AnagramCollector collector) {
        try {
            Scanner fileScanner = new Scanner(fileToProcess);
            while (fileScanner.hasNext()) {
                String nextLine = fileScanner.nextLine();
                collector.collect(nextLine);
            }
            collector.flush();

        } catch (IOException e) {
            throw new RuntimeException("Error reading from file: " + fileToProcess.getName(), e);
        }
    }

}
