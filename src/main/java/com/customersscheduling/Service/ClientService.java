package com.customersscheduling.Service;

import com.customersscheduling.Domain.Client;
import com.customersscheduling.Domain.ClientStores;
import com.customersscheduling.Domain.Store;
import com.customersscheduling.ExceptionHandling.CustomExceptions.ResourceNotFoundException;
import com.customersscheduling.Repository.ClientStoresRepository;
import com.customersscheduling.Repository.PersonRepository;
import com.customersscheduling.Repository.StaffTimetableRepository;
import com.customersscheduling.Repository.TimetableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClientService implements IClientService {


    @Autowired
    PersonRepository personRepo;

    @Autowired
    ClientStoresRepository clientStoresRepo;

    @Override
    public Client insertClient(Client client) {
        return (Client) personRepo.save(client);
    }

    @Override
    public List<Store> getStoresByEmail(String email) {
        return clientStoresRepo.findByPk_Client_EmailAndAccepted(email, true)
                .stream()
                .map(cs -> cs.getPk().getStore())
                .collect(Collectors.toList());
    }



    @Override
    public List<Store> getPendentRequests(String email) {
        List<ClientStores> l = clientStoresRepo.findByPk_Client_EmailAndAccepted(email, false);
        if(l.size()==0) throw new ResourceNotFoundException("Could not find any timetable for the user with the email '"+email+"'.");
        return l.stream()
                .map(cs -> cs.getPk().getStore())
                .collect(Collectors.toList());
    }


    @Override
    public Client getClient(String email) {
        Client client = (Client)personRepo.findByEmail(email);
        if(client == null) throw new ResourceNotFoundException("Could not find user with the email '"+email+"'.");
        return client;
    }

    @Override
    public Client deleteClient(String email) {
        Client client = getClient(email);
        personRepo.delete(client);
        return client;
    }
}
