package com.cs2340.team6.totallyratticalapp;

/**
 * This class represents a User object
 *
 * @author Group 6
 * @version 1.0
 * @since whenever
 *
 */

public class User {
    private String name;
    private String username;
    private String password;
    private Role role;

    /**
     * Creates user with given attributes
     *
     * @param name User's name
     * @param username user's username
     * @param password user's password
     * @param role user's role
     */
    public User (String name, String username, String password, Role role) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    /**
     * Returns name attribute for this user
     *
     * @return name
     */
    public String getName() {return name;}

    /**
     * Returns username attribute for this user
     *
     * @return username
     */
    public String getUsername() {return username;}

    /**
     * Returns password attribute for this user
     *
     * @return password
     */
    public String getPassword() {return password;}

    /**
     * Returns Role attribute for this user
     *
     * @return user's role
     */
    public Role getRole() {return role;}

    /**
     * This holds the possible values for the role: User and Admin
     *
     */
    public enum Role {
        USER ("User"),
        ADMIN ("Admin");

        private String roleString;
        Role(String string) {
            roleString = string;
        }

        /**
         * Returns string representation of the type
         *
         * @return String representation
         */
        public String toString() {return roleString;}
    }

    /**
     * Return string representation of this user object
     *
     * @return string representation
     */
    public String toString() {
        return name + " " + username + " " + password + " " + role.toString();
    }
}
