package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.sql.Date;

import backend.Cerveja;
import backend.CervejaDAO;

public class TelaCadastraCerveja extends JFrame {

    private JTextField txtNome;
    private JTextField txtTipo;
    private JTextField txtTeor;
    private JTextField txtIBU;
    private JTextField txtPais;
    private JTextField txtData;
    private JTextField txtLocal;
    private JTextField txtAvaliacao;
    private JTextField txtFabricante;

    private JTextArea txtComentarios;
    private JTextField txtImagem; 
    
    public void acaobotao() {
        try {
            
            Cerveja c = new Cerveja();

            c.setNome(txtNome.getText());
            c.setTipo(txtTipo.getText());
            c.setTeor(Double.parseDouble(txtTeor.getText()));
            c.setIbu(Integer.parseInt(txtIBU.getText()));
            c.setPaisOrigem(txtPais.getText());
            c.setFabricante(txtFabricante.getText());
            c.setDataDegustacao(Date.valueOf(txtData.getText()));
            c.setLocalDegustado(txtLocal.getText());
            c.setAvaliacao(Integer.parseInt(txtAvaliacao.getText()));
            c.setComentarios(txtComentarios.getText());
            c.setImg(txtImagem.getText());

            CervejaDAO dao = new CervejaDAO();
            dao.adicionar(c);

            JOptionPane.showMessageDialog(this,
                    "Cerveja cadastrada com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);

            dispose();

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Erro ao salvar a cerveja:\n" + ex.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public TelaCadastraCerveja() {

        setTitle("PampaBrew - Cadastrar Cerveja");
        setSize(550, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel painel = new JPanel();
        painel.setLayout(new BorderLayout());
        painel.setBackground(new Color(245, 245, 245));
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        getContentPane().add(painel);

        ImageIcon icon = new ImageIcon(getClass().getResource("/pampabrew/img/logo.png"));
        Image imgScaled = icon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        JLabel imgTopo = new JLabel(new ImageIcon(imgScaled));
        imgTopo.setHorizontalAlignment(SwingConstants.CENTER);

        painel.add(imgTopo, BorderLayout.NORTH);

        JPanel form = new JPanel();
        form.setLayout(new GridLayout(0, 1, 6, 6));
        form.setBackground(new Color(245, 245, 245));

        txtNome = addCampo(form, "Nome:");
        txtTipo = addCampo(form, "Tipo:");
        txtTeor = addCampo(form, "Teor Alcoólico (%):");
        txtIBU = addCampo(form, "IBU:");
        txtPais = addCampo(form, "País de Origem:");
        txtFabricante = addCampo(form, "Fabricante:");
        txtData = addCampo(form, "Data Degustação (AAAA-MM-DD):");
        txtLocal = addCampo(form, "Local Degustado:");
        txtAvaliacao = addCampo(form, "Avaliação (0-10):");

        form.add(new JLabel("Comentários:"));
        txtComentarios = new JTextArea(4, 20);
        txtComentarios.setLineWrap(true);
        txtComentarios.setWrapStyleWord(true);
        form.add(new JScrollPane(txtComentarios));

        form.add(new JLabel("Imagem:"));
        txtImagem = new JTextField();
        txtImagem.setEditable(false);
        form.add(txtImagem);

        JButton btnImagem = new JButton("Selecionar Imagem");
        btnImagem.setBackground(Color.LIGHT_GRAY);
        btnImagem.addActionListener(e -> selecionarImagem());
        form.add(btnImagem);

        painel.add(form, BorderLayout.CENTER);

        JButton btSalvar = new JButton("Salvar Cerveja");
        btSalvar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		acaobotao();
        	}
        });
        btSalvar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btSalvar.setBackground(Color.GRAY);
        btSalvar.setForeground(Color.WHITE);
        painel.add(btSalvar, BorderLayout.SOUTH);
    }

    private JTextField addCampo(JPanel panel, String label) {
        panel.add(new JLabel(label));
        JTextField campo = new JTextField();
        panel.add(campo);
        return campo;
    }

   
    public void selecionarImagem() {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Selecione um rótulo");

        int result = chooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File arquivoSelecionado = chooser.getSelectedFile();
            backend.Imagem img = new backend.Imagem();
             String nomeSalvo = img.salvar(arquivoSelecionado);

            if (nomeSalvo != null) {
                txtImagem.setText(nomeSalvo);
                JOptionPane.showMessageDialog(this, "Imagem salva em /rotulos com sucesso!");
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao salvar a imagem!");
            }
        }
    }


}
