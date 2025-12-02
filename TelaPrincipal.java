package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import backend.Usuario;

public class TelaPrincipal extends JFrame {

    private Usuario usuarioLogado;
    

    public TelaPrincipal(Usuario usuario) {

        this.usuarioLogado = usuario;

        setTitle("PampaBrew - Menu Principal");
        setSize(750, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(Color.WHITE);
        menuBar.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setJMenuBar(menuBar);

        JMenu menuHome = new JMenu("Home");
        menuHome.setFont(new Font("Segoe UI", Font.BOLD, 16));
        menuBar.add(menuHome);

        JMenuItem home = new JMenuItem("Tela Inicial");
        home.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		TelaPrincipal tp = new TelaPrincipal(usuario);
        		tp.setVisible(true);
        		
        	}
        });
        menuHome.add(home);
        JMenu menuUsuario = new JMenu("Usuário");
        menuUsuario.setFont(new Font("Segoe UI", Font.BOLD, 16));
        menuBar.add(menuUsuario);

        JMenuItem editarPerfil = new JMenuItem("Editar Perfil");
        editarPerfil.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TelaEditarPerfil tela = new TelaEditarPerfil(usuarioLogado);
                tela.setVisible(true);
            }
        });

        JMenuItem sair = new JMenuItem("Sair");
        sair.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		   dispose();
                   new TelaLogin().setVisible(true);
        	}
        });

        menuUsuario.add(editarPerfil);
        menuUsuario.add(sair);
        
        JMenu menuCervejas = new JMenu("Cervejas");
        menuCervejas.setFont(new Font("Segoe UI", Font.BOLD, 16));
        menuBar.add(menuCervejas);

        JMenuItem cadastrar = new JMenuItem("Cadastrar Cerveja");
        cadastrar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		TelaCadastraCerveja tcc = new TelaCadastraCerveja();
        		tcc.setVisible(true);
        	}
        });
        JMenuItem listar = new JMenuItem("Listar Cervejas");
        listar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		TelaListaCerveja tlc = new TelaListaCerveja(usuarioLogado);
        		tlc.setVisible(true);
        	}
        });
        JMenuItem galeria = new JMenuItem("Galeria de Rótulos");
        galeria.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		 TelaGaleria tg = new TelaGaleria();
        		    tg.setVisible(true);
        	}
        });

        menuCervejas.add(cadastrar);
        menuCervejas.add(listar);
        menuCervejas.add(galeria);

        JMenu menuRelatorio = new JMenu("Relatório");
        menuRelatorio.setFont(new Font("Segoe UI", Font.BOLD, 16));
        menuBar.add(menuRelatorio);

        JMenuItem itemRelatorio = new JMenuItem("Gerar Relatório");
        itemRelatorio.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		TelaRelatorio tr = new TelaRelatorio();
        		tr.setVisible(true);
        	}
        });
        menuRelatorio.add(itemRelatorio);

        JPanel painel = new JPanel();
        painel.setBackground(new Color(245, 245, 245));
        painel.setLayout(new BorderLayout());
       

        JLabel titulo = new JLabel("Bem-vindo ao PampaBrew!", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 32));
        painel.add(titulo, BorderLayout.NORTH);

        getContentPane().add(painel);

    }
}
