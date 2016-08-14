package com.revenage.springmvc.dao;

import com.revenage.springmvc.model.UserFriend;

import java.util.List;

/**
 * Created by revenage on 8/14/16.
 */
public interface UserFriendDao {

    UserFriend findById(int id);

    void saveUserFriend(UserFriend userEvent);

    void deleteUserFriendByName(String name);

    List<UserFriend> findAllUserFriends();

    UserFriend findUserFriendByName(String name);

    void deleteUserFriendById(String id);

}
