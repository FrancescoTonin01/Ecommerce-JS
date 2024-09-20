package com.ecommerce.ecommerce.model.mo;
import java.sql.Date;

public class Product {

        private int id;
        private String name;
        private String description;
        private double price;
        private int stock;
        private String brand;
        private String image;
        private int category_id;
        private boolean featured;
        private Date created_at;
        private Date updated_at;

        public Product() {}

        public int getId(){
            return id;
        }
        public void setId(int id){
            this.id = id;
        }
        // Getter e setter
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getStock() {
            return stock;
        }

        public void setStock(int stock) {
            this.stock = stock;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getCategoryId() {
            return category_id;
        }

        public void setCategoryId(int category_id) {
            this.category_id = category_id;
        }

        public boolean isFeatured() {
            return featured;
        }

        public void setFeatured(boolean featured) {
            this.featured = featured;
        }

        public Date getCreatedAt() {
            return created_at;
        }

        public void setCreatedAt(Date created_at) {
            this.created_at = created_at;
        }

        public Date getUpdatedAt() {
            return updated_at;
        }

        public void setUpdatedAt(Date updated_at) {
            this.updated_at = updated_at;
        }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
