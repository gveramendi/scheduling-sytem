package com.example.schedulingsystem.specification;

import com.example.schedulingsystem.domain.Student;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

public class StudentSearchSpecification implements Specification<Student> {

  private final String keyword;

  public StudentSearchSpecification(String keyword) {
    this.keyword = keyword;
  }


  @Override
  public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> criteriaQuery,
      CriteriaBuilder criteriaBuilder) {
    if (StringUtils.isBlank(this.keyword)) {
      return criteriaBuilder.conjunction();
    }

    String pattern = "%" + this.keyword.toLowerCase() + "%";

    return criteriaBuilder.or(
        criteriaBuilder.like(criteriaBuilder.lower(root.get("firstName")), pattern),
        criteriaBuilder.like(criteriaBuilder.lower(root.get("lastName")), pattern)
    );
  }
}
