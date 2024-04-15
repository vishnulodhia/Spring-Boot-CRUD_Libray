package com.springJPA.Library.Service;

import com.springJPA.Library.Modal.Book;
import com.springJPA.Library.Modal.IssueBook;
import com.springJPA.Library.Modal.UserDetail;
import com.springJPA.Library.repo.Bookrepo;
import com.springJPA.Library.repo.Issuerepo;
import com.springJPA.Library.repo.Userrepo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
@NoArgsConstructor

public class IssueBookService {
    @Autowired
    private Issuerepo IssueBookRepository;
    @Autowired
    private Bookrepo bookRepository;
    @Autowired
    private Userrepo userRepository;

    public List<IssueBook> getAllBook(){
        return IssueBookRepository.findAll();
    }
//    public ResponseEntity<IssueBook> getIssueBookById(@PathVariable Long id) {
//        Optional<IssueBook> issueBookObj = issueRepository.findById(id);
//        if (issueBookObj.isPresent()) {
//            return new ResponseEntity<>(issueBookObj.get(), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

    public Optional<IssueBook> getIssueById(Long id){
        return IssueBookRepository.findById(id);
    }

//    @PostMapping("/addIssueBook")
//    public ResponseEntity<IssueBook> addIssueBook(@RequestBody IssueBook issue) {
//        try {
//            changeStatus(issue.getBook().getBook_id());
//            changeNo(issue.getUser().getUser_id());
//            IssueBook IssueBookObj = (IssueBook) issueRepository.save(issue);
//
//            return new ResponseEntity<>(IssueBookObj, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    public IssueBook addIssue(IssueBook issue,Long id){
        changeStatus(issue.getBook().getBook_id());
//        AddIssueBookUser(issue);
        IssueBook issuetemp = IssueBookRepository.save(issue);
        AddIssueBookUser(issue);
         return issuetemp;
    }

    public void AddIssueBookUser(IssueBook issue){

        Optional<UserDetail> userData = userRepository.findById(issue.getUser().getUser_id());
        Book book = bookRepository.findById(issue.getBook().getBook_id()).get();
        book.setIssue_id(issue.getIssueBook_id());

        if(userData.isPresent()) {
            UserDetail userObj =  userData.get();
            List<Book> bookList =userObj.getBookdata();
            bookList.add(book);
            userObj.setBookdata(bookList);
            userRepository.save(userObj);
        }

    }


    public  void changeStatus(Long id){
        try {
            Bookrepo tempBookRepo = bookRepository;
            Optional<Book> bookData = bookRepository.findById(id);
            if (bookData.isPresent()) {
                Book updatedBookData = bookData.get();
                updatedBookData.setStatus("Issued");
                bookRepository.save(updatedBookData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

//    @PostMapping("/updateIssueBook/{id}")
//    public ResponseEntity<IssueBook> updateIssueBook(@PathVariable Long id, @RequestBody IssueBook issueBook) {
//        try {
//            Optional<IssueBook> issueBookData =  issueRepository.findById(id);
//            if (issueBookData.isPresent()) {
//                IssueBook updatedIssueBookDetail= issueBookData.get();
//                updatedIssueBookDetail.setIssue_date(issueBook.getIssue_date());
//                updatedIssueBookDetail.setReturn_date(issueBook.getReturn_date());
//                IssueBook issueBookObj  = (IssueBook) issueRepository.save(updatedIssueBookDetail);
//
//                return new ResponseEntity<>(issueBookObj, HttpStatus.CREATED);
//            }
//
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//
//    }

    public IssueBook updateIssue(Long id,IssueBook issueBook){

//        Optional<Book> bookData = bookRepository.findById(id);
//        Book  updatedBookData=null;
//        if (bookData.isPresent()) {
//            updatedBookData = bookData.get();
//            updatedBookData.setBookname(book.getBookname());
//            updatedBookData.setAuthor(book.getAuthor());
//            updatedBookData.setStatus(book.getStatus());
//            updatedBookData.setUpdate_by("Vishnu");
//        }
//        return updatedBookData;
        Optional<IssueBook> IssueData = IssueBookRepository.findById(id);
        IssueBook updateissueData = null;
        if(IssueData.isPresent()){
            updateissueData = IssueData.get();
            updateissueData.setIssue_date(issueBook.getIssue_date());
            updateissueData.setReturn_date(issueBook.getReturn_date());
            updateissueData.setUpdate_by("Vishnu");
        }
    return updateissueData;

    }


    public void  deleteIssueBookById(Long id){
        IssueBookRepository.deleteById(id);
    }

    public void deleteAll(){
        IssueBookRepository.deleteAll();
    }

}
