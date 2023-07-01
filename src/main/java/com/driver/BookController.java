package com.driver;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("books")
public class BookController {

    private List<Book> bookList;
    private int id;

    public List<Book> getBookList() {

        return bookList;
    }

    public void setBookList(List<Book> bookList) {

        this.bookList = bookList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BookController(){
        this.bookList = new ArrayList<Book>();
        this.id = 1;
    }

    // post request /create-book
    // pass book as request body
    @PostMapping("/create-book")
    public ResponseEntity createBook(@RequestBody Book book){
        // Your code goes here.
        if(bookList.contains(book)){
          return new ResponseEntity(book.getName(), HttpStatus.ALREADY_REPORTED);
        }
        bookList.add(book);
        return new ResponseEntity(book.getName(), HttpStatus.CREATED);
    }

    // get request /get-book-by-id/{id}
    // pass id as path variable
    // getBookById()

    @GetMapping("/get-book-by-id/{id}")
    public ResponseEntity getBookById(@PathVariable int id){
        if(bookList.contains(id) && id !=0){
            return new ResponseEntity(bookList.get(id), Objects.requireNonNull(HttpStatus.ALREADY_REPORTED));

        }

        return new ResponseEntity(bookList.get(id), HttpStatus.NOT_FOUND);


    }

    // delete request /delete-book-by-id/{id}
    // pass id as path variable
    // deleteBookById()

    @DeleteMapping("/delete-book-by-id/{id}")
    public  ResponseEntity deleteBookById(@PathVariable int id){

        if(bookList.contains(id) && id !=0){
            bookList.remove(id);
           return  new ResponseEntity(bookList.get(id), HttpStatus.GONE);

        }


        return new ResponseEntity(bookList.get(id),HttpStatus.NOT_FOUND);
    }




    // get request /get-all-books
    // getAllBooks()

    @GetMapping("/get-all-books")
    public ResponseEntity getAllBooks(){
        for(Book books : bookList){
            return new ResponseEntity(books,HttpStatus.FOUND);
        }
        return null;

    }

    // delete request /delete-all-books
    // deleteAllBooks()

    @DeleteMapping("/deletaAllBooks")
    public ResponseEntity deleAllBooks(){

        for(Book book: bookList){
            bookList.remove(book);

        }
        return new ResponseEntity( HttpStatus.ACCEPTED);
    }

    // get request /get-books-by-author
    // pass author name as request param
    // getBooksByAuthor()

    @GetMapping("/get-books-by-author")
    public ResponseEntity getBooksByAuthor(@RequestParam String authorName){

        Book book = new Book();

        book.setAuthor(authorName);
        if(bookList.contains(book.getAuthor())){
            return new ResponseEntity(book, HttpStatus.FOUND);
        }

        return new ResponseEntity(book, HttpStatus.NOT_FOUND);


    }


    // get request /get-books-by-genre
    // pass genre name as request param
    // getBooksByGenre()

    @GetMapping("/get-books-by-genre")
    public ResponseEntity getBooksByGenre(@RequestParam String generName){

        Book book = new Book();

        book.setGenre(generName);
        if(bookList.contains(book.getGenre())){
            return new ResponseEntity(book, HttpStatus.FOUND);
        }

        return new ResponseEntity(book, HttpStatus.NOT_FOUND);

    }
}
