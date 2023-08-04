package utku.basictask.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import utku.basictask.entity.Task;


public interface TaskRepository extends JpaRepository<Task,Long>{
}
