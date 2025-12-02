package backend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {
    private Connection conect;

    public UsuarioDAO() {
        this.conect = Conexao.getConexao();
    }

    public void adicionar(Usuario user) {
        String sql = "insert into usuario (nome, email, senha) values (?,?,?)";
        try {
            PreparedStatement stmt = this.conect.prepareStatement(sql);
            stmt.setString(1, user.getNome());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getSenha());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Usuario buscarPorEmail(String email) {
        String sql = "select id, nome, email, senha from usuario where email = ?";

        try {
            PreparedStatement stmt = this.conect.prepareStatement(sql);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Usuario us = new Usuario();
                us.setId(rs.getInt("id"));
                us.setNome(rs.getString("nome"));
                us.setEmail(rs.getString("email"));
                us.setSenha(rs.getString("senha"));

                rs.close();
                stmt.close();
                return us;
            }

            rs.close();
            stmt.close();
            return null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void alterar(Usuario user) {
        String sql = "update usuario set nome=?, email=?, senha=? where id=?";

        try {
            PreparedStatement stmt = this.conect.prepareStatement(sql);
            stmt.setString(1, user.getNome());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getSenha());
            stmt.setInt(4, user.getId());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void excluir(Usuario user) {
        String sql = "delete from usuario where id = ?";

        try {
            PreparedStatement stmt = this.conect.prepareStatement(sql);
            stmt.setInt(1, user.getId());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public Usuario buscaPorNomeESenha(String nome, String senha) {
        String sql = "select id, nome, email, senha from usuario where nome = ? and senha = ?";
        try {
            PreparedStatement stmt = this.conect.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Usuario us = new Usuario();
                us.setId(rs.getInt("id"));
                us.setNome(rs.getString("nome"));
                us.setEmail(rs.getString("email"));
                us.setSenha(rs.getString("senha"));

                rs.close();
                stmt.close();
                return us;
            }

            rs.close();
            stmt.close();
            return null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
