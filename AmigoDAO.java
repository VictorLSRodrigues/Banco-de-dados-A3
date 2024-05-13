package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Amigo;

/**
 * Realiza a persistência de dados.
 */
public class AmigoDAO {

    //Utilizado para retornar uma lista de amigos.
    public ArrayList<Amigo> minhaLista = new ArrayList<>();

    /**
     * Retorna a Lista de Amigos(objetos)
     */
    public ArrayList<Amigo> getMinhaLista() {

        minhaLista.clear(); // Limpa nosso ArrayList

               try {
            // Get a valid database connection (implement getConexao() method)
            Connection connection = getConexao();
            Statement stmt = connection.createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM tb_amigo");

            while (res.next()) {
                int id = res.getInt("id");
                String nome = res.getString("nome");
                String email = res.getString("email");
                int telefone = res.getInt("telefone");

                amigo objeto = new amigo(id,nome,email,telefone);
                minhaLista.add(objeto);
            }

            stmt.close();
        } catch (SQLException ex) {
            // Handle the exception (log or throw custom exception)
            System.err.println("Error: " + ex.getMessage());
        }

        return minhaLista;
    }

    public void setMinhaLista(ArrayList<Amigo> minhaLista) {
        this.minhaLista = minhaLista;
    }

    /**
     * Retorna o maior id de um amigo.
     */
    public int maiorID() {
        int maiorID = 0;
        try {
            Statement stmt = this.getConexao().createStatement();
            ResultSet res = stmt.executeQuery("SELECT MAX(id) id FROM tb_amigo");
            res.next();
            maiorID = res.getInt("id");
            stmt.close();
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        return maiorID;
    }

    /**
     * Retorna uma conexão com o banco de dados.
     */
    public Connection getConexao() {

        Connection connection = null;  //instância da conexão
        try {
            // Carregamento do JDBC Driver
            String driver = "com.mysql.cj.jdbc.Driver";
            Class.forName(driver);

            // Configurar a conexão
            String server = "localhost"; //caminho do MySQL
            String database = "db_amigo";
            String url = "jdbc:mysql://" + server + ":3306/" + database + "?useTimezone=true&serverTimezone=UTC";
            String user = "root";
            String password = "root";

            connection = DriverManager.getConnection(url, user, password);
            // Testando..
            if (connection != null) {
                System.out.println("Status: Conectado!");
            } else {
                System.out.println("Status: NÃO CONECTADO!");
            }
            return connection;

        } catch (ClassNotFoundException e) {  //Driver não encontrado
            System.out.println("O driver nao foi encontrado. " + e.getMessage());
            return null;
        } catch (SQLException e) {
            System.out.println("Nao foi possivel conectar...");
            return null;
        }
    }

    /**
     * Cadastra um novo amigo.
     */
    public boolean insertAmigoBD(Amigo objeto) {
        String sql = "INSERT INTO tb_amigo(id,nome,email,telefone) VALUES(?,?,?,?)";
        try {
            PreparedStatement stmt = this.getConexao().prepareStatement(sql);

            stmt.setInt(1, objeto.getId());
            stmt.setString(2, objeto.getNome());
            stmt.setString(3, objeto.getEmail());
            stmt.setInt(4, objeto.getTelefone());
            

            stmt.execute();
            stmt.close();

            return true;
        } catch (SQLException erro) {
            System.out.println("Erro:" + erro);
            throw new RuntimeException(erro);
        }
    }

    /**
     * Deleta um amigo específico pelo seu campo ID
     */
    public boolean deleteAmigoBD(int id) {
        try {
            Statement stmt = this.getConexao().createStatement();
            stmt.executeUpdate("DELETE FROM tb_amigo WHERE id = " + id);
            stmt.close();

        } catch (SQLException erro) {
            System.out.println("Erro:" + erro);
        }
        return true;
    }

    /**
     * Edita um amigo específico pelo seu campo ID
     */
    public boolean updateAmigoBD(Amigo objeto) {

        String sql = "UPDATE tb_amigo set nome = ? ,email = ? ,telefone = ? WHERE id = ?";

        try {
            PreparedStatement stmt = this.getConexao().prepareStatement(sql);

            stmt.setString(1, objeto.getNome());
            stmt.setString(2, objeto.getEmail());
            stmt.setInt(3, objeto.getTelefone());
            stmt.setInt(4, objeto.getId());

            stmt.execute();
            stmt.close();

            return true;

        } catch (SQLException erro) {
            System.out.println("Erro:" + erro);
            throw new RuntimeException(erro);
        }
    }

    /**
     * Carrega um amigo pelo ID
     */
    public Amigo carregaAmigo(int id) {
        Amigo objeto = new Amigo();
        objeto.setId(id);
        try {
            Statement stmt = this.getConexao().createStatement();

            ResultSet res = stmt.executeQuery("SELECT * FROM tb_amigo WHERE id = " + id);
            res.next();

            objeto.setNome(res.getString("nome"));
            objeto.setEmail(res.getString("email"));
            objeto.setTelefone(res.getInt("telefone"));

            stmt.close();
        } catch (SQLException erro) {
            System.out.println("Erro:" + erro);
        }
        return objeto;
    }
}
