package com.ddd.basic.infrastructure.circle;

import com.ddd.basic.domain.model.circle.Circle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface JpaCircleRepository extends JpaRepository<Circle, Long> {

    Optional<Circle> findByName(String name);

    @Query("select c " +
            "from Circle c " +
            "where c.name like concat('%', :keyword, '%')")
    List<Circle> search(@Param("keyword") String keyword, Pageable pageInfo);
}
