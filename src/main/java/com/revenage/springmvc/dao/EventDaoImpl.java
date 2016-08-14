package com.revenage.springmvc.dao;

import java.util.List;

import com.revenage.springmvc.model.UserEvent;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("userEventDao")
public class EventDaoImpl extends AbstractDao<Integer, UserEvent> implements EventDao {

	public UserEvent findById(int id) {
		return getByKey(id);
	}

	public void saveUserEvent(UserEvent userEvent) {
		persist(userEvent);
	}

	public void deleteUserEventByName(String name) {
		Query query = getSession().createSQLQuery("delete from Userevent where name = :name");
		query.setString("name", name);
		query.executeUpdate();
	}

	public void deleteUserEventById(String id) {
		Query query = getSession().createSQLQuery("delete from Userevent where id = :id");
		query.setString("id", id);
		query.executeUpdate();
	}

	public List<UserEvent> findAllUserEvents() {
		Criteria criteria = createEntityCriteria();
		return (List<UserEvent>) criteria.list();
	}

	public UserEvent findUserEventByName(String name) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("name", name));
		return (UserEvent) criteria.uniqueResult();
	}

}
