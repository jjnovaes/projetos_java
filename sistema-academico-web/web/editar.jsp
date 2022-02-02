<%-- 
    Document   : incluir
    Created on : 11/11/2015, 23:53:25
    Author     : joao
--%>

<%@page import="negocio.Curso"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Incluir Curso</title>
    </head>
    <body>
        <%
            Curso curso = (Curso) request.getAttribute("curso");
        %>        
        <h3>Cadastro de Curso</h3>     
        <form action="ControleCurso?acao=editar" method="post">
            <input type="hidden" name="id" value="<%=curso.getId()%>">
            <p>
                <label>Descrição</label>
                <input type="text" name="descricao"  value="<%=curso.getDescricao()%>"/>  
            </p>
            <p>
                <label>Modalidade:</label>
                <input type="text" name="modalidade" value="<%=curso.getDescricao()%>"/>  
            </p>
            <p>
                <input type="submit" value="Gravar" />  
            </p>
        </form>
    </body>
</html>
