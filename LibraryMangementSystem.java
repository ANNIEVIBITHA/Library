package project1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class LibrarySystem
{
    private
    String bookName, bookAuthor, issuedTo;
    LocalDate issuedOn;

    public LibrarySystem()
    {
        this.bookName = null;
        this.bookAuthor = null;
        this.issuedTo = null;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getIssuedTo() {
        return issuedTo;
    }

    public void setIssuedTo(String issuedTo) {
        this.issuedTo = issuedTo;
    }

    public LocalDate getIssuedOn() {
        return issuedOn;
    }

    public void setIssuedOn(LocalDate issuedOn) {
        this.issuedOn = issuedOn;
    }
   
    abstract void addBook();
    abstract void returnBook();
    abstract void issueBook();
}

class Librarian extends LibrarySystem
{
    List<Librarian> availableBooks, issuedBooks;
	
    Scanner sc = new Scanner(System.in);

    public Librarian()
    {
    	availableBooks = new ArrayList<Librarian>() ;
        issuedBooks = new ArrayList<Librarian>();    
    }

    public void list()
    {
    	Librarian l1 = new Librarian();
    	l1.setBookName("Computer Science");
    	l1.setBookAuthor("Thakur");
    	availableBooks.add(l1);
    	Librarian l2 = new Librarian();
    	l2.setBookName("DATABASE MANAGEMENT");
    	l2.setBookAuthor("Mani KUMAR");
    	availableBooks.add(l2);
    	Librarian l3 = new Librarian();
    	l3.setBookName("DATA MINING");
    	l3.setBookAuthor("Ravikumar");
    	availableBooks.add(l3);
    	Librarian l4 = new Librarian();
    	l4.setBookName("Compilers Principles");
    	l4.setBookAuthor("Alfred aho");
    	availableBooks.add(l4);
    	Librarian l5 = new Librarian();
    	l5.setBookName("Java Architecture");
    	l5.setBookAuthor("Gerald");
    	availableBooks.add(l5);
    }
    
   String getNameOfBook()
    {
        String bn;
        do {
            System.out.print("Enter Name of Book : ");
            bn = sc.nextLine();
            Librarian chk = isBookAvailable(bn);
            if(chk != null) {
                System.out.println("Book ' "+bn+" ' is Successfully returned ");
                printDetails(chk);
            }
            else
               return bn;
        }while(true);
    }
    String getAuthorOfBook()
    {
        System.out.print("Enter Name of Author : ");
        String an = sc.nextLine();
        return an;
    }
    void printDetails(Librarian l )
    {
        System.out.println("Name of Book : "+l.getBookName());
        System.out.println("Author of Book : "+l.getBookAuthor());
        if(l.getIssuedTo()!=null)
        {
            System.out.println("Book issued to "+l.getIssuedTo());
            System.out.println("Book issued on "+l.getIssuedOn());
        }
        System.out.println();
    }

    Librarian isBookAvailable(String bk)
    {
        for(Librarian book : availableBooks)
        {
            if(book.getBookName().equalsIgnoreCase(bk))
            {
                return book;
            }
        }
        return null;
    }
    Librarian isBookIssued(String bk) {
        for (Librarian book : issuedBooks) {
            if (book.getBookName().equalsIgnoreCase(bk)) {
                return book;
            }
        }
        return null;
    }

    void printAllBooks(String input)
    {
        if(input.equals("available"))
        {
            if(availableBooks.size()==0)
            {
                System.out.println("No books Available");
            }
            else {
                for (Librarian l : availableBooks) {
                    printDetails(l);
                }
            }
        }
        else
        {
            if(issuedBooks.size()==0)
            {
                System.out.println("No books Issued");
            }
            else {
                for (Librarian l : issuedBooks) {
                    printDetails(l);
                }
            }
        }
    }

    

    @Override
    void returnBook() {
        Librarian l3 = new Librarian();
        System.out.print("Enter Name of Book to return : ");
        String rb = sc.nextLine();
        Librarian chk = isBookAvailable(rb);
        Librarian chk1 = isBookIssued(rb);
        if(chk == null && chk1 == null)
        {
            System.out.println("Book ' "+rb+" ' is neither issued nor available");
        }
        else if(chk!=null)
        {
            System.out.println("Book ' "+rb+" ' is already returned and it's details are ");
            printDetails(chk);
        }
        else
        {
            l3.setBookName(chk1.getBookName());
            l3.setBookAuthor(chk1.getBookAuthor());
            availableBooks.add(l3);
            issuedBooks.remove(chk1);
            System.out.println("Book ' "+rb+" ' is Successfully returned ");
        }

    }

    @Override
    void issueBook() {
        Librarian l2 = new Librarian();
        System.out.print("Enter Name of Book to be issue : ");
        String ib = sc.nextLine();
        Librarian chk = isBookAvailable(ib);
        Librarian chk1 = isBookIssued(ib);

        if( chk== null && chk1==null) {
            System.out.println("Sorry! Book '"+ib+"' is not Available ");
        }
        else if (chk1 != null) {
            System.out.println("Sorry! Book '"+ib+"' is already issued and it's details are ");
            printDetails(chk1);
        }
        else
        {
            System.out.print("Enter Name of issuer : ");
            l2.setIssuedTo(sc.nextLine());
            l2.setBookName(chk.getBookName());
            l2.setBookAuthor(chk.getBookAuthor());
            l2.setIssuedOn(LocalDate.now());
            issuedBooks.add(l2);
            availableBooks.remove(chk);
            System.out.println("Book ' "+ib+" ' is Successfully issued to "+l2.getIssuedTo()+" Issued on :"+" "+l2.issuedOn);
        }

    }


	@Override
	void addBook() {
		 Librarian l1 = new Librarian();
	        l1.setBookName(getNameOfBook());
	        l1.setBookAuthor(getAuthorOfBook());
	        availableBooks.add(l1);
		
	}
}

public class LibraryMangementSystem  {
    public static void main(String[] args) {
           Librarian lib = new Librarian();
           lib.list();
           System.out.println("Welcome to Central Library");
           System.out.println();
           System.out.println("Available Books");
           lib.printAllBooks("available");
           Scanner sc = new Scanner(System.in);
           String ch;
           do {
               System.out.println("1. Add a book \n2. Issue a book.\n3. Return a book.\n4. Available books.\n5. Issued books.\n6. Exit  ");
               System.out.print("Enter Choice : ");
               ch = sc.nextLine();
               System.out.println();
               switch(ch)
               {
                   case "1" : lib.addBook();
                            break;
                	   
                   case "2" : lib.issueBook();
                            break;

                   case "3" : lib.returnBook();
                            break;

                   case "4" : lib.printAllBooks("available");
                            break;

                   case "5" : lib.printAllBooks("issued");
                            break;
                   default :
                	   System.out.println("EXIT");
               }
               System.out.println();
           }while(!ch.equals("6"));
    }
}













