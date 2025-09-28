package com.example.servlets;

import com.example.dao.VotersDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/Login")
public class Loginpage extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            // Get parameters correctly
            String cardNo = request.getParameter("cardno");
            String pinStr = request.getParameter("pin");

            // Validate inputs
            if (cardNo == null || cardNo.trim().isEmpty() || pinStr == null || pinStr.trim().isEmpty()) {
                out.println("Please enter both Voter Card Number and PIN");
                RequestDispatcher rd = request.getRequestDispatcher("Login.html");
                rd.include(request, response);
                return;
            }

            // Convert PIN safely
            int pin;
            try {
                pin = Integer.parseInt(pinStr);
            } catch (NumberFormatException e) {
                out.println("Invalid PIN format. Please enter a 4-digit number.");
                RequestDispatcher rd = request.getRequestDispatcher("Login.html");
                rd.include(request, response);
                return;
            }

            // Check login credentials
            VotersDAO dao = new VotersDAO();
            boolean isValid = dao.checkLogin(cardNo, pin);

            if (isValid) {
                out.println("<h3 style='color:green'>Login successful!</h3>");
                RequestDispatcher rd = request.getRequestDispatcher("Vote.html");
                rd.forward(request, response);
            } else {
                out.println("<p style='color:red'>Invalid credentials. Please try again or register.</p>");
                RequestDispatcher rd = request.getRequestDispatcher("Login.html");
                rd.include(request, response);
            }

        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
            e.printStackTrace(out);
        }
    }
}