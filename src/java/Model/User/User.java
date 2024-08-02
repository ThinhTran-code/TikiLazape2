/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.User;
import java.sql.Date;
/**
 *
 * @author hbtth
 */
public class User extends Account{
    protected String fullname;  
    protected String phoneNumber;
    protected String email;
    protected int gender;
    protected String address;
    protected Date DOB;
    protected String image;
    

    public User() {
    }

    public User(String username, String password) {
        super(username, password);
    }

    public User(int user_id, String username, String password, Date startDate, boolean auth) {
        super(user_id, username, password, startDate, auth);
    }

    public User(String fullname, String email, int gender, String address, Date DOB, String image) {
        this.fullname = fullname;
        this.email = email;
        this.gender = gender;
        this.address = address;
        this.DOB = DOB;
        this.image = image;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
