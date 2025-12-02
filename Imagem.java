package backend;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class Imagem {

    private String nomeArquivo;

    public String salvar(File arquivo) {
        if (arquivo == null || !arquivo.exists()) {
            return null;
        }

        String pasta = System.getProperty("user.dir") + File.separator + "rotulos";

        File dir = new File(pasta);
        if (!dir.exists()) {
            dir.mkdirs(); 
        }

        String ext = arquivo.getName().substring(arquivo.getName().lastIndexOf("."));
        nomeArquivo = System.currentTimeMillis() + ext;

        File destino = new File(dir, nomeArquivo);

        try {
            Files.copy(arquivo.toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);
            return nomeArquivo;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }
}
