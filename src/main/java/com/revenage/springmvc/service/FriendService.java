package com.revenage.springmvc.service;

import com.revenage.springmvc.model.UserFriend;
import java.util.List;

/**
 * Created by revenage on 8/14/16.
 */
public interface FriendService {
    UserFriend findById(int id);

    void saveUserFriend(UserFriend userFriend);

    void updateUserFriend(UserFriend userFriend);

    List<UserFriend> findAllUserFriends();

    UserFriend findUserFriendByName(String name);

    boolean isUserFriendNameUnique(Integer id, String name);

    void deleteUserFriendById(String id);

    /*List<UserFriend> findFriendByEvent(String id);*/
}
