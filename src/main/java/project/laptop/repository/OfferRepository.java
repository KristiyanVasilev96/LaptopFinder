package project.laptop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import project.laptop.model.entity.OfferEntity;



@Repository
public interface OfferRepository extends JpaRepository<OfferEntity,Long>,
        JpaSpecificationExecutor<OfferEntity> {

}
