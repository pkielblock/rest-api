package com.kielblockdev.restapi.services;

import com.kielblockdev.restapi.dtos.ClientRecordDTO;
import com.kielblockdev.restapi.models.ClientModel;
import com.kielblockdev.restapi.repositories.ClientRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientService {
    @Autowired
    ClientRepository clientRepository;

    public ResponseEntity<ClientModel> saveClient(ClientRecordDTO clientRecordDTO) {
        var clientModel = new ClientModel();
        BeanUtils.copyProperties(clientRecordDTO, clientModel);
        return ResponseEntity.status(HttpStatus.OK).body(clientRepository.save(clientModel));
    }

    public ResponseEntity<List<ClientModel>> getAllClients() {
        return ResponseEntity.status(HttpStatus.OK).body(clientRepository.findAll());
    }

    public ResponseEntity<Object> getOneClient(UUID id) {
        Optional<ClientModel> clientO = clientRepository.findById(id);
        if(clientO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(clientO.get());
    }

    public ResponseEntity<Object> updateClient(UUID id, ClientRecordDTO clientRecordDTO) {
        Optional<ClientModel> clientO = clientRepository.findById(id);
        if (clientO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found");
        }
        var clientModel = clientO.get();
        BeanUtils.copyProperties(clientRecordDTO, clientModel);
        return ResponseEntity.status(HttpStatus.OK).body(clientRepository.save(clientModel));
    }

    public ResponseEntity<Object> deleteClient(UUID id) {
        Optional<ClientModel> clientO = clientRepository.findById(id);
        if (clientO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found");
        }
        //Trying to delete by ID instead of object.
        clientRepository.deleteById(id);
        //clientRepository.delete(clientO.get());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
