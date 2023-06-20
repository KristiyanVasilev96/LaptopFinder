package project.laptop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.laptop.model.entity.UserRoleEntity;
import project.laptop.model.entity.enums.UserRoleEnum;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity,Long> {

    UserRoleEntity findByUserRole(UserRoleEnum user);
}
