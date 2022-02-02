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
import negocio.Aluno;
import negocio.Curso;
import conexao.Conexao;

/**
 *
 * @author Joao
 */
public class AlunoDao {

    private Connection connection;

    //Metodo construtor que atribui uma conexão à variável connection
    public AlunoDao() throws Exception {
        this.connection = Conexao.conectar();
    }


    /*
     * Método que insere dados na tabela Curso
     */
    public void incluir(Aluno aluno) throws Exception {
        PreparedStatement ps = null;
        if (aluno == null) {
            throw new Exception("O valor passado não pode ser nulo");
        }
        try {
            String sql = "INSERT INTO aluno (nome, matricula, telefone, endereco, idcurso) "
                    + "values (?,?,?,?,?)";
            ps = connection.prepareStatement(sql);
            ps.setString(1, aluno.getNome());
            ps.setInt(2, aluno.getMatricula());
            ps.setString(3, aluno.getTelefone());
            ps.setString(4, aluno.getEndereco());
            ps.setInt(5, aluno.getCurso().getId());
            ps.executeUpdate();
        } catch (SQLException sqle) {
            throw new Exception("Erro ao inserir dados " + sqle);
        } finally {
            ps.close();
        }
    }

    /*
    idaluno, idcurso, nome, matricula, telefone, endereco
     */
    /*
     *Metodo que altera uma linha da tabela
     */
    public void alterar(Aluno aluno) throws Exception {
        PreparedStatement ps = null;
        if (aluno == null) {
            throw new Exception("O valor passado não pode ser nulo");
        }
        try {
            String SQL = "UPDATE aluno SET nome = ?, matricula = ?, telefone = ?, endereco = ?, "
                    + " idcurso = ? WHERE idaluno = ?";
            ps = connection.prepareStatement(SQL);
            ps.setString(1, aluno.getNome());
            ps.setInt(2, aluno.getMatricula());
            ps.setString(3, aluno.getTelefone());
            ps.setString(4, aluno.getEndereco());
            ps.setInt(5, aluno.getCurso().getId());
            ps.setInt(6, aluno.getId());
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
    public void excluir(Aluno aluno) throws Exception {
        PreparedStatement ps = null;
        if (aluno == null) {
            throw new Exception("O valor passado não pode ser nulo");
        }
        try {
            String SQL = "DELETE FROM aluno WHERE idaluno=?";
            ps = connection.prepareStatement(SQL);
            ps.setInt(1, aluno.getId());
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
    public Aluno buscarUm(int id) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String SQL = "SELECT * FROM aluno WHERE idaluno = ?";
            ps = connection.prepareStatement(SQL);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (!rs.next()) {
                throw new Exception("Não foi encontrado nenhum registro com o ID: " + id);
            }

            String nome = rs.getString(3);
            int matricula = rs.getInt(4);
            String telefone = rs.getString(5);
            String endereco = rs.getString(6);

            int idcurso = rs.getInt(2);
            Curso curso = new Curso();
            CursoDao cursoDao = new CursoDao();
            curso = cursoDao.buscarUm(idcurso);

            return new Aluno(id, nome, matricula, telefone, endereco, curso);

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
            ps = connection.prepareStatement("SELECT * FROM aluno ORDER BY nome");
            rs = ps.executeQuery();
            List<Aluno> list = new ArrayList<Aluno>();
            while (rs.next()) {
                int id = rs.getInt(1);
                String nome = rs.getString(3);
                int matricula = rs.getInt(4);
                String telefone = rs.getString(5);
                String endereco = rs.getString(6);
                int idcurso = rs.getInt(2);
                Curso curso = new Curso();
                CursoDao cursoDao = new CursoDao();
                curso = cursoDao.buscarUm(idcurso);

                list.add(new Aluno(id, nome, matricula, telefone, endereco, curso));
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
