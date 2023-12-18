package com.global.commtech.test.anagramfinder;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class AnagramCommandLineRunnerTest {

    @InjectMocks
    AnagramCommandLineRunner anagramCommandLineRunner;

    @Test
    void shouldThrowExceptionWhenNoArgumentsPresent() {
        final var exception = assertThrows(Exception.class, () -> anagramCommandLineRunner.run());
        assertThat(exception.getMessage()).isEqualTo("Please ensure that the input file is provided");
    }

    @Test
    void shouldThrowExceptionWhenMoreThanOneArgumentIsPassed() {
        final var exception = assertThrows(Exception.class, () -> anagramCommandLineRunner.run("one", "two"));
        assertThat(exception.getMessage()).isEqualTo("Please ensure that the input file is provided");
    }

    @Test
    void shouldThrowExceptionWhenInputFileDoesNotExist() {
        final var exception = assertThrows(Exception.class, () -> anagramCommandLineRunner.run("notExists"));
        assertThat(exception.getMessage()).isEqualTo("notExists Does not exist");
    }

}