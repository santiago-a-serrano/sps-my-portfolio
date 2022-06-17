package com.google.sps.servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Logs the form data and forwards the request to /sentiment, so it does the sentiment analysis
@WebServlet("/form-handler")
public class FormHandlerServlet extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    // Get the value entered in the form.
    String name = request.getParameter("name");
    String reason = request.getParameter("reason");
    String email = request.getParameter("email");
    String message = request.getParameter("message");

    // Print the value so you can see it in the server logs.
    System.out.println("SOMEONE CONTACTED! {name: " + name + 
                       "}, {reason: " + reason + "}, {email: " + email +
                       "}, {message: " + message + "}");

    //So the sentiment servlet can also get the information from the form:
    RequestDispatcher rd = request.getRequestDispatcher("/sentiment");
    rd.forward(request, response);
    response.sendRedirect("/sentiment");
  }
}