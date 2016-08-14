package com.revenage.springmvc.service;

import java.util.List;

import com.revenage.springmvc.dao.EventDao;
import com.revenage.springmvc.model.UserEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("employeeService")
@Transactional
public class EventServiceImpl implements EventService {

	@Autowired
	private EventDao dao;
	
	public UserEvent findById(int id) {
		return dao.findById(id);
	}

	public void saveUserEvent(UserEvent userEvent) {
		dao.saveUserEvent(userEvent);
	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from db and update it with proper values within transaction.
	 * It will be updated in db once transaction ends. 
	 */
	public void updateUserEvent(UserEvent userEvent) {
		UserEvent entity = dao.findById(userEvent.getId());
		if(entity!=null){
			entity.setName(userEvent.getName());
			entity.setJoiningDate(userEvent.getJoiningDate());
			//entity.setSalary(userEvent.getSalary());
			//entity.setSsn(UserEvent.getSsn());
		}
	}

	public void deleteUserEventByName(String name) {
		dao.deleteUserEventByName(name);
	}
	public void deleteUserEventById(String id) {
		dao.deleteUserEventById(id);
	}

	public List<UserEvent> findAllUserEvents() {
		return dao.findAllUserEvents();
	}

	public UserEvent findUserEventByName(String name) {
		return dao.findUserEventByName(name);
	}

	public boolean isUserEventNameUnique(Integer id, String name) {
		UserEvent userEvent = findById(id);
		return ( userEvent == null || ((id != null) && (userEvent.getId() == id)));
	}
	
}
