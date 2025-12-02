package frontend;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import backend.Cerveja;
import backend.CervejaDAO;
import backend.Usuario;

public class TelaListaCerveja extends JFrame {

    private JTable tabela;
    private JComboBox<String> cbOrdenar;
    private Usuario usuarioLogado;
    
    public void acaobotao() {
        CervejaDAO dao = new CervejaDAO();
        LinkedList<Cerveja> lista;

        String opc = (String) cbOrdenar.getSelectedItem();

        if ("Nome".equals(opc)) {
            lista = dao.listarOrdenado("nome");
        } else if ("País".equals(opc)) {
            lista = dao.listarOrdenado("paisOrigem");
        } else if ("Avaliação".equals(opc)) {
            lista = dao.listarOrdenado("avaliacao");
        } else {
            lista = dao.listar();
        }

        DefaultTableModel modelo = new DefaultTableModel(
                new Object[]{
                        "Nome", "Fabricante", "Tipo", "Teor",
                        "IBU", "País", "Data Deg.", "Local",
                        "Avaliação", "Comentários"
                },
                0
        );

        for (Cerveja c : lista) {
            modelo.addRow(new Object[]{
                    c.getNome(),
                    c.getFabricante(),
                    c.getTipo(),
                    c.getTeor(),
                    c.getIbu(),
                    c.getPaisOrigem(),
                    c.getDataDegustacao(),
                    c.getLocalDegustado(),
                    c.getAvaliacao(),
                    c.getComentarios()
            });
        }

        tabela.setModel(modelo);
    }

    public TelaListaCerveja(Usuario usuario) {

        this.usuarioLogado = usuario;

        setTitle("Lista de Cervejas - PampaBrew");
        setSize(1050, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        JPanel topo = new JPanel();
        topo.setBackground(Color.DARK_GRAY);

        ImageIcon icon = new ImageIcon("img/logo.png");
        JLabel lblLogo = new JLabel(icon);
        topo.add(lblLogo);

        getContentPane().add(topo, BorderLayout.NORTH);

        JPanel painelOpcoes = new JPanel();
        painelOpcoes.setBackground(Color.DARK_GRAY);

        cbOrdenar = new JComboBox<>(new String[]{
                "Ordenar por...",
                "Nome",
                "País",
                "Avaliação"
        });

        JButton btOrdenar = new JButton("Aplicar");
        btOrdenar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		acaobotao();
        	}
        });

        painelOpcoes.add(cbOrdenar);
        painelOpcoes.add(btOrdenar);

        getContentPane().add(painelOpcoes, BorderLayout.SOUTH);

        tabela = new JTable();
        JScrollPane scroll = new JScrollPane(tabela);
        tabela.setFillsViewportHeight(true);

        getContentPane().add(scroll, BorderLayout.CENTER);

        JButton btnVoltar = new JButton("Voltar");
        topo.add(btnVoltar);

        btnVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new TelaPrincipal(usuarioLogado).setVisible(true);
            }
        });
    }
}
