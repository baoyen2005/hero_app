package com.example.booknews.model.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Hero implements Serializable {
    /*
        private String category;
        private String imageUrl;
        private String name;
        private String desc;


        public Book(String category, String imageUrl, String name, String desc) {
            this.category = category;
            this.imageUrl = imageUrl;
            this.name = name;
            this.desc = desc;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

     */


    @SerializedName("name")
    private String name;
    @SerializedName("realname")
    private String realname;
    @SerializedName("team")
    private String team;
    @SerializedName("firstappearance")
    private String firstappearance;
    @SerializedName("createdby")
    private String createdby;
    @SerializedName("publisher")
    private String publisher;
    @SerializedName("imageurl")
    private String imageurl;
    @SerializedName("bio")
    private String bio;

    public Hero(String name, String realname, String team, String firstappearance, String createdby, String publisher, String imageurl, String bio) {
        this.name = name;
        this.realname = realname;
        this.team = team;
        this.firstappearance = firstappearance;
        this.createdby = createdby;
        this.publisher = publisher;
        this.imageurl = imageurl;
        this.bio = bio;
    }

    public String getName() {
        return name;
    }

    public String getRealname() {
        return realname;
    }

    public String getTeam() {
        return team;
    }

    public String getFirstappearance() {
        return firstappearance;
    }

    public String getCreatedby() {
        return createdby;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getImageurl() {
        return imageurl;
    }

    public String getBio() {
        return bio;
    }

}
