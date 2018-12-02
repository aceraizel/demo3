package com.stackabuse.facebookapi.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.facebook.api.Account;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.Page;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Service;

@Service
public class FacebookServiceImpl implements FacebookService {
    private String accessToken;
    private String pageAccessToken;

    @Value("${spring.social.facebook.app-id}")
    private String facebookAppId;

    @Value("${spring.social.facebook.app-secret}")
    private String facebookAppSecret;

    private FacebookConnectionFactory createConnection() {
        return new FacebookConnectionFactory(facebookAppId, facebookAppSecret);
    }

    @Override
    public String generateFacebookAuthorizeUrl() {
        OAuth2Parameters params = new OAuth2Parameters();
        params.setRedirectUri("http://localhost:8080/facebook");
        params.setScope("email");
        return createConnection().getOAuthOperations().buildAuthenticateUrl(params);
    }

    @Override
    public void generateFacebookAccessToken(String code) {
        accessToken = createConnection().getOAuthOperations().exchangeForAccess(code, "http://localhost:8080/facebook", null).getAccessToken();
    }

    @Override
    public String getUserData() {
        Facebook facebook = new FacebookTemplate(accessToken);
        String[] fields = {"id", "name"};
        return facebook.fetchObject("me", String.class, fields);
    }

    @Override
    public PagedList<Account> getPages() {
        PagedList<Account> accounts = new FacebookTemplate(accessToken).pageOperations().getAccounts();
        pageAccessToken = accounts.get(0).getAccessToken();
        return accounts;
    }

    @Override
    public Page getUsersLiked(String id) {
        return new FacebookTemplate(pageAccessToken).pageOperations().getPage(id);
    }
}
