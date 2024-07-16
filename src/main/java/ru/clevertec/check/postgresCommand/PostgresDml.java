package ru.clevertec.check.postgresCommand;

import ru.clevertec.check.builder.DiscountCardBuilder;
import ru.clevertec.check.builder.IDiscountBuilder;
import ru.clevertec.check.builder.IProductBuilder;
import ru.clevertec.check.builder.ProductBuilderInBasket;
import ru.clevertec.check.entity.DebitCard;
import ru.clevertec.check.entity.DiscountCard;
import ru.clevertec.check.entity.Product;
import ru.clevertec.check.entity.ProductInBascket;
import ru.clevertec.check.util.JdbcConnectionManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostgresDml implements IPostgresDml{
    IProductBuilder iProductBuilder = new ProductBuilderInBasket();
    @Override
    public List<Product> getProductById(List<Long> idList) {
        List<Product> products = new ArrayList<>();
        String sql = """
                SELECT id_product, description_product, price_product, quantity_in_stock, wholesale_product
                FROM product
                WHERE id_product = ?
                """;
        try(var connection = JdbcConnectionManager.open();
            var preparedStatement = connection.prepareStatement(sql)) {


            for (Long i: idList) {
                preparedStatement.setLong(1, i);
                var resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    ProductInBascket product = (ProductInBascket) iProductBuilder.builder().setId(resultSet.getLong("id_product"))
                            .setDescription(resultSet.getString("description_product"))
                            .setPrice(resultSet.getBigDecimal("price_product"))
                            .setIsWholesale(resultSet.getBoolean("wholesale_product"))
                            .build();
                    products.add(product);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    @Override
    public DiscountCard getDiscountCardByNumber(int numberCard) {
        IDiscountBuilder iDiscountBuilder = new DiscountCardBuilder();
        DiscountCard card = null;
        String sql = """
                SELECT id_discount_card, number_discount_card, discount_percent
                FROM discount_card
                WHERE number_discount_card = ?;
                """;
        try(var connection = JdbcConnectionManager.open();
            var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, numberCard);
            var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long idDiscountCard = resultSet.getLong("id_discount_card");

                card = iDiscountBuilder.builder()
                        .setId(resultSet.getLong("id_discount_card"))
                        .setNumberCard(resultSet.getInt("number_discount_card"))
                        .setAmount(resultSet.getShort("discount_percent"))
                        .build();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return card;
    }
}
