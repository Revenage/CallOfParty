package com.revenage.springmvc.controller;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import com.revenage.springmvc.model.UserEvent;
import com.revenage.springmvc.model.UserFriend;
import com.revenage.springmvc.service.EventService;
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

@Controller
@RequestMapping("/")
public class AppController {

	@Autowired
	EventService eventService;

	@Autowired
	FriendService friendService;

	@Autowired
	MessageSource messageSource;

	/*
	 * This method will list all existing employees.
	 */
	@RequestMapping(value = { "/list" }, method = RequestMethod.GET)
	public String listUserEvents(ModelMap model) {

		List<UserEvent> userEvents = eventService.findAllUserEvents();
		model.addAttribute("userEvents", userEvents);
		return "alluserevents";
	}

	/*
	 * This method will provide the medium to add a new UserEvent.
	 */
	@RequestMapping(value = { "/new" }, method = RequestMethod.GET)
	public String newUserEvent(ModelMap model) {
		UserEvent userEvent = new UserEvent();
		model.addAttribute("userEvent", userEvent);
		model.addAttribute("edit", false);
		return "event";
	}

	/*
	 * This method will be called on form submission, handling POST request for
	 * saving UserEvent in database. It also validates the user input
	 */
	@RequestMapping(value = { "/new" }, method = RequestMethod.POST)
	public String saveUserEvent(@Valid UserEvent userEvent, BindingResult result,
								ModelMap model, RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			return "event";
		}

		/*
		 * Preferred way to achieve uniqueness of field [name] should be implementing custom @Unique annotation
		 * and applying it on field [name] of Model class [UserEvent].
		 * 
		 * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation
		 * framework as well while still using internationalized messages.
		 * 
		 */
		if(!eventService.isUserEventNameUnique(userEvent.getId(), userEvent.getName())){
			FieldError nameError =new FieldError("UserEvent","name",messageSource.getMessage("non.unique.name", new String[]{userEvent.getName()}, Locale.getDefault()));
		    result.addError(nameError);
			return "event";
		}
		
		eventService.saveUserEvent(userEvent);

		redirectAttributes.asMap().clear();
		//model.addAttribute("success", "UserEvent " + userEvent.getName() + " registered successfully");
		return "redirect:/list";
		//return "success";
	}


	/*
	 * This method will provide the medium to update an existing UserEvent.
	 */
	@RequestMapping(value = { "/edit-{id}-userevent" }, method = RequestMethod.GET)
	public String editUserEvent(@PathVariable String id, ModelMap model) {
		UserEvent userEvent = eventService.findById(Integer.parseInt(id));
		model.addAttribute("userEvent", userEvent);
		model.addAttribute("edit", true);
		List<UserFriend> userFriends = friendService.findAllUserFriends();
		//List<UserFriend> userFriends = friendService.findFriendByEvent(id);
		model.addAttribute("userFriends", userFriends);
		return "event";
	}
	
	/*
	 * This method will be called on form submission, handling POST request for
	 * updating UserEvent in database. It also validates the user input
	 */
	@RequestMapping(value = { "/edit-{id}-userevent" }, method = RequestMethod.POST)
	public String updateUserEvent(@Valid UserEvent userEvent, BindingResult result,
								  ModelMap model, @PathVariable String id) {

		if (result.hasErrors()) {
			return "event";
		}

		if(!eventService.isUserEventNameUnique(userEvent.getId(), userEvent.getName())){
			FieldError nameError =new FieldError("UserEvent","name",messageSource.getMessage("non.unique.name", new String[]{userEvent.getName()}, Locale.getDefault()));
		    result.addError(nameError);
			return "event";
		}

		eventService.updateUserEvent(userEvent);

		model.addAttribute("success", "Event " + userEvent.getName()	+ " updated successfully");
		return "success";
	}

	
	/*
	 * This method will delete an UserEvent by it's SSN value.
	 */
	@RequestMapping(value = { "/delete-{id}-userevent" }, method = RequestMethod.GET)
	public String deleteUserEvent(@PathVariable String id) {
		//eventService.deleteUserEventByName(name);
		eventService.deleteUserEventById(id);
		return "redirect:/list";
	}

	@RequestMapping(value = { "/edit-{id}-userevent/newuserfriend" }, method = RequestMethod.GET)
	public String newUserFriend(@PathVariable String id, ModelMap model) {
		UserFriend userFriend = new UserFriend();
		model.addAttribute("userFriend", userFriend);
		model.addAttribute("edit", false);
		return "userfriend";
	}

	@RequestMapping(value = { "/edit-{id}-userevent/newuserfriend" }, method = RequestMethod.POST)
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
