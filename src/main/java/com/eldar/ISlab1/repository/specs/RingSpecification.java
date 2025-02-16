package com.eldar.ISlab1.repository.specs;

import com.eldar.ISlab1.domain.dto.request.RingRequest;
import com.eldar.ISlab1.domain.model.RingEntity;
import com.sun.tools.xjc.reader.Ring;
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
public class RingSpecification implements Specification<RingEntity> {
    private String name;
    private Integer power;
    private Float weight;

    @Override
    public Predicate toPredicate(@NonNull Root<RingEntity> root, CriteriaQuery<?> query, @NonNull CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (name != null && !name.isEmpty()) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
        }
        if (power != null) {
            predicates.add(criteriaBuilder.equal(root.get("power"), power));
        }
        if (weight != null) {
            predicates.add(criteriaBuilder.equal(root.get("weight"), weight));
        }

        // Combine predicates with AND
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

}
