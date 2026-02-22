import java.util.Scanner;

class Library{
    private String bookTitle;
    private String bookId;
    private boolean isAvailable;
    private String borrowerName;
    private int daysBorrowed;
    private int totalBooksBorrowed;
    static int totalBooksCount = 0;
    public Library(){

    }

    public String getBookTitle() {
        return bookTitle;
    }

    public Library(String bookTitle , String bookId){
        this.bookTitle = bookTitle;
        this.bookId = bookId;
        this.isAvailable = true;
    }

    public String getBookId() {
        return bookId;
    }
    public Library(String name , int daysBorrowed) {
        this.bookTitle = name;
        this.daysBorrowed = daysBorrowed;
        this.isAvailable = false;
    }

    void borrowBook(String borrowerName){
        if (isAvailable==true){
            this.borrowerName = borrowerName;
            this.daysBorrowed = 0;
            this.isAvailable = false;
            System.out.println("Book is Borrowed to " + borrowerName);
            totalBooksBorrowed++;
        }else System.out.println("Book is already borrowed");
    }
    void returnBook(){
        if (isAvailable==false){
            this.isAvailable = true;
            this.borrowerName = null;
            System.out.println("Book is returned ");
        }else System.out.println("Book is Already Available");
    }
    void calculateFine(){
        if (isAvailable==false){
            if (daysBorrowed>14){
                System.out.println("Your Fine : " + daysBorrowed*0.5);
                return;
            }else System.out.println("Your Fine is : " + 0.0);
        }else System.out.println("Book is not borrowed yet");

    }
    void extendBorrowPeriod(int additionDays){
        if (additionDays>0 ){
            if (isAvailable==false){
                this.daysBorrowed += additionDays;
                System.out.println("Days are Extended");
            }else {
                System.out.println("Book is not Borrowed yet");
                return;
            }
        }else System.out.println("Days can't negative");

    }
    String getBookInfo(){
        return "Book Title: " + this.bookTitle + "\nBook ID : " + this.bookId
                + "\nAvailable : " + this.isAvailable + "\n";
    }
    boolean isOverdue(){
       if (isAvailable==false){
           if (daysBorrowed>14){
               return true;
           }
           return false;
       }else System.out.println("Book is not borrowed yet");
       return false;
    }

    int totalBorrowedbooks(){
        return totalBooksBorrowed;
    }
    static void displayMenu(){
        System.out.println("1. Create a Book");
        System.out.println("2. Borrow Book");
        System.out.println("3. Extend Borrow Days");
        System.out.println("4. Check if Overdue");
        System.out.println("5. Calculate Fine");
        System.out.println("6. Return Book");
        System.out.println("7. Show All Books");
        System.out.println("8. Exit");
    }

}
public class Task1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean isAlready = false;
        Library[] books = new Library[20];
        int rear = 0;
        int capacity = books.length;
        int bookCount = 0;
        while (true){
            System.out.println();
            Library.displayMenu();
            System.out.print("Enter Choice : ");
            int choice = input.nextInt();
            switch (choice){
                case 1 :
                    System.out.print("Enter Book Title : ");
                    input.nextLine();
                    String bookName = input.nextLine();
                    System.out.print("Enter Book Id : ");
                    String bookId = input.next();
                    isAlready =false;
                    for (int i = 0; i < bookCount; i++) {
                        if (books[i].getBookId().equals(bookId)){
                            isAlready =true;
                        }
                    }
                    if (!isAlready){
                        books[rear] = new Library(bookName, bookId);
                        rear = (rear + 1) % capacity;
                        System.out.println("Book is Created");
                        if (bookCount<capacity){
                            bookCount++;
                        }
                    }else System.out.println("Id Already exits");
                    break;
                case 2 :
                    System.out.print("Enter Book Title to borrow : ");
                    input.nextLine();
                    String name = input.nextLine();
                    boolean check = false;
                    if (bookCount>0){
                        for (int a=0 ; a<bookCount ; a++){
                            if (books[a].getBookTitle().equals(name)){
                                System.out.print("Enter Borrower name : ");
                                String bName = input.nextLine();
                                books[a].borrowBook(bName);
                                check =true;
                                break;
                            }
                        }
                        if (!check){
                            System.out.println("Failed to found, enter Valid Book name");
                        }
                    }else System.out.println("Book is not created!!");
                    break;
                case 3 :
                    System.out.print("Enter Book Title : ");
                    input.nextLine();
                    String dName = input.nextLine();
                    boolean checkForDays = false;
                    for (int i = 0; i < bookCount; i++) {
                        if (books[i].getBookTitle().equals(dName)){
                            checkForDays = true;
                            System.out.print("Enter Additional days : ");
                            int addDays = input.nextInt();
                            books[i].extendBorrowPeriod(addDays);
                            break;
                        }
                    }
                    if (!checkForDays){
                        System.out.println("Book is not Created ");
                    }
                    break;
                case 4 :
                    System.out.print("Enter Book Title : ");
                    input.nextLine();
                    String dueName = input.nextLine();
                    for (int i = 0; i <bookCount; i++) {
                        if (books[i].getBookTitle().equals(dueName)){
                            System.out.print("Is Overdue : " + books[i].isOverdue() + "\n");
                            break;
                        }
                    }
                    break;
                case 5 :
                    System.out.print("Enter Book Title : ");
                    input.nextLine();
                    String fineName = input.nextLine();
                    boolean isFine = false;
                    for (int i = 0; i < bookCount; i++) {
                        if (books[i].getBookTitle().equals(fineName)){
                            books[i].calculateFine();
                            isFine = true;
                            break;
                        }
                    }
                    if (!isFine){
                        System.out.println("Book is not created yet!");
                    }
                    break;
                case 6 :
                    System.out.print("Enter Book Title : ");
                    input.nextLine();
                    String returnBook = input.nextLine();
                    boolean isReturn = false;
                    for (int i = 0; i < bookCount; i++) {
                        if (books[i].getBookTitle().equals(returnBook)){
                            books[i].returnBook();
                            isReturn = true;
                        }

                    }
                    if (!isReturn){
                        System.out.println("Book doesn't exist");
                    }
                    break;
                case 7 :
                    System.out.println("====== ALL BOOKS ======");
                    for (int i = 0; i < bookCount; i++) {
                        System.out.println(books[i].getBookInfo());
                    }
                    break;
                case 8 :
                    System.out.println("Exiting....");
                    System.exit(0);
                default:
                    System.out.println("Invalid Choice");
            }
        }

    }
}
