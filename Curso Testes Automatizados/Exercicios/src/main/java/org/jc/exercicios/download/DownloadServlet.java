/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jc.exercicios.download;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author jean
 */
@WebServlet
public class DownloadServlet extends HttpServlet {

    /**
     * Realiza o download de uma nota fiscal previamente emitida. Para encontrar
     * uma nota fiscal, são necessários o cpf/cnpj do cliente (cpf_cnpj), a data
     * de emissão (dt_emissao) e o número da nota (num_nf). Somente a versão em
     * xml da nota fiscal permanece disponível para download.
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(final HttpServletRequest req,
        final HttpServletResponse resp)
        throws ServletException, IOException {
        boolean authenticated = req.authenticate(resp);
        if (authenticated) {
            String identificacao = StringUtils.defaultIfEmpty(
                req.getParameter("cpf_cnpj"),
                ""
            );
            String data = StringUtils.defaultIfEmpty(
                req.getParameter("dt_emissao"),
                ""
            );
            String numero = StringUtils.defaultIfEmpty(
                req.getParameter("num_nf"),
                ""
            );
            File dir = new File(System.getProperty("nfe.dir"));
            if (identificacao.matches("\\d+") && numero.matches("\\d+")
                && data.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}")) {
                File nfe = new File(
                    new File(dir, identificacao),
                    data + '_' + numero + ".xml"
                );
                if (nfe.exists()) {
                    resp.setContentType("text/xml");
                    try (
                        BufferedReader reader = new BufferedReader(
                            new FileReader(nfe)
                        );
                        PrintWriter writer = resp.getWriter();) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            writer.write(line);
                            writer.println();
                        }
                        writer.flush();
                    }
                    resp.setStatus(HttpServletResponse.SC_OK);
                }
            } else {
                String msg = String.format(
                    "Prezado cliente %s, não encontramos a nota número %s,"
                    + " na data %s.",
                    identificacao,
                    numero,
                    data
                );
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, msg);
            }
        } else {
            resp.sendError(
                HttpServletResponse.SC_FORBIDDEN,
                "Você não pode fazer download de notas fiscais!"
            );
        }
    }
}
