# ATM-APPLICATION
import java.util.*;
import java.text.*;

public class atm {
    static Scanner sc = new Scanner(System.in);
    static String Admin_User = "Sarve";
    static String Admin_Pass = "1234";
    static int Admin_attempt = 1;
    static int At[] = new int[4];
    static int At1[] = new int[4];
    static int atmamount;
    static int totalamountinuser = 0;

    static String User_login = "raj";
    static String User_Pass = "4321";
    static int User_attempt = 1;

    //Admin login
    public static void Admin_Login() {
        while (Admin_attempt <= 3) {
            System.out.print("\033[H\033[2J");
            System.out.print("\t Welcome Admin \nEnter the Admin User Id : ");
            String User_Id = sc.next();
            sc.nextLine();
            System.out.print("\nEnter the Admin Password : ");
            String User_Password = sc.next();
            sc.nextLine();

            // Checks the user id and Password
            if (Admin_User.equals(User_Id) && Admin_Pass.equals(User_Password)) {
                Admin();
            } else {
                Admin_attempt += 1;
            }
        }

        // If the attempt is more than 3 the account has been locked :-)
        if (Admin_attempt == 4) {
            System.out.println("Your Account Has been Locked  :-)");
            main(null);
        }
    }

    //User login
    public static void User_Login() {
        while (User_attempt <= 3) {
            System.out.print("\033[H\033[2J");
            System.out.print("\t Welcome user \nEnter the  User Id : ");
            String User_Id = sc.next();
            sc.nextLine();
            System.out.print("\nEnter the Admin Password : ");
            String User_Password = sc.next();
            sc.nextLine();

            // Checks the user id and Password
            if (User_login.equals(User_Id) && User_Pass.equals(User_Password)) {
                User();
            } else {
                User_attempt += 1;
            }
        }

        // If the attempt is more than 3 the account has been locked :-)
        if (User_attempt == 4) {
            System.out.println("Your Account Has been Locked  :-)");
            main(null);
        }
    }

    //Admin panel
    public static void Admin() {
        int i = 1;
        System.out.print("\033[H\033[2J");
        while (i != 0) {
            System.out.println("welcome Admin \n 1.Deposit\n2.check balance\n3.Exit");
            int q = sc.nextInt();
            switch (q) {
                case 1:
                    // Deposit;
                    System.out.print("\033[H\033[2J");
                    System.out.print("Enter the amount in 2000 : ");
                    int x = sc.nextInt();
                    At[0] += x * 2000;
                    System.out.print("Enter the amount in 500 : ");
                    x = sc.nextInt();
                    At[1] += x * 500;
                    System.out.print("Enter the amount in 200 : ");
                    x = sc.nextInt();
                    At[2] += x * 200;
                    System.out.print("Enter the amount in 100 : ");
                    x = sc.nextInt();
                    At[3] += x * 100;
                    System.out.println();
                    int atmamount = At[0] + At[1] + At[2] + At[3];
                    System.out.println("Total amount in  ATM: " + (atmamount));
                    break;

                case 2:
                    // ATM Balance;
                    System.out.println("2000 count : " + (At[0] / 2000) + " Amount Present : " + At[0]);
                    System.out.println("500 count : " + (At[1] / 500) + " Amount Present : " + At[1]);
                    System.out.println("200 count : " + (At[2] / 200) + " Amount Present : " + At[2]);
                    System.out.println("100 count : " + (At[3] / 100) + " Amount Present : " + At[3]);
                    System.out.println("Balance : " + (At[0] + At[1] + At[2] + At[3]));
                    break;

                //Return to main function
                case 3:
                    main(null);
                    break;

                
                default:
                    System.out.println("! Enter the valid case !");
            }
        }

    }

    //User panel
    public static void User() {
        int i = 1;
        System.out.print("\033[H\033[2J");
        while (i != 0) {
            System.out.println("welcome User \n 1.Deposit\n2.check balance\n3.withdraw\n4.mini statement\n5.Change pin \n6.exit");
            int q = sc.nextInt();
            switch (q) {
                case 1:
                    // Deposit;
                    System.out.print("Enter the amount in 2000 : ");
                    int x = sc.nextInt();
                    At1[0] += x * 2000;
                    System.out.print("Enter the amount in 500 : ");
                    x = sc.nextInt();
                    At1[1] += x * 500;
                    System.out.print("Enter the amount in 200 : ");
                    x = sc.nextInt();
                    At1[2] += x * 200;
                    System.out.print("Enter the amount in 100 : ");
                    x = sc.nextInt();
                    At1[3] += x * 100;
                    System.out.println();
                    totalamountinuser = At1[0] + At1[1] + At1[2] + At1[3];
                    System.out.println("Total amount : " + (totalamountinuser));
                    System.out.println("Your Money has been successfully deposited");
                    break;

                case 2:
                    //user Balance;
                    System.out.println("2000 count : " + (At1[0] / 2000) + " Amount Present : " + At1[0]);
                    System.out.println("500 count : " + (At1[1] / 500) + " Amount Present : " + At1[1]);
                    System.out.println("200 count : " + (At1[2] / 200) + " Amount Present : " + At1[2]);
                    System.out.println("100 count : " + (At1[3] / 100) + " Amount Present : " + At1[3]);
                    System.out.println("Balance : " + totalamountinuser);
                    break;

                case 3:
                    //withdraw 
                    System.out.print("\n ENTER THE AMOUNT: ");
                    int withdraw = sc.nextInt();
                    if (totalamountinuser >= withdraw) {
                        totalamountinuser = totalamountinuser - withdraw;

                        System.out.println("balance to user : " + totalamountinuser);
                    }
                    //Amount is lesser than user balance
                    else{
                        System.out.println("! Insufficient account balance !");
                    }
                    break;

                //Mini statement
                case 4:
                    System.out.println("User Balance : " + totalamountinuser);
                    Date date = new Date();
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    String strDate = formatter.format(date);
                    System.out.println(strDate);
                    break;

                //Change pin
                case 5:
                    System.out.print("Enter the New Pin : ");
                    String New_Pin = sc.next();
                    sc.nextLine();
                    User_Pass = New_Pin;
                    System.out.println("Your Pin has been Changed !");
                    break;

                //Back to main function
                case 6:
                    main(null);
                    break;


                default:
                    System.out.println("! Enter the valid case !");

            }
        }

    }

    public static void main(String[] args) {
        System.out.println("Welcome to ATM");
        System.out.println("1.Admin\n2.User\n3.Exit");
        int n = sc.nextInt();
        switch (n) {
            case 1:
                Admin_Login();
                break;

            case 2:
                User_Login();
                break;

            case 3:
                System.out.print("\033[H\033[2J");
                System.out.println("Exit successfully");

            default:
                System.out.println("Enter the valid cases");
        }

    }

}
