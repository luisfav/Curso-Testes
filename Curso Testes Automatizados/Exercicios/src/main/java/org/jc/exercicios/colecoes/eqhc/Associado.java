/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jc.exercicios.colecoes.eqhc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 *
 * @author jean
 */
public class Associado {
    private final long matricula;
    private final String nome;
    private final String cpf;
    private final String rg;
    private final LocalDate nascimento;
    private final LocalDate associacao;
    private final List<Dependente> dependentes;

    public Associado(final long matricula, final String nome, final String cpf,
        final String rg, final LocalDate nascimento,
        final LocalDate associacao) {
        this.matricula = matricula;
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
        this.nascimento = nascimento;
        this.associacao = associacao;
        this.dependentes = new ArrayList<>();
    }

    public boolean ehAniversarioDeNascimento(int ano) {
        return ehAniversario(this.nascimento, ano);
    }
    
    public boolean ehAniversarioDeAssociacao(int ano) {
        return ehAniversario(this.associacao, ano);
    }
    
    public int idade() {
        return LocalDate.now().getYear() - this.nascimento.getYear();
    }
    
    public void incluiDependente(final String nome, final LocalDate nasc) {
        this.dependentes.add(new Dependente(this, nome, nascimento));
    }
    
    public Collection<Dependente> filtraDependentesPor(
        final Predicate<Dependente> predicado) {
        return this.dependentes.stream()
            .filter(predicado)
            .collect(Collectors.toList());
    }
    
    private static boolean ehAniversario(final LocalDate data, final int ano) {
        return data.getYear() == ano;
    }

    @Override
    public boolean equals(final Object obj) {
        boolean equal;
        if(obj instanceof Associado) {
            Associado outro = (Associado) obj;
            equal = new EqualsBuilder().appendSuper(super.equals(obj))
                .append(nome, outro.nome).append(matricula, outro.matricula)
                .append(cpf, outro.cpf).append(rg, outro.rg)
                .append(nascimento, outro.nascimento)
                .append(associacao, outro.associacao)
                .append(dependentes, outro.dependentes)
                .isEquals();
        } else {
            equal = false;
        }
        return equal;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(13, 31).appendSuper(super.hashCode())
            .append(nome).append(matricula).append(cpf).append(rg)
            .append(nascimento).append(associacao).append(dependentes)
            .toHashCode();
    }
    
    
    
}
