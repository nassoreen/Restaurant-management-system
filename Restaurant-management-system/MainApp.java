import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class MainApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Menu menu = new Menu();
        Order order = new Order();
        boolean appRunning = true;

        
        while (appRunning) {
            System.out.println("\u001B[32mกรุณาเลือกบทบาท\u001B[0m:");
            System.out.println("1. รับออเดอร์");
            System.out.println("2. จัดการเมนู");
            System.out.println("0. ออกจากโปรแกรม");
            System.out.print("\u001B[32mกรุณาเลือก\u001B[0m:");
            int roleChoice = scanner.nextInt();
            scanner.nextLine();  

            switch (roleChoice) {
                case 1:
                    // กรณีที่เป็นลูกค้า
                    System.out.print("กรอกชื่อลูกค้า: ");
                    String customerName = scanner.nextLine();
                    System.out.print("เบอร์โทรศัพท์: ");
                    String customerPhone = scanner.nextLine();
                    Customer customer = new Customer(customerName, customerPhone);
                    customerMenu(scanner, menu, order, customer);
                    break;

                case 2:
                    // กรณีที่เป็นพนักงาน
                    System.out.print("กรอกชื่อพนักงาน: ");
                    String employeeName = scanner.nextLine();
                    System.out.print("กรอกหมายเลขพนักงาน: ");
                    String employeeID = scanner.nextLine();
                    Employee employee = new Employee(employeeName, employeeID);
                    employeeMenu(scanner, menu);
                    break;

                case 0:
                    System.out.println("\u001B[32mขอบคุณที่ใช้บริการ!\u001B[0m");
                    appRunning = false;
                    break;

                default:
                    System.out.println("\u001B[31mบทบาทที่เลือกไม่ถูกต้อง กรุณาลองอีกครั้ง\u001B[0m");
                    break;
            }
        }
        scanner.close();
    }

    private static void customerMenu(Scanner scanner, Menu menu, Order order, Customer customer) {
        boolean running = true;
        while (running) {
            System.out.println("\n--- เมนูหลัก (ลูกค้า) ---");
            System.out.println("1. สั่งอาหาร");
            System.out.println("2. สรุปคำสั่งซื้อ");
            System.out.println("3. เลือกวิธีการชำระเงิน");
            System.out.println("0. กลับไปยังหน้าหลัก");
            System.out.print("\u001B[32mกรุณาเลือกรายการที่ต้องการ:\u001B[0m ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                // ลูปสั่งอาหาร
                boolean ordering = true;
                while (ordering) {
                    menu.displayMenu();
                    System.out.print("เลือกหมายเลขอาหาร (\u001B[31mหรือกรอก 0 เพื่อยกเลิก\u001B[0m): ");
                    int itemNumber = scanner.nextInt();
                    if (itemNumber == 0) {
                        ordering = false; // กลับไปยังเมนูหลักของลูกค้า
                    } else if (itemNumber > 0 && itemNumber <= menu.getItemsCount()) {
                        System.out.print("กรุณากรอกจำนวน: ");
                        int quantity = scanner.nextInt();
                        String item = menu.getItem(itemNumber - 1);
                        double price = menu.getPrice(itemNumber - 1);
                        order.addItem(item, quantity, price);
                        System.out.println("\u001B[32mเพิ่มรายการอาหารเรียบร้อยแล้ว\u001B[0m");
                    } else {
                        System.out.println("\u001B[31mหมายเลขอาหารไม่ถูกต้อง กรุณาลองใหม่อีกครั้ง\u001B[0m");
                    }
                }
                break;

                case 2:
                    displayOrderSummary(customer, order);
                    break;


                case 3:
                    choosePaymentMethod(scanner);
                    break;

                case 0:
                    running = false;  // กลับไปยังหน้าหลัก
                    break;

                default:
                    System.out.println("\u001B[31mหมายเลขที่เลือกไม่ถูกต้อง กรุณาลองใหม่อีกครั้ง\u001B[0m");
                    break;
            }
        }
    }
        //การจัดการเพิ่มเมนู
    private static void employeeMenu(Scanner scanner, Menu menu) {
        boolean running = true;
        while (running) {
            System.out.println("\n--- เมนูหลัก (พนักงาน) ---");
            System.out.println("1. เพิ่มรายการอาหาร");
            System.out.println("2. ลบรายการอาหาร");
            System.out.println("3. แสดงเมนูทั้งหมด");
            System.out.println("0. กลับไปยังหน้าหลัก");
            System.out.print("\u001B[32mกรุณาเลือกรายการที่ต้องการ:\u001B[0m ");
            int choice = scanner.nextInt();
            scanner.nextLine();  

            switch (choice) {
                case 1:                //เพิ่มเมนูอาหาร
                    System.out.print("ชื่ออาหาร: ");
                    String newItem = scanner.nextLine();
                    System.out.print("ราคาของอาหาร: ");
                    double newPrice = scanner.nextDouble();
                    scanner.nextLine(); 
                    System.out.print("หมวดหมู่: ");
                    String category = scanner.nextLine();
                    System.out.print("เพิ่มโปรโมชั่นส่วนลด (%): ");
                    Double promotion = scanner.nextDouble();
                    menu.addItem(newItem, newPrice, category, promotion);
                    System.out.println("\u001B[32mเพิ่มรายการอาหารเรียบร้อยแล้ว\u001B[0m");
                    break;

                case 2:       //ลบเมนูอาหาร
                    menu.displayMenu();
                    System.out.print("\u001B[32mกรอกหมายเลขของรายการอาหารที่ต้องการลบ:\u001B[0m ");
                    int itemNumber = scanner.nextInt();
                    menu.removeItem(itemNumber - 1);    //เรียกใช้ removeiten จาก menu java
                    break;
                       
                case 3:  
                    menu.displayMenu();      //แสดงเมนูอาหาร เรียกใช้จาก Menu.java
                    break;

                case 0:
                    running = false;  // กลับไปยังหน้าหลัก
                    break;

                default:
                    System.out.println("\u001B[31mหมายเลขที่เลือกไม่ถูกต้อง กรุณาลองใหม่อีกครั้ง\u001B[0m");
                    break;
            }
        }
    }
      //ข้อมูลลูกค้าหัวใบเสร็จรับเงิน
    private static void displayOrderSummary(Customer customer, Order order) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");     
        String dateTime = LocalDateTime.now().format(formatter);     //แสดงเวลาปัจจุบัน

        System.out.println("\n--- สรุปคำสั่งซื้อ ---");
        System.out.println("ชื่อลูกค้า: " + customer.getName() + " (เบอร์โทร: " + customer.getPhone() + ")");
        System.out.println("วันที่สั่งซื้อ: " + dateTime);
        System.out.println("-----------------------------------------");
        order.displayOrder();
    }
        //การชำระเงิน
    private static void choosePaymentMethod(Scanner scanner) {
        boolean validChoice = false;
        int paymentChoice = 0;

        while (!validChoice) {
            System.out.println("\n--- เลือกวิธีการชำระเงิน ---");
            System.out.println("1. เงินสด");
            System.out.println("2. บัตรเครดิต");
            System.out.println("3. โอนเงินออนไลน์");
            System.out.println("0. \u001B[31mยกเลิก\u001B[0m");
            System.out.print("\u001B[32mกรุณาเลือก:\u001B[0m ");
            
            if (scanner.hasNextInt()) {
                paymentChoice = scanner.nextInt();
                if (paymentChoice >= 1 && paymentChoice <= 4) {
                    validChoice = true; 
                } else {
                    System.out.println("\u001B[31mหมายเลขที่เลือกไม่ถูกต้อง กรุณาลองใหม่อีกครั้ง\u001B[0m");
                }
            } else {
                System.out.println("กรุณากรอกหมายเลขที่ถูกต้อง");
                scanner.next(); 
            }
        }

        String paymentMethod;  //ช่องทางชำระ
        switch (paymentChoice) {
            case 1:
                paymentMethod = "เงินสด";
                break;
            case 2:
                paymentMethod = "บัตรเครดิต";
                break;
            case 3:
                paymentMethod = "โอนเงินออนไลน์";
                break;
            case 0:
                System.out.println("\u001B[31mยกเลิกการเลือกวิธีการชำระเงิน\u001B[0m");
                return; // ออก
            default:
                paymentMethod = "ไม่ทราบ";
                break;
        }

        System.out.printf("คุณเลือกวิธีการชำระเงิน: %s\n", paymentMethod);     //เรียกใช้ช่องทางชำระ
        System.out.print("คุณต้องการยืนยันการชำระเงินด้วยวิธีนี้หรือไม่? (yes/no): ");
        String confirmation = scanner.next().trim().toLowerCase();

        if (confirmation.equals("yes")) {
           
            System.out.println("\u001B[32mการชำระเงินด้วยวิธี " + paymentMethod + " ได้รับการยืนยันแล้ว\u001B[0m");
        } else {
            System.out.println("\u001B[31mยกเลิกการชำระเงิน\u001B[0m");
        }
    }
}
