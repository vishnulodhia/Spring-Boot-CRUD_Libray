package com.springJPA.Library.Controller;


//import com.springJPA.Library.Modal.Book;
//import com.springJPA.Library.Modal.IssueBook;
import com.springJPA.Library.Config.SpringSecurityConfig;
import com.springJPA.Library.Modal.UserDetail;
//import com.springJPA.Library.Service.IssueBookService;
import com.springJPA.Library.Service.UserServices;
//import com.springJPA.Library.repo.Bookrepo;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@AllArgsConstructor
@RestController
@RequestMapping("/user")

public class UserController {

    private UserServices userdetail;

    private SpringSecurityConfig secrityconfig;
    @GetMapping("/getAllUser")
    public ResponseEntity<List<UserDetail>> getAllUser(){
        try {
            List<UserDetail> UserList = userdetail.getUser();

            if (UserList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(UserList, HttpStatus.OK);
        } catch(Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



            @GetMapping("/getUserById/{id}")
    public ResponseEntity<UserDetail> getUserById(@PathVariable Long id) {
        try {
            Optional<UserDetail> userObj = userdetail.getUserDetailById(id);
            if (userObj.isPresent()) {
                return new ResponseEntity<>(userObj.get(), HttpStatus.OK);
            }
        }
        catch(Exception e){
            System.out.println(e);
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }




    @PostMapping("/addUser")
    public ResponseEntity<UserDetail> addUser(@RequestBody UserDetail user) {
        try {
            UserDetail userObj = userdetail.AddUser(user);
//            secrityconfig.setUsername(user.getUserName());
//            secrityconfig.setPassword(user.getPassword());

            return new ResponseEntity<>(userObj, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/updateUser/{id}")
    public ResponseEntity<UserDetail> updateUser(@PathVariable Long id, @RequestBody UserDetail user) {
        try {
            UserDetail userData = userdetail.updateUser(id,user);
            if (userData == null) {
                return new ResponseEntity<>(userData, HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(userData, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/deleteUserById/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id) {
        try {
            userdetail.deleteUserDetailById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/deleteAllUsers")
    public ResponseEntity<HttpStatus> deleteAllUsers() {
        try {
            userdetail.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
