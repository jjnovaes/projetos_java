<%-- 
    Document   : listar
    Created on : 12/11/2015, 00:02:52
    Author     : joao
--%>

<%@page import="java.util.List"%>
<%@page import="negocio.Curso"%>
<%@page import="dao.CursoDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
    </head>
    <body>
        <table border="1">
            <tr>
                <th>Descrição</th>
                <th>Modalidade</th>
                <th>Editar</th>
                <th>Excluir</th>
            </tr>
            <% CursoDao cursoDao = new CursoDao();
                List<Curso> cursos = cursoDao.buscarTodos();
                for (Curso curso : cursos) {%>
            <tr>
                <td> <%=curso.getDescricao()%> </td>
                <td> <%=curso.getModalidade()%> </td>
                <td><a href="ControleCurso?acao=buscarEditar&id=<%=curso.getId()%>">Editar</a></td>
                <td><a href="ControleCurso?acao=excluir&id=<%=curso.getId()%>">Excluir</a></td>
            </tr>
            <%   }%>
        </table>
        <p><a href="incluir.jsp">Incluir</a></p>
    </body>
</html>
