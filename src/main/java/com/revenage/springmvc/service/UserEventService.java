package com.revenage.springmvc.service;

import java.util.List;

import com.revenage.springmvc.model.UserEvent;
import com.revenage.springmvc.model.UserFriend;

public interface UserEventService {

	UserEvent findById(int id);
	
	void saveUserEvent(UserEvent userEvent);
	
	void updateUserEvent(UserEvent userEvent);
	
	void deleteUserEventByName(String name);

	List<UserEvent> findAllUserEvents();
	
	UserEvent findUserEventByName(String name);

	boolean isUserEventNameUnique(Integer id, String name);

	void deleteUserEventById(String id);
}
