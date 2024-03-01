package org.example.services;

import org.example.models.Book;
import org.example.models.Person;
import org.example.repositories.BooksRepository;
import org.example.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BooksService {
    private final BooksRepository booksRepository;
    private final PeopleRepository peopleRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository, PeopleRepository peopleRepository) {
        this.booksRepository = booksRepository;
        this.peopleRepository = peopleRepository;
    }

    public Page<Book> findAll(Pageable pageable){
        return booksRepository.findAll(pageable);
    }

    public List<Book> findAll(){
        return booksRepository.findAll();
    }


    public Book findOne(int id){
        Optional<Book> foundBook = booksRepository.findById(id);
        return foundBook.orElse(null);
    }

    @Transactional
    public void save(Book book){
        booksRepository.save(book);
    }

    @Transactional
    public void update(Book updatedBook, int id){
        updatedBook.setId(id);
        booksRepository.save(updatedBook);
    }

    @Transactional
    public void assign(int id, Person selectedPerson){
        LocalDateTime currentDate = LocalDateTime.now();
        LocalDateTime returnDate = currentDate.plusDays(10);
        Optional<Book> optionalBook = booksRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            Optional<Person> optionalPerson = peopleRepository.findById(selectedPerson.getId());
            if (optionalPerson.isPresent()) {
                Person person = optionalPerson.get();
                book.setOwner(person);
                book.setDateTakeBook(currentDate);
                book.setDateReturnBook(returnDate);
                booksRepository.save(book);
            }
        }
    }


    @Transactional
    public void release(int id){
       Optional<Book> optionalBook = booksRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setOwner(null);
        }
    }

    @Transactional
    public Optional<Person> getBookOwner(int id) {
        Optional<Book> book = booksRepository.findById(id);
        return book.map(Book::getOwner);
    }


    @Transactional
    public void delete(int id){
        booksRepository.deleteById(id);
    }

    public List<Book> search(String title) {
        return booksRepository.findByTitleLike(title);

    }

    @Transactional
    public boolean isBookOverdue(int id) {
        Optional<Book> optionalBook = booksRepository.findByOwnerId(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            LocalDateTime currentDate = LocalDateTime.now();
            LocalDateTime returnDate = book.getDateReturnBook();
            return currentDate.isAfter(returnDate);
        }
        return false;
    }
}
