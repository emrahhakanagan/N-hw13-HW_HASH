import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DeliveryManager deliveryManager = new DeliveryManager();

        try {
            check(deliveryManager);
        } catch (Exception e) {
            System.out.println("Неправильный ввод данных");
        } finally {
            check(deliveryManager);
        }

    }

    public static void runEntryData(Scanner scanner, DeliveryManager deliveryManager) {
        while (true) {
            int inputCommand = scanner.nextInt();

            switch (inputCommand) {
                case 0:
                    return;
                case 1:
                    newOrder(scanner, deliveryManager);
                    break;
                case 2:
                    deliveryManager.printAllOrders();
                    break;
                case 3:
                    deliveryManager.getInfoOrdersByCountries();
                    break;
                case 11:
                    printDeliveryEntriedPoints(scanner, deliveryManager);
                    break;
                case 22:
                    deliveryManager.printAllDeliveriesPoints();
                    break;
                default:
                    System.out.println("Такой команды нет");
            }
            printMainMenu();
        }
    }

    public static void printMainMenu() {
        System.out.println("\nВведите команду;");
        System.out.println("1- Заполнение нового заказа");
        System.out.println("2- Показать все заказы");
        System.out.println("3- Показать список стран ордеров");
        System.out.println("0- Выход");
        System.out.println("------------------------");
        System.out.println("11- Добавить новую точку");
        System.out.println("22- Показать список");
    }

    public static void printDeliveryEntriedPoints(Scanner scanner, DeliveryManager deliveryManager ) {
        System.out.print("Название страны доставки:  ");
        String countryDelivery = scanner.next();

        System.out.print("Название города доставки:  ");
        String cityDelivery = scanner.next();

        System.out.print("Стоимость за кг груза (формат: 0,0) в рублях:  ");
        double costCargo = scanner.nextDouble();

        deliveryManager.addNewPointDelivery(new Address(countryDelivery, cityDelivery), costCargo);

    }

    public static void check(DeliveryManager deliveryManager) {
        if (deliveryManager.getInfoIsEmptyPointsDeliveriesData()) {
            System.out.println("\nБаза пустая! Введите первую точку доставки; ");
            printDeliveryEntriedPoints(new Scanner(System.in), deliveryManager);
            printMainMenu();
            runEntryData(new Scanner(System.in), deliveryManager);
        } else {
            runEntryData(new Scanner(System.in), deliveryManager);
        }
    }

    public static void newOrder(Scanner scanner, DeliveryManager deliveryManager) {
        System.out.print("Страны получения доставки:  ");
        String countryDelivery = scanner.next();

        System.out.print("Город получения доставки:  ");
        String cityDelivery = scanner.next();

        System.out.print("Вес груза (формат: 0,0) в кг:  ");
        double weightCargo = scanner.nextDouble();

        deliveryManager.calculate(new Address(countryDelivery, cityDelivery), weightCargo);

    }

}