package com.springJPA.Library.Controller;


import com.springJPA.Library.Modal.IssueBook;
import com.springJPA.Library.Modal.ReturnBook;
import com.springJPA.Library.Service.IssueBookService;
import com.springJPA.Library.Service.ReturnServices;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/returnBook")
public class ReturnController {

    private ReturnServices returnbook;


    @GetMapping("/getAllReturnBook")
    public ResponseEntity<List<ReturnBook>> getAllIssueBook(){
        try {
            List<ReturnBook> returnList = returnbook.getAllBook();


            if (returnList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(returnList, HttpStatus.OK);
        } catch(Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/addReturnBook")
    public ResponseEntity<ReturnBook> addReturnBook(@RequestBody ReturnBook retur) {
        try {
            ReturnBook ReturnBookObj = returnbook.addReturn(retur);

            return new ResponseEntity<>(ReturnBookObj, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteAllUsers")
    public ResponseEntity<HttpStatus> deleteAllUsers() {
        try {
            returnbook.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
