
package com.example.artbot.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FavRes {

    @SerializedName("message")
    private Message mMessage;

    public Message getMessage() {
        return mMessage;
    }

    public void setMessage(Message message) {
        mMessage = message;
    }

    public class Message {

        @SerializedName("User Fav")
        private List<UserFav> mUserFav;

        public List<UserFav> getUserFav() {
            return mUserFav;
        }

        public void setUserFav(List<UserFav> userFav) {
            mUserFav = userFav;
        }

    }


}
