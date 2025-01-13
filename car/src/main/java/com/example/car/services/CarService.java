package com.example.car.services;

import com.example.car.client.ClientServiceClient;
import com.example.car.entities.Car;
import com.example.car.entities.Client;
import com.example.car.models.CarResponse;
import com.example.car.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class CarService {
    @Autowired
    private ClientServiceClient clientServiceClient;

    public List<CarResponse> findAll() {
        // Fetch all clients from the Client Microservice
        List<Client> clients = clientServiceClient.getAllClients();

        // Simulate fetching cars from the database
        List<Car> cars = Arrays.asList(
                new Car(1L, "Toyota", "Corolla", 1L),
                new Car(2L, "Honda", "Civic", 2L)
        );

        // Map Car entities to CarResponse models
        return cars.stream()
                .map(car -> mapToCarResponse(car, clients))
                .toList();
    }

    private CarResponse mapToCarResponse(Car car, List<Client> clients) {
        Client foundClient = clients.stream()
                .filter(client -> client.getId().equals(car.getClientId()))
                .findFirst()
                .orElse(null);

        return CarResponse.builder()
                .id(car.getId())
                .brand(car.getBrand())
                .client(foundClient)
                .model(car.getModel())
                .build();
    }

    public CarResponse findById(Long id) throws Exception {
        // Simulate fetching a car by ID from the database
        Car car = new Car(1L, "Toyota", "Corolla", 1L); // Replace with actual DB call

        // Fetch the associated client from the Client Microservice
        Client client = clientServiceClient.getClientById(car.getClientId());

        return CarResponse.builder()
                .id(car.getId())
                .brand(car.getBrand())
                .client(client)
                .model(car.getModel())
                .build();
}
}
