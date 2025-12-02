package backend;

import backend.Cerveja;
import backend.CervejaDAO;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Estatisticas extends JFrame {

    public Estatisticas() {

        setTitle("Estatísticas - PampaBrew");
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(1, 3));

        CervejaDAO dao = new CervejaDAO();

        DefaultCategoryDataset dsMedia = new DefaultCategoryDataset();
        dao.listarMediaNotasPorTipo().forEach(m -> {
            dsMedia.addValue(m.getMedia(), "Média", m.getTipo());
        });

        JFreeChart graficoMedia = ChartFactory.createBarChart(
                "Média de Notas por Tipo",
                "Tipo",
                "Média",
                dsMedia
        );

        DefaultCategoryDataset dsRanking = new DefaultCategoryDataset();

        List<Cerveja> ranking = dao.listarNota();
        ranking.stream().limit(10).forEach(c -> {
            dsRanking.addValue(c.getAvaliacao(), "Avaliação", c.getNome());
        });

        JFreeChart graficoRanking = ChartFactory.createBarChart(
                "Top 10 Cervejas",
                "Cerveja",
                "Nota",
                dsRanking
        );

        DefaultCategoryDataset dsMes = new DefaultCategoryDataset();

        dao.getDegustacoesPorMes().forEach(d -> {
            dsMes.addValue(d.getTotal(), "Degustações", d.getMesNome());
        });

        JFreeChart graficoMes = ChartFactory.createLineChart(
                "Degustações por Mês",
                "Mês",
                "Qtd",
                dsMes
        );

        add(new ChartPanel(graficoMedia));
        add(new ChartPanel(graficoRanking));
        add(new ChartPanel(graficoMes));
    }
}
