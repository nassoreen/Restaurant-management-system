import java.util.ArrayList;

public class Menu {
    private ArrayList<String> items = new ArrayList<>();   //เก็บชื่อเมนูแาหาร
    private ArrayList<Double> prices = new ArrayList<>();   //เก็บราคาอาหาร
    private ArrayList<String> categories = new ArrayList<>();  //เก็บประเภทอาหาร
    private ArrayList<Double> promotions = new ArrayList<>();  //เก็บโปรโมชั่น

    // เมนูเริ่มต้น
    public Menu() {
        addItem("Pizza", 130.0, "Food", 0.0);  
        addItem("Burger", 59.0, "Food", 10.0);  
        addItem("Salad", 40.0, "Food", 0.0);      
    }
     //แสดงเมนูอาหาร
    public void displayMenu() {
        System.out.println("==================================================================");
        System.out.println("                         เมนูอาหาร                   ");
        System.out.println("==================================================================");
        System.out.printf("| %-3s | %-20s | %-10s | %-10s | %-13s |\n", "ลำดับ", "ชื่ออาหาร", "ราคา", "หมวดหมู่", "โปรโมชั่นส่วนลด");
        System.out.println("|----------------------------------------------------------------|");
    
        for (int i = 0; i < items.size(); i++) {
            String promoText = promotions.get(i) > 0 ? String.format("%.0f%% ", promotions.get(i)) : "None";
            System.out.printf("| %-3d | %-20s | %-10.2f | %-10s | %-10s |\n", 
                    i + 1, items.get(i), prices.get(i), categories.get(i), promoText);
            System.out.println("|----------------------------------------------------------------|");
        }
        
        System.out.println("===================================================================");
    }
    

    // ประเภทของเมนู
    public void displayMenuByCategory(String category) {
        System.out.println("\nเมนู - หมวดหมู่: " + category);
        System.out.println("---------------------------------------------------------");
        for (int i = 0; i < items.size(); i++) {
            if (categories.get(i).equalsIgnoreCase(category)) {
                String promoText = promotions.get(i) > 0 ? String.format(" (%.0f%% off)", promotions.get(i)) : "";
                System.out.printf("%d. %s - %.2f บาท%s\n",
                        i + 1, items.get(i), prices.get(i), promoText);
            }
        }
    }

    // โปรโมชั่น
    public void displayPromotions() {
        System.out.println("\nเมนู - รายการโปรโมชั่น");
        System.out.println("---------------------------------------------------------");
        for (int i = 0; i < items.size(); i++) {
            if (promotions.get(i) > 0) {
                System.out.printf("%d. %s - %.2f บาท (%.0f%% off)\n",
                        i + 1, items.get(i), prices.get(i) * (1 - promotions.get(i) / 100), promotions.get(i)); 
            }
        }
    }

    
    public double getPrice(int itemIndex) {
        double price = prices.get(itemIndex);
        double discount = promotions.get(itemIndex);
        return price * (1 - discount / 100); 
    }

    // ตั้งชื่อเมนู
    public String getItem(int itemIndex) {
        return items.get(itemIndex);
    }

    // เพิ่มเมนูไอเท็ม
    public void addItem(String item, double price, String category, double promotion) {
        items.add(item);
        prices.add(price);
        categories.add(category);
        promotions.add(promotion);
        
    }

    // ลบเมนูไอเท็ม
    public void removeItem(int itemIndex) {
        if (itemIndex >= 0 && itemIndex < items.size()) {
            String removedItem = items.get(itemIndex);
            items.remove(itemIndex);
            prices.remove(itemIndex);
            categories.remove(itemIndex);
            promotions.remove(itemIndex);
            System.out.println("\u001B[31mลบสำเร็จแล้ว:\u001B[0m " + removedItem);
        } else {
            System.out.println("ไม่พบสินค้าที่ต้องการจะลบ");
        }
    }


    
    public int getItemsCount() {
        return items.size(); 
    }

}
