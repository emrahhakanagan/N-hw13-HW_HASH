public class CostDelivery {
    private double costForKg;
    private double costForOrderDelivery;
    private double weightCargo;

    public CostDelivery(double costForKg) {
        this.costForKg = costForKg;
    }

    public CostDelivery(double weightCargo, double costForOrderDelivery) {
        this.weightCargo = weightCargo;
        this.costForOrderDelivery = costForOrderDelivery;
    }

    public double getCostForKg() {
        return costForKg;
    }

    public double getCostForOrderDelivery() {
        return costForOrderDelivery;
    }

    public double getWeightCargo() {
        return weightCargo;
    }

    @Override
    public String toString() {
        return "CostDelivery{" +
                "costForKg=" + costForKg +
                '}';
    }
}
