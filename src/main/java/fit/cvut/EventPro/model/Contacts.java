package fit.cvut.EventPro.model;

import fit.cvut.EventPro.entity.ContactsEntity;
import fit.cvut.EventPro.entity.UserEntity;
public class Contacts {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String username;
    private UserEntity contactOf;

    private UserEntity userContact;

    public static Contacts toModel (ContactsEntity entity) {
        Contacts model = new Contacts();
        model.setId(entity.getId());
        model.setUsername(entity.getUsername());
        model.setName(entity.getName());
        model.setSurname(entity.getSurname());
        model.setEmail(entity.getEmail());
        model.setContactOf(entity.getUser());
        model.setUserContact(entity.getUserContact());

        return model;
    }

    public Contacts() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserEntity getContactOf() {
        return contactOf;
    }

    public void setContactOf(UserEntity contactOf) {
        this.contactOf = contactOf;
    }


    public UserEntity getUserContact() {
        return userContact;
    }

    public void setUserContact(UserEntity userContact) {
        this.userContact = userContact;
    }
}

