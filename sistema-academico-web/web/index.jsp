<%-- 
    Document   : index
    Created on : 21/11/2015, 00:05:03
    Author     : joao
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="ControleLogin?acao=login" method="post">
            <p>
                <label>Login:</label>
                <input type="text" name="login">
            </p>
            <p>
                <label>Senha:</label>
                <input type="password" name="senha">
            </p>
            <p>
                <input type="submit" value="Entrar">
            </p>
        </form>
    </body>
</html>
