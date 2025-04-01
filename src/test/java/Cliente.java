public class Cliente {

    private String nome;
    private int idade;
    private int id;
    private int risco;

    /*public Cliente(){
        String nome = "N/D";
        int idade = 0;
        int id = 0;
        int risco = 0;
    }*/

    public Cliente(String nome, int idade, int id){
        this.nome = nome;
        this.idade = idade;
        this.id = id;
        int risco = 0;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRisco() {
        return risco;
    }

    public void setRisco(int risco) {
        this.risco = risco;
    }
}
