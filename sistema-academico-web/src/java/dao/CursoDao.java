/*
 * Classe (DAO) que fará as operações com as tabelas do MySQL
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import negocio.Curso;
import conexao.Conexao;

/**
 *
 * @author Joao
 */
public class CursoDao {

    private Connection connection;

    //Metodo construtor que atribui uma conexão à variável connection
    public CursoDao() throws Exception {
        this.connection = Conexao.conectar();
    }


    /*
     * Método que insere dados na tabela Curso
     */
    public void incluir(Curso curso) throws Exception {
        //recebe uma consulta sql
        PreparedStatement ps = null;
        if (curso == null) {
            throw new Exception("O valor passado não pode ser nulo");
        }
        try {
            String sql = "INSERT INTO curso (descricao, modalidade) values (?,?)";
            ps = connection.prepareStatement(sql);
            ps.setString(1, curso.getDescricao());
            ps.setString(2, curso.getModalidade());
            ps.executeUpdate();
        } catch (SQLException sqle) {
            throw new Exception("Erro ao inserir dados " + sqle);
        } finally {
            ps.close();
        }
    }

    /*
     *Metodo que altera uma linha da tabela
     */
    public void alterar(Curso curso) throws Exception {
        PreparedStatement ps = null;
        if (curso == null) {
            throw new Exception("O valor passado não pode ser nulo");
        }
        try {
            String SQL = "UPDATE curso SET descricao = ?, modalidade = ? WHERE idcurso = ?";
            ps = connection.prepareStatement(SQL);
            ps.setString(1, curso.getDescricao());
            ps.setString(2, curso.getModalidade());
            ps.setInt(3, curso.getId());
            ps.executeUpdate();
        } catch (SQLException sqle) {
            throw new Exception("Erro ao atualizar dados: " + sqle);
        } finally {
            ps.close();
        }
    }

    /*
     * Método que exclui uma linha na tabela Curso
     */
    public void excluir(Curso curso) throws Exception {
        PreparedStatement ps = null;
        if (curso == null) {
            throw new Exception("O valor passado não pode ser nulo");
        }
        try {
            String SQL = "DELETE FROM curso WHERE idcurso = ?";
            ps = connection.prepareStatement(SQL);
            ps.setInt(1, curso.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Erro ao excluir dados: " + e);
        } finally {
            ps.close();
        }
    }

    /*
     * Método que busca um curso na tabela
     */
    public Curso buscarUm(int id) throws Exception {
        //consulta sql
        PreparedStatement ps = null;
        //resultado da consulta
        ResultSet rs = null;
        try {
            String SQL = "SELECT * FROM curso WHERE idcurso = ?";
            ps = connection.prepareStatement(SQL);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            //teste para verificar se o resultado da consutla foi vazio
            if (!rs.next()) {
                throw new Exception("Não foi encontrado nenhum registro com o ID: " + id);
            }
            //numeração obdecer a sequencia da tabela
            String descricao = rs.getString(2);
            String modalidade = rs.getString(3);
            return new Curso(id, descricao, modalidade);
        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            ps.close();
            rs.close();
        }
    }

    /*
     * Método que busca todos os cursos da tabela
     */
    public List buscarTodos() throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = connection.prepareStatement("SELECT * FROM curso ORDER BY descricao");
            rs = ps.executeQuery();
            List<Curso> list = new ArrayList<Curso>();
            while (rs.next()) {
                int id = rs.getInt(1);
                String descricao = rs.getString(2);
                String modalidade = rs.getString(3);
                list.add(new Curso(id, descricao, modalidade));
            }
            return list;

        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            ps.close();
            rs.close();
        }
    }
}
