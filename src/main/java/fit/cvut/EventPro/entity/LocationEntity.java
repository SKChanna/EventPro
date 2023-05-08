package fit.cvut.EventPro.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class LocationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String location;

    @ManyToOne
    @JsonBackReference(value = "locations")
    @JoinColumn(name = "event")
    private EventEntity event;

    @OneToMany(mappedBy = "location", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference(value = "dateAndTimes")
    private List<DateAndTimeEntity> dateAndTimes;


    public LocationEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EventEntity getEvent() {
        return event;
    }

    public void setEvent(EventEntity event) {
        this.event = event;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<DateAndTimeEntity> getDateAndTimes() {
        return dateAndTimes;
    }

    public void setDateAndTimes(List<DateAndTimeEntity> dateAndTimes) {
        this.dateAndTimes = dateAndTimes;
    }
}
