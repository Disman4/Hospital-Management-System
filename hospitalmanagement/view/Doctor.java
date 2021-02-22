/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalmanagement.view;

/**
 *
 * @author Tanaka
 */
public class Doctor {
    private String id;
    private String name;
    private String dob;
    private String contact;
    private String email;
    private String homeaddress;

    public Doctor(String id, String name, String dob, String contact, String email, String homeaddress) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.contact = contact;
        this.email = email;
        this.homeaddress = homeaddress;
    }

    public String getHomeaddress() {
        return homeaddress;
    }

    public void setHomeaddress(String homeaddress) {
        this.homeaddress = homeaddress;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    
}
