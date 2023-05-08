package fit.cvut.EventPro.repository;

import fit.cvut.EventPro.entity.UserEntity;
import fit.cvut.EventPro.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepo extends CrudRepository<UserEntity, Long> {
    UserEntity findByUsername (String username);

    @Query("select ch from UserEntity ch order by ch.id desc")
    List<UserEntity> all();

    @Query("select ch from UserEntity ch where ch.id = ?1")
    UserEntity byId(Long id);


}
