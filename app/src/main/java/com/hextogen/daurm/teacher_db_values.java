package com.hextogen.daurm;

public class teacher_db_values {


    String id, t_name, email, password, dept , desig;


    public teacher_db_values(){

    }

    public teacher_db_values(String id,String t_name, String email, String password, String dept,String desig) {
        this.id = id;
        this.t_name = t_name;
        this.email = email;
        this.password = password;
        this.dept = dept;
        this.desig = desig;
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

    public String getDesig(){ return desig; }

    public void setId(String id) {
        this.id = id;
    }

    public void setT_name(String t_name) {
        this.t_name = t_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public void setDesig(String desig) {
        this.desig = desig;
    }
}
