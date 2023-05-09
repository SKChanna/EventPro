package fit.cvut.EventPro.entity;

import jakarta.persistence.*;

@Entity
public class InvitationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String status;

    @OneToOne
    @JoinColumn(name = "event_id")
    private EventEntity event;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToOne
    @JoinColumn(name = "contact_id")
    private ContactsEntity contactsEntity;

    private Boolean feedbackDone = false;

    public Boolean getFeedbackDone() {
        return feedbackDone;
    }

    public void setFeedbackDone(Boolean feedbackDone) {
        this.feedbackDone = feedbackDone;
    }

    public ContactsEntity getContactsEntity() {
        return contactsEntity;
    }

    public void setContactsEntity(ContactsEntity contactsEntity) {
        this.contactsEntity = contactsEntity;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public EventEntity getEvent() {
        return event;
    }

    public void setEvent(EventEntity event) {
        this.event = event;
    }
}
