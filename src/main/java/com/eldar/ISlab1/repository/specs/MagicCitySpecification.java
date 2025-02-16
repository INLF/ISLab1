package com.eldar.ISlab1.repository.specs;

import com.eldar.ISlab1.domain.model.BookCreatureType;
import com.eldar.ISlab1.domain.model.MagicCityEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.*;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class MagicCitySpecification implements Specification<MagicCityEntity> {
    private String name;
    private Integer area;
    private Integer population;
    private LocalDate establishmentAt;
    private Integer populationDensity;
    private Boolean isCapital = false;
    private BookCreatureType governorType;

    @Override
    public Predicate toPredicate(@NonNull Root<MagicCityEntity> root, CriteriaQuery<?> query, @NonNull CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if (name != null) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name + "%"));
        }
        if (area != null) {
            predicates.add(criteriaBuilder.equal(root.get("area"), area));
        }
        if (population != null) {
            predicates.add(criteriaBuilder.equal(root.get("population"), population));
        }
        if (establishmentAt != null) {
            predicates.add(criteriaBuilder.equal(root.get("establishmentAt"), establishmentAt));
        }
        if (populationDensity != null) {
            predicates.add(criteriaBuilder.equal(root.get("populationDensity"), populationDensity));
        }
        if (isCapital != null) {
            predicates.add(criteriaBuilder.equal(root.get("isCapital"), isCapital));
        }
        if (governorType != null) {
            predicates.add(criteriaBuilder.equal(root.get("governorType"), governorType));
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }


}
