package urfu.mvoronin.course_project_house_residents.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import urfu.mvoronin.course_project_house_residents.entity.UserAction;

@Repository
public interface UserActionRepository extends JpaRepository<UserAction, Long> {
}
