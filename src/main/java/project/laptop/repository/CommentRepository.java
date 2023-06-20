package project.laptop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.laptop.model.entity.CommentEntity;
import project.laptop.model.entity.OfferEntity;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<CommentEntity,Long> {
    Optional<List<CommentEntity>> findAllByOffer(OfferEntity offer);

    Optional<CommentEntity> findById(Long id);


}
