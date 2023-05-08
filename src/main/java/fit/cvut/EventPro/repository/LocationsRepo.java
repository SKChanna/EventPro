package fit.cvut.EventPro.repository;

import fit.cvut.EventPro.entity.LocationEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface LocationsRepo extends CrudRepository<LocationEntity, Long> {

    @Modifying
    @Query(value = "delete from location_entity where location_entity.event=?1" , nativeQuery = true)
    void delete(Long l);

}
