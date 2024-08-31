package ru.clevertec.check.servlet.discount;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.clevertec.check.http.dto.DiscountCardDto;
import ru.clevertec.check.service.DiscountCardService;
import ru.clevertec.check.service.IDiscountCardService;
import ru.clevertec.check.util.JdbcConnectionManager;
import ru.clevertec.check.util.JspHelper;
import ru.clevertec.check.util.PropertiesUtil;

import java.io.IOException;

@WebServlet("/creatediscount")
public class DiscountCreateServlet extends HttpServlet {
    IDiscountCardService discountCardService = DiscountCardService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.getPath("createcard")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DiscountCardDto dtoCard = DiscountCardDto.builder()
                .numberOfDiscountCard(Integer.parseInt(req.getParameter("number")))
                .discountAmount((Short.parseShort(req.getParameter("amount"))))
                .build();
//        try {
            discountCardService.addDiscountCart(
                    dtoCard
            );
            doGet(req, resp);
            resp.sendRedirect("/creatediscount");
//        } catch (ValidationException exception) {
//
//        }


    }
}
