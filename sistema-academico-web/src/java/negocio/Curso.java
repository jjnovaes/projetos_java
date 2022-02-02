package negocio;

public class Curso {

    private int id;
    private String descricao;
    private String modalidade;

    public Curso() {
    }

    public Curso(int id, String descricao, String modalidade) {
        this.id = id;
        this.descricao = descricao;
        this.modalidade = modalidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModalidade() {
        return modalidade;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }
}
