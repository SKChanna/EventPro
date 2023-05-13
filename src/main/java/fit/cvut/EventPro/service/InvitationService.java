package fit.cvut.EventPro.service;

import fit.cvut.EventPro.dto.InvitationDto;
import fit.cvut.EventPro.entity.ContactsEntity;
import fit.cvut.EventPro.entity.EventEntity;
import fit.cvut.EventPro.entity.InvitationEntity;
import fit.cvut.EventPro.entity.UserEntity;
import fit.cvut.EventPro.repository.*;
import fit.cvut.EventPro.service.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class InvitationService {

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
    private ContactRepo contactRepo;

    @Autowired
    private EmailService emailService;


    public List<InvitationEntity> getAll(Long id) throws Exception {
        if (id == null) {
            List<InvitationEntity> invitationEntity = invitationRepo.all();
            return invitationEntity;
        } else {
            List<InvitationEntity> invitationEntity = invitationRepo.allByUser(id);
            return invitationEntity;
        }
    }

    @Transactional
    public InvitationEntity add(InvitationEntity invitationEntity) throws Exception {

        if ((invitationEntity.getUser() == null || invitationEntity.getUser().getId() == null) && (invitationEntity.getContactsEntity() == null || invitationEntity.getContactsEntity().getId() == null)) {
            throw new Exception("User or Contacts is required");
        }

        if (invitationEntity.getEvent() == null || invitationEntity.getEvent().getId() == null) {
            throw new Exception("Event is required");
        }

        if (invitationEntity.getUser() != null) {
            UserEntity userEntity = userRepo.byId(invitationEntity.getUser().getId());
            if (userEntity == null) {
                throw new Exception("User not found with id = " + invitationEntity.getUser().getId());
            }
            invitationEntity.setUser(userEntity);
        }

        EventEntity eventEntity = eventRepo.byId(invitationEntity.getEvent().getId());
        if (eventEntity == null) {
            throw new Exception("Event not found with id = " + invitationEntity.getEvent().getId());
        }
        invitationEntity.setEvent(eventEntity);
        invitationEntity.setFeedbackDone(false);

        invitationEntity = invitationRepo.save(invitationEntity);

        if (invitationEntity.getContactsEntity() != null) {
            ContactsEntity contactsEntity = contactRepo.byId(invitationEntity.getContactsEntity().getId());
            if (contactsEntity == null) {
                throw new Exception("Contact not found with id = " + invitationEntity.getContactsEntity().getId());
            }
            invitationEntity.setContactsEntity(contactsEntity);
//            sendHTMLMailToken(String eventName, String invitationSentBy, String emailTo, Long invitationId, Long contactId);
            emailService.sendHTMLMailToken(eventEntity.getTitle(), eventEntity.getUser().getName(), contactsEntity.getEmail(), invitationEntity.getId(), contactsEntity.getId());
        }

        invitationEntity = invitationRepo.save(invitationEntity);

        return invitationEntity;

    }


    @Transactional
    public List<InvitationEntity> addMultiple(InvitationDto invitationDto) throws Exception {

        if (invitationDto.getEventId() == null) {
            throw new Exception("Event is required");
        }

        if ( (invitationDto.getUserIds() == null || invitationDto.getUserIds().size() == 0) && (invitationDto.getContactIds() == null || invitationDto.getContactIds().size() == 0) ) {
            throw new Exception("No user or contact is selected, select at least one");
        }

        List<InvitationEntity> invitationEntityList = new ArrayList<>();

        if (invitationDto.getUserIds() != null) {
            for (Long id: invitationDto.getUserIds()) {
                InvitationEntity invitation = new InvitationEntity();
                invitation.setStatus("Pending");
                invitation.setEvent(new EventEntity(invitationDto.getEventId()));
                invitation.setUser(new UserEntity(id));
                invitationEntityList.add(add(invitation));
            }
        }

        if (invitationDto.getContactIds() != null) {
            for (Long id : invitationDto.getContactIds()) {
                InvitationEntity invitation = new InvitationEntity();
                invitation.setStatus("Pending");
                invitation.setEvent(new EventEntity(invitationDto.getEventId()));
                invitation.setContactsEntity(new ContactsEntity(id));
                invitationEntityList.add(add(invitation));
            }
        }

        return invitationEntityList;
    }

    @Transactional
    public InvitationEntity updateStatus(InvitationEntity invitationEntity) throws Exception {

        if (invitationEntity.getId() == null) {
            throw new Exception("Id required!");
        }

        if (invitationEntity.getStatus() == null || invitationEntity.getStatus().isEmpty()) {
            throw new Exception("Status required!");
        }

        InvitationEntity invitation = invitationRepo.byId(invitationEntity.getId());
        if (invitation == null) {
            throw new Exception("Invitation not found with id = "+invitation.getId());
        }

        invitation.setStatus(invitationEntity.getStatus());

        return invitation;

    }

    public List<InvitationEntity> getInvitationList(Long id) {
        return invitationRepo.allByEvent(id);
    }

}
