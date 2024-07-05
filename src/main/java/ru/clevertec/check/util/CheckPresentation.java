package ru.clevertec.check.util;

import ru.clevertec.check.entity.Check;
import ru.clevertec.check.entity.ProductInBascket;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


public class CheckPresentation {
    Check check;
    String result;



    public CheckPresentation(Check check) {
        this.check = check;
    }


    public String display() {
        result = getHead() + getGoods() + getDiscount() +getConclusion();
        return result;
    }

    public String getHead() {
        String resultHead = """
                Date;Time
                %s;%s
                
                QTY;DESCRIPTION;PRICE;DISCOUNT;TOTAL
                """.formatted(check.getLocalDateTime().toLocalDate(),
                check.getLocalDateTime().toLocalTime());
        return resultHead;
    }

    public String getGoods() {
        StringBuilder sb = new StringBuilder();
        List<ProductInBascket> products = check.getProducts();
        for (ProductInBascket pib: products) {
            String temp = "%s;%s;%s;%s;%s\n".formatted(pib.getQuantity(), pib.getProductDescription(),
                    pib.getProductPrice(), pib.getDiscount(), pib.getTotal());
            sb.append(temp);
        }
        return sb.toString();
    }
    public String getDiscount() {
        String dicount = """
                DISCOUNT CARD;DISCOUNT PERCENTAGE
                %s;%s%%
                    
                """.formatted(check.getDiscountCard().getNumberOfDiscountCard(), check.getDiscountCard().getDiscountAmount());
        return dicount;
    }

    public String getConclusion() {
        String conclution = """
                TOTAL PRICE;TOTAL DISCOUNT;TOTAL WITH DISCOUNT
                %s$;%s$;%s$
                """.formatted(check.getTotalPrice(), check.getTotalDiscount(), check.getTotalDiscount());
        return conclution;
    }

    public void writeToFile() throws IOException {
        Path output = Path.of("result.csv");
        Files.writeString(output, display());
    }



}
