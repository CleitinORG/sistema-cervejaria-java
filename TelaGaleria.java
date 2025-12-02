package frontend;

import backend.Cerveja;
import backend.CervejaDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.LinkedList;
import java.awt.event.ActionListener;

public class TelaGaleria extends JFrame {

    public TelaGaleria() {
        setTitle("Galeria de Rótulos - PampaBrew");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Galeria de Rótulos", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        getContentPane().add(titulo, BorderLayout.NORTH);

        JPanel painelGrid = new JPanel();
        painelGrid.setLayout(new GridLayout(0, 4, 15, 15));
        painelGrid.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        painelGrid.setBackground(Color.WHITE);

        JScrollPane scroll = new JScrollPane(painelGrid);
        getContentPane().add(scroll, BorderLayout.CENTER);

        CervejaDAO dao = new CervejaDAO();
        LinkedList<Cerveja> lista = dao.listar();

        for (Cerveja c : lista) {
            painelGrid.add(rotulo(c));
        }

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        	}
        });

        JPanel rodape = new JPanel();
        rodape.add(btnVoltar);
        getContentPane().add(rodape, BorderLayout.SOUTH);
    }

    private JPanel rotulo(Cerveja c) {

        JPanel card = new JPanel();
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        card.setLayout(new BorderLayout());

        String caminho = System.getProperty("user.dir")
                + File.separator + "rotulos"
                + File.separator + c.getImg();

        ImageIcon icone;

        File imgFile = new File(caminho);

        if (imgFile.exists()) {
            ImageIcon raw = new ImageIcon(caminho);
            Image img = raw.getImage().getScaledInstance(150, 180, Image.SCALE_SMOOTH);
            icone = new ImageIcon(img);
        } else {
            icone = new ImageIcon();
        }

        JLabel lblImg = new JLabel(icone);
        lblImg.setHorizontalAlignment(SwingConstants.CENTER);
        card.add(lblImg, BorderLayout.CENTER);

        String data = (c.getDataDegustacao() != null) ? c.getDataDegustacao().toString() : "—";

        JLabel lblInfo = new JLabel("<html><center>" +
                "<b>" + c.getNome() + "</b><br>" + data +
                "</center></html>");
        lblInfo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblInfo.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

        card.add(lblInfo, BorderLayout.SOUTH);

        return card;
    }
}
