package fit.cvut.EventPro.repository;

import fit.cvut.EventPro.entity.ContactsEntity;
import fit.cvut.EventPro.entity.UserEntity;
import fit.cvut.EventPro.model.Contacts;
import fit.cvut.EventPro.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ContactRepo extends CrudRepository<ContactsEntity, Long> {
    @Query("select ch from ContactsEntity ch order by ch.id desc")
    List<ContactsEntity> all();

    @Query("select ch from ContactsEntity ch where ch.user.id = ?1 order by ch.id desc")
    List<ContactsEntity> allByUserId(Long id);


}