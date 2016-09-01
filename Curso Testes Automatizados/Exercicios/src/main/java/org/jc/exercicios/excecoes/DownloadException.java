/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jc.exercicios.excecoes;

/**
 * EXceção específica para falhas de download.
 * @author jean
 */
public class DownloadException extends Exception {
    /**
     * Cris uma instância de <code>DownloadException</code> com a mensagem de
     * detalhe especificada e a causa da exceção.
     *
     * @param msg Mensagem de detalhe.
     * @param cause Causa do erro.
     */
    public DownloadException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
