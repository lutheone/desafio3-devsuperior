package com.lutheone.desafio3.devsuperior.clientcrud.dto;


import com.lutheone.desafio3.devsuperior.clientcrud.entities.Client;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class ClientDTO {

    private Long id;

    @Size(min = 3, max = 80)
    @NotBlank(message = "Field cannot be blank")
    private String name;

    @Size(min = 11, max = 11)
    @NotBlank(message = "Field cannot be blank")
    private String cpf;

    @Positive(message = "Price must be positive")
    private Double income;

    @PastOrPresent(message = "Birth date cannot be in the future")
    private LocalDate birthDate;
    private Integer children;

    public ClientDTO(){
    }

    public ClientDTO(Long id, Integer children, LocalDate birthDate, Double income, String cpf, String nome) {
        this.id = id;
        this.children = children;
        this.birthDate = birthDate;
        this.income = income;
        this.cpf = cpf;
        this.name = nome;
    }

    public ClientDTO(Client client) {
        id = client.getId();
        children = client.getChildren();
        birthDate = client.getBirthDate();
        income = client.getIncome();
        cpf = client.getCpf();
        name = client.getName();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public Double getIncome() {
        return income;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Integer getChildren() {
        return children;
    }
}
