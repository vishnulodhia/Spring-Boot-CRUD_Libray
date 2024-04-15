package com.springJPA.Library.Controller;


import com.springJPA.Library.Modal.Book;
import com.springJPA.Library.Modal.IssueBook;
import com.springJPA.Library.Modal.UserDetail;
import com.springJPA.Library.Service.IssueBookService;
import com.springJPA.Library.repo.Bookrepo;
import com.springJPA.Library.repo.Issuerepo;
import com.springJPA.Library.repo.Userrepo;
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
@RequestMapping("/issueBook")
public class IssueBookController {

    private IssueBookService Issuebook;

    @GetMapping("/getAllIssueBook")
    public ResponseEntity<List<IssueBook>> getAllIssueBook(){
        try {
            List<IssueBook> UserList = Issuebook.getAllBook();


            if (UserList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(UserList, HttpStatus.OK);
        } catch(Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @GetMapping("/getIssueBookById/{id}")
    public ResponseEntity<IssueBook> getIssueBookById(@PathVariable Long id) {
        Optional<IssueBook> issueBookObj = Issuebook.getIssueById(id);
        if (issueBookObj.isPresent()) {
            return new ResponseEntity<>(issueBookObj.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    @PostMapping("/addIssueBook")
    public ResponseEntity<IssueBook> addIssueBook(@RequestBody IssueBook issue) {
        try {
            IssueBook IssueBookObj = Issuebook.addIssue(issue,issue.getUser().getUser_id());

            return new ResponseEntity<>(IssueBookObj, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/updateIssueBook/{id}")
    public ResponseEntity<IssueBook> updateIssueBook(@PathVariable Long id, @RequestBody IssueBook issueBook) {
        try {
            IssueBook issueBookData =  Issuebook.updateIssue(id,issueBook);
            if (issueBookData==null)
                return new ResponseEntity<>(issueBookData, HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(issueBookData, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }


    @DeleteMapping("/deleteIssueBookById/{id}")
    public ResponseEntity<HttpStatus> deleteIssueBook(@PathVariable Long id) {
        try {
            Issuebook.deleteIssueBookById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/deleteAllIssueBook")
    public ResponseEntity<HttpStatus> deleteAllIssueBooks() {
        try {
            Issuebook.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
