package project.laptop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.laptop.model.entity.BrandEntity;

@Repository
public interface BrandRepository extends JpaRepository<BrandEntity,Long> {
}
