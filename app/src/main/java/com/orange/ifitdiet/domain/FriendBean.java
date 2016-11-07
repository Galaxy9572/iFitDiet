package com.orange.ifitdiet.domain;

import com.orange.ifitdiet.common.Bean;

/**
 * Created by 廖俊瑶 on 2016/10/26.
 */

public class FriendBean extends Bean {
    private String userId;
    private String friendId;
    private String date;//加好友的日期

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
