package br.com.dlweb.maternidade.bebe;

public class Bebe implements java.io.Serializable {

    private int id;
    private String nome;
    private String data_nascimento;
    private String peso;
    private int altura;

    public Bebe() {}

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public String getNome() {

        return nome;
    }

    public void setNome(String nome) {

        this.nome = nome;
    }

    public String getData_nascimento() {

        return data_nascimento;
    }

    public void setData_nascimento(String data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public String getPeso() {

        return peso;
    }

    public void setPeso(String peso) {

        this.peso = peso;
    }

    public int getAltura() {

        return altura;
    }

    public void setAltura(int altura) {

        this.altura = altura;
    }
}

