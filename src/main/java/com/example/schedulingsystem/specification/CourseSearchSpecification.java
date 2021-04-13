package com.example.schedulingsystem.specification;

import com.example.schedulingsystem.domain.Course;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

public class CourseSearchSpecification implements Specification<Course> {

  private final String keyword;

  public CourseSearchSpecification(String keyword) {
    this.keyword = keyword;
  }

  @Override
  public Predicate toPredicate(Root<Course> root, CriteriaQuery<?> criteriaQuery,
      CriteriaBuilder criteriaBuilder) {
    if (StringUtils.isBlank(this.keyword)) {
      return criteriaBuilder.conjunction();
    }

    String pattern = "%" + this.keyword.toLowerCase() + "%";

    return criteriaBuilder.or(
        criteriaBuilder.like(criteriaBuilder.lower(root.get("code")), pattern),
        criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), pattern),
        criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), pattern)
    );
  }
}
