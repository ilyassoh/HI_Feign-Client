package com.example.car.client;

import com.example.car.entities.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "client-service")
public interface ClientServiceClient {

    @GetMapping("/api/client")
    List<Client> getAllClients();

    @GetMapping("/api/client/{id}")
    Client getClientById(@PathVariable Long id);

}
