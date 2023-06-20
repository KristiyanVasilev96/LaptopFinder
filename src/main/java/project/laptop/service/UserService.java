package project.laptop.service;

import org.modelmapper.ModelMapper;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import project.laptop.exceptions.ObjectNotFoundException;
import project.laptop.model.dto.userDTO.UpdateEmailDTO;
import project.laptop.model.dto.userDTO.UserRegisterDto;
import project.laptop.model.dto.userDTO.UserRoleDto;
import project.laptop.model.entity.UserEntity;
import project.laptop.model.entity.UserRoleEntity;
import project.laptop.model.entity.enums.UserRoleEnum;
import project.laptop.repository.UserRepository;
import project.laptop.repository.UserRoleRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final UserDetailsService userDetailsService;
    private final UserRoleRepository userRoleRepository;

    public UserService(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, EmailService emailService, UserDetailsService userDetailsService, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;

        this.emailService = emailService;
        this.userDetailsService = userDetailsService;
        this.userRoleRepository = userRoleRepository;
    }


    public void createUserIfNotExist(String email) { //facebook
        var userOpt = userRepository.findByEmail(email);

        if (userOpt.isEmpty()) {
            var newUser = new UserEntity()
                    .setEmail(email)
                    .setPassword(null).setFirstName("New")
                    .setLastName("User")
                    .setPassword("12345");

            UserRoleEntity userRoleEntity = userRoleRepository.findByUserRole(UserRoleEnum.USER);
            newUser.getUserRoles().add(userRoleEntity);

            userRepository.save(newUser);


        }
    }

    public void registerAndLogin(UserRegisterDto userRegisterDto, Locale preferredLocale) {

        UserEntity newUser = modelMapper.map(userRegisterDto, UserEntity.class)
                .setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));

        UserRoleEntity userRoleEntity = userRoleRepository.findByUserRole(UserRoleEnum.USER);
        newUser.getUserRoles().add(userRoleEntity);


        userRepository.save(newUser);
        login(newUser.getEmail());
        emailService.sendRegistrationEmail(newUser.getEmail(), newUser.getFirstName()
                + " " + newUser.getLastName(), preferredLocale);

    }

    public void login(String userName) {
        UserDetails userDetails =
                userDetailsService.loadUserByUsername(userName);

        Authentication auth =
                new UsernamePasswordAuthenticationToken(
                        userDetails,
                        userDetails.getPassword(),
                        userDetails.getAuthorities()
                );

        SecurityContextHolder.getContext().setAuthentication(auth);
    }


    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new ObjectNotFoundException("User not found with email: " + email));
    }


    public void updateEmail(UpdateEmailDTO updateEmailDTO) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(() -> new ObjectNotFoundException("Email not found what to do now:("));


        modelMapper.map(updateEmailDTO, userEntity);
        userRepository.save(userEntity);

        Authentication newAuth = new UsernamePasswordAuthenticationToken(updateEmailDTO.getEmail(), auth.getCredentials(), auth.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(newAuth);
        login(updateEmailDTO.getEmail());

    }


    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public void changeUserRole(UserRoleDto userRoleDto) {
        UserEntity user = userRepository.findByEmail(userRoleDto.getUserEmail()).orElse(null);

        UserRoleEntity roleEntity = new UserRoleEntity(UserRoleEnum.USER);
        roleEntity.setUserRole(userRoleDto.getUserRole());
        roleEntity = userRoleRepository.save(roleEntity);

        List<UserRoleEntity> roles = new ArrayList<>();
        roles.add(roleEntity);

        user.setUserRoles(roles);
        userRepository.save(user);

    }


    public String getUserRole(String email) {
        UserEntity user = findByEmail(email);
        UserRoleEntity userRole = user.getUserRoles().stream().findFirst().orElseThrow(() -> new RuntimeException("User has no roles"));
        return userRole.getUserRole().name();
    }
}


