package model;
import dao.AmigoDAO;
import java.util.ArrayList;
public class Amigo {

    private String nome;
    private int id;
    private String email;
    private int telefone;
    private AmigoDAO dao;
    //Construtores

    public Amigo() {
        this("", 0, "", 0);
    }

    public Amigo(String nome, int id, String email, int telefone) {
        this.nome = nome;
        this.id = id;
        this.email = email;
        this.telefone = telefone;
        dao = new AmigoDAO();
    }
    
    //Getters and Setters

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }
        @Override
    public String toString() {
        return super.toString()+ ",nome=" + nome + ", email=" + email + ",telefone=" + telefone;
    }

    /*  ABAIXO OS MÉTODOS PARA USO JUNTO COM O DAO
        SIMULANDO A ESTRUTURA EM CAMADAS PARA USAR COM BANCOS DE DADOS.
     */
    /**
     * Retorna a Lista de Amigos(objetos)
     */
    public ArrayList<Amigo> getMinhaLista() {
        return dao.getMinhaLista();
    }

    /**
     * Cadastra novo amigo
     */
    public boolean insertAmigoBD(String nome, String email,int telefone) {
        int id = this.maiorID() + 1;
        Amigo objeto = new Amigo(nome,id,email, telefone);
        dao.insertAmigoBD(objeto);
        return true;

    }

    /**
     * Deleta um amigo especÍfico pelo seu campo ID
     */
    public boolean deleteAmigoBD(int id) {
        dao.deleteAmigoBD(id);
        return true;
    }

    /**
     * Edita um amigo especÍfico pelo seu campo ID
     */
    public boolean updateAmigoBD(int id, String nome,String email, int telefone) {
        Amigo objeto = new Amigo(nome,id,email, telefone);
        dao.updateAmigoBD(objeto);
        return true;
    }

    /**
     * Carrega dados de um amigo especÍfico pelo seu ID
     */
    public Amigo carregaAmigo(int id) {
        return dao.carregaAmigo(id);
    }

    /**
     * Retorna o maior ID da nossa base de dados
     */
    public int maiorID() {
        return dao.maiorID();
    }
}
