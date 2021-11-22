package com.bezkoder.spring.datajpa.model;

public class AccountProfile {
    Account account;
    Profile profile;
    Signon signon;
    BannerData bannerData;

    AccountProfile(String userId, String email, String fName, String lName, String status, String addr1, String addr2, String city, String state, String zip, String country, String phone, String favcategory, String bannername, String languagePreference, String favouriteCategoryId, boolean listOption, boolean bannerOption, String password){
        
        account = new Account(userId, email, fName, lName, status, addr1, addr2, city, state, zip, country, phone);
        
        profile = new Profile(userId, languagePreference, favouriteCategoryId, listOption, bannerOption);
        
        signon = new Signon(userId, password);
        
        bannerData = new BannerData(favcategory, bannername);
    
    }

    public Account getAccount(){
        return this.account;
    }
    
    public Profile getProfile(){
        return this.profile;
    }

    public Signon getSignon(){
        return this.signon;
    }
    
    public BannerData getBannerData(){
        return this.bannerData;
    }
}