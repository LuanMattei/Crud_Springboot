package com.example.crud.services;

import com.example.crud.Exception.userNotFoundException;
import com.example.crud.entities.User;
import com.example.crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class userServices {
    @Autowired
    private UserRepository rep;


    public List<User> listAll(){
        return (List<User>) rep.findAll();
    }

    public void save(User user) {
        rep.save(user);
    }

    public User get(Long id) throws userNotFoundException {
        rep.findById(id);
        Optional<User> userResult = rep.findById(id);
        if(userResult.isPresent()){
            return userResult.get();
        }
        throw new userNotFoundException("Could Not find any user with id> "+ id);
    }

    public void delete(long id) throws userNotFoundException {

        if(rep.countById(id) == null || rep.countById(id) == 0){
          throw new userNotFoundException("Could not find any user with ID");
        }
        rep.deleteById(id);
    }
}
