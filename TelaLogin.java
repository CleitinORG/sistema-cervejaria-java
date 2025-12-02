package frontend;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import backend.Usuario;
import backend.UsuarioDAO;
import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaLogin extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtUsuario;
    private JPasswordField txtSenha;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                TelaLogin frame = new TelaLogin();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void acaobotao() {
        String usuario = txtUsuario.getText().trim();
        String senha = new String(txtSenha.getPassword()).trim();

        UsuarioDAO dao = new UsuarioDAO();
        Usuario logado = dao.buscaPorNomeESenha(usuario, senha);

        if (logado != null) {
        	new TelaPrincipal(logado).setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Usuário ou senha incorretos.");
        }
    }

    public TelaLogin() {

        setTitle("PampaBrew - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(500, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(245, 245, 245));
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        setContentPane(contentPane);
        contentPane.setLayout(new MigLayout("", "[grow]", "[grow]"));
        JPanel painel = new JPanel();
        painel.setBackground(Color.WHITE);
        painel.setBorder(new EmptyBorder(25, 25, 25, 25));
        painel.setLayout(new MigLayout("", "[grow]", "[][]20[][][][]20[]20[]"));
        painel.setPreferredSize(new Dimension(380, 350));

        contentPane.add(painel, "cell 0 0,alignx center,aligny center");

        ImageIcon icon = new ImageIcon(getClass().getResource("/pampabrew/img/logo.png"));
        Image imgScaled = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        JLabel img = new JLabel(new ImageIcon(imgScaled));
        painel.add(img, "cell 0 0,alignx center");

        JLabel lblLogin = new JLabel("Acesse sua conta");
        lblLogin.setFont(new Font("Tahoma", Font.BOLD, 18));
        painel.add(lblLogin, "cell 0 1,alignx center, gapbottom 15");

        JLabel lblUsuario = new JLabel("Usuário:");
        lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
        painel.add(lblUsuario, "cell 0 2,alignx left");

        txtUsuario = new JTextField();
        txtUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
        painel.add(txtUsuario, "cell 0 3,growx");

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
        painel.add(lblSenha, "cell 0 4,alignx left");

        txtSenha = new JPasswordField();
        txtSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
        painel.add(txtSenha, "cell 0 5,growx");

        JButton btLogar = new JButton("Entrar");
        btLogar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                acaobotao();
            }
        });
        btLogar.setFocusPainted(false);
        btLogar.setBackground(Color.LIGHT_GRAY);
        btLogar.setForeground(Color.BLACK);
        btLogar.setFont(new Font("Tahoma", Font.BOLD, 15));
        btLogar.setPreferredSize(new Dimension(130, 40));
        btLogar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        painel.add(btLogar, "cell 0 6,alignx center, gaptop 10");

        JButton btCriar = new JButton("Criar conta");
        btCriar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TelaCriarConta tela = new TelaCriarConta();
                tela.setVisible(true);
            }
        });
        btCriar.setFocusPainted(false);
        btCriar.setBackground(Color.LIGHT_GRAY);
        btCriar.setForeground(Color.BLACK);
        btCriar.setFont(new Font("Tahoma", Font.BOLD, 15));
        btCriar.setPreferredSize(new Dimension(150, 40));
        btCriar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        painel.add(btCriar, "cell 0 7,alignx center, gaptop 5");
    }
}
