package fit.cvut.EventPro.repository;

import fit.cvut.EventPro.entity.ReviewEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface ReviewRepo extends CrudRepository<ReviewEntity, Long> {

    @Query("select ch from ReviewEntity ch where ch.invitationEntity.event.id = ?1 order by ch.id desc ")
    List<ReviewEntity> byEvent(Long id);

    @Modifying
    @Query(value = "delete from review_entity where review_entity.invitation_id=?1" , nativeQuery = true)
    void deleteByInvitation(Long l);


}