<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Harshal's Portfolio</title>
</head>
<body>
    <h1>Welcome to My Portfolio</h1>
    <p>This is the backend base using JSP and Servlets.</p>
    
    <hr>
	<h3>Download My Resume</h3>
	<a href="assest/Harshal-Resume.pdf" download>Click Here to Download Resume (PDF)</a>
    

    <form action="./contactServlet" method="post">
        <input type="text" name="name" placeholder="Your Name" required /><br><br>
        <input type="email" name="email" placeholder="Your Email" required /><br><br>
        <textarea name="message" placeholder="Your Message" required></textarea><br><br>
        <input type="submit" value="Send Message" />
    </form>
</body>
</html>