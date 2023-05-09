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

    @Query("select ch from InvitationEntity ch where ch.id = ?1")
    List<InvitationEntity> allByUser(Long id);

    @Modifying
    @Query("UPDATE InvitationEntity SET status = 'PENDING' WHERE id = ?1")
    Boolean updateStatusToPendingByEvent(Long id);

}