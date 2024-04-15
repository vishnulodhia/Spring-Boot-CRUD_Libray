package com.springJPA.Library.Service;

//import com.springJPA.Library.Modal.IssueBook;
import com.springJPA.Library.Modal.Book;
import com.springJPA.Library.Modal.UserDetail;
import com.springJPA.Library.repo.Userrepo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
@NoArgsConstructor
public class UserServices {
    @Autowired
    private Userrepo userRepository;
//    @GetMapping("/getAllUser")
//    public ResponseEntity<List<UserDetail>> getAllUser(){
//        try {
//            List<UserDetail> UserList = new ArrayList<>();
//            userRepository.findAll().forEach(UserList::add);
//
//            if (UserList.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//
//            return new ResponseEntity<>(UserList, HttpStatus.OK);
//        } catch(Exception ex) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    public List<UserDetail> getUser(){
        return  userRepository.findAll();
    }

    public Optional<UserDetail> getUserDetailById(Long id){
        return userRepository.findById(id);
    }

//    @PostMapping("/addUser")
//    public ResponseEntity<UserDetail> addUser(@RequestBody UserDetail user) {
//        try {
//            UserDetail userObj = (UserDetail) userRepository.save(user);
//            return new ResponseEntity<>(userObj, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    public UserDetail AddUser(UserDetail user){
         user.setBookdata(new ArrayList<Book>());
        return userRepository.save(user);
    }

   public UserDetail updateUser(Long id, UserDetail user){
       Optional<UserDetail> userData = userRepository.findById(id);
       UserDetail updatedUserData = null;
       if(userData.isPresent()){
           updatedUserData = userData.get();
           updatedUserData.setUserName(user.getUserName());
           updatedUserData.setPassword(user.getPassword());
           updatedUserData.setUpdate_by("Vishnu");
       }
       return updatedUserData;

   }


    public void  deleteUserDetailById(Long id){
        userRepository.deleteById(id);
    }

    public void deleteAll(){
        userRepository.deleteAll();
    }





}
