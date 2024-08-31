package ru.clevertec.check.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.clevertec.check.entity.DiscountCard;
import ru.clevertec.check.http.dto.DiscountCardDto;
import ru.clevertec.check.service.DiscountCardService;
import ru.clevertec.check.service.IDiscountCardService;
import ru.clevertec.check.util.JdbcConnectionManager;
import ru.clevertec.check.util.JspHelper;
import ru.clevertec.check.util.PropertiesUtil;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;


@WebServlet("/discount")
public class DiscountCardServlet extends HttpServlet {
    IDiscountCardService discountCardService = DiscountCardService.getInstance();
    String number = "1111";


    @Override
    public void init(ServletConfig config) throws ServletException {
        PropertiesUtil.getINIT();
        JdbcConnectionManager.getINIT();
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        Optional<DiscountCardDto> cardDto = discountCardService.
                findByNumber(Integer.parseInt(number));
        if (cardDto.isPresent()) {
            req.setAttribute("discount", cardDto.get());
        }
        req.getRequestDispatcher(JspHelper.getPath("discount")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        number = req.getParameter("number");
        resp.sendRedirect("/discount");
    }


//    @Override
//    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String card = req.getParameter("card");
//
//    }
    //        try (var writer = resp.getWriter()) {
//            writer.write("<h3>Дисконтная карта</h3>");
//            discountCardService.findAll().forEach(cardDto -> {
//                writer.write("""
//                        <p>__________________________________________</p>
//                        <div>cardNumber = %d</div>
//                        <div>cardNumber = %d %%</div>
//                        </div>
//                        """.formatted(cardDto.getNumberOfDiscountCard(), cardDto.getDiscountAmount()));
//            });
//            writer.write("</ul>");
}





