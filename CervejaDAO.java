package backend;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import backend.MediaTipo;
import backend.DegustacaoMes;

public class CervejaDAO {

    private Connection conect;

    public CervejaDAO() {
        this.conect = Conexao.getConexao();
    }

 
    public void adicionar(Cerveja c) {
        String sql = "INSERT INTO cerveja (nome, tipo, teor, ibu, paisOrigem, dataDegustacao, "
                + "localDegustado, avaliacao, comentarios, fabricante, img) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement stmt = conect.prepareStatement(sql);

            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getTipo());
            stmt.setDouble(3, c.getTeor());
            stmt.setInt(4, c.getIbu());
            stmt.setString(5, c.getPaisOrigem());
            stmt.setDate(6, c.getDataDegustacao()); 
            stmt.setString(7, c.getLocalDegustado());
            stmt.setInt(8, c.getAvaliacao());
            stmt.setString(9, c.getComentarios());
            stmt.setString(10, c.getFabricante());
            stmt.setString(11, c.getImg());

            stmt.execute();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public LinkedList<Cerveja> listar() {

        String sql = "SELECT * FROM cerveja";
        LinkedList<Cerveja> lista = new LinkedList<>();

        try {
            PreparedStatement stmt = conect.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(montarObjeto(rs));
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }

    public LinkedList<Cerveja> listarOrdenado(String campo) {

        String sql = "SELECT * FROM cerveja ORDER BY " + campo + " ASC";
        LinkedList<Cerveja> lista = new LinkedList<>();

        try {
            PreparedStatement stmt = conect.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(montarObjeto(rs));
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }

    public LinkedList<Cerveja> listarPorNome(String nome) {

        String sql = "SELECT * FROM cerveja WHERE nome LIKE ?";
        LinkedList<Cerveja> lista = new LinkedList<>();

        try {
            PreparedStatement stmt = conect.prepareStatement(sql);
            stmt.setString(1, "%" + nome + "%");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(montarObjeto(rs));
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }

    public Cerveja buscar(String nome) {

        String sql = "SELECT * FROM cerveja WHERE nome = ?";

        try {
            PreparedStatement stmt = conect.prepareStatement(sql);
            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Cerveja c = montarObjeto(rs);
                rs.close();
                stmt.close();
                return c;
            }

            rs.close();
            stmt.close();
            return null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Cerveja montarObjeto(ResultSet rs) throws SQLException {

        Cerveja c = new Cerveja();

        c.setNome(rs.getString("nome"));
        c.setTipo(rs.getString("tipo"));
        c.setTeor(rs.getDouble("teor"));
        c.setIbu(rs.getInt("ibu"));
        c.setPaisOrigem(rs.getString("paisOrigem"));
        c.setDataDegustacao(rs.getDate("dataDegustacao"));
        c.setLocalDegustado(rs.getString("localDegustado"));
        c.setAvaliacao(rs.getInt("avaliacao"));
        c.setComentarios(rs.getString("comentarios"));
        c.setFabricante(rs.getString("fabricante"));
        c.setImg(rs.getString("img"));

        return c;
    }
    
   
    public LinkedList<MediaTipo> listarMediaNotasPorTipo() {
        String sql = 
            "SELECT tipo, AVG(avaliacao) AS media " +
            "FROM cerveja GROUP BY tipo ORDER BY media DESC";

        LinkedList<MediaTipo> lista = new LinkedList<>();

        try {
            PreparedStatement stmt = conect.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(
                    new MediaTipo(
                        rs.getString("tipo"),
                        rs.getDouble("media")
                    )
                );
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }
    
    public LinkedList<Cerveja> listarNota() {
        String sql = "SELECT * FROM cerveja ORDER BY avaliacao DESC";

        LinkedList<Cerveja> lista = new LinkedList<>();

        try {
            PreparedStatement stmt = conect.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(montarObjeto(rs));
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }
    public LinkedList<DegustacaoMes> getDegustacoesPorMes() {

        String sql =
            "SELECT MONTH(dataDegustacao) AS mes, COUNT(*) AS total " +
            "FROM cerveja GROUP BY MONTH(dataDegustacao) " +
            "ORDER BY mes";

        LinkedList<DegustacaoMes> lista = new LinkedList<>();

        String[] nomesMeses = {
            "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho",
            "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"
        };

        try {
            PreparedStatement stmt = conect.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int mes = rs.getInt("mes");
                int total = rs.getInt("total");

                lista.add(new DegustacaoMes(
                    nomesMeses[mes - 1],
                    total
                ));
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }




}
