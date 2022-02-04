package com.imd0509.gerenciabiblioteca.model;

import java.io.Serializable;
import java.util.List;

public class Usuario implements Serializable {
    private String nome;
    private List<Emprestimo> emprestimos;

    public Usuario() {
    }

    public Usuario(String nome, List<Emprestimo> emprestimos) {
        this.nome = nome;
        this.emprestimos = emprestimos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public void setEmprestimos(List<Emprestimo> emprestimos) {
        this.emprestimos = emprestimos;
    }
}
