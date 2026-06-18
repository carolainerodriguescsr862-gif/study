package com.example.study.domain.service;

import com.example.study.api.dto.UserRequestDTO;
import com.example.study.api.dto.UserResponseDTO;
import com.example.study.api.mapper.UserMapper;
import com.example.study.domain.entity.User;
import com.example.study.domain.exception.BusinessException;
import com.example.study.domain.exception.ResourceNotFoundException;
import com.example.study.domain.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private User findByIdOrThrow(String id){
        return  userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    public UserResponseDTO create(UserRequestDTO dto){
        boolean exists = userRepository.existsByName(dto.getName());
        if(exists){
            throw new BusinessException("There is already a user with that name!");
        }

        String cleanPassword = dto.getPassword();
        String encryptedPassword = passwordEncoder.encode(cleanPassword);

        User user = new User();
        user.setName(dto.getName());
        user.setPassword(encryptedPassword);

        User saved = userRepository.save(user);
        return UserMapper.toDTO(saved);
    }
}
