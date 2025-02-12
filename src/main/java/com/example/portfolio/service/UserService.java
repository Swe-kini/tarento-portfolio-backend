package com.example.portfolio.service;

import com.example.portfolio.model.User;
import com.example.portfolio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

   
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User createUser(String name, String email, String phone, String linkedin, String github, String description, MultipartFile profilePic) throws IOException {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPhone(phone);
        user.setLinkedin(linkedin);
        user.setGithub(github);
        user.setDescription(description);
        if (profilePic != null && !profilePic.isEmpty()) {
            user.setProfilePic(profilePic.getBytes());
        }
        return userRepository.save(user);
    }

    
    public User updateUser(Long id, String name, String email, String phone, String linkedin, String github, String description, MultipartFile profilePic) throws IOException {
        return userRepository.findById(id).map(user -> {
            user.setName(name);
            user.setEmail(email);
            user.setPhone(phone);
            user.setLinkedin(linkedin);
            user.setGithub(github);
            user.setDescription(description);
            try {
                if (profilePic != null && !profilePic.isEmpty()) {
                    user.setProfilePic(profilePic.getBytes());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return userRepository.save(user);
        }).orElse(null);
    }

   
    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
