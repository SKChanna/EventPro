package fit.cvut.EventPro.repository;

import fit.cvut.EventPro.entity.DateAndTimeEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface DateAndTimeRepo extends CrudRepository<DateAndTimeEntity, Long> {

    @Modifying
    @Query(value = "delete from date_and_time_entity where date_and_time_entity.location=?1" , nativeQuery = true)
    void delete(Long l);

}
