package fit.cvut.EventPro.model;

import fit.cvut.EventPro.entity.ContactsEntity;
import fit.cvut.EventPro.entity.UserEntity;

import java.util.List;

public class User {
    private Long id;
    private String username;
    private String name;
    private String surname;
    private Long numberOfOrganizedEvents = 0l;

    private List<ContactsEntity> contacts;

    private Boolean admin;

    public static User toModel (UserEntity entity) {
        User model = new User();
        model.setId(entity.getId());
        model.setUsername(entity.getUsername());
        model.setName(entity.getName());
        model.setSurname(entity.getSurname());
        model.setNumberOfOrganizedEvents(entity.getNumberOfOrganizedEvents());
        model.setContacts(entity.getContacts());
        model.setAdmin(entity.getAdmin());

        return model;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public List<ContactsEntity> getContacts() {
        return contacts;
    }

    public void setContacts(List<ContactsEntity> contacts) {
        this.contacts = contacts;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }
}
