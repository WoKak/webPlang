package webplang.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import webplang.domain.UserToRegister;
import webplang.service.UserToRegisterService;

import javax.validation.Valid;

/**
 * Created by Michał on 2017-07-26.
 * Registration page controller.
 */

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private UserToRegisterService userToRegisterService;

    @Autowired
    public RegistrationController(UserToRegisterService userToRegisterService) {
        this.userToRegisterService = userToRegisterService;
    }

    /**
     * Method which binds data.
     */
    @RequestMapping(method = RequestMethod.GET)
    public String getUserDataFromForm(Model model) {

        UserToRegister newUserToRegister = new UserToRegister();
        model.addAttribute("userToRegister", newUserToRegister);
        return "register";
    }

    /**
     * Method which adds data to database.
     */
    @RequestMapping(method = RequestMethod.POST)
    public String processAddNewUserForm
            (@ModelAttribute("userToRegister") @Valid UserToRegister newUserToRegister, BindingResult result) throws Exception{

        userToRegisterService.addUser(newUserToRegister, result);
        return "redirect:/login";
    }
}
