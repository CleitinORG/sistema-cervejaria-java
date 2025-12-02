package frontend;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import backend.Usuario;
import backend.UsuarioDAO;
import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaCriarConta extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField nome;
    private JTextField email;
    private JPasswordField senha;
    
    public void acaobotao() {

        String nomeU = nome.getText().trim();
        String emailU = email.getText().trim();
        String senhaU = new String(senha.getPassword()).trim();

        Usuario novo = new Usuario();
        novo.setNome(nomeU);
        novo.setEmail(emailU);
        novo.setSenha(senhaU);

        UsuarioDAO dao = new UsuarioDAO();

        try {
            dao.adicionar(novo);

            JOptionPane.showMessageDialog(this,
                    "Conta criada com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);


        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Erro ao criar conta:\n" + ex.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }


    public TelaCriarConta() {

        setTitle("PampaBrew - Criar Conta");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel contentPane = new JPanel();
        contentPane.setBackground(new Color(245, 245, 245));
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        setContentPane(contentPane);

        contentPane.setLayout(new MigLayout("", "[grow]", "[grow]"));

        JPanel painel = new JPanel();
        painel.setBackground(Color.WHITE);
        painel.setBorder(new EmptyBorder(25, 25, 25, 25));
        painel.setLayout(new MigLayout("", "[grow]", "[][]20[][][][][][]20[][][][]"));
        painel.setPreferredSize(new Dimension(380, 360));
        contentPane.add(painel, "cell 0 0,alignx center,aligny center");

        ImageIcon icon = new ImageIcon(getClass().getResource("/pampabrew/img/logo.png"));
        Image imgScaled = icon.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        JLabel img = new JLabel(new ImageIcon(imgScaled));
        painel.add(img, "cell 0 0,alignx center");

        JLabel lblTitulo = new JLabel("Criar nova conta");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
        painel.add(lblTitulo, "cell 0 1,alignx center, gapbottom 15");

        JLabel lblNome = new JLabel("Nome completo:");
        lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
        painel.add(lblNome, "cell 0 2,alignx left");

        nome = new JTextField();
        nome.setFont(new Font("Tahoma", Font.PLAIN, 14));
        painel.add(nome, "cell 0 3,growx");

        JLabel lblEmail = new JLabel("E-mail:");
        lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
        painel.add(lblEmail, "cell 0 4,alignx left");

        email = new JTextField();
        email.setFont(new Font("Tahoma", Font.PLAIN, 14));
        painel.add(email, "cell 0 5,growx");

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
        painel.add(lblSenha, "cell 0 6,alignx left");

        senha = new JPasswordField();
        senha.setFont(new Font("Tahoma", Font.PLAIN, 14));
        painel.add(senha, "cell 0 7,growx");

        JButton btCriar = new JButton("Criar conta");
        btCriar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		acaobotao();
        		TelaLogin tela = new TelaLogin();
        		tela.setVisible(true);
        		dispose();
        	}
        });
        btCriar.setFocusPainted(false);
        btCriar.setBackground(Color.LIGHT_GRAY);
        btCriar.setForeground(Color.BLACK);
        btCriar.setFont(new Font("Tahoma", Font.BOLD, 15));
        btCriar.setPreferredSize(new Dimension(130, 40));
        btCriar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        painel.add(btCriar, "cell 0 10,alignx center,gapy 15");

    }
}
