package org.example.repositories;

import org.example.models.Book;
import org.example.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PeopleRepository extends JpaRepository<Person,Integer> {
    Optional<Person> findPersonByFullName(String fullName);

}
