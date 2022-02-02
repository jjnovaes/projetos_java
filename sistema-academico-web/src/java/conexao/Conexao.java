/*
 *  Classe responsável pela conexao com o banco de dados MySQL
 */
package conexao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {

    public static Connection conectar() throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost/dbacademico", "root", "123456");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
