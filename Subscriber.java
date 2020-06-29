/**
 * File: Subscriber.java
 * Author(s): Pierre Abraham Mulamba
 * Date of creation (modification): 20200627 (20200627)
 * Description: creating the class Subscriber with default values using
 * the builder design pattern method
 * link: https://stackoverflow.com/questions/222214/
 * managing-constructors-with-many-parameters-in-java/222295#222295
 * Compilation: javac *.java
 * Run: java Main
 * Note: jdk (Java development Kits)
 *       jre (Java Runtime Environment)
 *       se (Standard Edition)
 * 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smart.tech;

/**
 * Attributes
 * ==========
 * idNumber
 * firstName
 * lastName
 * age
 * count
 * 
 * Methods
 * =========
 * Subscriber(): ctor
 * Subscriber(idNumber, firstName, lastName, age)
 * Accessor 
 * Mutator
 * print
 * @author pamulamba
 */
public class Subscriber extends Object {
    private String idNumber;
    private String firstName;
    private String lastName;
    private int age;
    private static int count;
    
    // Ctor
    public Subscriber(){}
    
    public Subscriber(String id_number, String first_name, String last_name, int age_){
        idNumber = id_number;
        firstName = first_name;
        lastName = last_name;
        age = age_;
        ++count;
    }
    
    // Accessor methods
    public String getIdNumber(){
        return idNumber;
    }
    
    public String getFirstName(){
        return firstName;
    }
    
    public String getLastName(){
        return lastName;
    }
    
    public int getAge(){
        return age;
    }
    
    public static int getCount(){
        return count;
    }
    
    // Copy ctor
    public Subscriber(Subscriber subscriber){
        this.idNumber = subscriber.getIdNumber();
        this.firstName = subscriber.getFirstName();
        this.lastName = subscriber.getLastName();
        this.age = subscriber.getAge();
    }
    
    // Mutator methods
    public void setIdNumber(String id_number){
        idNumber = ((0 < id_number.length() && id_number.length() <= 6) ? id_number : "" );
    }
    
    public void setFirstName(String first_name){
        firstName = ((0 < first_name.length() && first_name.length() <= 10 ) ? first_name : "");
    }
    
    public void setLastName(String last_name){
        lastName = ((0 < last_name.length() && last_name.length() <= 10 ) ? last_name : "");
    }
    
    public void setAge(int age_){
        age = ((3 <= age_ && age_ <= 120) ? age_ : 0);
    }
    
    // Builder design pattern methods
    public Subscriber idNumber(String id_number){
        this.idNumber = id_number;
        return this;
    }
    
    public Subscriber firstName(String first_name){
        this.firstName = first_name;
        return this;
    }
    
    public Subscriber lastName(String last_name){
        this.lastName = last_name;
        return this;
    }
    
    public Subscriber age(int age_){
        this.age = age_;
        return this;
    }
    
    // Printing methods
     public void print(){
         System.out.printf("\nSubscriber: #%s. %s , %s. %d\n",
                 getIdNumber(), getLastName(), getFirstName(), getAge());
    }
   
} // End class Subscriber
