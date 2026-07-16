package com.luv2code.springboot.employees.entity;

import jakarta.persistence.*;

@Entity    // JPA annotation that tells SpringBoot to treat this Java class as a DB table mapping, so each object can be stored in as a row in that table (move to Obsidian)
@Table(name="employee")  // optional JPA annotation that specifies the DB table this Entity maps to
public class Employee {

    @Id   // marks a field as the primary key of a JPA entity. It uniquely identifies each row, and every entity should have one @Id field. @Id is often paired with @GeneratedValue so the database generates the key automatically instead of you setting it manually.
    @GeneratedValue(strategy = GenerationType.IDENTITY)   // uses the database’s auto-increment/identity column
    @Column(name="id")
    private long id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email")
    private String email;

    public Employee() {
    }

/*
    public Employee(String firstName, String lastName, String email) {
        //this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
*/

    public Employee(long id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
