package webplang.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import webplang.domain.UserToRegister;
import webplang.repository.UserRepository;

/**
 * Created by Michał on 2017-07-26.
 * Service for RegistrationController
 */

@Service
public class UserToRegisterService {


    private UserRepository userRepository;

    @Autowired
    public UserToRegisterService(UserRepository ur) {

        this.userRepository = ur;
    }

    /**
     * method responsible for calling repository method for user registration
     */
    public void addUser(UserToRegister newUserToRegister, BindingResult bindingResult) throws Exception {

        userRepository.addUser(newUserToRegister, bindingResult);
    }
}
