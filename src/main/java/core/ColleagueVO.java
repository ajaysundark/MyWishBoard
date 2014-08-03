package main.java.core;

import java.sql.Date;

public class ColleagueVO {
        String last_name;
        Date dob;
        String designation;
        String contact_no;
        String email;
        String location;
        public String getLast_name() {
                return last_name;
        }
        public void setLast_name(String last_name) {
                this.last_name = last_name;
        }
        public Date getDob() {
                return dob;
        }
        public void setDob(Date dob) {
                this.dob = dob;
        }
        public String getDesignation() {
                return designation;
        }
        public void setDesignation(String designation) {
                this.designation = designation;
        }
        public String getContact_no() {
                return contact_no;
        }
        public void setContact_no(String contact_no) {
                this.contact_no = contact_no;
        }
        public String getEmail() {
                return email;
        }
        public void setEmail(String email) {
                this.email = email;
        }
        public String getLocation() {
                return location;
        }
        public void setLocation(String location) {
                this.location = location;
        }
}