package core;

import java.util.Date;

public class ColleagueVO {
		String first_name;
        public String getFirst_name()
		{
			return first_name;
		}
		public void setFirst_name(String first_name)
		{
			this.first_name = first_name;
		}

		String last_name;
        public String getLast_name() {
                return last_name;
        }
        public void setLast_name(String last_name) {
                this.last_name = last_name;
        }
        
        Date dob;
        public Date getDob() {
                return dob;
        }
        public void setDob(Date dob) {
                this.dob = dob;
        }
        
        String designation;
        public String getDesignation() {
                return designation;
        }
        public void setDesignation(String designation) {
                this.designation = designation;
        }
        
        String contact_no;
        public String getContact_no() {
                return contact_no;
        }
        public void setContact_no(String contact_no) {
                this.contact_no = contact_no;
        }
        
        String email;
        public String getEmail() {
                return email;
        }
        public void setEmail(String email) {
                this.email = email;
        }
        
        String location;
        public String getLocation() {
                return location;
        }
        public void setLocation(String location) {
                this.location = location;
        }
        
		@Override
		public String toString()
		{
			return "ColleagueVO [first_name=" + first_name + ", last_name=" + last_name + ", dob=" + dob + ", designation=" + designation + ", contact_no=" + contact_no + ", email=" + email
					+ ", location=" + location + "]";
		}

}