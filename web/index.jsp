<%-- 
    Document   : index
    Created on : 2025年3月14日, 下午03:51:50
    Author     : tmlib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--<link rel="stylesheet" href="css/index.css">-->
        <title>Acer International Bakery (AIB)</title>
    </head>
    <body>
        <!--Login & register-->
        <br>
        <h1>Acer International Bakery (AIB) System</h1>
        <div>
            <br>
            <table>
                <form method="get" action="login">
                    <input type="hidden" name="action" value="login">
                    <tr><td><b>User ID</b></td><td><input type="text" name="empid"></td></tr>
                    <tr><td><b>Password</b></td><td><input type="password" name="pwd"></td></tr>
                    <tr><td colspan="2"><input type="submit" value="Login"></td></tr>
                </form>
            </table>
            <br>
        </div>
    </body>
</html>
