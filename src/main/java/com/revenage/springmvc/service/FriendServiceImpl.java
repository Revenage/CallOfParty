package com.revenage.springmvc.service;

import com.revenage.springmvc.dao.FriendDaoImpl;
import com.revenage.springmvc.model.UserFriend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by revenage on 8/14/16.
 */
@Service("friendService")
@Transactional
public class FriendServiceImpl implements FriendService {

    @Autowired
    private FriendDaoImpl dao;

    public UserFriend findById(int id) {
        return dao.findById(id);
    }

    public void saveUserFriend(UserFriend userFriend) {
        dao.saveUserFriend(userFriend);
    }

    public void updateUserFriend(UserFriend userFriend) {
        UserFriend entity = dao.findById(userFriend.getId());
        if(entity!=null){
            entity.setName(userFriend.getName());
            //entity.setJoiningDate(userFriend.getJoiningDate());
            //entity.setSalary(userFriend.getSalary());
            //entity.setSsn(userFriend.getSsn());
        }
    }

    public List<UserFriend> findAllUserFriends() {
        return dao.findAllUserFriends();
    }

    public UserFriend findUserFriendByName(String name) {
        return dao.findUserFriendByName(name);
    }

    public boolean isUserFriendNameUnique(Integer id, String name) {
        UserFriend userFriend = findById(id);
        return ( userFriend == null || ((id != null) && (userFriend.getId() == id)));

    }

    public void deleteUserFriendById(String id) {
        dao.deleteUserFriendById(id);
    }

    /*public List<UserFriend> findFriendByEvent(String id) {
        return dao.findFriendByEvent(id);
    }*/
}
