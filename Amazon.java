import java.util.*;

public class Amazon {
    static Scanner sc = new Scanner(System.in);
    static String Admin_User = "aaaa";
    static String Admin_Pass = "1234";
    public static ArrayList<add_merchant> merchant = new ArrayList<>();
    static int merchId = 1;
    static int User_Attempt = 3;
    static int Current_User = -1;
    static int Merchant_attempt = 3;
    static int Current_Merchant = -1;
    public static ArrayList<String> products =  new ArrayList<>();
    static Dictionary<String, ArrayList<String>> All_Products = new Hashtable<>();
    static ArrayList<Creat_User_Obj> User_List = new ArrayList<>();
    // =  new ArrayList<>();

    
    public static void Admin_Login() {
        System.out.print("\033[H\033[2J");
        System.out.print("\t Welcome Admin \nEnter the  Admin Id : ");
        String Admin_Id = sc.next();
        sc.nextLine();
        System.out.print("\nEnter the Admin Password : ");
        String Admin_Password = sc.next();
        sc.nextLine();
        if (Admin_User.equals(Admin_Id) && Admin_Pass.equals(Admin_Password)) {
            Admin();
        }
    }
    public static void Merchant() {
        int i = 1;
        System.out.println("\nWelcome merchant \n1.Merchant login\n2.Merchant register\n3.EXIT");
        while (i > 0){
            int n = sc.nextInt();
            switch (n){
                case 1:
                    Merchant_login();
                    break;
                case 2:
                    Merchant_register();
                    break;
                case 3:
                    main(null);
                    break;
                default :
                    System.out.println("Enter the valid cases");
                    break;

            }
        }
    }

    public static void Merchant_login(){
        while (Merchant_attempt >= 0) {
            System.out.print("Welcome to Merchant Login \nEnter your Id : ");
            String ID = sc.next();
            sc.nextLine();
            System.out.print("\nEnter your Password : ");
            String Password = sc.next();
            sc.nextLine();
            int i = 0;
            for (add_merchant merch : merchant) {
                if (ID.equals(Integer.toString(merch.merchId)) && Password.equals(merch.mpass)) {
                    if (merch.merchverify.equals("Yes")) {
                        Merchant_attempt = 3;
                        Current_Merchant = i;
                        Merchantpanel();
                    } else {
                        System.out.println("Merchant is not verified !");
                        Merchant();
                    }
                }
                i += 1;
            }
            Merchant_attempt -= 1;
            Merchant();
        }
    }
    public static void Merchant_register(){
        System.out.println("\nMerchent Register \nEnter Your Name : ");
        String name = sc.next();
        sc.nextLine();
        System.out.println("Enter your Password : ");
        String password = sc.next();
        sc.nextLine();
        merchant.add(new add_merchant(name, password, merchId, "No"));
        System.out.println("Your User Id => " + merchId);
        merchId += 1;
        Merchant();
    }

    static void Update_product(){
        try {
            System.out.print("Enter the product name : ");
            String product = sc.next();
            sc.nextLine();
            // String g = Merchants.get(Current_Merchant).M_Products.get(product).get(0);
            if (!merchant.get(Current_Merchant).M_Products.get(product).get(0).equals("null")) {

                System.out.println("Enter the product Count : ");
                int count = sc.nextInt();
                System.out.println("Enter the Updated Price : ");
                int price = sc.nextInt();
                List<String> arr1 = new ArrayList<>();
                // count += Integer.parseInt(g);
                String h = merchant.get(Current_Merchant).M_Products.get(product).get(0);
                arr1.add(Integer.toString(count));
                arr1.add(Integer.toString(price));
                merchant.get(Current_Merchant).M_Products.put(product, (ArrayList<String>) arr1);
                List<String> arr = new ArrayList<>();
                int count1 = (Integer.parseInt(All_Products.get(product).get(0))) - (Integer.parseInt(h) - count);
                arr.add(Integer.toString(count1));
                arr.add(Integer.toString(price));
                All_Products.put(product, (ArrayList<String>) arr);

            }
        } catch (Exception e) {
            System.out.println("The product is not found in the List !");
        }
    }
    public static void Compare_product(){
        System.out.println("Enter the Product Name : ");
        String product = sc.next();
        sc.nextLine();
        System.out.println("Product Name : " + product);
        int k = 0;
        for (int i = 0; i < merchant.size(); i++) {
            Enumeration enu = merchant.get(i).M_Products.keys();
            while (enu.hasMoreElements()) {
                if (enu.nextElement().equals(product)) {
                    System.out.println("Merchant Name : " + merchant.get(i).mname + "  Product Price : "
                            + merchant.get(i).M_Products.get(product).get(1));
                    k += 1;
                }
            }
        }
        if (k <= 0) {
            System.out.println("Product is Not Found !");
        }
    }

    public static void Remove_product(){
        System.out.print("Enter the product name : ");
        String product = sc.next();
        sc.nextLine();
        try {
            List<String> s = merchant.get(Current_Merchant).M_Products.remove(product);
            System.out.println(s + "   " + merchant.get(Current_Merchant).M_Products);
            List<String> arr = new ArrayList<>();
            int count1 = Integer.parseInt(All_Products.get(product).get(0)) - Integer.parseInt(s.get(0));
            String price = All_Products.get(product).get(1);
            arr.add(Integer.toString(count1));
            arr.add(price);
            All_Products.put(product, (ArrayList<String>) arr);
            System.out.println("Product removed Successfully !");
        } catch (Exception e) {
            System.out.println("Product is Not Found in your Product List !");
        }
    }

    public static void Merchantpanel(){
        int i = 1;
        System.out.println("Welcome to Merchant Panal\n1: Add Product\n2 : Update Product\n3 : Compare Product\n4 : Remove Product\n5 : List the Products\n6 : Back");
        while (i > 0){
            int n = sc.nextInt();
            switch (n) {
                case 1:
                    Add_Product_category();
                    break;
                case 2:
                    Update_product();
                    break;
                case 3:
                    Compare_product();
                    break;
                case 4:
                    Remove_product();
                    break;
                case 5:
                    Products();
                    break;
                case 6:
                    main(null);
                    break;
                    
            }
        }

    }

    public static void Add_merchant() {
        int i = 1;
        System.out.println("1.add merchant \n2.exit");
        while (i > 0) {
            int n = sc.nextInt();
            switch (n) {
                case 1:
                    System.out.println("merchant name : ");
                    String mname = sc.next();
                    sc.nextLine();
                    System.out.println("merchant password : ");
                    String mpass = sc.next();
                    sc.nextLine();
                    merchant.add(new add_merchant(mname, mpass, merchId, "Approved"));
                    System.out.println("Merchant ID " + merchId);
                    merchId += 1;
                    i = i - 1;
                    break;

                case 2:
                    main(null);
                    break;
            }

        }

    }

    public static void Products() {
        System.out.println("Products : ");
        //products = new ArrayList<String>();
        System.out.println(All_Products);

    }

    public static void Add_Product_category() {
       // System.out.println("Add Product category");
       //ArrayList<String> products =  new ArrayList<>();
       //products = new ArrayList<String>();
        // System.out.println("view merchant ");
        // System.out.println(merchant);
        // i = i - 1;
        // break;
        // for (int i = 1; i < merchant.size(); i++) {
        //     System.out.print(merchant.get(i) + " ");
        //     System.out.println();
        // }
        //System.out.println();
        // String px=sc.nextLine();
        // sc.next();
        // for(String x:products){
        //     if(x.equals(px)){
        //         System.out.println("Product has already  available add another product");
                
        //     }
           
        //      else{
        //          products.add(px);
        //      }
        // }
        System.out.print("Enter the Product Name : ");
                String productname = sc.next();
                sc.nextLine();
                List<String> arr = new ArrayList<>();
                arr.add("0");
                arr.add("0");
                Enumeration enu = All_Products.keys();
                int k = -1;
                while (enu.hasMoreElements()) {
                    if (productname.equals(enu.nextElement())) {
                        k = 1;
                    }
                }
                if (k == -1) {
                    All_Products.put(productname, (ArrayList<String>) arr);

                } 
                else {
                    System.out.println("Product has already  available add another product");
                }
             
        // products.add(px);
        // System.out.println(products);
        //Admin();

        
        

    }

    public static void Merchant_list() {
        //System.out.println("Merchants Names :");
        for (add_merchant merch : merchant) {
            System.out.println("Merchant Name : " + merch.mname + " Merchant Id : " + merch.merchId+ "  Verified : " + merch.merchverify);
        }
    }

    public static void Approve_merchant() {
        System.out.println("Approve_merchant");
        System.out.println("Enter Merchant Id : ");
                String merchId = sc.next();
                sc.nextLine();
                for (add_merchant merch : merchant) {
                    if (merchId.equals(Integer.toString(merch.merchId))) {
                        merch.merchverify = "Yes";
                        System.out.println(" Merchant Name : " + merch.mname + " Merchant Id : " + merch.merchId
                                + "Verified : " + merch.merchverify);
                    } else {
                        System.out.println("User Id is not founded");
                    }
                }
    }

    public static void remove_merchant() {
        System.out.println("remove-merchant : ");
        for (add_merchant merch : merchant) {
            System.out.println("Merchant Name : " + merch.mname + " Merchant Id : " + merch.merchId+ "  Verified : " + merch.merchverify);
        }
        System.out.print("Enter the Id of the Merchant to remove : ");
        String id = sc.next();
        sc.nextLine();
        int temp = -1;
        int i = 0;
        for (add_merchant merch : merchant) {
            if ((Integer.toString(merch.merchId)).equals(id)) {
                temp = i;

            }
            i += 1;
        }
        if (temp != -1) {
            merchant.remove(temp);
            System.out.println("Merchant has removed");
        } else {
            System.out.println("Id is not match with any other Id's :-)");
        }
    }

    public static void Admin() {
        int i = 1;
        //System.out.print("\033[H\033[2J");
        while (i != 0) {
            System.out.println("welcome admin");
            System.out.println(
                    "\n1.Add Merchant \n2.remove merchant\n3.All products\n4.Add Product\n5.Approve merchant\n6.Merchant list\n7.EXIT");
            int n = sc.nextInt();
            switch (n) {
                case 1:
                    // System.out.println("Add or remove merchant");
                    Add_merchant();
                    break;
                case 2:
                    remove_merchant();
                    break;
                case 3:
                    // System.out.println("Products");
                    Products();
                    break;
                case 4:
                    // System.out.println("Product category");
                    Add_Product_category();
                    break;
                case 5:
                    // System.out.println("Approve merchant");
                    Approve_merchant();
                    break;

                case 6:
                    Merchant_list();
                    break;
                // main(null);
                // break;
                case 7:
                    // remove_merchant(merchant);
                    main(null);
                    break;
                default:
                    System.out.println("Enter the valid case");
                    break;
            }

        }
    }

    public static void User(){

    }

    

    public static void User_login() {
        System.out.println("User_login ");
        while (User_Attempt >= 0) {
            System.out.print("Login Page\nEnter the User User Id : ");
            String id = sc.next();
            sc.nextLine();
            System.out.print("Enter the Password : ");
            String password = sc.next();
            sc.nextLine();
            for (int i = 0; i < User_List.size(); i++) {
                // System.out.println(User_List.get(i).U_Id+" "+id+"
                // "+User_List.get(i).U_Password+" "+password);
                if (Integer.toString(User_List.get(i).U_Id).equals(id)&& User_List.get(i).U_Password.equals(password)) {
                    Current_User = i;
                    User();
                    break;
                }
            }
            User_Attempt -= 1;
            User_login();
        }
    }

    public static void main(String args[]) {
        System.out.println("AMAZON SHOPPING APPLICATION:");
        System.out.println("\n1.Admin \n2.Merchant \n3.User\n4.Exit");
        int n = sc.nextInt();
        switch (n) {
            case 1:
                Admin_Login();
                break;
            case 2:
                Merchant();
                break;
            case 3:
                User_login();
                break;
            case 4:
                main(null);
                break;
            default:
                System.out.println("Enter the valid cases");

        }
    }
}

class add_merchant {
    public String mname, mpass, merchverify;
    public int merchId;
    public Dictionary<String, ArrayList<String>> M_Products = new Hashtable<>();

    add_merchant(String name, String password, int id, String verified) {
        this.mname = name;
        this.mpass = password;
        this.merchId = id;
        this.merchverify = verified;

    }
}

 class Creat_User_Obj {
 public String U_Name, U_Password;
 public int U_Id, U_Money;
 public Dictionary<String, ArrayList<String>> U_Cart = new Hashtable<>();
 public List<String> Statement = new ArrayList<>();
 public List<String> U_Buy = new ArrayList<>();

 Creat_User_Obj(String name, String password, int id) {
 this.U_Name = name;
 this.U_Password = password;
 this.U_Id = id;
 this.U_Money = 0;
 }
 }
