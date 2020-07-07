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
public class Library {
    // Attributes or instances variables
    private final int SIZE_SUBS = 100;
    private final Subscriber[] listSubs;
    private int nSubscribers;
    
    private final int SIZE_BOOKS = 1000;
    private final Book[] listBooks;
    private int nBooks;
    
    private final int SIZE_BORROWS = 200;
    private final Borrow[] listBorrows;
    private int nBorrows;
    
    // Ctor    
    public Library(){
        listSubs = new Subscriber[SIZE_SUBS];
        listBooks = new Book[SIZE_BOOKS];
        listBorrows = new Borrow[SIZE_BORROWS];
        nSubscribers = 0;
        nBooks = 0;
        nBorrows = 0;
    }
    
    /**
     * Add an instance of Subscriber to the array of Subscribers
     * @param subscriber 
     */
    public void addSubscriber(Subscriber subscriber){
        if (nSubscribers < SIZE_SUBS) {
            listSubs[nSubscribers++] = new Subscriber(subscriber);
            subscriber.print();
            System.out.println("Added " + nSubscribers + " Subscriber(s) Successfully to the library\n");
        }        
    }
    
    /**
     * Delete an instance of Subscriber based on the id_number of a Subscriber object
     * @param id_number 
     */
    public void delSubscriber(String id_number){
        for (int i = 0; i < nSubscribers; i++) {
            if (listSubs[i].getIdNumber().equals(id_number)) {
                listSubs[i] = listSubs[nSubscribers - 1];
                nSubscribers--;
                break;
            }
        }
    }
    
    /**
     * Add an instance of Book to the array of Books
     * @param book 
     */
    public void addBook(Book book){
        if (nBooks < SIZE_BOOKS) {
            listBooks[nBooks++] = new Book(book);
            book.print();
            System.out.println("Added " + nBooks + " Book(s) Successfully to the Library\n");
        }       
    }
    
    /**
     * Delete an instance of Books based on the quote of a Book object
     * @param quote 
     */
    public void delBook(String quote){
        for(int i = 0; i < nBooks; i++){
            if (listBooks[i].getQuote().equals(quote) ) {
                listBooks[i] = listBooks[nBooks -1];
                nBooks--;
                break;
            }
        }       
    }
    
    /**
     * Adding an instance of Borrow the the list of Borrow
     * @param borrow 
     */
    private void addBorrow(Borrow borrow){
        if(nBorrows < SIZE_BORROWS){
            listBorrows[nBorrows++] = new Borrow(borrow);
            borrow.print();
            System.out.println("\nAdded " + nBorrows + " Borrow(s) Successfully to the Library");
        }
    }
    
    /**
     * Delete an instance of Borrow  from the list of Borrowers
     * @param borrow 
     */
    private void delBorrow(Borrow borrow){
        for (int i = 0; i < nBorrows; i++) {
            if (listBorrows[i].getSubscriber().getIdNumber().equals(borrow.getSubscriber().getIdNumber()) && 
                    listBorrows[i].getBook().getQuote().equals(borrow.getBook().getQuote())) {
                listBorrows[i] = listBorrows[nBorrows - 1];
                nBorrows--;
                System.out.println("\nSuccessful Return Process");
                break;
            }
        }        
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
        
        for (int i = 0; i < nBooks ; i++) {
            if(listBooks[i].getTitle().contains(title)){
                listBooks[i].print();
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
        
        for (int i = 0; i < nBooks; i++) {
            if (listBooks[i].getQuote().contains(quote)) {
                listBooks[i].print();
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
        
        if(nBorrows == 0){
            /**
             * Loop to check if the book is available -- condition 1. 
             */
            for (int i = 0; i < nBooks; i++) {
                if (listBooks[i].getQuote().equals(book_quote)) {
                    if (listBooks[i].getNAvailable() > 0) {
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
            for (int j = 0; j < nSubscribers; j++) {
                if(listSubs[j].getIdNumber().equals(sub_id_number)){
                    if (listSubs[j].getAge() >= listBooks[indexBook].getMinReaderAge()) {
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
                listBooks[indexBook].nAvailables(listBooks[indexBook].getNAvailable() - 1);
                Borrow tmp = new Borrow(listSubs[indexSub], listBooks[indexBook], borrow_return_date);
                addBorrow(tmp);
                isSuccessful = true;
                System.out.println("Successful Borrowing Process");
            }
        }else if (nBorrows > 0) {
            /**
             * Loop to check if the book is available -- condition 1. 
             */
            for (int i = 0; i < nBooks; i++) {
                if (listBooks[i].getQuote().equals(book_quote)) {
                    if (listBooks[i].getNAvailable() > 0) {
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
            for (int j = 0; j < nSubscribers; j++) {
                if(listSubs[j].getIdNumber().equals(sub_id_number)){
                    if (listSubs[j].getAge() >= listBooks[indexBook].getMinReaderAge()) {
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
            for (int k = 0; k < nBorrows; k++) {
                if (listBorrows[k].getSubscriber().getIdNumber().equals(sub_id_number)) {
                    nTimesAllowedToBorrow++;
                }
                
                /**
                 * Check if the subscriber has already borrowed the same book
                 */
                if (listBorrows[k].getSubscriber().getIdNumber().equals(sub_id_number) && 
                       listBorrows[k].getBook().getQuote().equals(book_quote) ){
                    hasSubscriberBorrowedTheBook = true;
                }
            }
            
            boolean meet_conditions = false;
            meet_conditions = (!hasSubscriberBorrowedTheBook) && (nTimesAllowedToBorrow < MAXIMUM_BORROW) && 
                    (isBookAvailable && hasRequiredAge);
            
            if (meet_conditions) {
                //listBorrows[nBorrows++] = new Borrow(listSubs[indexSub], listBooks[indexBook], borrow_return_date);
                listBooks[indexBook].nAvailables(listBooks[indexBook].getNAvailable() - 1);
                Borrow tmp = new Borrow(listSubs[indexSub], listBooks[indexBook], borrow_return_date);
                addBorrow(tmp);
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
        
        for (int i = 0; i < nBorrows; i++) {
            if (listBorrows[i].getSubscriber().getIdNumber().equals(sub_id_number) && 
                    listBorrows[i].getBook().getQuote().equals(book_quote)) {
                delBorrow(listBorrows[i]);
                //listBorrows[i] = listBorrows[nBorrows - 1];
                //nBorrows--;
                isSuccessful = true;
                //System.out.println("Successful Return Process");
                break;
            }
            
            for (int j = 0; j < nBooks; j++) {
                if (listBooks[j].getQuote().equals(book_quote)) {
                    listBooks[j].setNAvalaible(listBooks[j].getNAvailable() + 1);
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
        for (int i = 0; i < nBorrows; i++) {
            if (listBorrows[i].getSubscriber().getIdNumber().equals(sub_id_number)) {
                listBorrows[i].getSubscriber().print();
            }
            break;
        }
        
        for (int i = 0; i < nBorrows; i++) {
            if (listBorrows[i].getSubscriber().getIdNumber().equals(sub_id_number)) {
                listBorrows[i].print();
            }
        }
    }
    
    public void print(){
        /**
         * Printing the list of Subscribers
         */
        System.out.println("\n// PRINTING THE SUBSCRIBERS OF THE LIBRARY");
        for (int i = 0; i < nSubscribers; i++) {
            listSubs[i].print();            
        }
        
        /**
         * Printing the list of Books
         */
        System.out.println("\n// PRINTING THE BOOKS OF THE LIBRARY");
        for (int i = 0; i < nBooks; i++) {
            listBooks[i].print();
        }
        
        /**
         * Printing the list of Borrows
         */
        System.out.println("\n// PRINTING THE BORROWS OF THE LIBRARY");
        for (int i = 0; i < nBorrows; i++) {
            listBorrows[i].print();
        }
    }
    
    
} // End of class Library
