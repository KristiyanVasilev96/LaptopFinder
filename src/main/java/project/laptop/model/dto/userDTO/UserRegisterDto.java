package project.laptop.model.dto.userDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import project.laptop.model.entity.UserRoleEntity;
import project.laptop.model.entity.enums.UserRoleEnum;
import project.laptop.model.validation.FieldMatch;
import project.laptop.model.validation.UniqueEmail;

import java.util.List;

@FieldMatch(
        first1 = "password",
        second2 = "confirmPassword",
        message = "Passwords do not match!"
)
public class UserRegisterDto {

    @NotNull(message = "First name cannot be empty or null.")
    @Size(min = 2, max = 20, message = "First name length must be between 2 and 20 characters.")
    private String firstName;
    @NotNull(message = "Last name name cannot be empty or null.")
    @Size(min = 2, max = 20, message = "Last name length must be between 2 and 20 characters.")
    private String lastName;
    @Email(message = "User email should be valid.")
    @NotEmpty(message = "User email should be provided.")
    @UniqueEmail(message = "Email should be unique.")
    private String email;


    @NotNull(message = "Password cannot be empty or null.")
    @Size(min = 5,message = "Password must be at least 5 characters long.")
    private String password;
    private String confirmPassword;



    public UserRegisterDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public UserRegisterDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserRegisterDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegisterDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegisterDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegisterDto setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }

}

