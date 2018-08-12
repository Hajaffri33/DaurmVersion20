package com.hextogen.daurm;

public class teacher_db_values {


    String id, t_name, email, password, dept;


    public teacher_db_values(){

    }

    public teacher_db_values(String id,String t_name, String email, String password, String dept) {
        this.id = id;
        this.t_name = t_name;
        this.email = email;
        this.password = password;
        this.dept = dept;
    }

    public String getId() {
        return id;
    }

    public String getT_name() {
        return t_name;
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
