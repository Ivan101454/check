package ru.clevertec.check.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class JspHelper {
    private static final String JSP_FORMAT = "WEB-INF/%s.jsp";
    public static String getPath(String jspname) {
        return String.format(JSP_FORMAT, jspname);
    }
}
