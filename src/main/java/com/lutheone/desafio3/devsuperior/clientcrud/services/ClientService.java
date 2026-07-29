package com.lutheone.desafio3.devsuperior.clientcrud.services;

import com.lutheone.desafio3.devsuperior.clientcrud.dto.ClientDTO;
import com.lutheone.desafio3.devsuperior.clientcrud.entities.Client;
import com.lutheone.desafio3.devsuperior.clientcrud.repositories.ClientRepository;
import com.lutheone.desafio3.devsuperior.clientcrud.services.exceptions.DatabaseExeception;
import com.lutheone.desafio3.devsuperior.clientcrud.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

    private final ClientRepository repository;

    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id) {
        return repository.findById(id).map(ClientDTO::new).orElseThrow(()
                -> new ResourceNotFoundException("Client not found"));
    }

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll(Pageable pageable) {
        Page<Client> page = repository.findAll(pageable);
        return page.map(ClientDTO::new);
    }

    @Transactional
    public ClientDTO insert(ClientDTO clientDTO) {
        Client client = new Client();
        copyDtoToEntity(clientDTO, client);
        return new ClientDTO(repository.save(client));
    }

    @Transactional
    public ClientDTO update(Long id, ClientDTO clientDTO) {
        try {
            Client entity = repository.getReferenceById(id);
            copyDtoToEntity(clientDTO, entity);
            entity = repository.save(entity);
            return new ClientDTO(entity);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
        try {
            repository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseExeception("Cannot delete resource with id: " + id + " due to integrity violation");
        }
    }

    private void copyDtoToEntity(ClientDTO clientDTO, Client client) {
        client.setName(clientDTO.getName());
        client.setCpf(clientDTO.getCpf());
        client.setIncome(clientDTO.getIncome());
        client.setBirthDate(clientDTO.getBirthDate());
        client.setChildren(clientDTO.getChildren());
    }

}
