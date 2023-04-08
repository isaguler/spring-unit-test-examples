package com.isaguler.springunittestexamples.service.greeting;

import com.isaguler.springunittestexamples.model.greeting.Person;
import com.isaguler.springunittestexamples.repository.greeting.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class GreetingService {

    private final TranslateService translateService;
    private final PersonRepository personRepository;

    public GreetingService(TranslateService translateService, PersonRepository personRepository) {
        this.translateService = translateService;
        this.personRepository = personRepository;
    }

    public String greet(Long userId, String fromLanguage, String toLanguage) {
        Optional<Person> person = personRepository.findById(userId);
        String name = person.map(Person::fullName).orElse("World");

        String greeting = "Hello, " + name + "!";

        return translateService.translate(greeting, fromLanguage, toLanguage);
    }
}
