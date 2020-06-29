/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smart.tech;

/**
 *
 * @author pamulamba
 */
public class Borrow {
    /**
     * Composition: Objects as instance variable of other class
     */
    private Subscriber subscriber;
    private Book book;
    private int returnDate;
    private static int count;
    
    // Ctor
    public Borrow(){}
    
    public Borrow(Subscriber subscriber_, Book book_, int return_date){
        subscriber = new Subscriber(subscriber_);
        book = new Book(book_);
        returnDate = return_date;
        ++count;
    }
    
    // Accessor methods
    public Subscriber getSubscriber(){
        return subscriber;
    }
    
    public Book getBook(){
        return book;
    }
    
    public int getReturnDate(){
        return returnDate;
    }
    
    public static int getCount(){
        return count;
    }
    
    // Copy ctor
    public Borrow(Borrow borrow){
        this.subscriber = borrow.getSubscriber();
        this.book = borrow.getBook();
        this.returnDate = borrow.getReturnDate();
    }
    
    // Mutator methods
    public void setSubscriber(Subscriber subscriber_){
        subscriber = ((subscriber_ != null) ? new Subscriber(subscriber_) : null);
    }
    
    public void setBook(Book book_){
        book = ((book_ != null) ? new Book(book_) : null);
    }
    
    public void setReturnDate(int return_date){
        returnDate = ((return_date < getReturnDate()) ? getReturnDate() : return_date);
    }
    
    // Builder design pattern methods
    public Borrow subscriber(Subscriber subscriber){
        this.subscriber = subscriber;
        return this;
    }
    
    public Borrow book(Book book){
        this.book = book;
        return this;
    }
    
    public Borrow returnDate(int return_date){
        this.returnDate = return_date;
        return this;
    }
    
    // Printing method
    public void print(){
        System.out.println("\n==============================================================================================");
        subscriber.print();
        book.print();
        System.out.printf("Borrow number #%d. Return date %d", Borrow.getCount(), getReturnDate());
    }
    
}
