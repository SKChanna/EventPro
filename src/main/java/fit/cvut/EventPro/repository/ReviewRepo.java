package fit.cvut.EventPro.repository;

import fit.cvut.EventPro.entity.ReviewEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface ReviewRepo extends CrudRepository<ReviewEntity, Long> {

    @Query("select ch from ReviewEntity ch order by ch.invitationEntity.event.id = ?1 desc")
    List<ReviewEntity> byEvent(Long id);

}