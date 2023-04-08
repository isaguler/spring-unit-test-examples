package com.isaguler.springunittestexamples.repository.greeting;

import com.isaguler.springunittestexamples.model.greeting.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
