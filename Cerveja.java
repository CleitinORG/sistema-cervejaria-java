package backend;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Cerveja {

    private String nome;
    private String tipo;
    private double teor;
    private int ibu;
    private String paisOrigem;
    private Date dataDegustacao; 
    private String localDegustado;
    private int avaliacao;
    private String comentarios;
    private String img; 
    private String fabricante;

    public Cerveja() {}

    public Cerveja(String nome, String tipo, double teor, int ibu, String paisOrigem,
                   Date dataDegustacao, String localDegustado, int avaliacao,
                   String comentarios, String fabricante, String img) {

        this.nome = nome;
        this.tipo = tipo;
        this.teor = teor;
        this.ibu = ibu;
        this.paisOrigem = paisOrigem;
        this.dataDegustacao = dataDegustacao;
        this.localDegustado = localDegustado;
        this.avaliacao = avaliacao;
        this.comentarios = comentarios;
        this.fabricante = fabricante;
        this.img = img;
    }

 

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getTeor() {
        return teor;
    }

    public void setTeor(double teor) {
        this.teor = teor;
    }

    public int getIbu() {
        return ibu;
    }

    public void setIbu(int ibu) {
        this.ibu = ibu;
    }

    public String getPaisOrigem() {
        return paisOrigem;
    }

    public void setPaisOrigem(String paisOrigem) {
        this.paisOrigem = paisOrigem;
    }

    public Date getDataDegustacao() {
        return dataDegustacao;
    }

    public void setDataDegustacao(Date dataDegustacao) {
        this.dataDegustacao = dataDegustacao;
    }

    public String getLocalDegustado() {
        return localDegustado;
    }

    public void setLocalDegustado(String localDegustado) {
        this.localDegustado = localDegustado;
    }

    public int getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(int avaliacao) {
        this.avaliacao = avaliacao;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    @Override
    public String toString() {

        return "Cerveja {" +
                "\nNome: " + nome +
                "\nTipo: " + tipo +
                "\nTeor alcoólico: " + teor + "%" +
                "\nIBU: " + ibu +
                "\nPais de origem: " + paisOrigem +
                "\nData da degustação: " + dataDegustacao +
                "\nLocal degustado: " + localDegustado +
                "\nAvaliação (0–10): " + avaliacao +
                "\nComentários: " + comentarios +
                "\nFabricante: " + fabricante +
                "\nImagem: " + img +
                "\n}";
    }
}
