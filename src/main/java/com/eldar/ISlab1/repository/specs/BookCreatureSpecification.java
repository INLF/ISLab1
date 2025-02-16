package com.eldar.ISlab1.repository.specs;

import com.eldar.ISlab1.domain.model.BookCreatureEntity;
import com.eldar.ISlab1.domain.model.BookCreatureType;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class BookCreatureSpecification implements Specification<BookCreatureEntity>  {
    private String name;
    private Integer age;
    private BookCreatureType type;
    private Long cityId;
    private Long ringId;

    @Override
    public Predicate toPredicate(@NonNull Root<BookCreatureEntity> root, CriteriaQuery<?> query, @NonNull CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (name != null && !name.isEmpty()) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
        }
        if (age != null) {
            predicates.add(criteriaBuilder.equal(root.get("age"), age));
        }
        if (type != null) {
            predicates.add(criteriaBuilder.equal(root.get("type"), type));
        }
        if (cityId != null) {
            predicates.add(criteriaBuilder.equal(root.get("city").get("id"), cityId));
        }
        if (ringId != null) {
            predicates.add(criteriaBuilder.equal(root.get("ring").get("id"), ringId));
        }

        // Combine predicates with AND
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

}
