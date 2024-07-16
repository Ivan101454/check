package ru.clevertec.check.postgresCommand;

public class PostgresOperation {
    private final static PostgresOperation postgresOperation = new PostgresOperation();
    private String discountDdl = """
            CREATE TABLE IF NOT EXISTS discount_card (
                id_discount_card BIGSERIAL PRIMARY KEY,
                number_discount_card INT NOT NULL,
                discount_percent SMALLINT NOT NULL
            );
            """;
    private String productDDl = """
            CREATE TABLE IF NOT EXISTS product (
                id_product BIGSERIAL PRIMARY KEY,
                description_product VARCHAR NOT NULL,
                price_product DECIMAL NOT NULL,
                quantity_in_stock INT NOT NULL,
                wholesale_product BOOLEAN
            );
            """;
    private String fillDiscountTable = """
            INSERT INTO discount_card (
                number_discount_card,
                discount_percent
            ) VALUES
                (1111, 3),
                (2222, 3),
                (3333, 4),
                (4444, 5);
            """;
    private String fillProductTable = """
    COPY product(id_product, description_product, price_product, quantity_in_stock, wholesale_product) FROM 'C:\\Users\\user\\IdeaProjects\\check\\src\\main\\resources\\products.csv' DELIMITER ';' CSV HEADER;
""";


    private PostgresOperation() {

    }


    public static PostgresOperation createPostgresOperation() {
        return postgresOperation;
    }
    public String initDatabaseTables() {
        return getDiscountDdl() + getProductDDl() + getFillDiscountTable() +getFillProductTable();
    }
    public String getDiscountDdl() {
        return discountDdl;
    }

    public String getProductDDl() {
        return productDDl;
    }

    public String getFillDiscountTable() {
        return fillDiscountTable;
    }

    public String getFillProductTable() {
        return fillProductTable;
    }

}
