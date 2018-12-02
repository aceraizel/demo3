package com.stackabuse.facebookapi.service;

import org.springframework.social.facebook.api.Account;
import org.springframework.social.facebook.api.Page;
import org.springframework.social.facebook.api.PagedList;

public interface FacebookService {
    public String generateFacebookAuthorizeUrl();

    public void generateFacebookAccessToken(String code);

    public String getUserData();

    public PagedList<Account> getPages();

    public Page getUsersLiked(String id);
}
