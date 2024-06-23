package ru.job4j.ood.ocp;

public class Product {
    public double calculatePrice(double basePrice, String discountType) {
        double price = 0D;
        if (discountType.equals("fixed")) {
            price = basePrice - 10.0;
        } else if (discountType.equals("percentage")) {
            price = basePrice * 0.9;
        }
        return price;
    }
}
