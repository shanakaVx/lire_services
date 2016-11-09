/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Lire_Authentication;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Shanaka
 */
public class ConnectionManager {

    private Connection con = null;
    private String url;
    private String pw;
    private String un;

    public ConnectionManager(String URL, String un, String pw) {
        this.url = URL;
        this.un = un;
        this.pw = pw;

        try {
            con = DriverManager.getConnection(url, un, pw);
            System.out.println("MYSQL db connected at "+URL);
        } catch (SQLException ex) {
            System.out.println("DB Failed! " + ex);
        }
    }

    
    /*
    *
    * @desc Login the user
    * @param un user name
    * @param pw password
    * @return returns the application MD5 hash key to use in each API call
    *
    */
    public String login(String un, String pw) {
        String appKey = getHash();
        ResultSet rs = null;
        String truk = "TRUNCATE `lire`.`currentuser`";
        String query = "SELECT * FROM lire.users where email = '" + un + "' AND password = '" + pw + "';";
        
        System.out.println(query);

        try {
            Statement stmt = con.createStatement();
            stmt.execute(truk);
            rs = stmt.executeQuery(query);
            if(!rs.next())
                return "Failed";
            else{
                int c = stmt.executeUpdate("UPDATE `lire`.`users` SET `appKey`='"+appKey+"' WHERE `email`='"+un+"';");
                //stmt.executeQuery("UPDATE `lire`.`users` SET `appKey`='"+appKey+"' WHERE `email`='"+un+"';");
                if(c>0){
                    String id = getUid(appKey);
                    String intoUid = "INSERT INTO `lire`.`currentuser` (`uid`, `appkey`, `username`) VALUES ('"+id+"', '"+appKey+"', '"+un+"');";
                    stmt.executeUpdate(intoUid);
                    String uname = getUname(appKey);
                    String uid = getUid(appKey);
                    return appKey+","+uname+","+uid;
                }
            }
            
        } catch (SQLException ex) {
            System.out.println("sql error "+ex);
        }
        
        return "error!";
    }
    
    
    
    /*
    *
    * @desc logs out a user
    * @param un user name
    * @param appk appkey
    * @return returns success upone logout failed otherwise
    *
    */
    public String logOut(String un, String appk) {
        String query = "UPDATE `lire`.`users` SET `appKey`='1' WHERE `email`='"+un+"' and `appKey` = '"+appk+"';";
        String truk = "TRUNCATE `lire`.`currentuser`";
        
        try {
            Statement stmt = con.createStatement();
             int c = stmt.executeUpdate(query);
             if(c>0){
                 stmt.execute(truk);
                 return "success";
             }
        } catch (Exception e) {
            System.out.println("Sql error "+e);
        }
        return "error";
    }
    
    
    
    
    public String register(String mail, String name, String pw){
        String query = "INSERT INTO `lire`.`users` (`email`, `name`, `password`) VALUES ('"+mail+"', '"+name+"', '"+pw+"');";
        try {
            Statement stmt = con.createStatement();
            int c = stmt.executeUpdate(query);
            if(c>0)
                return "success";
        } catch (Exception e) {
            System.out.println("Sql error "+e);
        }
        
        return "error";
    }
    
    
    
    /*
    *
    * @desc get uid when the appkey given
    * @param appk application key
    * @return returns the uid
    *
    */
    public String getUid(String appk){
        String query = "SELECT uid FROM lire.users where appKey = '"+appk+"';";
        String uid = "No UID found";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                uid = rs.getString("uid");
            }
        } catch (Exception e) {
            System.out.println("Sql error - "+e);
        }
        return uid;
    }
    

    /*
    *
    * @desc get the current UID
    * @return returns the urrent uid of the user
    *
    */
    public String getUname(String appk){
        String query = "Select name from lire.users where appKey = '"+appk+"';";
        System.out.println(query);
        String uid = "notfound";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                uid = rs.getString("name");
            }
        } catch (Exception e) {
            System.out.println("Sql error - "+e);
        }
        System.out.println(uid);
        return uid;
    }
    
    
    /*
    *
    * @desc get the current UID
    * @return returns the urrent uid of the user
    *
    */
    public String getUidPhp(){
        String query = "SELECT * FROM lire.currentuser;";
        String uid = "No UID found";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                uid = rs.getString("uid");
            }
        } catch (Exception e) {
            System.out.println("Sql error - "+e);
        }
        return uid;
    }
    
    
    /*
    *
    * @desc check if the user has logged in
    * @param appKey the API MD5 hash pass key
    * @return true if user exsists false otherwise
    *
    */
    public boolean checkLogin(String appKey){
        String query = "SELECT * FROM lire.users where appKey = '"+appKey+"';";
        System.out.println(query);
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if(rs.next())
                return true;
            
        } catch (SQLException ex) {
            System.out.println("sql error "+ex);
        }
        return false;
    }
    
    
    
    /*
    *
    * @desc creates the md5 hash from the current date and time
    * @return returns the md5 hash
    *
    */
    private String getHash() {
   
        String original = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        MessageDigest md = null;
        
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        md.update(original.getBytes());
        byte[] digest = md.digest();
        StringBuilder sb = new StringBuilder();
        
        for (byte b : digest) {
            sb.append(String.format("%02x", b & 0xff));
        }
        
        return sb.toString();
    }
    
}
