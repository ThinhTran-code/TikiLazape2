/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.User;

/**
 *
 * @author hbtth
 */
public class Oauth_Account extends User{
    private String oauth_user_id;
    private int from;

    public Oauth_Account() {
    }

    public Oauth_Account(String oauth_user_id, int from) {
        this.oauth_user_id = oauth_user_id;
        this.from = from;
    }

    public String getOauth_user_id() {
        return oauth_user_id;
    }

    public void setOauth_user_id(String oauth_user_id) {
        this.oauth_user_id = oauth_user_id;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }
    
    
}
