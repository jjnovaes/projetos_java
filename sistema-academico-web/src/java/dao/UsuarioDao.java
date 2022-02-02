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
import negocio.Usuario;
import conexao.Conexao;

/**
 *
 * @author Joao
 */
public class UsuarioDao {

    private Connection connection;

    //Metodo construtor que atribui uma conexão à variável connection
    public UsuarioDao() throws Exception {
        this.connection = Conexao.conectar();
    }

    /*
     * Método que busca valida um usuario
     */
    public boolean validarUsuario(Usuario usuario) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String SQL = "SELECT * FROM usuario WHERE login = ? AND senha = ?";
            ps = connection.prepareStatement(SQL);
            ps.setString(1, usuario.getLogin());
            ps.setString(2, usuario.getSenha());
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            ps.close();
            rs.close();
        }
    }


    /*
     * Método que insere dados na tabela Curso
     */
    public void incluir(Usuario usuario) throws Exception {
        PreparedStatement ps = null;
        if (usuario == null) {
            throw new Exception("O valor passado não pode ser nulo");
        }
        try {
            String sql = "INSERT INTO usuario (login, senha) values (?,?)";
            ps = connection.prepareStatement(sql);
            ps.setString(1, usuario.getLogin());
            ps.setString(2, usuario.getSenha());
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
    public void alterar(Usuario usuario) throws Exception {
        PreparedStatement ps = null;
        if (usuario == null) {
            throw new Exception("O valor passado não pode ser nulo");
        }
        try {
            String SQL = "UPDATE usuario SET senha = ? WHERE login = ?";
            ps = connection.prepareStatement(SQL);
            ps.setString(1, usuario.getSenha());
            ps.setString(2, usuario.getLogin());
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
    public void excluir(Usuario usuario) throws Exception {
        PreparedStatement ps = null;
        if (usuario == null) {
            throw new Exception("O valor passado não pode ser nulo");
        }
        try {
            String SQL = "DELETE FROM usuario WHERE login=?";
            ps = connection.prepareStatement(SQL);
            ps.setString(1, usuario.getLogin());
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
    public Usuario buscarUm(String login) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String SQL = "SELECT * FROM usuario WHERE login = ?";
            ps = connection.prepareStatement(SQL);
            ps.setString(1, login);
            rs = ps.executeQuery();
            if (!rs.next()) {
                throw new Exception("Não foi encontrado nenhum registro com o LOGIN: " + login);
            }

            String senha = rs.getString(2);

            return new Usuario(login, senha);

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
                String area = rs.getString(3);
                list.add(new Curso(id, descricao, area));
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
