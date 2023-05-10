package fit.cvut.EventPro.dto;

import java.util.List;

public class InvitationDto {

    private Long eventId;
    private List<Long> userIds;
    private List<Long> contactIds;

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public List<Long> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Long> userIds) {
        this.userIds = userIds;
    }

    public List<Long> getContactIds() {
        return contactIds;
    }

    public void setContactIds(List<Long> contactIds) {
        this.contactIds = contactIds;
    }
}

