package org.example.services;

import org.example.models.Book;
import org.example.models.Person;
import org.example.repositories.BooksRepository;
import org.example.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {
    private final PeopleRepository peopleRepository;
    private final BooksRepository booksRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository, BooksRepository booksRepository) {
        this.peopleRepository = peopleRepository;
        this.booksRepository = booksRepository;
    }

    public List<Person> findAll(){
        return peopleRepository.findAll();
    }

    public Person findOne(int id){
        Optional<Person> foundPerson = peopleRepository.findById(id);
        return foundPerson.orElse(null);
    }

    @Transactional
    public void save(Person person){
        peopleRepository.save(person);
    }

    @Transactional
    public void update(Person updatedPerson,int id){
        updatedPerson.setId(id);
        peopleRepository.save(updatedPerson);
    }

    @Transactional
    public Optional<Person> getPersonByFullName(String fullName){
        return peopleRepository.findPersonByFullName(fullName);
    }

//    @Transactional
//    public List<Book> getBookByPersonId(int id){
//        return booksRepository.findPersonById(id);
//    }
    @Transactional
    public List<Book> getBookByPerson(int id) {
        Optional<Person> person = peopleRepository.findById(id);
        if (person.isPresent()) {
            Person person1 = person.get();
            return booksRepository.findByOwner(person1);
        }
        return null;
    }

    @Transactional
    public void delete(int id){
        peopleRepository.deleteById(id);
    }
}
