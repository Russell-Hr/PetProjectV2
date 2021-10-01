package com.example.FinalProject;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

/**
 * SimpleServletFilter реализует интерфейс Filter
 */
@WebFilter("/SimpleServletFilter")
public class PageFilter implements Filter {
    private FilterConfig filterConfig;
    private static ArrayList<String> pages;  // хранилище страниц

    /**
     * Конструктор по умолчанию
     */
    public PageFilter() {
        // Создание хранилища страниц
        if (pages == null)
            pages = new ArrayList<String>();
    }

    /**
     * Метод освобождения ресурсов
     *
     * @see Filter#destroy()
     */
    @Override
    public void destroy() {
        filterConfig = null;
    }

    /**
     * Метод инициализации фильтра
     *
     * @see Filter#init(FilterConfig)
     */
    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        filterConfig = fConfig;
    }

    /**
     * Метод фильтрации
     *
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {
        if (filterConfig.getInitParameter("active").equalsIgnoreCase("true")) {
            HttpServletRequest req = (HttpServletRequest) request;
            HttpSession session = req.getSession();
            String pageUri = req.getRequestURI();
            if (pageUri != null && session.getAttribute("role")!= null) {
                if (((pageUri.contains("my_")) && !(session.getAttribute("role").equals("user")) ||
                        ((pageUri.contains("all_")) && !(session.getAttribute("role").equals("manager"))))) {
                    ServletContext ctx = filterConfig.getServletContext();
                    RequestDispatcher dispatcher = ctx.getRequestDispatcher("/index.jsp");
                    req.getSession().invalidate();
                    dispatcher.forward(request, response);
                    return;
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}