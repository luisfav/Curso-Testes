package org.jc.exercicios.excecoes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.zip.GZIPInputStream;

/**
 * Encapsula uma página web.
 * @author jean
 */
public class Pagina {
    /**
     * Codificação do conteúdo a ser lido.
     */
    private static final String GZIP = "gzip";
    /**
     * Endereço da página.
     */
    private final String endereco;

    public Pagina(String end) {
        this.endereco = end;
    }
    
    /**
     * Faz o download do conteúdo da página.
     * @return String com o conteúdo da página, codificado com UTF-8.
     * @throws org.jc.exercicios.excecoes.DownloadException Caso não seja
     * possível realizar o download.
     */
    public String download() throws DownloadException {
        final StringBuilder conteudo = new StringBuilder();
        try {
            URLConnection conn = new URL(this.endereco).openConnection();
            conn.setRequestProperty("Accept", "gzip, deflate");
            leConteudo(
                conn.getInputStream(),
                conteudo,
                conn.getContentEncoding()
            );
        } catch (MalformedURLException ex) {
            throw new DownloadException(
                String.format(
                    "O dendereço informado não é válido: \"%s\"",
                    endereco
                ),
                ex
            );
        } catch (IOException ex) {
            throw new DownloadException(
                String.format(
                    "Falha ao recuperar conteúdo de \"%s\"",
                    endereco
                ),
                ex
            );
        }
        return conteudo.toString();
    }

    /**
     * Lê o conteúdo do InputStream.
     * @param input Origem do dados.
     * @param armazem Onde os dados lidos serão colocados.
     * @param encoding Codificação da origem dos dados
     * @throws IOException Caso haja um erro na leitura da entrada.
     */
    private static void leConteudo(InputStream input, StringBuilder armazem,
        String encoding) throws IOException {
        InputStreamReader bridge;
        if(Pagina.GZIP.equals(encoding)) {
            bridge = new InputStreamReader(new GZIPInputStream(input));
        } else {
            bridge = new InputStreamReader(input);
        }
        try (BufferedReader reader = new BufferedReader(bridge)) {
            String linha;
            do {
                linha = reader.readLine();
                if (linha != null)
                  //armazem.append(linha).append('\n');
                  armazem.append(linha);
            } while (linha != null);
        }
    }
}
