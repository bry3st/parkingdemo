package com.demo.parking.jpaRepositories;

import com.demo.parking.models.EntryPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EntryPointRepository extends JpaRepository<EntryPoint, Long> {

    @Query("SELECT e FROM EntryPoint e WHERE e.entryPointName = ?1")
    Optional<EntryPoint> findExistingName(String name);

    @Query("SELECT e.id FROM EntryPoint e WHERE e.coordinate_x = ?1 and e.coordinate_y = ?2")
    Long findByCoordinate_xAndCoordinate_y(int entry_x, int entry_y);
}
