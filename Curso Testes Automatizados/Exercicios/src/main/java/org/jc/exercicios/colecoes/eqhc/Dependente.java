/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jc.exercicios.colecoes.eqhc;

import java.time.LocalDate;
import java.util.function.Predicate;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 *
 * @author jean
 */
public class Dependente {
    
    public static class NomeContem implements Predicate<Dependente> {
        private final String parte;

        public NomeContem(String parte) {
            this.parte = parte;
        }

        @Override
        public boolean test(Dependente dependente) {
            return dependente.nome.contains(parte);
        }
    }
    
    public static class NascidoEntre implements Predicate<Dependente> {

        private final LocalDate inicio;
        private final LocalDate fim;

        public NascidoEntre(LocalDate inicio, LocalDate fim) {
            this.inicio = inicio;
            this.fim = fim;
        }

        @Override
        public boolean test(Dependente dependente) {
            return inicio.isBefore(dependente.nascimento)
                && fim.isAfter(dependente.nascimento);
        }
    }
    
    private final Associado associado;
    private final String nome;
    private final LocalDate nascimento;

    public Dependente(final Associado associado, final String nome,
        final LocalDate nascimento) {
        this.associado = associado;
        this.nome = nome;
        this.nascimento = nascimento;
    }
    
    public int idade() {
        return LocalDate.now().getYear() - nascimento.getYear();
    }

    @Override
    public boolean equals(Object obj) {
        boolean equal;
        if(obj instanceof Dependente) {
            Dependente outro = (Dependente) obj;
            equal = new EqualsBuilder()
                .append(associado, outro.associado).append(nome, outro.nome)
                .append(nascimento, outro.nascimento)
                .isEquals();
        } else {
            equal = false;
        }
        return equal;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(13, 31).appendSuper(super.hashCode())
            .append(associado).append(nome).append(nascimento)
            .toHashCode();
    }
}
