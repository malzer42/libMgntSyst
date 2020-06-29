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
public class Book extends Object{
    private String quote;
    private String title;
    private int year;
    private int minReaderAge;
    private int nPossess;
    private int nAvailables;
    private static int count;
    
    // Ctor
    public Book(){
        quote = "quote";
        title = "title";
        year = 0;
        minReaderAge = 0;
        nPossess = 0;
        nAvailables = nPossess;
        ++count;
    }
    
    public Book(String quote_, String title_, int year_, int n_possess){
        quote = quote_;
        title = title_;
        year = year_;
        nPossess = n_possess;
        nAvailables = nPossess;
    }
    
    /**
     *
     * @throws Throwable
     */
    @Override
    protected void finalize() throws Throwable{
        try {
            --count;
        }finally {
            super.finalize();
        }
    }
    
    // Accessor methods
    public String getQuote(){
        return quote;
    }
    
    public String getTitle(){
        return title;
    }
    
    public int getYear(){
        return year;
    }
    
    public int getMinReaderAge(){
        return minReaderAge;
    }
    
    public int getNPossess(){
        return nPossess;
    }
    
    public int getNAvailable(){
        return nAvailables;
    }
    
    public static int getCount(){
        return count;
    }
    
    // Copy ctor
    public Book(Book book){
        this.quote = book.getQuote();
        this.title = book.getTitle();
        this.year = book.getYear();
        this.minReaderAge = book.getMinReaderAge();
        this.nPossess = book.getNPossess();
        this.nAvailables = book.getNAvailable();
    }
    
    // Mutator methods
    public void setQuote(String quote_){
        quote = ((0 < quote_.length() && quote_.length() <= 20) ? quote_ : "");
    }
    
    public void setTitle(String title_){
        title = ((0 < title_.length() && title_.length() <= 20) ? title_ : "");
    }
    
    public void setYear(int year_){
        year = ((1600 < year_ && year_ <= 2020 ) ? year_: 9999);
    }
    
    public void setMinReaderAge(int min_reader_age){
        minReaderAge = ((3 < min_reader_age && min_reader_age <= 120) ? min_reader_age : 999);
    }
    
    public void setNPossess(int n_possess){
        nPossess = ((0 < n_possess && n_possess <= 100) ? n_possess : 0);
    }
    
    public void setNAvalaible(int n_availables){
        nAvailables = ((n_availables <= getNPossess()) ? n_availables : 0);
    }
    
    // Builder design pattern methods
    public Book quote(String quote){
        this.quote = quote;
        return this;
    }
    
    public Book title(String title){
        this.title = title;
        return this;
    }
    
    public Book year(int year){
        this.year = year;
        return this;
    }
    
    public Book minReaderAge(int min_reader_age){
        this.minReaderAge = min_reader_age;
        return this;
    }
    
    public Book nPossess(int n_possess){
        this.nPossess = n_possess;
        return this;
    }
    
    public Book nAvailables(int n_availables){
        this.nAvailables = n_availables;
        return this;
    }
    
    // Printing method
    public void print(){
        System.out.printf("Book: %s. %s. %d. %d y.o. and more\n",
                getQuote(), getTitle(), getYear(), getMinReaderAge());
    }

} // End class Book
