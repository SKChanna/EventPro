package fit.cvut.EventPro.service;

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
public class EventService {

    @Autowired
    private EventRepo eventRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private LocationAndDateTimeService locationAndDateTimeService;
    @Autowired
    private LocationsRepo locationsRepo;
    @Autowired
    private DateAndTimeRepo dateAndTimeRepo;

    public List<EventEntity> getAll(Long id) throws Exception {
        if (id == null) {
            List<EventEntity> eventEntityList = eventRepo.all();
            return eventEntityList;
        } else {
            List<EventEntity> eventEntityList = eventRepo.allByUser(id);
            return eventEntityList;
        }
    }

    @Transactional
    public EventEntity add(EventEntity event) throws Exception {

        if (event.getTitle() == null || event.getTitle().isEmpty()) {
            throw new Exception("Title is empty!");
        }
        if (event.getDescription() == null || event.getDescription().isEmpty()) {
            throw new Exception("Description is empty!");
        }
        if (event.getUser() == null || event.getUser().getId() == null) {
            throw new Exception("User is required!");
        }

        UserEntity userEntity = userRepo.byId(event.getUser().getId());

        if (userEntity == null) {
            throw new Exception("User not found with id = " + event.getUser().getId());
        }

        EventEntity eventEntity = new EventEntity();

        eventEntity.setTitle(event.getTitle());
        eventEntity.setDescription(event.getDescription());
        eventEntity.setUser(userEntity);
        eventEntity = eventRepo.save(eventEntity);

        List<LocationEntity> locationEntities = event.getLocations();
        locationEntities = locationAndDateTimeService.add(locationEntities, eventEntity);

        eventEntity.setLocations(locationEntities);
        eventEntity = eventRepo.save(eventEntity);

        return eventEntity;

    }

    @Transactional
    public EventEntity update(EventEntity event) throws Exception {

        if (event.getTitle() == null || event.getTitle().isEmpty()) {
            throw new Exception("Title is empty!");
        }
        if (event.getDescription() == null || event.getDescription().isEmpty()) {
            throw new Exception("Description is empty!");
        }

        EventEntity eventEntity = eventRepo.byId(event.getId());
        eventEntity.setDescription(event.getDescription());
        eventEntity.setTitle(event.getTitle());
        eventEntity = eventRepo.save(eventEntity);

        for ( LocationEntity location : eventEntity.getLocations()) {
            dateAndTimeRepo.delete(location.getId());
        }
        locationsRepo.delete(eventEntity.getId());
        eventEntity.setLocations(null);

        List<LocationEntity> locationEntities = event.getLocations();
        locationEntities = locationAndDateTimeService.add(locationEntities, eventEntity);
        eventEntity.setLocations(locationEntities);

        eventEntity = eventRepo.save(eventEntity);

        return eventEntity;

    }

}
