package com.imd0509.gerenciabiblioteca.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Usuario implements Serializable {
    private String cpf;
    private String nome;
    private String email;
    private String cep;
    private String bairro;
    private String rua;
    private String numero;
    private String complemento;
    private List<Emprestimo> emprestimos;

    public Usuario() {
        emprestimos = new ArrayList<>();
    }


    public Usuario(String cpf, String nome, String email, String cep, String bairro, String rua, String numero, List<Emprestimo> emprestimos) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.cep = cep;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
        this.emprestimos = emprestimos;
    }

    public Usuario(String cpf, String nome, String email, String cep, String bairro, String rua, String numero, String complemento, List<Emprestimo> emprestimos) {
        this(cpf, nome, email, cep, bairro, rua, numero, emprestimos);
        this.complemento = complemento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public void setEmprestimos(List<Emprestimo> emprestimos) {
        this.emprestimos = emprestimos;
    }

    public void update(Usuario usuario) {
        nome = usuario.getNome();
        email = usuario.getEmail();
        cep = usuario.getCep();
        bairro = usuario.getBairro();
        rua = usuario.getRua();
        numero = usuario.getNumero();
        complemento = usuario.getComplemento();
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
        return cpf.equals(that.getCpf());
    }
}
