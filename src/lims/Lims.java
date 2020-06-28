/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lims;

import com.smart.tech.Subscriber;
import com.smart.tech.Book;
import com.smart.tech.Borrow;
import com.smart.tech.Library;
import java.util.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;



/**
 *
 * @author pamulamba
 */
public class Lims {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
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
            final int SUBSCRIBER_ARRAY_SIZE = 6;
            Subscriber subs[] = new Subscriber[SUBSCRIBER_ARRAY_SIZE];

            for(int i = 0; i < subs.length; i++){
                subs[i] = new Subscriber();
            }   

            subs[1] = new Subscriber().idNumber("20201");
            subs[2] = new Subscriber().idNumber("20202").firstName("Pierre");
            subs[3] = new Subscriber().idNumber("20203").firstName("Abraham").lastName("Mulamba");
            subs[4] = new Subscriber().idNumber("20204").firstName("Peter").lastName("Mutombo").age(40);
            subs[5] = new Subscriber(subs[3]);
            
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
            
            books[0] = new Book().quote("Test").title("Title Test").year(2010).minReaderAge(7).nPossess(10);
            
            books[7] = new Book().quote("HB514").title("Big Java").year(2010)
                    .minReaderAge(9).nPossess(4); // ("GA403", "Big Java", 2009, 8, 3);

            books[1] = new Book().quote("GA403").title("Big C++").year(2009)
                    .minReaderAge(8).nPossess(3); // ("GA403", "Big C++", 2009, 8, 3)

            books[2] = new Book().quote("QA203").title("Calcul a plusieurs variables partie 1")
                    .year(2011).minReaderAge(3).nPossess(2);

            books[3] = new Book().quote("QA204").title("Calcul a plusieurs variables partie 2")
                    .year(2011).minReaderAge(3).nPossess(2);

            books[4] = new Book().quote("AC409").title("Le chateau d'Ortrante").year(1764)
                    .minReaderAge(16).nPossess(1);

            books[5] = new Book().quote("BD302").title("Harry Potter et le prisionier d'Azkaban")
                    .year(1999).minReaderAge(3).nPossess(1);

            books[6] = new Book().quote("CE413").title("Ibssz Qpuufs et le prisionier c'balbcbo")
                    .year(2000).minReaderAge(4).nPossess(2);

            books[8] = new Book(books[2]);

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
            borrows[1] = new Borrow(subs[5], books[7], 200507);
            borrows[2] = new Borrow(subs[4], books[3], 200403);
            borrows[3] = new Borrow(subs[3], books[4], 200304);

            System.out.println("\n// Printing the instances of class Borrow");
            for (Borrow borrow : borrows) {
                borrow.print();
            }
            
            System.out.println("\n\n// Creating the Library instance");// Creating the Library instance
            Library library = new Library();
            
            System.out.println("//Adding Subscriber instances to the Library");
            for (Subscriber sub : subs) {
                library.addSubscriber(sub);
            }
            
            System.out.println("\n\n//Adding Book instances to the Library");
            for (Book book: books) {
                library.addBook(book);
            }
            
            
            System.out.println("\n// Searching Book by Title");
            String title;
            title = jin.nextLine();
            System.out.print("Enter the title to search for: ? ");
            title = jin.nextLine();
            
            library.searchBookByTitle(title);
            
            System.out.println("\n// Searching Book by Quote");
            String quote;
            System.out.print("Enter the quote to search for: ? ");
            quote = jin.nextLine();
            
            library.searchBookByQuote(quote);
            
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
    }
    
}
