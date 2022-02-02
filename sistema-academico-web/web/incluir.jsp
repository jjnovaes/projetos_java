<%-- 
    Document   : incluir
    Created on : 11/11/2015, 23:53:25
    Author     : joao
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Incluir Curso</title>
    </head>
    <body>
        <h3>Cadastro de Curso</h3>     
        <form action="ControleCurso?acao=incluir" method="post">
            <p>
                <label>Descrição</label>
                <input type="text" name="descricao" />  
            </p>
            <p>
                <label>Modalidade:</label>
                <input type="text" name="modalidade" />  
            </p>
            <p>
                <input type="submit" value="Gravar" />  
            </p>
        </form>
    </body>
</html>
