package com.kielblockdev.restapi.controllers;

import com.kielblockdev.restapi.dtos.ClientRecordDTO;
import com.kielblockdev.restapi.models.ClientModel;
import com.kielblockdev.restapi.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class ClientController {
    @Autowired
    ClientService clientService;

    @PostMapping("/clients")
    public ResponseEntity<ClientModel> saveClient(@RequestBody @Valid ClientRecordDTO clientRecordDTO) {
        return clientService.saveClient(clientRecordDTO);
    }

    @GetMapping("/clients")
    public ResponseEntity<List<ClientModel>> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/clients/{id}")
    public ResponseEntity<Object> getOneClient(@PathVariable(name = "id") UUID id) {
        return  clientService.getOneClient(id);
    }

    @PutMapping("/clients/{id}")
    public ResponseEntity<Object> updateClient(@PathVariable(name = "id") UUID id,
                                               @RequestBody @Valid ClientRecordDTO clientRecordDTO) {
        return clientService.updateClient(id, clientRecordDTO);
    }

    @DeleteMapping("/clients/{id}")
    public ResponseEntity<Object> deleteClient(@PathVariable(name = "id") UUID id) {
        return clientService.deleteClient(id);
    }
}
