package com.revenage.springmvc.dao;

import com.revenage.springmvc.configuration.HibernateUtil;
import com.revenage.springmvc.model.UserEvent;
import com.revenage.springmvc.model.UserFriend;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by revenage on 8/14/16.
 */

@Repository("userFriendDao")
public class FriendDaoImpl extends AbstractDao<Integer, UserFriend> implements FriendDao {
    public UserFriend findById(int id) {
        return getByKey(id);
    }

    //SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    //Session session = sessionFactory.openSession();

    //UserEvent userEvent = new UserEvent();

    public void saveUserFriend(UserFriend userFriend) {
        //persist(userFriend);
        //userFriend.setUserEvent(userEvent);
       // userEvent.getStockDailyRecords().add(userFriend);

        //session.beginTransaction();
        //session.save(userFriend);
    }

    public void deleteUserFriendByName(String name) {
        Query query = getSession().createSQLQuery("delete from Userfriend where name = :name");
        query.setString("name", name);
        query.executeUpdate();
    }

    public void deleteUserFriendById(String id) {
        Query query = getSession().createSQLQuery("delete from Userfriend where id = :id");
        query.setString("id", id);
        query.executeUpdate();
    }

    public List<UserFriend> findAllUserFriends() {
        Criteria criteria = createEntityCriteria();
        return (List<UserFriend>) criteria.list();
    }

    public UserFriend findUserFriendByName(String name) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("name", name));
        return (UserFriend) criteria.uniqueResult();
    }

    public List<UserFriend> findFriendByEvent(String id) {
        Criteria criteria = createEntityCriteria();
        //criteria.add(Restrictions.eq("event_id", id));
        return (List<UserFriend>) criteria.list();
    }
}
