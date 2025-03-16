import java.util.ArrayList;


public class Order {
    private ArrayList<String> orderedItems = new ArrayList<>();  //เก็บชื่อของรายการที่ได้รับการสั่งซื้อ
    private ArrayList<Integer> quantities = new ArrayList<>();   //เก็บจำนวนสั่งซื้อ
    private ArrayList<Double> itemPrices = new ArrayList<>();    //เก็บราคา
    private double totalCost = 0.0;
    private double tex = 0.07 ;
    private double totaltex = 0.0;

    public void addItem(String item, int quantity, double price) {
        orderedItems.add(item);
        quantities.add(quantity);
        itemPrices.add(price); 
        totalCost += price * quantity;
        totaltex += totalCost+(tex * totalCost);
    }
       //แสดงใบเสร็จรับเงิน
    public void displayOrder() {
        System.out.println("=====================================");
        System.out.println("               ใบเสร็จรับเงิน               ");
        System.out.println("=====================================");
        System.out.printf("%-20s %5s %10s\n", "รายการ", "จำนวน", "ราคา");
        System.out.println("-------------------------------------");

        for (int i = 0; i < orderedItems.size(); i++) {
            String item = orderedItems.get(i);
            int quantity = quantities.get(i);
            double price = itemPrices.get(i) * quantity;
            System.out.printf("%-20s %5d %10.2f บาท\n", item, quantity, price);
        }

        System.out.println("-------------------------------------");
        System.out.printf("รวมทั้งสิ้น: %26.2f บาท\n", totalCost);
        System.out.printf("ภาษี 7 : %26.2f บาท\n", totalCost * tex);
        System.out.printf("รวมภาษี: %26.2f บาท\n", totaltex);
        System.out.println("=====================================");
    }

    public double getTotalCost() {
        return totalCost;
    }

    public double getTotaltex() {
        return totaltex;
    }

}
