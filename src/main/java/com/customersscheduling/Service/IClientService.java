package com.customersscheduling.Service;

import com.customersscheduling.Domain.Booking;
import com.customersscheduling.Domain.Client;
import com.customersscheduling.Domain.Store;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

public interface IClientService {
    Client insertClient(Client client);
    List<Store> getStoresByEmail(String email);
    List<Store> getPendentRequests(String email);
    Client getClient(String email);

    Client deleteClient(String email);

    List<Booking> getBooks(String email);

}
