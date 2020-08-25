package com.example.freedev;

import com.google.firebase.database.Exclude;

public class Request {
    private String FKey;
    private String FName;
    private String FEmail;
    private String FSubject;
    private String FDescription;
    private String FStatus;
    public Request(){
    }
    public Request(String name, String email, String subject, String description, String status){

     FName=name;
     FEmail=email;
     FSubject=subject;
     FDescription=description;
     FStatus=status;


    }

@Exclude
    public String getKey() {
        return FKey;
    }
    @Exclude
    public void setKey(String key) {
        this.FKey = key;
    }

    public String getName() {
        return FName;
    }

    public void setName(String name) {

        this.FName = name;
    }

    public String getEmail() {

        return FEmail;
    }

    public void setEmail(String email) {

        this.FEmail = email;
    }

    public String getSubject() {
        return FSubject;
    }

    public void setSubject(String subject) {

        this.FSubject = subject;
    }

    public String getDescription() {
        return FDescription ;
    }

    public void setDescription(String description) {
        this.FDescription = description;
    }

    public String getStatus() {
        return FStatus;
    }

    public void setStatus(String status) {
        this.FStatus = status;
    }


}
