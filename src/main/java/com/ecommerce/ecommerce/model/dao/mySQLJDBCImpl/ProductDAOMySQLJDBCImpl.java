package com.ecommerce.ecommerce.model.dao.mySQLJDBCImpl;

import com.ecommerce.ecommerce.model.dao.ProductDAO;
import com.ecommerce.ecommerce.model.mo.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOMySQLJDBCImpl implements ProductDAO {

    Connection conn;

    public ProductDAOMySQLJDBCImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Product findById(int id) throws Exception {
        PreparedStatement ps;
        Product product = null;

        try {

            String sql
                    = " SELECT *"
                    + " FROM products "
                    + " WHERE "
                    + "   id = ?";
            ps = conn.prepareStatement(sql);
            ps.setLong(1, id);

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                product = read(resultSet);
            }
            resultSet.close();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return product;

    }

    @Override
    public List<Product> findAll() throws Exception {
        Product product;
        PreparedStatement ps;
        ArrayList<Product> products = new ArrayList<Product>();

        try {

            String sql
                    = " SELECT * "
                    + " FROM products ";

            ps = conn.prepareStatement(sql);

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                product = read(resultSet);
                products.add(product);
            }

            resultSet.close();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return products;
    }


    @Override
    public List<Product> findByCategory(int categoryId) throws Exception {
        return List.of();
    }

    @Override
    public List<Product> findFeatured() throws Exception {
        return List.of();
    }

    @Override
    public void create(Product product) throws Exception {

    }

    @Override
    public void update(Product product) throws Exception {

    }

    @Override
    public void delete(int id) throws Exception {

    }

    @Override
    public List<Product> searchByName(String keyword) throws Exception {
        return List.of();
    }

    @Override
    public List<Product> findInStock() throws Exception {
        return List.of();
    }

    @Override
    public List<Product> findByPriceRange(double minPrice, double maxPrice) throws Exception {
        return List.of();
    }
    Product read(ResultSet rs){
        Product product = new Product();
        try {
            product.setId(rs.getInt("id"));
        } catch (SQLException sqle) {
        }
        try {
            product.setName(rs.getString("name"));
        } catch (SQLException sqle) {
        }
        try {
            product.setDescription(rs.getString("description"));
        } catch (SQLException sqle) {
        }
        try {
            product.setPrice(rs.getDouble("price"));
        } catch (SQLException sqle) {
        }
        try {
            product.setBrand(rs.getString("brand"));
        } catch (SQLException sqle) {
        }
        try {
            product.setCategoryId(rs.getInt("categoryId"));
        } catch (SQLException sqle) {
        }    try {
            product.setFeatured(rs.getBoolean("featured"));
        } catch (SQLException sqle) {
        }try {
            product.setCreatedAt(rs.getDate("created_at"));
        } catch (SQLException sqle) {
        }
        try {
            product.setUpdatedAt(rs.getDate("updated_at"));
        } catch (SQLException sqle) {
        }
        return product;
    }
}


