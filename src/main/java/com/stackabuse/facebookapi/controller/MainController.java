package com.stackabuse.facebookapi.controller;

import com.stackabuse.facebookapi.service.FacebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.*;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class MainController {

    @Autowired
    private FacebookService facebookService;
    private Facebook facebook;
    private ConnectionRepository connectionRepository;

    public MainController(Facebook facebook, ConnectionRepository connectionRepository) {
        this.facebook = facebook;
        this.connectionRepository = connectionRepository;
    }

//    @RequestMapping(value = "feed", method = RequestMethod.GET)
//    public String feed(Model model) {
//
//        if(connectionRepository.findPrimaryConnection(Facebook.class) == null) {
//            return "redirect:/connect/facebook";
//        }
//            String token = "EAAYVFTZACfBcBABUWxJnEqA9wn2P9jwCxWXcrockcXTz0ZAGb3ZAy49FprsZB9ZBnwPnaSHffai0sTNMDJDjoMdbUx3bK6ZBQ8rYnFj7RLnTSL1GdCw9whyDo6MynHz1z4XPEO3zTDVpmL6ZBjC7z3c8qVOtjt0ZC1nbqD9Dgz7ZAHF4LZBhxQC6R6mrdXgJZAlKraeB8EvzRcVrwZDZD";
//            facebook = new FacebookTemplate(token);
//            User userProfile = facebook.userOperations().getUserProfile();
//            model.addAttribute("userProfile", userProfile);
//            PagedList<Post> userFeed = facebook.feedOperations().getFeed();
//            model.addAttribute("userFeed", userFeed);
//            String pageId = facebook.pageOperations().getPage("https://www.facebook.com/Nightcore-Music-320403711889712").getId();
//            System.out.println(pageId);
//            String members = facebook.pageOperations().getPage(pageId).getMembers();
//            System.out.println(members);
//            return "feed";
////        }
//    }
//
//    @RequestMapping(value = "friends", method = RequestMethod.GET)
//    public String friends(Model model) {
//
//        if(connectionRepository.findPrimaryConnection(Facebook.class) == null) {
//            return "redirect:/connect/facebook";
//        }
//        String token = "EAAYVFTZACfBcBAJBvpZC6RwABhbM1Anwf3MSkILuDpr9m4IQoFsNCpZAech5THjafZBn0lcnWbg6Ypt64hXEnXOZBWJbe9WDAoIveGhpJ7Y9Luv1H6uADm7uQZAckcStCgX123hRaXCK2Kj4rJi3hee49rHkbB5ZBf5DSIyH7kvCzFHXGgrtqFnNRWoZAORH3NCjafOZCgb8ETgZDZD";
//        facebook = new FacebookTemplate(token);
//        User userProfile = facebook.userOperations().getUserProfile();
//        model.addAttribute("userProfile", userProfile);
//        List<User> friends = facebook.friendOperations().getFriendProfiles();
//        model.addAttribute("friends", friends);
//
//        return "friends";
//    }
    @GetMapping
    public List<String> welcome() {
        List<String> urls = new ArrayList<String>();
        urls.add("http://localhost:8080/generateFacebookAuthorizeUrl");
        urls.add("http://localhost:8080/getUserData");
        return urls;
    }

    @GetMapping("/generateFacebookAuthorizeUrl")
    public String generateFacebookAuthorizeUrl() {
        return facebookService.generateFacebookAuthorizeUrl();
    }

    @GetMapping("/facebook")
    public void generateFacebookAccessToken(@RequestParam("code") String code) {
        facebookService.generateFacebookAccessToken(code);
    }

    @GetMapping("/getUserData")
    public String getUserData() {
        return facebookService.getUserData();
    }

    @GetMapping("/getPages")
    public PagedList<Account> getPages() {
        return facebookService.getPages();
    }

    @GetMapping("/getPage")
    public Page getPage(@RequestParam("id") String id) {
        return facebookService.getUsersLiked(id);
    }
}
