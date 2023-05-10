package fit.cvut.EventPro.service;

import fit.cvut.EventPro.entity.*;
import fit.cvut.EventPro.repository.*;
import fit.cvut.EventPro.service.email.EmailService;
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

    @Autowired
    private InvitationRepo invitationRepo;

    @Autowired
    private ReviewRepo reviewRepo;

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
        eventEntity.setEndDateTime(event.getEndDateTime());
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
        eventEntity.setEndDateTime(event.getEndDateTime());
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

        invitationRepo.updateStatusToPendingByEvent(eventEntity.getId());

        return eventEntity;

    }

    @Transactional
    public ReviewEntity addReview(ReviewEntity review) throws Exception {

        if (review.getStars() == null) {
            throw new Exception("Stars is empty!");
        }
        if (review.getInvitationEntity() == null || review.getInvitationEntity().getId() == null) {
            throw new Exception("Invitation is required!");
        }

        InvitationEntity invitation = invitationRepo.byId(review.getInvitationEntity().getId());

        if (invitation == null) {
            throw new Exception("Invitation not found with id = "+review.getInvitationEntity().getId());
        }

        invitation.setFeedbackDone(true);
        invitation = invitationRepo.save(invitation);

        review.setInvitationEntity(invitation);
        review = reviewRepo.save(review);

        return review;

    }

    @Transactional
    public List<ReviewEntity> getReviewsByEvent(Long eventId) throws Exception {

        if (eventId == null) {
            throw new Exception("Event is must");
        }

        List<ReviewEntity> reviews = reviewRepo.byEvent(eventId);

        return reviews;

    }

    @Transactional
    public Boolean deleteEvent(Long eventId) throws Exception {

        if (eventId == null) {
            throw new Exception("Event id is required!");
        }

        dateAndTimeRepo.deleteByEvent(eventId);
        locationsRepo.delete(eventId);

        reviewRepo.deleteByEvent(eventId);
        invitationRepo.deleteByEvent(eventId);

        eventRepo.deleteById(eventId);

        return true;
    }


}
