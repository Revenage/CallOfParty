package com.revenage.springmvc.dao;

import java.util.List;

import com.revenage.springmvc.model.UserEvent;

public interface UserEventDao {

	UserEvent findById(int id);

	void saveUserEvent(UserEvent userEvent);
	
	void deleteUserEventByName(String name);
	
	List<UserEvent> findAllUserEvents();

	UserEvent findUserEventByName(String name);

	void deleteUserEventById(String id);
}
