package com.ecommerce.ecommerce.model.dao;

import java.util.List;

import com.ecommerce.ecommerce.model.mo.Product;

public interface ProductDAO {
    
    // Trova un prodotto per ID
    Product findById(int id) throws Exception;
    
    // Trova tutti i prodotti
    List<Product> findAll() throws Exception;
    
    // Trova prodotti per categoria
    List<Product> findByCategory(int categoryId) throws Exception;
    
    // Trova prodotti in evidenza
    List<Product> findFeatured() throws Exception;
    
    // Salva un nuovo prodotto
    void create(Product product) throws Exception;
    
    // Aggiorna un prodotto esistente
    void update(Product product) throws Exception;
    
    // Elimina un prodotto
    void delete(int id) throws Exception;
    
    // Cerca prodotti per nome
    List<Product> searchByName(String keyword) throws Exception;
    
    // Trova prodotti in stock
    List<Product> findInStock() throws Exception;
    
    // Trova prodotti per intervallo di prezzo
    List<Product> findByPriceRange(double minPrice, double maxPrice) throws Exception;
}
