package urfu.mvoronin.course_project_house_residents.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import urfu.mvoronin.course_project_house_residents.entity.Resident;

public interface ResidentRepository extends JpaRepository<Resident, Long> {
}
