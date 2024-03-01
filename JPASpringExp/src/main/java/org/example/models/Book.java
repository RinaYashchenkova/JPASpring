package org.example.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
@Entity
@Table(name = "Book")
public class Book {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "title")
    @NotEmpty(message = "Название не может быть пустым")
    @Size(min = 2, max = 150,message = "Название должно состоять от 2 до 150 символов")
    private String title;
    @Column(name = "author")
    @NotEmpty(message = "Название не может быть пустым")
    @Size(min = 2, max = 100,message = "Название должно состоять от 2 до 150 символов")
    private String author;
    @Column(name = "date_of_publication")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date dateOfPublication;
    @ManyToOne
    @JoinColumn(name = "person_id",referencedColumnName = "id")
    private Person owner;

    @Column(name = "date_take_book")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime dateTakeBook;

    @Column(name = "date_return_book")
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDateTime dateReturnBook;

    public Book(){

    }

    public Book(int id, String title, String author, Date dateOfPublication) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.dateOfPublication = dateOfPublication;
    }

    public LocalDateTime getDateTakeBook() {
        return dateTakeBook;
    }

    public void setDateTakeBook(LocalDateTime dateTakeBook) {
        this.dateTakeBook = dateTakeBook;
    }

    public LocalDateTime getDateReturnBook() {
        return dateReturnBook;
    }

    public void setDateReturnBook(LocalDateTime dateReturnBook) {
        this.dateReturnBook = dateReturnBook;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getDateOfPublication() {
        return dateOfPublication;
    }

    public void setDateOfPublication(Date dateOfPublication) {
        this.dateOfPublication = dateOfPublication;
    }
}
