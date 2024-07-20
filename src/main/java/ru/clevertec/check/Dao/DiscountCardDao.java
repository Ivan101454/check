package ru.clevertec.check.Dao;

import ru.clevertec.check.builder.DiscountCardBuilder;
import ru.clevertec.check.entity.DiscountCard;
import ru.clevertec.check.exception.DaoException;
import ru.clevertec.check.util.JdbcConnectionManager;

import java.sql.*;
import java.util.Optional;

public class DiscountCardDao implements CrudDiscountCard<DiscountCard, Long, Integer> {
    private static final DiscountCardDao CARD_DAO = new DiscountCardDao();
    private static final String DELETE_SQL = """
            DELETE FROM discount_card
            WHERE id_discount_card = ?;
            """;
    private static final String SAVE_SQL = """
            INSERT INTO discount_card(number_discount_card, discount_percent)
            VALUES (?, ?);
            """;
    private static final String UPDATE_SQL = """
            UPDATE discount_card
            SET number_discount_card = ?,
            discount_percent = ?
            WHERE id_discount_card = ?
            """;
    private static final String FIND_BY_ID = """
            SELECT id_discount_card,
            number_discount_card,
            discount_percent
            FROM discount_card
            WHERE id_discount_card = ?
            """;
    private static final String FIND_BY_NUMBER = """
            SELECT id_discount_card,
            number_discount_card,
            discount_percent
            FROM discount_card
            WHERE number_discount_card = ?
            """;

    private DiscountCardDao() {

    }

    public Optional<DiscountCard> findByNumber(Integer id) {
        try (var connection = JdbcConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_NUMBER)) {
            preparedStatement.setInt(1, id);
            var resultSet = preparedStatement.executeQuery();
            DiscountCard discountCard = null;
            if (resultSet.next()) {
                discountCard = new DiscountCardBuilder().builder()
                        .setId(resultSet.getLong("id_discount_card"))
                        .setNumberCard(resultSet.getInt("number_discount_card"))
                        .setAmount(resultSet.getShort("discount_percent"))
                        .build();
            }
            return Optional.ofNullable(discountCard);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public Optional<DiscountCard> findById(Long id) {
        try (var connection = JdbcConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setLong(1, id);
            var resultSet = preparedStatement.executeQuery();
            DiscountCard discountCard = null;
            if (resultSet.next()) {
                discountCard = new DiscountCardBuilder().builder()
                        .setId(resultSet.getLong("id_discount_card"))
                        .setNumberCard(resultSet.getInt("number_discount_card"))
                        .setAmount(resultSet.getShort("discount_percent"))
                        .build();
            }
            return Optional.ofNullable(discountCard);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public void update(DiscountCard discountCard) {
        try (Connection connection = JdbcConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setInt(1, discountCard.getNumberOfDiscountCard());
            preparedStatement.setShort(2, discountCard.getDiscountAmount());
            preparedStatement.setLong(3, discountCard.getIdDiscountCard());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public DiscountCard save(DiscountCard discountCard) {
        try (var connection = JdbcConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, discountCard.getNumberOfDiscountCard());
            preparedStatement.setShort(2, discountCard.getDiscountAmount());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                discountCard.setIdDiscountCard(generatedKeys.getLong("id_discount_card"));
            }
            return discountCard;
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

    public static DiscountCardDao getInstance() {
        return CARD_DAO;
    }
}
