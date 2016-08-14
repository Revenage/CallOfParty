package com.revenage.springmvc.controller;

import com.revenage.springmvc.model.UserFriend;
import com.revenage.springmvc.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Locale;

/**
 * Created by revenage on 8/14/16.
 */
@Controller
@RequestMapping("/edit-{id}-userevent")
public class FriendController {

    @Autowired
    FriendService friendService;

    @Autowired
    MessageSource messageSource;


    @RequestMapping(value = { "/newuserfriend" }, method = RequestMethod.GET)
    public String newUserFriend(@PathVariable String id, ModelMap model) {
        UserFriend userFriend = new UserFriend();
        model.addAttribute("userFriend", userFriend);
        model.addAttribute("edit", false);
        return "userfriend";
    }

    @RequestMapping(value = { "/newuserfriend" }, method = RequestMethod.POST)
    public String saveUserFriend(@PathVariable String id, @Valid UserFriend userFriend, BindingResult result,
                                 ModelMap model, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "userfriend";
        }

		/*
		 * Preferred way to achieve uniqueness of field [name] should be implementing custom @Unique annotation
		 * and applying it on field [name] of Model class [UserEvent].
		 *
		 * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation
		 * framework as well while still using internationalized messages.
		 *
		 */
        if(!friendService.isUserFriendNameUnique(userFriend.getId(), userFriend.getName())){
            FieldError nameError =new FieldError("UserEvent","name",messageSource.getMessage("non.unique.name", new String[]{userFriend.getName()}, Locale.getDefault()));
            result.addError(nameError);
            return "userfriend";
        }
        friendService.saveUserFriend(userFriend);

        redirectAttributes.asMap().clear();
        //model.addAttribute("success", "UserEvent " + userEvent.getName() + " registered successfully");
        return "redirect:/edit-{id}-userevent";
        //return "success";
    }
}
