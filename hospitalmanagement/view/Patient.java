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
public class Patient {
    private String id;
    private String name;
    private String dob;
    private String gender;
    private String bloodgroup;
    private String phonenumber;
    private String docid;
    private String medid;

    public Patient(String id, String name, String dob, String gender, String bloodgroup, String phonenumber, String docid, String medid) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.bloodgroup = bloodgroup;
        this.phonenumber = phonenumber;
        this.docid = docid;
        this.medid = medid;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBloodgroup() {
        return bloodgroup;
    }

    public void setBloodgroup(String bloodgroup) {
        this.bloodgroup = bloodgroup;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getDocid() {
        return docid;
    }

    public void setDocid(String docid) {
        this.docid = docid;
    }

    public String getMedid() {
        return medid;
    }

    public void setMedid(String medid) {
        this.medid = medid;
    }
    
    
}
