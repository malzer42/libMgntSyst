/**
   File: Main.java
   Author(s): Pierre Abraham Mulamba
   Date of creation (modification): 20200614(20200614)
   Description: Driver for the lib_mgnt_sys
   Compilation: javac *.java
   Run: java Main
   Note: jdk (Java development Kits)
         jre (Java Runtime Environment)
         se (Standard Edition)
*/

import com.smart.tech.Subscriber;
import com.smart.tech.Book;
import com.smart.tech.Borrow;
import com.smart.tech.Library;

import java.util.*;
import java.io.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;  

class Main
{
    public static void main(String[] argvs){
	try{
            final long startTime = System.currentTimeMillis();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            System.out.println(dtf.format(now));
            // Process process = Runtime.getRuntime().exec(String.format("cls"));
            // process.waitFor();
            // Display the program message
            System.out.println("Library Management System (Lib_mgnt_sys)");
            System.out.println("If you fail to plan, you are planning to fail");
            
            /**
             * Testing the Supreme Commander
             * Converts centimeters to feet and inches
             */
            double cm;
            int feet;
            int inches;
            int remainder;

            final double CM_PER_INCH = 2.54; // final = constant in C++
            final int IN_PER_FOOT = 12;

            /**
             * create an objet jin of type Scanner that takes input
             * from terminal like cin in C++
             */
            Scanner jin = new Scanner(System.in);

            // Prompt the user and get the new value
            System.out.print("Exactly how many cm ? ");
            cm = jin.nextDouble();

            // Convert and output the result
            inches = (int)(cm/CM_PER_INCH);
            feet = inches / IN_PER_FOOT;
            remainder = inches % IN_PER_FOOT;
                       
            
            System.out.printf("%.2f cm = %d ft, %d in\n", cm, feet, remainder);
            
            System.out.println("\nLIBRARY MANAGEMENT INVENTORY SYSTEM");
            System.out.println("INTEGRATION TEST SECTION");

            // Creating instances of the class Subscriber
            final int SUBSCRIBER_ARRAY_SIZE = 3;
            Subscriber subs[] = new Subscriber[SUBSCRIBER_ARRAY_SIZE];

            for(int i = 0; i < subs.length; i++){
                subs[i] = new Subscriber();
            }   

            subs[0] = new Subscriber().idNumber("1839456").firstName("Jon").lastName("Doe").age(23);
            subs[1] = new Subscriber().idNumber("1630236").firstName("Nicolas").lastName("Gagnon").age(8);
            subs[2] = new Subscriber().idNumber("1268348").firstName("Martin").lastName("Tremblay").age(18);
            
            System.out.println("\n// Printing the instances of Subscriber");
            for (Subscriber sub : subs) {
                sub.print();
            }
            
            // Creating instances of the class Book
            final int BOOK_ARRAY_SIZE = 9;
            Book books[] = new Book[BOOK_ARRAY_SIZE];
            
            for(int i = 0; i < books.length; i++){
                books[i] = new Book();
            }
            // Creation of Books instances
            
            books[0] = new Book().quote("HB514").title("Big D++").year(2010).minReaderAge(9).nPossess(4);
            books[1] = new Book().quote("GA403").title("Big C++").year(2009).minReaderAge(8).nPossess(3); 
            books[2] = new Book().quote("QA203").title("Multiple variables Calculus part 1").year(2011).minReaderAge(3).nPossess(2);
            books[3] = new Book().quote("QA204").title("Multiple variable Calculus part 2").year(2011).minReaderAge(3).nPossess(2);
            books[4] = new Book().quote("AC409").title("Ortrante castle").year(1764).minReaderAge(16).nPossess(1);
            books[5] = new Book().quote("BD302").title("Harry P. and the Azkaban prisoner").year(1999).minReaderAge(3).nPossess(1);
            books[6] = new Book().quote("CE413").title("Ibssz  le prisionier c'balbcbo").year(2000).minReaderAge(4).nPossess(2);
            books[7] = new Book().quote("GA407").title("Big Java").year(2010).minReaderAge(9).nPossess(4); 
            books[8] = new Book(books[2]).quote("ZY969").title("Single Variable Calculus");

            System.out.println("\n// Printing the instances of the class Book");
            
            for(Book book : books){
                book.print();
            }
            
            // Creating instances of Borrow
            final int BORROW_ARRAY_SIZE = 4;
            Borrow borrows[] = new Borrow[BORROW_ARRAY_SIZE];
            
            for(int i = 0; i < borrows.length; i++){
                borrows[i] = new Borrow();
            }
            
            borrows[0] = new Borrow(subs[2], books[5], 201001);
            borrows[1] = new Borrow(subs[0], books[7], 200507);
            borrows[2] = new Borrow(subs[1], books[3], 200403);
            borrows[3] = new Borrow(subs[2], books[4], 200304);

            System.out.println("\n// Printing the instances of class Borrow");
            for (Borrow borrow : borrows) {
                borrow.print();
            }
            
            System.out.println("\n\n// Creating the Library instance");// Creating the Library instance
            Library library = new Library();
            
            System.out.println("// Adding Subscriber instances to the Library");
            for (Subscriber sub : subs) {
                library.addSubscriber(sub);
            }
            
            System.out.println("\n\n// Adding Book instances to the Library");
            for (Book book: books) {
                library.addBook(book);
            }
            
            /**
             * System.out.println("\n\n// Adding Borrow instances to the Library");
             * for(Borrow borrow : borrows){
             * library.addBorrow(borrow);
             * }
             */
            
            System.out.print("\n/*******************************************************************/\n");
            System.out.print("/*                      BEGINNING OF TESTS                         */\n");
            System.out.print("/* Remaining modification are at the bottom of main function    .  */\n");
            System.out.print("/*******************************************************************/\n");
            
            System.out.println("\n// Searching Book by Title");
            String title;
            title = jin.nextLine();
            System.out.print("Enter the title to search for...: ? ");
            title = jin.nextLine();
            
            library.searchBookByTitle(title);
            
            System.out.println("\n// Searching Book by Quote");
            String quote;
            System.out.print("Enter the quote to search for...: ? ");
            quote = jin.nextLine();
            
            library.searchBookByQuote(quote);
            
            
            System.out.print("TESTING THE BORROWING\n\n");
            System.out.print("// Should fail because Nicolas is too young! AC409 <--> 1630236");
            if (library.borrowingBookBySubcriber("1630236", "AC409", 160204)){
                System.out.print("AC409 borrowed by 1630236\n");
            }
            else{
                System.out.print("\n!!!BORROWING OF AC409 by 1630236 failed because the Subscriber is too young!!!\n");
            }
            
            System.out.print( "// Should work. BD302 borrowed by 1268348");
            if (library.borrowingBookBySubcriber("1268348", "BD302", 160204)){
                System.out.print("BD302 borrowed by 1268348\n");
            }
            else{
                System.out.print("\n!!!BORROWING BD302 by 1268348 failed!!!\n");
            }
            
            System.out.print("// Should not work because the book is not available anymore\n");
            if (library.borrowingBookBySubcriber("1839456", "BD302", 160204)){
                System.out.print("BD302 borrowed by 1839456\n");
            }
            else {
                System.out.print("!!!BORROWING BD302 by 1839456 failed bcoz the book is not available anymore!!!\n");
            }
            
            System.out.print("// Shoud not work because the subscriber has the book\n");
            if (library.borrowingBookBySubcriber("1630236", "BD302", 160204)){
                System.out.print("BD302 borrowed by 1630236\n");
            } 
            else {
                System.out.print("!!!BORROWING BD302 by 1630236 failed becauase the subsciber has the book!!!\n");
            }

            System.out.print("// Should work");
            if (library.borrowingBookBySubcriber("1630236", "QA204", 160204)){
                System.out.print("QA204 borrowed by 1630236\n");
            } 
            else {
                System.out.print("!!!BORROWING QA204 by 1630236 failed!!!\n");
            }

            System.out.print("// Should not work because 1630236 has reached the limit of books to borrow\n");
            if (library.borrowingBookBySubcriber("1630236", "QA203", 160204)){
                System.out.print("QA203 borrowed by 1630236\n");
            }
            else {
                System.out.print("!!!BORROWING QA203 by 1630236 failed bcoz 1630236 has reached the limit!!!\n");
            }

            // Info of a subscriber before returning a book
            System.out.print("\n************************************************\n");
            System.out.print("\nINFO OF A SUBSCRIBER BEFORE RETURNING A BOOK.\n");
            System.out.print("\n************************************************\n");
            library.infoSubscriber("1630236");

            System.out.print("\n\nTESTS ON BOOKS RETURN.\n");

            System.out.print("// should work : QA204 returned by 1630236\n");
            if (library.returnBookBySubscriber("1630236", "QA204")) {
                System.out.print("QA204 return by 1630236\n");
            } 
            else {
                System.out.print("!!!RETURNING of BD302 by 1630236 Failed!!!\n");
            }

            System.out.print("// Should not work because the subscriber never borrowed a book\n");
            if (library.returnBookBySubscriber("1839456", "QA203")) {
                System.out.print("QA203 return by 1839456\n");
            } 
            else {
                System.out.print("!!!RETURNING of QA203 by 1839456 failed bcoz 1839456 never borrowed QA203!!!\n");
            }

            // Info of a Subscriber after returning a book
            System.out.print("\nINFO OF A SUBSCRIBER AFTER RETURNING A BOOK.\n");
            library.infoSubscriber("1630236");
            
            
            System.out.println("\n// Printing the library");
            library.print();
            
            System.out.print("\n/*******************************************************************/\n");
            System.out.print("/*                      END OF TESTS                               */\n");
            System.out.print("/*******************************************************************/\n");
            
            // Cleanup secton
            for(Subscriber sub : subs){
                sub = null;
            }
            
            for (Book book : books) {
                book = null;
            }
            
            System.gc();
                        
            // End of the program and computation of the time
            
            final long endTime = System.currentTimeMillis();
            System.out.println("\n\nTotal execution time: " +
                               (endTime - startTime));

            System.out.println("Program Ended Successfully\n");


	}
	catch(Exception e){
	    System.out.println(e);
	}
	finally{
	    //process.destroy();
	}
    }
}
