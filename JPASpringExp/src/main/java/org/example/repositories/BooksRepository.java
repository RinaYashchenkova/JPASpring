package org.example.repositories;

import org.example.models.Book;
import org.example.models.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BooksRepository extends JpaRepository<Book,Integer> {
    List<Book> findByOwner(Person owner);

    Page<Book> findAll(Pageable pageable);

    List<Book> findAll();

    List<Book> findByTitleLike(String title);

    Optional<Book> findByOwnerId(int id);

    Optional<Book> findById(int id);
}
