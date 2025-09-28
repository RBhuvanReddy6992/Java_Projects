package com.example.servlets;

import com.example.dao.VotersDAO;
import com.example.dto.Voters;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;  // IMPORT ADDED

@WebServlet("/Registration")
public class Registration extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            // Collect form data
            String fullName = request.getParameter("fname");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String confirmPassword = request.getParameter("cpassword");
            String cardNo = request.getParameter("cardno");
            String pinStr = request.getParameter("pin");

            if (fullName == null || email == null || password == null || 
                    cardNo == null || pinStr == null) {
                    out.println("Error: Missing parameters");
                    return;
                }

                // Convert PIN
                int pin;
                try {
                    pin = Integer.parseInt(pinStr);
                } catch (NumberFormatException e) {
                    out.println("Error: Invalid PIN");
                    return;
                }

                // Check password match
                if (!password.equals(confirmPassword)) {
                    out.println("Error: Passwords don't match");
                    return;
                }

                // Create voter object
                Voters voter = new Voters();
                voter.setName(fullName);
                voter.setEmail(email);
                voter.setPassword(password);
                voter.setCardNo(cardNo);
                voter.setPin(pin);

                // Register voter
                VotersDAO dao = new VotersDAO();
                boolean result = dao.registerVoter(voter);

                if (result) {
                    // Redirect to Thankyou page
                    response.sendRedirect("Thankyou.html");
                } else {
                    out.println("Registration failed. Please try again.");
                }

            } catch (Exception e) {
                out.println("Error: " + e.getMessage());
            }
        }
    }