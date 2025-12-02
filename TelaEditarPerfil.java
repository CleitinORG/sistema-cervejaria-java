package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import backend.Usuario;
import backend.UsuarioDAO;
import net.miginfocom.swing.MigLayout;

public class TelaEditarPerfil extends JFrame {

    private JTextField txtNome;
    private JTextField txtEmail;
    private JPasswordField txtSenha;

    private Usuario usuario;

    private void acaobotao() {
        try {
            usuario.setNome(txtNome.getText().trim());
            usuario.setEmail(txtEmail.getText().trim());
            usuario.setSenha(new String(txtSenha.getPassword()).trim());

            UsuarioDAO dao = new UsuarioDAO();
            dao.alterar(usuario); 

            JOptionPane.showMessageDialog(
                    this,
                    "Perfil atualizado com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE
            );

            dispose();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Erro ao atualizar perfil:\n" + ex.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public TelaEditarPerfil(Usuario usuarioLogado) {

        this.usuario = usuarioLogado;

        setTitle("PampaBrew - Editar Perfil");
        setSize(500, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel content = new JPanel();
        content.setBackground(new Color(245, 245, 245));
        content.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        content.setLayout(new MigLayout("", "[grow]", "[][grow]"));
        setContentPane(content);

        JPanel header = new JPanel();
        header.setBackground(Color.WHITE);
        header.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        header.setLayout(new MigLayout("", "[grow]", "[][]"));

        ImageIcon icon = new ImageIcon(getClass().getResource("/pampabrew/img/logo.png"));
        Image imgScaled = icon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        JLabel img = new JLabel(new ImageIcon(imgScaled));
        header.add(img, "cell 0 0,alignx center");

        JLabel titulo = new JLabel("Editar Perfil");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        header.add(titulo, "cell 0 1,alignx center, gaptop 5");

        content.add(header, "cell 0 0,growx");

        JPanel form = new JPanel();
        form.setBackground(Color.WHITE);
        form.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        form.setLayout(new MigLayout("", "[grow]", "[][]15[][]15[][]30[]"));

        form.add(new JLabel("Nome completo:"), "cell 0 0,alignx left");
        txtNome = new JTextField(usuario.getNome());
        txtNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
        form.add(txtNome, "cell 0 1,growx");

        form.add(new JLabel("E-mail:"), "cell 0 2,alignx left");
        txtEmail = new JTextField(usuario.getEmail());
        txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
        form.add(txtEmail, "cell 0 3,growx");

        form.add(new JLabel("Senha:"), "cell 0 4,alignx left");
        txtSenha = new JPasswordField(usuario.getSenha());
        txtSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
        form.add(txtSenha, "cell 0 5,growx");

        JButton btSalvar = new JButton("Salvar Alterações");
        btSalvar.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btSalvar.setBackground(Color.LIGHT_GRAY);
        btSalvar.setForeground(Color.BLACK);
        btSalvar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                acaobotao();
            }
        });

        form.add(btSalvar, "cell 0 6,alignx center");

        content.add(form, "cell 0 1,grow");

    }

}
