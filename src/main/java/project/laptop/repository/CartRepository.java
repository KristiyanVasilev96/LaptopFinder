package project.laptop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.laptop.model.entity.CartEntity;

public interface CartRepository extends JpaRepository<CartEntity,Long> {
}
