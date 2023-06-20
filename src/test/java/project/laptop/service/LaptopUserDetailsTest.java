package project.laptop.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import project.laptop.model.entity.UserEntity;
import project.laptop.model.entity.UserRoleEntity;
import project.laptop.model.entity.enums.UserRoleEnum;
import project.laptop.model.user.LaptopUserDetails;
import project.laptop.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LaptopUserDetailsTest {
    @Mock
    private UserRepository mockUserRepository;

    private LaptopUserDetailsService toTest;

    @BeforeEach
    void setUp() {
        toTest = new LaptopUserDetailsService(mockUserRepository);
    }

    @Test
    void testLoadUserByUsername_UserExists() {
        //arrange
        UserEntity testUserEntity = new UserEntity()
                .setEmail("test@example.com")
                .setFirstName("Pesho")
                .setLastName("Petrov")
                .setImageUrl("http://image/com/image")
                .setPassword("12345")
                .setUserRoles(List.of(
                        new UserRoleEntity(UserRoleEnum.USER).setUserRole(UserRoleEnum.ADMIN)
                        , new UserRoleEntity(UserRoleEnum.USER).setUserRole(UserRoleEnum.USER)));


        when(mockUserRepository.findByEmail(testUserEntity.getEmail())).thenReturn(Optional.of(testUserEntity));

        //act -> type casted :D
        LaptopUserDetails userDetails = (LaptopUserDetails)
                toTest.loadUserByUsername(testUserEntity.getEmail());

        //assert
        Assertions.assertEquals(testUserEntity.getEmail(),
                userDetails.getUsername());

        Assertions.assertEquals(testUserEntity.getFirstName(), userDetails.getFirstName());
        Assertions.assertEquals(testUserEntity.getLastName(), userDetails.getLastName());
        Assertions.assertEquals(testUserEntity.getPassword(), userDetails.getPassword());
        Assertions.assertEquals(testUserEntity.getFirstName()+" "+testUserEntity.getLastName()
                ,userDetails.getFullName());


        var authorities=userDetails.getAuthorities();
        Assertions.assertEquals(2,authorities.size());

        var authoritiesIter=authorities.iterator();

        Assertions.assertEquals("ADMIN",UserRoleEnum.ADMIN.name(),
                authoritiesIter.next().getAuthority());

        Assertions.assertEquals("USER",UserRoleEnum.USER.name(),
                authoritiesIter.next().getAuthority());
    }

    @Test
    void testLoadUserByUsername_UserDoesNotExist() {
        //arrange
        //NOTE: no need to arrange anything, mocks return empty optionals

        //act and assert
        Assertions.assertThrows(UsernameNotFoundException.class,
                ()->toTest.loadUserByUsername("non-existant@example.com"));


    }
}
