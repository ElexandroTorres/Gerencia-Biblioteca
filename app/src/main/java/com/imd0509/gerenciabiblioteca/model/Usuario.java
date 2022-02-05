package com.imd0509.gerenciabiblioteca.model;

import java.io.Serializable;
import java.util.List;

public class Usuario implements Serializable {
    private Integer id;
    private String nome;
    private List<Emprestimo> emprestimos;

    public Usuario() {
    }

    public Usuario(Integer id, String nome, List<Emprestimo> emprestimos) {
        this.id = id;
        this.nome = nome;
        this.emprestimos = emprestimos;
    }

    public Usuario(String nome, List<Emprestimo> emprestimos) {
        this.nome = nome;
        this.emprestimos = emprestimos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public void update(Usuario usuario) {
        nome = usuario.getNome();
        emprestimos = usuario.getEmprestimos();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Usuario that = (Usuario) o;
        return id.equals(that.getId());
    }
}
