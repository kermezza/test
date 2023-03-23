<%@ page import="java.net.CookieStore" %>
<!DOCTYPE html>
<html>
<body>

<h1>My First Heading+''zedzdz''</h1>
<%
    String c = null;
    Cookie[] cookies = null;
    cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            while (cookie.getName().equals("email")) {
                c = cookie.getValue();
                break;
            }
        }
        if (c != null) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("Profile.jsp");

            requestDispatcher.forward(request, response);


        }


    }

%>

<a type="button" href="./Servlet" value="go to servlet">go to servlet</a><br><br>

<form method="post" action="./Servlet">
    <input type="text" name="email">
    <input type="password" name="password">
    <input type="submit" value="Login">
</form>

</body>
</html>