package com.customersscheduling.Service;

import com.customersscheduling.Domain.Client;
import com.customersscheduling.Domain.Store;

import java.util.List;

public interface IClientService {
    Client insertClient(Client client);
    List<Store> getStoresByEmail(String email);
    List<Store> getPendentRequests(String email);
    Client getClient(String email);
}
