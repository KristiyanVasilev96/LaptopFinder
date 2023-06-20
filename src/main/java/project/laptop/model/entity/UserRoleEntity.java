package project.laptop.model.entity;

import jakarta.persistence.*;
import project.laptop.model.entity.enums.UserRoleEnum;

import java.util.ArrayList;

@Entity
@Table(name = "user_roles")
public class UserRoleEntity extends BaseEntity{
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRoleEnum userRole;

    public UserRoleEntity(UserRoleEnum userRole) {
        this.userRole=userRole;
    }

    public UserRoleEntity() {

    }



    public UserRoleEnum getUserRole() {

        return userRole;
    }

    public UserRoleEntity setUserRole(UserRoleEnum userRole) {
        this.userRole = userRole;
        return this;
    }
}
