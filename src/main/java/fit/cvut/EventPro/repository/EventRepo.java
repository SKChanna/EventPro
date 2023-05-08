package fit.cvut.EventPro.repository;

import fit.cvut.EventPro.entity.EventEntity;
import fit.cvut.EventPro.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EventRepo extends CrudRepository<EventEntity, Long> {

    @Query("select ch from EventEntity ch order by ch.id desc")
    List<EventEntity> all();

    @Query("select ch from EventEntity ch where ch.id = ?1")
    EventEntity byId(Long id);

    @Query("select ch from EventEntity ch where ch.id = ?1")
    List<EventEntity> allByUser(Long id);


}