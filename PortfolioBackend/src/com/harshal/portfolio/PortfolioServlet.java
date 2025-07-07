package com.harshal.portfolio;

import java.io.*;
import java.sql.*;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.json.JSONObject;

@WebServlet("/contactServlet")
public class PortfolioServlet extends HttpServlet {
    
    final String username = "htborase@gmail.com";
    final String password = "xgmo ugrr erjs kuhr";

    final String dbUrl = "jdbc:mysql://localhost:3306/portfolio";
    final String dbUser = "root";
    final String dbPass = "root";

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        resp.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type");
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // ✅ CORS headers
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setContentType("text/plain");

        // ✅ JSON read from Angular
        BufferedReader reader = request.getReader();
        StringBuilder sb = new StringBuilder();
        String line;
        while((line = reader.readLine()) != null){
            sb.append(line);
        }

        JSONObject json = new JSONObject(sb.toString());
        String name = json.getString("name");
        String email = json.getString("email");
        String message = json.getString("message");

        System.out.println("📥 Received: " + name + ", " + email + ", " + message);

        boolean mailSent = false;
        boolean dbInserted = false;

        // Email sending
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(username));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(username));
            msg.setSubject("New Contact from Portfolio");
            msg.setText("Name: " + name + "\nEmail: " + email + "\nMessage: " + message);

            Transport.send(msg);
            System.out.println("✅ Mail sent");
            mailSent = true;

        } catch (MessagingException e) {
            e.printStackTrace();
        }

        // DB insert
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass);

            String sql = "INSERT INTO contact (name, email, message) VALUES (?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, message);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Saved to DB");
                dbInserted = true;
            }

            stmt.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Final response
        if (mailSent && dbInserted) {
            response.getWriter().write("Message sent & saved successfully.");
        } else if (mailSent) {
            response.getWriter().write("Message emailed but DB failed.");
        } else if (dbInserted) {
            response.getWriter().write("Saved to DB but email failed.");
        } else {
            response.getWriter().write("Something went wrong.");
        }
    }
}