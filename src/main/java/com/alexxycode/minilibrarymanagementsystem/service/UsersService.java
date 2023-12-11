package com.alexxycode.minilibrarymanagementsystem.service;

import com.alexxycode.minilibrarymanagementsystem.exceptionHandler.NotFoundException;
import com.alexxycode.minilibrarymanagementsystem.model.Users;
import com.alexxycode.minilibrarymanagementsystem.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {


    private final UsersRepository usersRepository;
    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }


    @Cacheable(value = "getallusers")
    public List<Users> getAllUsers(){
        return usersRepository.findAll();
    }

    @CacheEvict(value = "getallusers",allEntries = true)
    public Users addUser(Users users){
        return usersRepository.save(users);
    }
    @CacheEvict(value = "getallusers",allEntries = true)
    public String deleteUser(int id){
        Users userToBeDeleted = usersRepository.findById(id).orElseThrow(()->new NotFoundException("No user with such ID exists"));
       // if (userToBeDeleted != null)
            usersRepository.deleteById(id);
            return String.format("the user with 'fullname' %s and ID '%d' was deleted successfully",
                    userToBeDeleted.getFullName(),userToBeDeleted.getId());


    }
    @CacheEvict(value = "getallusers",allEntries = true)
    public String updateUser(int id, Users user){

       if( usersRepository.findById(id).isPresent()){
           Users userToUpdate = findById(id).getBody();
           assert userToUpdate != null;
           userToUpdate.setFullName(user.getFullName());
           userToUpdate.setAge(user.getAge());
           userToUpdate.setAddress(user.getAddress());
           userToUpdate.setEmail(user.getEmail());
           usersRepository.save(userToUpdate);
           return ("user successfully updated");
       }
       return String.format("No user with this id '%d' exists",id);
    }

    public ResponseEntity<Users> findById(int id){
        Users users = (usersRepository.findById(id).orElseThrow(()-> new NotFoundException("user does not exist")));

        return ResponseEntity.ok(users);
    }

    public List<Users> findUserByFullName(String fullName){
        List<Users> usersList = usersRepository.findByFullName(fullName);
        if (usersList.isEmpty())
            throw(new NotFoundException(String.format("Sorry no user with (%s) as fullname exists",fullName)));
        return usersList;
    }

    public Users findUserByEmail(String email){
        return usersRepository.findByEmail(email).orElseThrow(()->
                new NotFoundException("Sorry email ("+email+") does not exist"));
    }

}
