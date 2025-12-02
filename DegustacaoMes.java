package backend;

public class DegustacaoMes{

    private String mesNome;
    private int total;

    public DegustacaoMes(String mesNome, int total) {
        this.mesNome = mesNome;
        this.total = total;
    }

    public String getMesNome() {
        return mesNome;
    }

    public int getTotal() {
        return total;
    }
}
