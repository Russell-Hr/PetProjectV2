package com.example.FinalProject.service;

import com.example.FinalProject.Constants;
import com.example.FinalProject.annotation.Bench;
import org.springframework.stereotype.Service;

@Service
public class CalculateParcelParamService {
    @Bench
    public double calculatePrice(int distance, int amount, double weight) {
        double calculatedPrice = 20;
        if (amount > 5000) {
            calculatedPrice = Math.ceil(calculatedPrice + 0.01 * calculatedPrice * amount / 5000);
        }
        if (distance > 300) {
            calculatedPrice = Math.ceil(calculatedPrice + 0.1 * calculatedPrice * distance / 300);
        }
        if (weight > 15) {
            calculatedPrice = Math.ceil(calculatedPrice + 0.01 * calculatedPrice * weight / 15);
        }
        return calculatedPrice;
    }

    public int calculateDistance(String fromPoint, String toPoint) {
        int calculatedDistance = 0;
        int indexFromPoint = 0;
        int indexToPoint = 0;
        for (int i = 0; i < Constants.CITIES.length; i++) {
            if (fromPoint.equals(Constants.CITIES[i])) {
                indexFromPoint = i;
            }
            if (toPoint.equals(Constants.CITIES[i])) {
                indexToPoint = i;
            }
        }
        calculatedDistance = Constants.DISTANCES[indexFromPoint][indexToPoint];
        return calculatedDistance;
    }
}
