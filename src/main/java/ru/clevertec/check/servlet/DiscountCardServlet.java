package ru.clevertec.check.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.clevertec.check.entity.DiscountCard;
import ru.clevertec.check.http.dto.DiscountCardDto;
import ru.clevertec.check.service.DiscountCardService;
import ru.clevertec.check.service.IDiscountCardService;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet("/discount")
public class DiscountCardServlet extends HttpServlet {
    private final DiscountCardService discountCardService = DiscountCardService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<DiscountCardDto> all = discountCardService.findAll();
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        try (var writer = resp.getWriter()) {
            writer.write("<h1>Hello from First Servlet</h2>");
        }
        try(var writer = resp.getWriter();) {
            while (all.listIterator().hasNext()) {
                writer.write("<h1>Список скидочных карт</h1>");
                writer.write("<ul>");
                all.forEach(cardDto -> {
                    writer.write("""
                    <li>
                        <a href="card?cardNumber=%d">Скидка %s</a>
                    </li>
                    """.formatted(cardDto.getNumberOfDiscountCard(), cardDto.getDiscountAmount()));
                });
            }
            writer.write("</ul>");
        }
    }

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
//    }
}
