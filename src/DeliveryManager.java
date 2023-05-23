import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class DeliveryManager {

    Map<Address, CostDelivery> costPerAddress = new HashMap<>();
    Map<Address, CostDelivery> ordersAll = new HashMap<>();

    public void addNewPointDelivery(Address address, double costKgDelivery) {
        costPerAddress.put(address, new CostDelivery(costKgDelivery));
        System.out.println("\n-----------------------------------------------");
        System.out.println("Добавлена новая запись точки доставки;");
        System.out.println("СТРАНА     ГОРОД            СТОИМОСТЬ (за 1кг)");
        System.out.printf("%-10s %-15s %15.2fруб.\n", address.getCountry(),
                address.getCity(), costKgDelivery);
        System.out.println("-----------------------------------------------\n");
    }

    public boolean getInfoIsEmptyPointsDeliveriesData() {
        return costPerAddress.isEmpty();
    }

    public void printAllDeliveriesPoints() {
        int count = 0;
        System.out.println("\n--------------------------------------------------");
        System.out.println("Доступные точки доставки;");
        System.out.println("№   СТРАНА     ГОРОД            СТОИМОСТЬ (за 1кг)");
        for (Map.Entry<Address, CostDelivery> delivery : costPerAddress.entrySet()) {
            System.out.printf("%-2d- %-10s %-15s %15.2fруб.\n", ++count, delivery.getKey().getCountry(),
                    delivery.getKey().getCity(), delivery.getValue().getCostForKg());
        }
        System.out.println("--------------------------------------------------\n");
    }

    public void printAllOrders() {
        if (!ordersAll.isEmpty()) {
            int count = 0;
            System.out.println("\n-----------------------------------------------------------------");
            System.out.println("Заказанные все доставки;");
            System.out.println("№   СТРАНА     ГОРОД            ВЕС ГРУЗА(кг)     СТОИМОСТЬ(руб.)");
            for (Map.Entry<Address, CostDelivery> delivery : ordersAll.entrySet()) {
                System.out.printf("%-2d- %-10s %-15s %12.1fкг %15.2fруб.\n", ++count, delivery.getKey().getCountry(),
                        delivery.getKey().getCity(), delivery.getValue().getWeightCargo(),
                        delivery.getValue().getCostForOrderDelivery());
            }
            System.out.println("-----------------------------------------------------------------\n");
        } else {
            System.out.println("Список сделанных заказов пуст пока!");
        }
    }

    public void calculate(Address sendingAddress, double sendingWeight) {
        double costForSending = 0;
        if (costPerAddress.containsKey(sendingAddress)) {
            costForSending = sendingWeight * costPerAddress.get(sendingAddress).getCostForKg();
            ordersAll.put(sendingAddress, new CostDelivery(sendingWeight, costForSending));
        }

        if (costForSending > 0) {
            double totalSumOrders = 0;
            for (CostDelivery cd : ordersAll.values()) {
                totalSumOrders += cd.getCostForOrderDelivery();
            }

            System.out.println("\n--------------------------------------------------------------------");
            System.out.println("Информация об отправлении;");
            System.out.println("Страна        Город            Вес Груза(кг)         Стоимость(руб.)");
            System.out.printf("%-13s %-15s %11.1f кг %18.2f руб.\n", sendingAddress.getCountry(), sendingAddress.getCity(),
                    sendingWeight, costForSending);
            System.out.println("------------\n");
            System.out.printf("Общая сумма: %36.2f руб.\n", totalSumOrders);
            System.out.println("--------------------------------------------------------------------\n");
        } else {
            System.out.println("Доставки по этому адресу нет!");
        }
    }

    public void getInfoOrdersByCountries() {
        Set<String> countriesOrders = new TreeSet<>();

        for (Address countryAdd : ordersAll.keySet()) {
            countriesOrders.add(countryAdd.getCountry());
        }

        int count = 0;
        System.out.println("\n---------------------------------------------------");
        System.out.println("Список уникальных стран сделанных ордеров доставки");
        System.out.println("№   СТРАНА");
        for (String countryGet : countriesOrders) {
            System.out.printf("%-2d- %s\n", ++count, countryGet);
        }
        System.out.println("---------------------------------------------------\n");
    }
}
