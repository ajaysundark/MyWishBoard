package main.java.core;

import java.util.Date;

public class ColleagueBuilder {

		private String firstName;
        private String lastName;
        private Date dob;
        private String designation;
        private String contact;
        private String email;
        private String location;

        public static ColleagueBuilder aColleague() {
                return new ColleagueBuilder();
        }


        public ColleagueBuilder addLocation(String location) {
                this.location = location;
                return this;
        }

        public ColleagueBuilder addEmailId(String email) {
                this.email = email;
                return this;
        }

        public ColleagueBuilder addContactNo(String contact) {
                this.contact = contact;
                return this;
        }

        public ColleagueBuilder addDesignation(String designation) {
                this.designation = designation;
                return this;
        }

        public ColleagueBuilder addDOB(Date dob) {
                this.dob = dob;
                return this;
        }

        public ColleagueBuilder addLastName(String lastName) {
                this.lastName = lastName;
                return this;
        }
        
        public ColleagueBuilder addFirstName(String firstName) {
        	this.firstName = firstName;
        	return this;
        }

        public ColleagueVO build() {
                ColleagueVO colleague = new ColleagueVO();
                addFieldsToVO(colleague);
                return colleague;
        }

        private void addFieldsToVO(ColleagueVO colleague) {
        		colleague.setFirst_name(this.firstName);
                colleague.setLast_name(this.lastName);
                colleague.setDob(this.dob);
                colleague.setDesignation(this.designation);
                colleague.setContact_no(this.contact);
                colleague.setEmail(this.email);
                colleague.setLocation(this.location);
        }

}
