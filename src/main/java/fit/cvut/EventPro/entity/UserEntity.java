package fit.cvut.EventPro.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;
    private String surname;
    private Long numberOfOrganizedEvents = 0l;
    private String username;
    private String password;

    private Boolean admin = false;


    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference(value = "contacts")
    private List<ContactsEntity> contacts;

    public List<ContactsEntity> getContacts() {
        return contacts;
    }

    public void setContacts(List<ContactsEntity> contacts) {
        this.contacts = contacts;
    }

    public UserEntity(Long id) {
        this.id = id;
    }

    public UserEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Long getNumberOfOrganizedEvents() {
        return numberOfOrganizedEvents;
    }

    public void setNumberOfOrganizedEvents(Long numberOfOrganizedEvents) {
        this.numberOfOrganizedEvents = numberOfOrganizedEvents;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }
}
