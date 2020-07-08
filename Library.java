/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smart.tech;
import java.util.ArrayList;

/**
 * File: Library.java
 * @author pamulamba
 */
public class Library {
    // Attributes or instances variables
    private final ArrayList<Subscriber> listSubs;
    private final ArrayList<Book> listBooks;
    private final ArrayList<Borrow> listBorrows;
      
    // Ctor    
    public Library(){
        listSubs = new ArrayList<>();
        listBooks = new ArrayList<>();
        listBorrows = new ArrayList<>();
    }
    
    
    public ArrayList<Subscriber> getListSubs(){
        return listSubs;
    }
    
    public ArrayList<Book> getListBooks(){
        return listBooks;
    }
    
    public ArrayList<Borrow> getListBorrows(){
        return listBorrows;
    }
    
  
   
   
    
    /**
     * Search and display all the books of the library with a title containing the 
     * provided title pattern
     * @param title 
     */
    public void searchBookByTitle(String title){
        
        // System.out.println(nBooks);
        
        boolean isBookTitlePresent;
        isBookTitlePresent = false;
        
        for (int i = 0; i < listBooks.size() ; i++) {
            if(listBooks.get(i).getTitle().contains(title)){
                listBooks.get(i).print();
                isBookTitlePresent = true;
            }
        }
        
        if (!isBookTitlePresent) {
            System.out.println("\nNo book found with a title matching: " + title);
        }
    } // End of the method searchBookByTitle
    
    /**
     * Search and display all the books of the library with a quote containing the
     * provided quote pattern
     * @param quote 
     */
    public void searchBookByQuote(String quote){
        
        boolean isBookQuotePresent;
        isBookQuotePresent = false;
        
        for (int i = 0; i < listBooks.size(); i++) {
            if (listBooks.get(i).getQuote().contains(quote)) {
                listBooks.get(i).print();
                isBookQuotePresent = true;
            }
        }
        
        if (!isBookQuotePresent) {
            System.out.println("No book found with a quote matching: " + quote);
        }
    } // End of the method searchBookByQuote
    
   /**
    * Check if it is possible for a Subscriber to borrow a book
    * If yes then a new Borrow instance is created and added to the listBorrows
    * It is possible for a Subscriber to borrow a Book under the following conditions:
    * 1. The Book is available
    * 2. The Subscriber has the required age to read the book
    * 3. The Subscriber does not already borrow the same book
    * 4. The Subscriber did not exceeds the maximum number of books allowed to borrow
    * @param sub_id_number
    * @param book_quote
    * @param borrow_return_date
    * @return 
    */
    public boolean borrowingBookBySubcriber(String sub_id_number, String book_quote, int borrow_return_date){
        boolean isSuccessful;
        isSuccessful = false;
        boolean isBookAvailable;
        isBookAvailable = false;
        boolean hasRequiredAge;
        hasRequiredAge = false;
        
        final int MAXIMUM_BORROW = 2;
        
        int indexBook = 0;
        int indexSub = 0;
        
        if(listBorrows.isEmpty()){
            /**
             * Loop to check if the book is available -- condition 1. 
             */
            for (int i = 0; i < listBooks.size(); i++) {
                if (listBooks.get(i).getQuote().equals(book_quote)) {
                    if (0 < listBooks.get(i).getNAvailable()) {
                        indexBook = i;
                        isBookAvailable = true;
                        break;
                    }
                    break;
                }
            }
            
            /**
             * Loop to check if the Subscriber meets the required age to read to book -- condition 2.
             */
            for (int j = 0; j < listSubs.size(); j++) {
                if(listSubs.get(j).getIdNumber().equals(sub_id_number)){
                    if (listSubs.get(j).getAge() >= listBooks.get(indexBook).getMinReaderAge()) {
                        indexSub = j;
                        hasRequiredAge = true;
                        break;
                    }
                }
            }
            
            /**
             * Since list of borrows is empty therefore conditions 3 and 4 are met
             */
            boolean meeting_initial_conditions;
            meeting_initial_conditions = false;
            
            meeting_initial_conditions = isBookAvailable && hasRequiredAge;            
            
            if (meeting_initial_conditions) {
                //listBorrows[nBorrows++] = new Borrow(listSubs[indexSub], listBooks[indexBook], borrow_return_date);
                listBooks.get(indexBook).nAvailables(listBooks.get(indexBook).getNAvailable() - 1);
                Borrow tmp = new Borrow(listSubs.get(indexSub), listBooks.get(indexBook), borrow_return_date);
                listBorrows.add(tmp);
                System.out.println("\nAdded " + listBorrows.size() + " Borrow(s) Successfully to the Library");
                isSuccessful = true;
                System.out.println("Successful Borrowing Process");
            }
        }else if (0 < listBorrows.size()) {
            /**
             * Loop to check if the book is available -- condition 1. 
             */
            for (int i = 0; i < listBooks.size(); i++) {
                if (listBooks.get(i).getQuote().equals(book_quote)) {
                    if (0 < listBooks.get(i).getNAvailable()) {
                        indexBook = i;
                        isBookAvailable = true;
                        break;
                    }
                    break;                    
                }                                
            }
            
            /**
             * Loop to check if the Subscriber meets the required age to read to book -- condition 2.
             */
            for (int j = 0; j < listSubs.size(); j++) {
                if(listSubs.get(j).getIdNumber().equals(sub_id_number)){
                    if (listSubs.get(j).getAge() >= listBooks.get(indexBook).getMinReaderAge()) {
                        indexSub = j;
                        hasRequiredAge = true;
                        break;
                    }
                    break;
                }
            }
            
            boolean hasSubscriberBorrowedTheBook;
            hasSubscriberBorrowedTheBook = false;
            
            int nTimesAllowedToBorrow = 0;
            
            /**
             * Loop to count the number of times a Subscriber occurs in the list of Borrows -- condition 4.
             */
            for (int k = 0; k < listBorrows.size(); k++) {
                if (listBorrows.get(k).getSubscriber().getIdNumber().equals(sub_id_number)) {
                    nTimesAllowedToBorrow++;
                }
                
                /**
                 * Check if the subscriber has already borrowed the same book
                 */
                if (listBorrows.get(k).getSubscriber().getIdNumber().equals(sub_id_number) && 
                       listBorrows.get(k).getBook().getQuote().equals(book_quote) ){
                    hasSubscriberBorrowedTheBook = true;
                }
            }
            
            boolean meet_conditions = false;
            meet_conditions = (!hasSubscriberBorrowedTheBook) && (nTimesAllowedToBorrow < MAXIMUM_BORROW) && 
                    (isBookAvailable && hasRequiredAge);
            
            if (meet_conditions) {
                //listBorrows[nBorrows++] = new Borrow(listSubs[indexSub], listBooks[indexBook], borrow_return_date);
                listBooks.get(indexBook).nAvailables(listBooks.get(indexBook).getNAvailable() - 1);
                Borrow tmp = new Borrow(listSubs.get(indexSub), listBooks.get(indexBook), borrow_return_date);
                listBorrows.add(tmp); 
                System.out.println("\nAdded " + listBorrows.size() + " Borrow(s) Successfully to the Library");
                isSuccessful = true;
                System.out.println("Successful Borrowing Process");                
            }
            
        }else{
            isSuccessful = false;
        }
        return isSuccessful;
    } // End of the method borrowingBookBySubscriber
    
    /**
     * Return a Book borrowed by the subscriber
     * If the subscriber borrowed that book then the borrow record is deleted from the list of borrowers
     * Else nothing is done
     * The method return a Boolean value to for a successful or failed  return
     * @param sub_id_number
     * @param book_quote
     * @return 
     */
    public boolean returnBookBySubscriber(String sub_id_number, String book_quote){
        boolean isSuccessful;
        isSuccessful = false;
        
        for (int i = 0; i < listBorrows.size(); i++) {
            if (listBorrows.get(i).getSubscriber().getIdNumber().equals(sub_id_number) && 
                    listBorrows.get(i).getBook().getQuote().equals(book_quote)) {
                listBorrows.remove(i);
                // delBorrow(listBorrows[i]);
                //listBorrows[i] = listBorrows[nBorrows - 1];
                //nBorrows--;
                isSuccessful = true;
                //System.out.println("Successful Return Process");
                break;
            }
            
            for (int j = 0; j < listBooks.size(); j++) {
                if (listBooks.get(j).getQuote().equals(book_quote)) {
                    listBooks.get(j).setNAvalaible(listBooks.get(j).getNAvailable() + 1);
                    break;
                }
                
            }
        }
        
        return isSuccessful;
    }
    
    /**
     * Display the information about a Subscriber(lastName, firstName, Borrow).
     * @param sub_id_number 
     */
    public void infoSubscriber(String sub_id_number){
        for (int i = 0; i < listBorrows.size(); i++) {
            if (listBorrows.get(i).getSubscriber().getIdNumber().equals(sub_id_number)) {
                listBorrows.get(i).getSubscriber().print();
            }
            break;
        }
        
        for (int i = 0; i < listBorrows.size(); i++) {
            if (listBorrows.get(i).getSubscriber().getIdNumber().equals(sub_id_number)) {
                listBorrows.get(i).print();
            }
        }
    }
    
    public void print(){
        /**
         * Printing the list of Subscribers
         */
        System.out.println("\n// PRINTING THE SUBSCRIBERS OF THE LIBRARY");
        for (int i = 0; i < listSubs.size(); i++) {
            listSubs.get(i).print();            
        }
        
        /**
         * Printing the list of Books
         */
        System.out.println("\n// PRINTING THE BOOKS OF THE LIBRARY");
        for (int i = 0; i < listBooks.size(); i++) {
            listBooks.get(i).print();
        }
        
        /**
         * Printing the list of Borrows
         */
        System.out.println("\n// PRINTING THE BORROWS OF THE LIBRARY");
        for (int i = 0; i < listBorrows.size(); i++) {
            listBorrows.get(i).print();
        }
    }
    
    
} // End of class Library
