package me.mano.attendence_management_system.repo;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import me.mano.attendence_management_system.entity.StudentEntity;

public interface StudentRepo extends JpaRepository<StudentEntity, String> {

    @Query("SELECT s FROM StudentEntity s WHERE LOWER(CONCAT(s.firstName, ' ', s.lastName)) = LOWER(:fullName)")
    Optional<StudentEntity> findByFullNameIgnoreCase(@Param("fullName") String fullName);

    @Query("SELECT s FROM StudentEntity s WHERE LOWER(CONCAT(s.firstName, ' ', s.lastName)) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<StudentEntity> searchByName(@Param("query") String query);
}
