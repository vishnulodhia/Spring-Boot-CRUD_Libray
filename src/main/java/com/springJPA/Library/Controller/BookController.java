package com.springJPA.Library.Controller;


import com.springJPA.Library.Modal.Book;
import com.springJPA.Library.Service.BookServices;
import com.springJPA.Library.repo.Bookrepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@AllArgsConstructor
@RestController
@RequestMapping("/book")
public class BookController {

   private BookServices bookServices;

        @GetMapping("/getAllBooks")
    public ResponseEntity<List<Book>> getAllBooks() {
//        try {
//            List<Book> bookList =
//            bookRepository.findAll();
//
//            if (bookList.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//
//            return new ResponseEntity<>(bookList, HttpStatus.OK);
//        } catch(Exception ex) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }


            try {
                List<Book>  BookList = bookServices.getBook();

                if (BookList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
                return new ResponseEntity<>(BookList, HttpStatus.OK);
            }
            catch(Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getUnissuedBook")
    public ResponseEntity<List<Book>> getAllUnissuedBook() {
//        try {
//            List<Book> bookList =
//            bookRepository.findAll();
//
//            if (bookList.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//
//            return new ResponseEntity<>(bookList, HttpStatus.OK);
//        } catch(Exception ex) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }


        try {
            List<Book>  BookList = bookServices.getUnissuedBook();

            if (BookList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(BookList, HttpStatus.OK);
        }
        catch(Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getBookById/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
//        Optional<Book> bookObj = bookRepository.findById(id);
//        if (bookObj.isPresent()) {
//            return new ResponseEntity<>(bookObj.get(), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }

        try {
            Optional<Book> book = bookServices.getBookById(id);

            if (book.isPresent()) {
                return new ResponseEntity<>(book.get(), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/addBook")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
//        try {
//            Book bookObj = (Book) bookRepository.save(book);
//            return new ResponseEntity<>(bookObj, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }

        try{
           Book bookobj = bookServices.AddBook(book);
           return new ResponseEntity<>(bookobj,HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/updateBook/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
      Book BookObj = bookServices.UpdateBookById(id,book);
      try {
          if (BookObj == null)
              return new ResponseEntity<>(BookObj, HttpStatus.NO_CONTENT);
          return new ResponseEntity<>(BookObj, HttpStatus.CREATED);
      }
      catch (Exception e){
          return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }

    }

    @DeleteMapping("/deleteBookById/{id}")
    public ResponseEntity<HttpStatus> deleteBookById(@PathVariable Long id) {
//        try {
//            bookRepository.deleteById(id);
//            return new ResponseEntity<>(HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }

        try{
            bookServices.deleteBookById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        }
    @DeleteMapping("/deleteAllBooks")
    public ResponseEntity<HttpStatus> deleteAllBooks() {
//        try {
//            bookRepository.deleteAll();
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
        try{
            bookServices.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }



    }







}