package com.springJPA.Library.Service;


import com.springJPA.Library.Modal.Book;
import com.springJPA.Library.Modal.IssueBook;
import com.springJPA.Library.Modal.ReturnBook;
import com.springJPA.Library.Modal.UserDetail;
import com.springJPA.Library.repo.Bookrepo;
import com.springJPA.Library.repo.Issuerepo;
import com.springJPA.Library.repo.Returnrepo;
import com.springJPA.Library.repo.Userrepo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Calendar;
import java.util.List;
import java.util.Date;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ReturnServices {

    @Autowired
    private Returnrepo returnBookRepository;
    @Autowired
    private Bookrepo bookRepository;
    @Autowired
    private Userrepo userRepository;
    @Autowired
    private Issuerepo issueRepository ;


    public List<ReturnBook> getAllBook(){
        return returnBookRepository.findAll();
    }
    public ReturnBook addReturn(ReturnBook retur){
        removebook(retur);
        calculateFine(retur);
        changeStatus(retur.getBook().getBook_id());
        return returnBookRepository.save(retur);
    }

    public void removebook(ReturnBook retur){
        UserDetail userObj = userRepository.findById(retur.getUser().getUser_id()).get();
        List<Book> book = userObj.getBookdata();
        for (int i = 0; i <book.size() ; i++) {
            if(book.get(i).getBook_id()==retur.getBook().getBook_id())
                book.remove(i);


        }
        userObj.setBookdata(book);
    }
    public  void changeStatus(Long id){
        try {
            Bookrepo tempBookRepo = bookRepository;
            Optional<Book> bookData = bookRepository.findById(id);
            if (bookData.isPresent()) {
                Book updatedBookData = bookData.get();
                updatedBookData.setStatus("unIssued");
                updatedBookData.setIssue_id(0);
                bookRepository.save(updatedBookData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void calculateFine(ReturnBook retur){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 12);
        Timestamp date1 =  new Timestamp(calendar.getTimeInMillis());
        Timestamp date2 = issueRepository.findById(bookRepository.findById(retur.getBook().getBook_id()).get().getIssue_id()).get().getIssue_date();
//        System.out.println(issueRepository.findById(bookRepository.findById(retur.getBook().getBook_id()).get().getIssue_id()).get().getIssue_date());
         long diff = date1.getTime()-date2.getTime();
        long betweenDay= (diff / (1000 * 60 * 60 * 24))-8;
        System.out.println(betweenDay);
        UserDetail userObj = userRepository.findById(retur.getUser().getUser_id()).get();
        if(betweenDay<=0)
         return;
        System.out.println(userObj);
        userObj.setFine(betweenDay*50);
        if(userObj.getTotalFine()==null)
            userObj.setTotalFine((long)0);
        long totalfine = userObj.getTotalFine() + betweenDay*50;
        userObj.setTotalFine(totalfine);
        userRepository.save(userObj);
    }

    public void deleteAll(){
        returnBookRepository.deleteAll();
    }



}
