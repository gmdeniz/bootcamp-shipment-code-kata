package com.trendyol.shipment;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Basket {

    private List<Product> products;
    private static final int threshold = 3;

    private ShipmentSize getMaxProductSize(){
        int maxProductSize = 0;
        for (Product p : products){
            int currentProductSize = p.getSize().ordinal();
            if(currentProductSize > maxProductSize){
                maxProductSize = currentProductSize;
            }
        }
        return ShipmentSize.values()[maxProductSize];
    }

    private ShipmentSize processMoreThanOrEqualToThresholdBasket() {

        Optional<ShipmentSize> moreThanThresholdProductSize = products.stream()
            .collect(Collectors.groupingBy(Product::getSize, Collectors.counting()))
            .entrySet()
            .stream()
            .filter(entry -> entry.getValue() >= 3)
            .map(Map.Entry::getKey)
            .findFirst();

        if(moreThanThresholdProductSize.isPresent()){
            return moreThanThresholdProductSize.get().getNextSize();
        }

        return getMaxProductSize();
    }

    private ShipmentSize processLessThanThresholdBasket(){
        return getMaxProductSize();
    }

    public ShipmentSize getShipmentSize() {

        if(products.isEmpty()){
            return null;
        }

        if(products.size() >= threshold){
            return processMoreThanOrEqualToThresholdBasket();
        }
        else {
            return processLessThanThresholdBasket();
        }
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
