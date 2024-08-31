package ru.clevertec.check.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.clevertec.check.util.JdbcConnectionManager;
import ru.clevertec.check.util.PropertiesUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Stream;

@WebServlet("/first")
public class FirstServlet extends HttpServlet {


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PropertiesUtil.getINIT();
        resp.setContentType("text/html");
        try (var writer = resp.getWriter()) {
            writer.write("<h1>Hello from First Servlet</h2>");
        }
    }


    @Override
    public void destroy() {
        super.destroy();
    }
}