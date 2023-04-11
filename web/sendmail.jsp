<%-- 
    Document   : send
    Created on : 18-02-2023, 14:30:52
    Author     : phanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Email Verification Example</title>
    </head>
    <body>
        <h1>Register Email Address</h1>
        <form action="sendMail" method="post">
            <input type="text" name="type" value="verifiEmail" hidden>
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>
            <button type="submit">Register</button>
        </form>
    </body>
</html>
