/*
 * Login.java is a Model in the Model-View-Service (MCS) design pattern.
 * Login.java provides data for the construction of GetLogin POJOs.
 */
package com.mitsurishi.repairdbapi;

public class Login {
    private String username;
    private String password;


    // Parameterized Login constructor
    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }


    // Empty Login constructor
    public Login() {
    }

    // Accessors and mutators
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
