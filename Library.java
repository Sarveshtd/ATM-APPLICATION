import java.util.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Library {
    static String Admin_Name = "sa";
    static String Admin_Id = "1";
    static String Admin_Password = "1234";
    static int Admin_Id_Generate = 2;
    static ArrayList<Admin> Admin_List = new ArrayList<>();

    static ArrayList<Fine> Fine_List = new ArrayList<>();

    static int Current_User = -1;
    static int User_Id_Generate = 1;
    static ArrayList<User> User_List = new ArrayList<>();

    static ArrayList<Book> Book_List = new ArrayList<>();

    static Scanner sc = new Scanner(System.in);

    static void Admin_Login() {
        while (true) {
            System.out.println("[ ENTER 'EXIT' IN PASSWORD TO GO BACK TO MAIN MENU :-) ]");
            System.out.print("Enter the Admin Id : ");
            String id = sc.nextLine();
            System.out.print("Enter the password :");
            String password = sc.nextLine();
            for (int i = 0; i < Admin_List.size(); i++) {
                if (Admin_List.get(i).Id.equals(id) && Admin_List.get(i).Password.equals(password)) {
                    Admin_Name = Admin_List.get(i).Name;
                    Admin_DashBoard();
                }
            }
            if (id.equals(Admin_Id) && password.equals(Admin_Password)) {
                Admin_DashBoard();
            } else if (id.equals("EXIT") || password.equals("EXIT")) {
                main(null);
            } else {
                System.out.println("Admin Id or Password miss match");
            }
        }
    }

    static void Admin_DashBoard() {
        while (true) {
            System.out.println("Welcome " + Admin_Name + " !");
            System.out.println("1.Add new Book\n2.Remove Book\n3.Edit Book\n4.View all Book's\n5.View Borrowed Book List\n6.View Fine List\n7.Register a new Student \n8.Register a new Admin \n9.Total List \n10.Back");
            System.out.print("Enter your chooise : ");
            String ch = sc.nextLine();
            if (ch.equals("1")) {
                Add_New_Book();
            } else if (ch.equals("2")) {
                Remove_Book();
            } else if (ch.equals("3")) {
                Edit_Book();
            } else if (ch.equals("4")) {
                View_Books();
            } else if (ch.equals("5")) {
                View_Borrowed_List();
            } else if (ch.equals("6")) {
                Show_Fine_List();
            } else if (ch.equals("7")) {
                User_Register();
            } else if (ch.equals("8")) {
                Admin_Register();
            } else if (ch.equals("9")) {
                Borrower_and_Returners();
            } else if(ch.equals("10")){
                main(null);
            } else {
                System.out.println("Enter the valid Number !");
            }
        }
    }

    static void Admin_Register() {
        a: while (true) {
            System.out.println("[ ENTER 'EXIT' IN PASSWORD TO GO BACK TO MAIN MENU :-) ]\n");
            System.out.print("Enter the Admin Name : ");
            String name = sc.nextLine();
            System.out.print("Enter the password :");
            String password = sc.nextLine();
            if (name.equals("EXIT") || password.equals("EXIT")) {
                break a;
            } else if (!name.equals(null) && !password.equals(null)) {
                Admin_List.add(new Admin(name, Integer.toString(User_Id_Generate), password));
                System.out.println("Admin Id." + User_Id_Generate + " \n !!!! Dont Forgot Your User Id !!!!");
                ++Admin_Id_Generate;
            } else {
                System.out.println("Enter valid Name or Password");
            }
        }
    }

    static void Add_New_Book() {
        System.out.print("Enter the Book ISBN : ");
        String isbn = sc.nextLine();
        Boolean is_available = true;
        for (int i = 0; i < Book_List.size(); i++) {
            if (Book_List.get(i).ISBN == Integer.parseInt(isbn)) {
                is_available = false;
            }
        }
        if (is_available) {
            System.out.print("Enter the Book Name : ");
            String name = sc.nextLine();
            System.out.print("Enter the Book count : ");
            String count = sc.nextLine();
            System.out.print("Enter the Book Price : ");
            String price = sc.nextLine();
            System.out.println(isbn + " " + name + " " + count + " " + price);
            try {
                ArrayList<Book_L> bl = new ArrayList<>();
                ArrayList<Book_L> rl = new ArrayList<>();
                Book_List.add(new Book(name, Integer.parseInt(isbn), Integer.parseInt(count), Integer.parseInt(price),
                        bl, rl));
            } catch (Exception e) {
                System.out.println(e);
            }
            System.out.println("Book Added Successfully :-) ");
        } else {
            System.out.println("Book is Already Available !");
        }
    }

    static void Remove_Book() {
        System.out.print("Enter the Book ISBN : ");
        String isbn = sc.nextLine();
        Boolean is_available = true;
        int book_num = -1;
        for (int i = 0; i < Book_List.size(); i++) {
            if (Book_List.get(i).ISBN == Integer.parseInt(isbn)) {
                is_available = false;
                book_num = i;
            }
        }
        if (!is_available) {
            Book_List.remove(book_num);
            System.out.println("Book removed Successfully !");
        } else {
            System.out.println("Book is Not Available !");
        }
    }

    static void Edit_Book() {
        System.out.print("Enter the Book ISBN : ");
        String isbn = sc.nextLine();
        Boolean is_available = true;
        int book_num = -1;
        for (int i = 0; i < Book_List.size(); i++) {
            if (Book_List.get(i).ISBN == Integer.parseInt(isbn)) {
                is_available = false;
                book_num = i;
            }
        }
        if (!is_available) {
            String name = Book_List.get(book_num).Name;
            Book_List.remove(book_num);
            System.out.print("Enter the Book count : ");
            String count = sc.nextLine();
            System.out.print("Enter the Book Price : ");
            String price = sc.nextLine();
            ArrayList<Book_L> bl = new ArrayList<>(Book_List.get(book_num).Borrowed_Users_List);
            ArrayList<Book_L> rl = new ArrayList<>(Book_List.get(book_num).Returned_Users_List);
            Book_List.add(
                    new Book(name, Integer.parseInt(isbn), Integer.parseInt(count), Integer.parseInt(price), bl, rl));
        } else {
            System.out.println("Book is Not Available !");
        }
    }

    static void Borrower_and_Returners(){
        System.out.println("Borrowed Users List : \n");
        for(int i =0;i<Book_List.size();i++){
            System.out.println("\nBook Name : "+Book_List.get(i).Name+"     ISBN : "+Book_List.get(i).ISBN+"\n");
            for(int j =0;j<Book_List.get(i).Borrowed_Users_List.size();j++){
                System.out.println("\t --> User Name : "+Book_List.get(i).Borrowed_Users_List.get(j).U_Name);
                System.out.println("\t --> Borrowed Date : "+Book_List.get(i).Borrowed_Users_List.get(j).Borrow_date);
                System.out.println("\t --> Excepted to Return : "+Book_List.get(i).Borrowed_Users_List.get(j).Return_date);
            }
        }
        System.out.println("Returned Users List : \n");
        for(int i =0;i<Book_List.size();i++){
            System.out.println("\nBook Name : "+Book_List.get(i).Name+"     ISBN : "+Book_List.get(i).ISBN+"\n");
            for(int j =0;j<Book_List.get(i).Returned_Users_List.size();j++){
                System.out.println("\t --> User Name : "+Book_List.get(i).Returned_Users_List.get(j).U_Name);
                System.out.println("\t --> Borrowed Date : "+Book_List.get(i).Returned_Users_List.get(j).Borrow_date);
                System.out.println("\t --> Excepted to Return : "+Book_List.get(i).Returned_Users_List.get(j).Return_date);
            }
        }
    }

    static void View_Books() {
        a: while (true) {
            System.out.println("1.View \n2.Sort By Name \n3.Sort By Quantity \n4.Search \n5.Back");
            System.out.print("Enter your Chooise : ");
            String ch = sc.nextLine();
            if (ch.equals("1")) {
                for (int i = 0; i < Book_List.size(); i++) {
                    System.out.println("Name : " + Book_List.get(i).Name);
                    System.out.println("ISBN : " + Book_List.get(i).ISBN);
                    System.out.println("Count : " + Book_List.get(i).Count);
                    System.out.println("Price : " + Book_List.get(i).Price);
                    System.out.println("---------------------------------");
                }
            } else if (ch.equals("2")) {
                Book_List.sort(new NameSorter());
                for (int i = 0; i < Book_List.size(); i++) {
                    System.out.println("Name : " + Book_List.get(i).Name);
                    System.out.println("ISBN : " + Book_List.get(i).ISBN);
                    System.out.println("Count : " + Book_List.get(i).Count);
                    System.out.println("Price : " + Book_List.get(i).Price);
                    System.out.println("---------------------------------");
                }
            } else if (ch.equals("3")) {
                Book_List.sort(new QuantitySorter());
                for (int i = 0; i < Book_List.size(); i++) {
                    System.out.println("Name : " + Book_List.get(i).Name);
                    System.out.println("ISBN : " + Book_List.get(i).ISBN);
                    System.out.println("Count : " + Book_List.get(i).Count);
                    System.out.println("Price : " + Book_List.get(i).Price);
                    System.out.println("---------------------------------");
                }
            } else if (ch.equals("4")) {
                System.out.print("Enter the  ISBN number : ");
                String isbn = sc.nextLine();
                for (int i = 0; i < Book_List.size(); i++) {
                    if (Integer.parseInt(isbn) == Book_List.get(i).ISBN) {
                        System.out.println("---------------------------------");
                        System.out.println("Name : " + Book_List.get(i).Name);
                        System.out.println("ISBN : " + Book_List.get(i).ISBN);
                        System.out.println("Count : " + Book_List.get(i).Count);
                        System.out.println("Price : " + Book_List.get(i).Price);
                        System.out.println("---------------------------------");
                    }
                }
            } else if (ch.equals("5")) {
                break a;
            } else {
                System.out.println("Enter the valid Chooise !");
            }
        }
    }

    static void View_Borrowed_List() {
        System.out.println("Borrower's List : ");
        for (int i = 0; i < Book_List.size(); i++) {
            System.out.println("Name : " + Book_List.get(i).Name);
            System.out.println("Borrowers List :- ");
            for (int j = 0; j < Book_List.get(i).Borrowed_Users_List.size(); j++) {
                System.out.println("\t" + (j + 1) + "." + Book_List.get(i).Borrowed_Users_List.get(j).Name);
            }
        }
    }

    static void Show_Fine_List() {
        System.out.println("Fine List ");
        for (int i = 0; i < Fine_List.size(); i++) {
            System.out.println((i + 1) + ". Book Name : " + Fine_List.get(i).Book_Name);
            System.out.print("  ::   User Name : " + Fine_List.get(i).User_Name);
        }
    }

    static void User_Panel() {
        a: while (true) {
            System.out.println("Enter the User Panel !");
            System.out.println("\n1.Login \n2.Back");
            String Ch = sc.nextLine();
            if (Ch.equals("1")) {
                User_Login();
            } else if (Ch.equals("2")) {
                break a;
            } else {
                System.out.println("Enter a valid Chooise !");
            }
        }
    }

    static void User_Register() {
        a: while (true) {
            System.out.println("[ ENTER 'EXIT' IN PASSWORD TO GO BACK TO MAIN MENU ) ]");
            System.out.print("Enter the User Name : ");
            String name = sc.nextLine();
            System.out.print("Enter the password :");
            String password = sc.nextLine();
            if (name.equals("EXIT") || password.equals("EXIT")) {
                break a;
            } else if (!name.equals(null) && !password.equals(null)) {
                User_List.add(new User(name, Integer.toString(User_Id_Generate), password));
                System.out.println("User Id." + User_Id_Generate + " \n !!!! Dont Forgot Your User Id !!!!");
                ++User_Id_Generate;
                break a;
            } else {
                System.out.println("Enter valid Name or Password");
            }
        }
    }

    static void User_Login() {
        while (true) {
            System.out.println("[ ENTER 'EXIT' IN PASSWORD TO GO BACK TO MAIN MENU :-) ]");
            System.out.print("Enter the User Id : ");
            String id = sc.nextLine();
            System.out.print("Enter the password :");
            String password = sc.nextLine();
            for (int i = 0; i < User_List.size(); i++) {
                if (id.equals(User_List.get(i).Id) && password.equals(User_List.get(i).Password)) {
                    Current_User = i;
                    User_DashBoard();
                }
            }
            if (id.equals("EXIT") || password.equals("EXIT")) {
                main(null);
            } else {
                System.out.println("User Id or Password miss match");
            }
        }
    }

    static void User_DashBoard() {
        while (true) {
            System.out.println("\n");
            System.out.println("Welcome " + User_List.get(Current_User).Name + " !");
            System.out.println(
                    "1.Borrow Book \n2.View Books \n3.View Borrowed Book List \n4.Wallet \n5.Return Book \n6.Back");
            String ch = sc.nextLine();
            if (ch.equals("1")) {
                Borrow_Book();
            } else if (ch.equals("2")) {
                View_Books();
            } else if (ch.equals("3")) {
                System.out.println("Borrowed Book List :");
                for (int i = 0; i < User_List.get(Current_User).Borrowed_Book_List.size(); i++) {
                    System.out.print("\t. " + User_List.get(Current_User).Borrowed_Book_List.get(i).ISBN + " =. "
                            + User_List.get(Current_User).Borrowed_Book_List.get(i).Name);
                }
            } else if (ch.equals("4")) {
                Wallet();
            } else if (ch.equals("5")) {
                Return_Book();
            } else if (ch.equals("6")) {
                main(null);
            } else {
                System.out.println("Enter the valid number !");
            }
        }
    }

    static void Wallet() {
        a: while (true) {
            System.out.println("Wallet \n1.View Balance \n2.Add Money \n3.Back");
            System.out.println("Enter your choice : ");
            String ch = sc.nextLine();
            if (ch.equals("1")) {
                System.out.println("Available Balance : " + User_List.get(Current_User).Balance);
            } else if (ch.equals("2")) {
                System.out.println("");
                System.out.print("Enter the Amount : ");
                String amount = sc.nextLine();
                User_List.get(Current_User).Balance += Integer.parseInt(amount);
                System.out.println("Amount added SuccessFully !");
            } else if (ch.equals("3")) {
                break a;
            } else {
                System.out.println("Enter the valid Chooice !");
            }
        }
    }

    static void Borrow_Book() {
        System.out.print("Enter the Book ISBN : ");
        String isbn = sc.nextLine();
        Boolean is_available = false;
        Boolean is_user = true;
        int book_num = -1;
        for (int i = 0; i < Book_List.size(); i++) {
            if (Book_List.get(i).ISBN == Integer.parseInt(isbn)) {
                if (Book_List.get(i).Count > 0) {
                    is_available = true;
                    book_num = i;
                }
            }
        }
        if (User_List.get(Current_User).Balance >= 500) {
            if (is_available) {
                for (int i = 0; i < User_List.get(Current_User).Borrowed_Book_List.size(); i++) {
                    if (User_List.get(Current_User).Borrowed_Book_List.get(i).ISBN == Integer.parseInt(isbn)) {
                        is_user = false;
                    }
                }
                if (is_user) {
                    if (User_List.get(Current_User).Borrowed_Book_List.size() < 3) {
                        String name = Book_List.get(book_num).Name;
                        int count = Book_List.get(book_num).Count - 1;
                        int price = Book_List.get(book_num).Price;
                        Book_List.remove(book_num);
                        LocalDate bdate = LocalDate.now();
                        String bd = bdate + "";
                        LocalDate rdate = LocalDate.now().plusDays(15);
                        String rd = rdate + "";
                        ArrayList<Book_L> bl = new ArrayList<>(Book_List.get(book_num).Borrowed_Users_List);
                        ArrayList<Book_L> rl = new ArrayList<>(Book_List.get(book_num).Returned_Users_List);
                        Book_List.add(new Book(name, Integer.parseInt(isbn), count, price, bl, rl));

                        Book_List.get(book_num).Borrowed_Users_List
                                .add(new Book_L(name, Integer.parseInt(isbn), User_List.get(Current_User).Name, bd,
                                        rd));

                        User_List.get(Current_User).Borrowed_Book_List
                                .add(new Book_L(name, Integer.parseInt(isbn), User_List.get(Current_User).Name, bd,
                                        rd));

                        System.out.println("Book Borrowed Successfully :-) ");
                    } else {
                        System.out.println("You have already Borrowed 3 books !");
                    }
                } else {
                    System.out.println("You have this Book already !");
                }
            } else {
                System.out.println("Book is Not Available in Libary !");
            }
        } else {
            System.out.println("Balance is insuficient !");
        }
    }

    static void Return_Book() {
        System.out.print("Enter the Book ISBN number : ");
        String isbn = sc.nextLine();
        Boolean is_user = false;
        int user_num = -1;
        String rdate = "";
        for (int i = 0; i < User_List.get(Current_User).Borrowed_Book_List.size(); i++) {
            if (User_List.get(Current_User).Borrowed_Book_List.get(i).ISBN == Integer.parseInt(isbn)) {
                is_user = true;
                user_num = i;
                rdate = User_List.get(Current_User).Borrowed_Book_List.get(i).Return_date;
            }
        }
        if (is_user) {
            Boolean is_available = false;
            int book_num = -2;
            for (int i = 0; i < Book_List.size(); i++) {
                if (Book_List.get(i).ISBN == Integer.parseInt(isbn)) {
                    if (Book_List.get(i).Count >= 0) {
                        is_available = true;
                        book_num = i;
                    }
                }
            }
            if (is_available) {
                int b1_num = -3;
                String bname = "";
                String rbook = "";
                String bbook = "";
                Boolean is_k = false;

                if(!is_k){
                    LocalDate date = LocalDate.now();
                    String dt = date + "";
                    if (rdate.compareTo(dt) > 0 || rdate.compareTo(dt) == 0) {
                        System.out.println("You returned the book befour the Dedline !");
                        User_List.get(Current_User).Borrowed_Book_List.remove(user_num);
    
    
                        ArrayList<Book_L> bl = new ArrayList<>(Book_List.get(book_num).Borrowed_Users_List);
                        ArrayList<Book_L> rl = new ArrayList<>(Book_List.get(book_num).Returned_Users_List);
                        int count = Book_List.get(book_num).Count + 1;
                        int price = Book_List.get(book_num).Price;
                        String nam = Book_List.get(book_num).Name;
    
                        Book_List.remove(book_num);
    
                        Book_List.add(new Book(nam, Integer.parseInt(isbn), count, price, bl, rl));
                    } else {
                        System.out.println("Return date is OUT !");
                        LocalDate dateBefore = LocalDate.parse(rdate);
                        LocalDate dateAfter = LocalDate.parse(dt);
                        long no_of_days = ChronoUnit.DAYS.between(dateBefore, dateAfter);
                        no_of_days *= 0.2;
                        User_List.get(Current_User).Balance -= no_of_days;
    
                        Fine_List.add(new Fine(bname, User_List.get(Current_User).Name, dt));
    
                        User_List.get(Current_User).Borrowed_Book_List.remove(user_num);
    
    
                        ArrayList<Book_L> bl = new ArrayList<>(Book_List.get(book_num).Borrowed_Users_List);
                        ArrayList<Book_L> rl = new ArrayList<>(Book_List.get(book_num).Returned_Users_List);
                        int count = Book_List.get(book_num).Count + 1;
                        int price = Book_List.get(book_num).Price;
                        String nam = Book_List.get(book_num).Name;
    
                        Book_List.remove(book_num);
    
                        Book_List.add(new Book(nam, Integer.parseInt(isbn), count, price, bl, rl));
                    }
                    System.out.println("Book Returned Successfully !");
                } else{
                    System.out.println("No ");
                }
            } else {
                System.out.println("Book is no longer available in Library !");
            }
        } else {
            System.out.println("You Don't have that book !");
        }
    }

    public static void main(String args[]) {
        a: while (true) {
            System.out.println("Welcome To Library Management System !");
            System.out.println("1.Admin\n2.User \n3.Exit");
            String Ch = sc.nextLine();
            if (Ch.equals("1")) {
                Admin_Login();
            } else if (Ch.equals("2")) {
                User_Panel();
            } else if (Ch.equals("3")) {
                break a;
            } else {
                System.out.println("Enter a valid Chooise !");
            }
        }
    }
}

class User {
    public String Name, Password, Id;
    public int Balance;
    public ArrayList<Book_L> Borrowed_Book_List = new ArrayList<>();

    User(String name, String id, String password) {
        this.Name = name;
        this.Id = id;
        this.Password = password;
        this.Balance = 1500;
    }
}

class Admin {
    public String Name, Password, Id;

    Admin(String name, String id, String password) {
        this.Name = name;
        this.Id = id;
    }
}

class Book {
    public String Name;
    public int ISBN, Count, Price;
    public ArrayList<Book_L> Borrowed_Users_List = new ArrayList<>();
    public ArrayList<Book_L> Returned_Users_List = new ArrayList<>();

    Book(String name, int isbn, int count, int price, ArrayList<Book_L> bl, ArrayList<Book_L> rl) {
        this.Name = name;
        this.ISBN = isbn;
        this.Count = count;
        this.Price = price;
    }

    public String getName() {
        return Name;
    }

    public String getcount() {
        return Integer.toString(Count);
    }
}

class Book_L {
    public int ISBN;
    public String Name, Borrow_date, Return_date, U_Name;

    Book_L(String name, int isbn, String uname, String bd, String rd) {
        this.ISBN = isbn;
        this.Name = name;
        this.U_Name = uname;
        this.Borrow_date = bd;
        this.Return_date = rd;
    }
}

class Fine {
    public String Book_Name, User_Name, Return_Date;

    Fine(String book, String user, String rd) {
        this.Book_Name = book;
        this.User_Name = user;
        this.Return_Date = rd;
    }
}

class NameSorter implements Comparator<Book> {
    @Override
    public int compare(Book n1, Book n2) {
        return n2.getName().compareToIgnoreCase(n1.getName());
    }
}

class QuantitySorter implements Comparator<Book> {
    @Override
    public int compare(Book q1, Book q2) {
        return q2.getcount().compareTo(q1.getcount());
    }
}