package com.example.FinalProject.dto;

import com.example.FinalProject.Constants;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.io.Serializable;
import java.sql.Date;

    @Component
    public class ParcelDto implements Serializable, Comparable<ParcelDto> {
        private int id;
        private String fromPoint;
        private String toPoint;
        private String deliveryAddress;
        private String category;
        private int distance;
        private int length;
        private int width;
        private int height;
        private double weight;
        private double price;

        private String status;
        private int userId;
        private Date createDate;
        private Date paymentDate;
        private Date deliveryDate;

        public ParcelDto() {
        }

        public ParcelDto(String fromPoint, String toPoint, int length, int width, int height, double weight) {
            this.fromPoint = fromPoint;
            this.toPoint = toPoint;
            this.length = length;
            this.width = width;
            this.height = height;
            this.weight = weight;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getFromPoint() {
            return fromPoint;
        }

        public void setFromPoint(String fromPoint) {
            this.fromPoint = fromPoint;
        }

        public String getToPoint() {
            return toPoint;
        }

        public void setToPoint(String toPoint) {
            this.toPoint = toPoint;
        }

        public String getDeliveryAddress() {
            return deliveryAddress;
        }

        public void setDeliveryAddress(String deliveryAddress) {
            this.deliveryAddress = deliveryAddress;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public int getLength() {
            return length;
        }

        public void setLength(int length) {
            this.length = length;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public double getWeight() {
            return weight;
        }

        public void setWeight(double weight) {
            this.weight = weight;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public Date getCreateDate() {
            return createDate;
        }

        public void setCreateDate(Date createDate) {
            this.createDate = createDate;
        }

        public Date getPaymentDate() {
            return paymentDate;
        }

        public void setPaymentDate(Date paymentDate) {
            this.paymentDate = paymentDate;
        }

        public Date getDeliveryDate() {
            return deliveryDate;
        }

        public void setDeliveryDate(Date deliveryDate) {
            this.deliveryDate = deliveryDate;
        }

        @Override
        public String toString() {
            return "ParcelDto{" +
                    "id=" + id +
                    ", fromPoint='" + fromPoint + '\'' +
                    ", toPoint='" + toPoint + '\'' +
                    ", deliveryAddress='" + deliveryAddress + '\'' +
                    ", category='" + category + '\'' +
                    ", distance=" + distance +
                    ", length=" + length +
                    ", width=" + width +
                    ", height=" + height +
                    ", weight=" + weight +
                    ", price=" + price +
                    ", status='" + status + '\'' +
                    ", userId=" + userId +
                    ", createDate=" + createDate +
                    ", paymentDate=" + paymentDate +
                    ", deliveryDate=" + deliveryDate +
                    '}';
        }

        @Override
        public int compareTo(ParcelDto parcelDto) {
            return 0;
        }
    }




