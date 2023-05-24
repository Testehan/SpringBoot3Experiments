package com.testehan.springboot3.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public void addNewUser(User user) {
        System.out.println(user);
        Optional<User> result = userRepository.findUserByEmail(user.getEmail());
        if (result.isPresent()){
            throw new IllegalStateException("email taken");
        } else {
            userRepository.save(user);
        }

    }

    public void deleteUser(Long userId) {
        boolean isUserInDB = userRepository.existsById(userId);
        if (!isUserInDB){
            throw new IllegalStateException("user with id " + userId + " does not exist in the DB");
        } else {
            userRepository.deleteById(userId);
        }
    }

    @Transactional
    public void updateUser(Long userId, String name, String email) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()){
            User user = userOptional.get();
            if (name!=null && name.length()>0 && !Objects.equals(name, user.getName())) {
                user.setName(name);
            }
            if (email!=null && email.length()>0 && !Objects.equals(email, user.getEmail())) {
                user.setEmail(email);
            }
        } else {
            throw new IllegalStateException("user with id " + userId + " does not exist in the DB");
        }

    }
}
