package com.example.whatsappkotlin.modelClasses

class Users {
    private var uid: String=""
    private var email: String=""
    private var username: String=""
    private var profile: String=""
    private var cover: String=""
    private var status: String=""
    private var search: String=""
    private var instagram: String=""
    private var facebook: String=""
    private var websites: String=""
    constructor()

    constructor(
        uid: String,
        email: String,
        username: String,
        profile: String,
        cover: String,
        status: String,
        search: String,
        instagram: String,
        facebook: String,
        websites: String
    ) {
        this.uid = uid
        this.email = email
        this.username = username
        this.profile = profile
        this.cover = cover
        this.status = status
        this.search = search
        this.instagram = instagram
        this.facebook = facebook
        this.websites = websites
    }
    fun getUid(): String? {
        return uid
    }

    fun setUid(uid: String?) {
        this.uid = uid!!
    }

    fun getEmail(): String? {
        return email
    }

    fun setEmail(email: String?) {
        this.email = email!!
    }

    fun getUsername(): String? {
        return username
    }

    fun setUsername(username: String?) {
        this.username = username!!
    }

    fun getProfile(): String? {
        return profile
    }

    fun setProfile(profile: String?) {
        this.profile = profile!!
    }

    fun getCover(): String? {
        return cover
    }

    fun setCover(cover: String?) {
        this.cover = cover!!
    }

    fun getStatus(): String? {
        return status
    }

    fun setStatus(status: String?) {
        this.status = status!!
    }

    fun getSearch(): String? {
        return search
    }

    fun setSearch(search: String?) {
        this.search = search!!
    }

    fun getInstagram(): String? {
        return instagram
    }

    fun setInstagram(instagram: String?) {
        this.instagram = instagram!!
    }

    fun getFacebook(): String? {
        return facebook
    }

    fun setFacebook(facebook: String?) {
        this.facebook = facebook!!
    }

    fun getWebsites(): String? {
        return websites
    }

    fun setWebsites(websites: String?) {
        this.websites = websites!!
    }
}









