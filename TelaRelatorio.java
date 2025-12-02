package frontend;

import backend.CervejaDAO;

import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import backend.MediaTipo;
import backend.DegustacaoMes;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaRelatorio extends JFrame {

    private CervejaDAO dao = new CervejaDAO();
    private JButton btnVoltar;

    public TelaRelatorio() {
        setTitle("Relatórios e Estatísticas");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        	}
        });

        JPanel topo = new JPanel();
        topo.add(btnVoltar);
        getContentPane().add(topo, BorderLayout.NORTH);

        JTabbedPane abas = new JTabbedPane();

        abas.add("Média de Notas por Tipo", painelMediaPorTipo());
        abas.add("Degustações por Mês", painelDegustacoesMes());

        getContentPane().add(abas, BorderLayout.CENTER);

        setVisible(true);
    }

    private JPanel painelMediaPorTipo() {
        List<MediaTipo> lista = dao.listarMediaNotasPorTipo();

        DefaultCategoryDataset dados = new DefaultCategoryDataset();

        for (MediaTipo m : lista) {
            dados.addValue(m.getMedia(), "Média", m.getTipo());
        }

        JFreeChart grafico = ChartFactory.createBarChart(
                "Média de Notas por Tipo",
                "Tipo da Cerveja",
                "Média",
                dados
        );

        return new ChartPanel(grafico);
    }

    private JPanel painelDegustacoesMes() {
        List<DegustacaoMes> lista = dao.getDegustacoesPorMes();

        DefaultPieDataset dados = new DefaultPieDataset();

        for (DegustacaoMes d : lista) {
            dados.setValue(d.getMesNome(), d.getTotal());
        }

        JFreeChart grafico = ChartFactory.createPieChart(
                "Degustações por Mês",
                dados,
                true,
                true,
                false
        );

        return new ChartPanel(grafico);
    }
}
