package com.epam.xml.servlet;

import com.epam.xml.parser.Action;
import com.epam.xml.parser.ActionFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/banks")
@MultipartConfig
public class Servlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        mainRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        mainRequest(request, response);
    }

    private void mainRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Optional<Action> commandOptional = ActionFactory.defineCommand(request.getParameter("selector"));
        Action command = commandOptional.orElseThrow(IllegalArgumentException::new);
        Optional<String> page = command.execute(request);
        RequestDispatcher dispatcher = request.getRequestDispatcher(page.get());
        dispatcher.forward(request, response);
    }
}
