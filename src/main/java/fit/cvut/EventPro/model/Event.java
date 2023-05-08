package fit.cvut.EventPro.model;

import fit.cvut.EventPro.entity.EventEntity;
import fit.cvut.EventPro.entity.LocationEntity;
import fit.cvut.EventPro.entity.UserEntity;

import java.util.List;

public class Event {

    private Long id;
    private String title;
    private String description;
    private List<LocationEntity> locations;

    private UserEntity user;

    public static Event toModel (EventEntity entity) {
        Event model = new Event();
        model.setId(entity.getId());
        model.setTitle(entity.getTitle());
        model.setDescription(entity.getDescription());
        model.setLocations(entity.getLocations());
        model.setUser(entity.getUser());

        return model;
    }

    public Event() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<LocationEntity> getLocations() {
        return locations;
    }

    public void setLocations(List<LocationEntity> locations) {
        this.locations = locations;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
