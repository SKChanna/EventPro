package fit.cvut.EventPro.repository;

import fit.cvut.EventPro.entity.LocationEntity;
import org.springframework.data.repository.CrudRepository;

public interface LocationsRepo extends CrudRepository<LocationEntity, Long> {
}
