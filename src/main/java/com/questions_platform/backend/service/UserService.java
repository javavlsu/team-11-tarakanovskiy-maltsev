package com.questions_platform.backend.service;

import com.questions_platform.backend.domain.Role;
import com.questions_platform.backend.domain.StudentGroup;
import com.questions_platform.backend.domain.User;
import com.questions_platform.backend.repository.StudentGroupRepository;
import com.questions_platform.backend.repository.UserRepository;
import com.questions_platform.backend.util.UserNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final StudentGroupRepository groupRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       StudentGroupRepository groupRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean isUserExist(User user){
        Optional<User> dbUser = userRepository.findByUsername(user.getUsername());
        return dbUser.isEmpty();
    }

    public void createStudent(User user){
        if (isUserExist(user)){
            return;
        }
        user.setRoles(Collections.singleton(Role.STUDENT));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        save(user);
    }

    public void createTeacher(User user){
        if (isUserExist(user)){
            return;
        }
        user.setRoles(Collections.singleton(Role.TEACHER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        save(user);
    }

    public User findById(Long id){
        return userRepository.findById(id)
                .orElseThrow( () -> new UserNotFoundException(id));
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public void addGroup(Long userId, Long groupId){
        StudentGroup group = groupRepository.findById(groupId)
                .orElseThrow();
        User user = findById(userId);
        user.setGroup(group);
        save(user);
    }

    public void changePassword(String password, Long id){
        User user = findById(id);
        user.setPassword(passwordEncoder.encode(password));
        save(user);
    }

    public void save(User user){
        userRepository.save(user);
    }
}
