package com.isaguler.springunittestexamples.service.greeting;

import com.isaguler.springunittestexamples.model.greeting.Person;
import com.isaguler.springunittestexamples.repository.greeting.PersonRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GreetingServiceTest {

    @Mock
    private PersonRepository personRepository;

    @Mock
    private TranslateService translateService;

    @InjectMocks
    private GreetingService greetingService;

    @Test
    @DisplayName("Greet Existing Person Test")
    void greetForPersonDoesExist() {
        // Set the expectations on the Dependencies
        when(personRepository.findById(anyLong()))
                .thenReturn(Optional.of(new Person(1L, "Isa", "Guler")));

        when(translateService.translate("Hello, Isa Guler!", "en", "en"))
                .thenReturn("Hello, Isa Guler!");

        // Test greet method
        String greeting = greetingService.greet(1L, "en", "en");
        assertEquals(greeting, "Hello, Isa Guler!");

        // Verify that dependencies were called as expected
        InOrder inOrder = inOrder(personRepository, translateService);
        inOrder.verify(personRepository).findById(anyLong());
        inOrder.verify(translateService).translate(anyString(), eq("en"), eq("en"));
    }

    @Test
    @DisplayName("Greet Non Existing Person Test")
    void greetForPersonDoesNotExist() {
        // Set the expectations on the Dependencies
        when(personRepository.findById(anyLong()))
                .thenReturn(Optional.empty());

        when(translateService.translate("Hello, World!", "en", "en"))
                .thenReturn("Hello, World!");

        // Test greet method
        String greeting = greetingService.greet(100L, "en", "en");
        assertEquals(greeting, "Hello, World!");

        // Verify that dependencies were called as expected
        InOrder inOrder = inOrder(personRepository, translateService);
        inOrder.verify(personRepository).findById(anyLong());
        inOrder.verify(translateService).translate(anyString(), eq("en"), eq("en"));
    }
}