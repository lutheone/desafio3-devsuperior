package com.lutheone.desafio3.devsuperior.clientcrud.repositories;

import com.lutheone.desafio3.devsuperior.clientcrud.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}
