package fit.cvut.EventPro.repository;

import fit.cvut.EventPro.entity.DateAndTimeEntity;
import org.springframework.data.repository.CrudRepository;

public interface DateAndTimeRepo extends CrudRepository<DateAndTimeEntity, Long> {
}
