package org.example.controllers;

import org.example.models.Book;
import org.example.models.Person;
import org.example.services.BooksService;
import org.example.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PeopleService peopleService;
    private final BooksService booksService;

    @Autowired
    public PeopleController(PeopleService peopleService, BooksService booksService) {
        this.peopleService = peopleService;
        this.booksService = booksService;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("people", peopleService.findAll());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable("id") int id){
        model.addAttribute("person",peopleService.findOne(id));
        model.addAttribute("book",peopleService.getBookByPerson(id));
        model.addAttribute("isOverdue", booksService.isBookOverdue(id));
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person")Person person){
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "people/new";
        }
        peopleService.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("person",peopleService.findOne(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person updetedPerson, BindingResult bindingResult,
                         @PathVariable("id")int id){
        if (bindingResult.hasErrors()){
            return "people/edit";
        }
        peopleService.update(updetedPerson,id);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id")int id){
        peopleService.delete(id);
        return "redirect:/people";
    }
}
