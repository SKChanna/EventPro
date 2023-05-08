package fit.cvut.EventPro.service;

import fit.cvut.EventPro.entity.DateAndTimeEntity;
import fit.cvut.EventPro.entity.EventEntity;
import fit.cvut.EventPro.entity.LocationEntity;
import fit.cvut.EventPro.entity.UserEntity;
import fit.cvut.EventPro.repository.DateAndTimeRepo;
import fit.cvut.EventPro.repository.EventRepo;
import fit.cvut.EventPro.repository.LocationsRepo;
import fit.cvut.EventPro.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LocationAndDateTimeService {

    @Autowired
    private EventRepo eventRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private LocationsRepo locationsRepo;

    @Autowired
    private DateAndTimeRepo dateAndTimeRepo;

    @Transactional
    public List<LocationEntity> add(List<LocationEntity> locations, EventEntity event) throws Exception {

        if (locations == null || locations.size() == 0) {
            throw new Exception("Locations are required!");
        }

        for (LocationEntity location: locations) {

            if (location.getLocation() == null || location.getLocation().isEmpty() ) {
                throw new Exception("Location is empty!");
            }

            location.setEvent(event);

            if (location.getDateAndTimes() == null || location.getDateAndTimes().size() == 0) {
                throw new Exception("Date and time is required!");
            }

            for (DateAndTimeEntity dateAndTimeEntity: location.getDateAndTimes()) {

                if (dateAndTimeEntity == null ) {
                    throw new Exception("Date and time is required!");
                }

            }

        }

        locations = (List<LocationEntity>) locationsRepo.saveAll(locations);


        return locations;

    }

}
