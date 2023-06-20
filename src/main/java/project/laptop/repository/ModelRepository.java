package project.laptop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.laptop.model.entity.ModelEntity;

@Repository
public interface ModelRepository extends JpaRepository<ModelEntity,Long> {
}
