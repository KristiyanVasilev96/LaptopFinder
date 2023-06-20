package project.laptop.model.dto.userDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import project.laptop.model.validation.FieldMatch;
import project.laptop.model.validation.UniqueEmail;
@FieldMatch(
        first1 = "email",
        second2 = "confirmEmail",
        message = "Emails do not match!"
)
public class UpdateEmailDTO {
    private String currentEmail;
    @Email(message = "You must enter a valid email!")
    @UniqueEmail(message = "Email must be unique!")
    @NotNull(message = "Please enter email!")
    @NotEmpty(message = "Please enter email!")
    private String email;

    private String confirmEmail;


    public UpdateEmailDTO() {
    }

    public String getCurrentEmail() {
        return currentEmail;
    }

    public UpdateEmailDTO setCurrentEmail(String currentEmail) {
        this.currentEmail = currentEmail;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UpdateEmailDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getConfirmEmail() {
        return confirmEmail;
    }

    public UpdateEmailDTO setConfirmEmail(String confirmEmail) {
        this.confirmEmail = confirmEmail;
        return this;
    }
}
