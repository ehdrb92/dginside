package com.donggyu.dginside.user;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public User createUser(UserJoinDTO userJoinDTO) {
            boolean isExistUser = userRepository.existsByUsername(userJoinDTO.getUsername());

            if (isExistUser) {
                throw new DataIntegrityViolationException("Username Dulicate");
            }

            String encodedPassword = bCryptPasswordEncoder.encode(userJoinDTO.getPassword());

            User newUser = new User();

            newUser.setUsername(userJoinDTO.getUsername());
            newUser.setPassword(encodedPassword);
            newUser.setName(userJoinDTO.getName());
            newUser.setEmail(userJoinDTO.getEmail());
            newUser.setNickname(userJoinDTO.getNickname());

            return userRepository.save(newUser);
    }

    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User Not Found"));
    }

    public boolean updateUser(Long id, UserUpdateDTO user) {
        userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User Not Found"));
        int updateCount = userRepository.updateUserById(id, user.getEmail());
        return updateCount == 1;
    }

    public void deleteUser(Long id) {
        userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User Not Found"));
        userRepository.deleteById(id);
    }
}
