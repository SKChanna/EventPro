package fit.cvut.EventPro.service;

import fit.cvut.EventPro.entity.UserEntity;
import fit.cvut.EventPro.exception.UserAlreadyExistException;
import fit.cvut.EventPro.exception.UserNotFoundException;
import fit.cvut.EventPro.model.User;
import fit.cvut.EventPro.repository.UserRepo;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Base64;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public User verify(UserEntity user) throws UserNotFoundException {
        UserEntity foundUser = userRepo.findByUsername(user.getUsername());
        if (foundUser == null || user.getPassword() == null || user.getPassword().length() == 0) {
            throw new UserNotFoundException("Username or password is wrong!");
        }

        String pass = new String(Base64.getDecoder().decode(foundUser.getPassword()));

        if (!pass.equals(user.getPassword())) {
            throw new UserNotFoundException("Username or password is wrong!");
        }

        return User.toModel(foundUser);
    }

    public UserEntity registration (UserEntity user) throws UserAlreadyExistException {

        if (user.getUsername() == null || user.getUsername().length() == 0 ) {
            throw new UserAlreadyExistException("Username is required");
        }
        if (user.getPassword() == null || user.getPassword().length() == 0 ) {
            throw new UserAlreadyExistException("Username is required");
        }

        if (userRepo.findByUsername(user.getUsername()) != null) {
            throw new UserAlreadyExistException("User with the same username already exists.");
        }

        String encodedPass = Base64.getEncoder().encodeToString(user.getPassword().getBytes());
        user.setPassword(encodedPass);

        return userRepo.save(user);
    }

    public User getOne (Long id) throws UserNotFoundException {
        UserEntity foundUser = userRepo.findById(id).get();
        if (foundUser != null) {
            throw new UserNotFoundException("User wasn't found.");
        }
        return User.toModel(foundUser);
    }

    public List<UserEntity> getAllUsers() throws UserAlreadyExistException {
        List<UserEntity> users = userRepo.all();
        return users;
    }
}
