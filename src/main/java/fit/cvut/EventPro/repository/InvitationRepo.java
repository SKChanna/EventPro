package fit.cvut.EventPro.repository;

import fit.cvut.EventPro.entity.InvitationEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface InvitationRepo extends CrudRepository<InvitationEntity, Long> {

    @Query("select ch from InvitationEntity ch order by ch.id desc")
    List<InvitationEntity> all();

    @Query("select ch from InvitationEntity ch where ch.id = ?1")
    InvitationEntity byId(Long id);

    @Query("select ch from InvitationEntity ch where ch.user.id = ?1 order by ch.id desc ")
    List<InvitationEntity> allByUser(Long id);

    @Modifying
    @Query("UPDATE InvitationEntity ch SET ch.status = 'Pending' WHERE ch.event.id = ?1")
    void updateStatusToPendingByEvent(Long id);

    @Modifying
    @Query(value = "delete from invitation_entity where invitation_entity.event_id=?1" , nativeQuery = true)
    void deleteByEvent(Long l);

    @Query("select ch from InvitationEntity ch where ch.event.id = ?1 order by ch.id desc ")
    List<InvitationEntity> allByEvent(Long id);

}