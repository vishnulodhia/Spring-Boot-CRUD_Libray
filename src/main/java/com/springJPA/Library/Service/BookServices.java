package com.springJPA.Library.Service;


//Feature like add book

import com.springJPA.Library.Modal.Book;
import com.springJPA.Library.repo.Bookrepo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
@NoArgsConstructor
public class BookServices {

   @Autowired
    private Bookrepo bookRepository;
    public List<Book> getBook(){
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id){
        return bookRepository.findById(id);
    }

    public Book AddBook(Book book){
        return bookRepository.save(book);
    }


    public Book UpdateBookById(Long id,Book book){
        Optional<Book> bookData = bookRepository.findById(id);
        Book  updatedBookData=null;
        if (bookData.isPresent()) {
             updatedBookData = bookData.get();
            updatedBookData.setBookname(book.getBookname());
            updatedBookData.setAuthor(book.getAuthor());
            updatedBookData.setStatus(book.getStatus());
            updatedBookData.setUpdate_by("Vishnu");
        }
return updatedBookData;
    }

    public List<Book>getUnissuedBook(){
        List<Book> AllBook =getBook();
        List<Book> unissuedBook = new ArrayList<>();
        for(Book BookObj : AllBook){
            if(BookObj.getStatus().equals("unissued"))
            unissuedBook.add(BookObj);
        }
     return unissuedBook;
    }
//    @DeleteMapping("/deleteBookById/{id}")
//    public ResponseEntity<HttpStatus> deleteBookById(@PathVariable Long id) {
//        try {
//            bookRepository.deleteById(id);
//            return new ResponseEntity<>(HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//    @DeleteMapping("/deleteAllBooks")
//    public ResponseEntity<HttpStatus> deleteAllBooks() {
//        try {
//            bookRepository.deleteAll();
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    public void  deleteBookById(Long id){
         bookRepository.deleteById(id);
    }

    public void deleteAll(){
        bookRepository.deleteAll();
    }




}
