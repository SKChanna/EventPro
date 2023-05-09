package fit.cvut.EventPro.service;

import fit.cvut.EventPro.entity.ContactsEntity;
import fit.cvut.EventPro.entity.UserEntity;
import fit.cvut.EventPro.exception.UserAlreadyExistException;
import fit.cvut.EventPro.exception.UserNotFoundException;
import fit.cvut.EventPro.model.Contacts;
import fit.cvut.EventPro.model.User;
import fit.cvut.EventPro.repository.ContactRepo;
import fit.cvut.EventPro.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Base64;
import java.util.List;

@Service
public class ContactService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ContactRepo contactRepo;


    public List<ContactsEntity> getAll(Long id) {
        if (id == null) {
            return contactRepo.all();
        } else {
            return contactRepo.allByUserId(id);
        }
    }

    @Transactional
    public Contacts createNew(ContactsEntity contact) throws Exception {

        if (contact.getName() == null) {
            throw new Exception("Name is required.");
        }
        if (contact.getEmail() == null) {
            throw new Exception("Email is required.");
        }
        if (contact.getUsername() == null) {
            throw new Exception("Username is required.");
        }
        if (contact.getUser() == null || contact.getUser().getId() == null) {
            throw new Exception("User is required.");
        }
        UserEntity userEntity = userRepo.byId(contact.getUser().getId());
        if (userEntity == null) {
            throw new Exception("User is not found in db with id = "+contact.getUser().getId());
        }
        contact.setUser(userEntity);

        if (contactRepo.byEmail(contact.getEmail()) != null) {
            throw new Exception("Contact with same email already exist.");
        }

        if (contact.getUserContact() != null && contact.getUserContact().getId() == null) {
            UserEntity user = userRepo.byId(contact.getUserContact().getId());

            if (user == null) {
                throw new Exception("User Contact is not found in db with id = "+contact.getUserContact().getId());
            }
            contact.setUserContact(user);
        } else {
            contact.setUserContact(null);
        }


        contact = contactRepo.save(contact);
        return Contacts.toModel(contact);

    }

}
