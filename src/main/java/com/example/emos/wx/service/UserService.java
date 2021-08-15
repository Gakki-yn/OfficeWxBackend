package com.example.emos.wx.service;

import org.springframework.stereotype.Service;

import java.util.Set;


public interface UserService {

    public int registerUser(String registerCode, String code, String nickname, String photo);

    public Set<String> searchUserPermissions(int userId);

}