package com.example.analyticsdashboard.Repo;

import com.example.analyticsdashboard.Entity.FeatureClick;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface FeatureClickRepo extends JpaRepository<FeatureClick, Long> {

    @Query("""
        SELECT f.featureName, COUNT(f)
        FROM FeatureClick f
        JOIN f.user u
        WHERE (:gender IS NULL OR u.gender = :gender)
        AND (:minAge IS NULL OR u.age >= :minAge)
        AND (:maxAge IS NULL OR u.age <= :maxAge)
        AND (:startDate IS NULL OR f.timestamp >= :startDate)
        AND (:endDate IS NULL OR f.timestamp <= :endDate)
        GROUP BY f.featureName
    """)
    List<Object[]> getFilteredBarData(
            @Param("gender") String gender,
            @Param("minAge") Integer minAge,
            @Param("maxAge") Integer maxAge,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );


    @Query("""
    SELECT DATE(f.timestamp), COUNT(f)
    FROM FeatureClick f
    JOIN f.user u
    WHERE  
     (:startDate IS NULL OR f.timestamp >= :startDate)
    AND (:endDate IS NULL OR f.timestamp < :endDatePlusOneDay)
    AND (:gender IS NULL OR u.gender = :gender)
    AND (:minAge IS NULL OR u.age >= :minAge)
    AND (:maxAge IS NULL OR u.age <= :maxAge)
    GROUP BY DATE(f.timestamp)
    ORDER BY DATE(f.timestamp)
""")
    List<Object[]> getLineChartData(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            @Param("gender") String gender,
            @Param("minAge") Integer minAge,
            @Param("maxAge") Integer maxAge
    );
}
