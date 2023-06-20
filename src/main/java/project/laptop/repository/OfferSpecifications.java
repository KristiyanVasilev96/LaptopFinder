package project.laptop.repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import project.laptop.model.dto.offerDTO.SearchOfferDto;
import project.laptop.model.entity.OfferEntity;

import java.util.ArrayList;
import java.util.List;

public class OfferSpecifications implements Specification<OfferEntity> {
    private final SearchOfferDto searchOfferDto;

    public OfferSpecifications(SearchOfferDto searchOfferDto) {
        this.searchOfferDto = searchOfferDto;
    }


    @Override
    public Predicate toPredicate(Root<OfferEntity> root,
                                 CriteriaQuery<?> query,
                                 CriteriaBuilder cb) {

        final List<Predicate> predicates = new ArrayList<>();

        if (searchOfferDto.getMinPrice() != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("price"), searchOfferDto.getMinPrice()));
        }

        if (searchOfferDto.getMaxPrice() != null) {
            predicates.add(cb.and(cb.lessThanOrEqualTo(root.get("price"), searchOfferDto.getMaxPrice())));
        }

        if (searchOfferDto.getModel() != null && !searchOfferDto.getModel().isEmpty()) {
            predicates.add(cb.and(cb.equal(root.join("model").get("name"), searchOfferDto.getModel())));
        }



        return cb.and(predicates.toArray(new Predicate[0]));
    }
}
