package fit.cvut.EventPro.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class ContactsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;
    private String surname;
    private String email;
    private String username;

    @ManyToOne
    @JsonBackReference(value = "contacts")
    @JoinColumn(name = "user")
    private UserEntity user;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity userContact;

    public ContactsEntity(Long id) {
        this.id = id;
    }

    public ContactsEntity() {
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

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public UserEntity getUserContact() {
        return userContact;
    }

    public void setUserContact(UserEntity userContact) {
        this.userContact = userContact;
    }

}
