package ru.clevertec.check.dao;

import ru.clevertec.check.builder.ProductBuilderInStock;
import ru.clevertec.check.entity.ProductInStock;
import ru.clevertec.check.exception.DaoException;
import ru.clevertec.check.util.JdbcConnectionManager;

import java.sql.*;
import java.util.Optional;

public class ProductInStockDao implements CrudProductInStock<ProductInStock, Long> {
    private static final ProductInStockDao PRODUCT_DAO = new ProductInStockDao();
    private static final String DELETE_SQL = """ 
            DELETE FROM product
            WHERE id_product = ?
            """;
    private static final String SAVE_SQL = """
            INSERT INTO product(description_product, price_product, quantity_in_stock, wholesale_product)
            VALUES (?, ?, ?, ?);
            """;
    private static final String UPDATE_SQL = """
            UPDATE product
            SET description_product = ?,
            price_product = ?,
            quantity_in_stock = ?,
            wholesale_product = ?
            WHERE id_product = ?
            """;
    private static final String FIND_BY_ID = """
            SELECT id_product,
            description_product,
            price_product,
            quantity_in_stock,
            wholesale_product
            FROM product
            WHERE id_product = ?
            """;
    private ProductInStockDao() {

    }
    public Optional<ProductInStock> findById(Long id) {
        try (var connection = JdbcConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setLong(1, id);
            var resultSet = preparedStatement.executeQuery();
            ProductInStock product = null;
            if (resultSet.next()) {
                product = new ProductBuilderInStock()
                        .builder()
                        .setId(resultSet.getLong("id_product"))
                        .setDescription(resultSet.getString("description_product"))
                        .setPrice(resultSet.getBigDecimal("price_product"))
                        .setQuantity(resultSet.getInt("quantity_in_stock"))
                        .setIsWholesale(resultSet.getBoolean("wholesale_product"))
                        .build();
            }
            return Optional.ofNullable(product);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
    public void update(ProductInStock product) {
        try (var connection = JdbcConnectionManager.get();
             var preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, product.getProductDescription());
            preparedStatement.setBigDecimal(2, product.getProductPrice());
            preparedStatement.setInt(3, product.getQuantity());
            preparedStatement.setBoolean(4, product.isWholesaleProduct());
            preparedStatement.setLong(5, product.getProductId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public ProductInStock save(ProductInStock product) {
        try (var connection = JdbcConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, product.getProductDescription());
            preparedStatement.setBigDecimal(2, product.getProductPrice());
            preparedStatement.setInt(   3, product.getQuantity());
            preparedStatement.setBoolean(4, product.isWholesaleProduct());
            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                product.setProductId(generatedKeys.getLong("id_product"));
            }
            return product;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public boolean delete(Long id) {
        try (var connection = JdbcConnectionManager.get();
             var preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public static ProductInStockDao getProductDao() {
        return PRODUCT_DAO;
    }
}
