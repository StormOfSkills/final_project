package com.codingelab.tutorial;

public class Name {


        private String name;
        private String phone;
        private String email;
        private int status;

        public Name(String name, String phone, String email, int status) {
            this.name = name;
            this.phone = phone;
            this.email = email;
            this.status = status;
        }

        public String getName() {
            return name;
        }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public int getStatus(){
            return status;
        }
}
