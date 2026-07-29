package com.lutheone.desafio3.devsuperior.clientcrud.entities.pk;

import com.lutheone.desafio3.devsuperior.clientcrud.entities.Client;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.apache.catalina.User;

@Embeddable
public class ClientPk {

    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    public ClientPk() {
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
