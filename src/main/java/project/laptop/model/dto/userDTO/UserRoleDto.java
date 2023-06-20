package project.laptop.model.dto.userDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import project.laptop.model.entity.enums.UserRoleEnum;

public class UserRoleDto {
    @NotBlank(message = "Please select a user.")
    private String userEmail;

    @NotNull(message = "Please select a role.")
    private UserRoleEnum userRole;

    public UserRoleDto() {
    }

    public String getUserEmail() {
        return userEmail;
    }

    public UserRoleDto setUserEmail(String userEmail) {
        this.userEmail = userEmail;
        return this;
    }

    public UserRoleEnum getUserRole() {
        return userRole;
    }

    public UserRoleDto setUserRole(UserRoleEnum userRole) {
        this.userRole = userRole;
        return this;
    }
}
