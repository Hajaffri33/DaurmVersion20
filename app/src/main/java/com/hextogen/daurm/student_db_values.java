package com.hextogen.daurm;

public class student_db_values {

    String id, s_name, rolln, email, password, dept;

    public student_db_values(){


    }

    public student_db_values(String id, String s_name, String rolln, String email, String password, String dept) {
        this.id = id;
        this.s_name = s_name;
        this.rolln = rolln;
        this.email = email;
        this.password = password;
        this.dept = dept;
    }

    public String getId() {
        return id;
    }

    public String getS_name() {
        return s_name;
    }

    public String getRolln() {
        return rolln;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getDept() {
        return dept;
    }

}
