package com.example.emos.wx.db.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;

@Mapper
public  interface TbUserDao {
   public  boolean haveRootUser();
   public int insert(HashMap param);
   public  Integer searchIdByOpenId(String s);
}