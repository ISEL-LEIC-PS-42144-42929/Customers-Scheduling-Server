package com.customersscheduling.Service;

import com.customersscheduling.DTO.*;
import com.customersscheduling.HALObjects.BookingResource;
import com.customersscheduling.HALObjects.ServiceResource;
import com.customersscheduling.HALObjects.StoreResource;
import com.customersscheduling.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StoreService implements IStoreService {

    @Autowired
    StoreRepository storeRepo;

    @Autowired
    StoreServicesRepository storeServicesRepo;

    @Autowired
    ServiceRepository servicesRepo;

    @Autowired
    BookingRepository bookingRepo;

    @Autowired
    ClientStoresRepository clientStoresRepo;

    @Autowired
    PersonRepository personRepo;


    @Override
    public StoreResource insertStore(Store store) {
        return storeRepo.save(store).toResource();
    }

    @Override
    public ServiceResource insertServiceForStore(StoreServices s) {
        com.customersscheduling.DTO.Service serv = s.getService();
        com.customersscheduling.DTO.Service s1 = servicesRepo.findService(serv.getDescription(), serv.getPrice(), serv.getTitle(), serv.getDuration());
        if(s1!=null) s.getService().setId(s1.getId());
        return s.getService().toResource();
    }

    @Override
    public BookingResource insertBook(Booking booking) {
        return bookingRepo.save(booking).toResource();
    }

    @Override
    public Booking getBookingById(int id) {
        return bookingRepo.getOne(id);
    }

    @Override
    public Store getStoreByNif(String nif) {
        return storeRepo.findByNif(nif);
    }

    @Override
    public List<Store> getStoresOfUser(String email) {
        return storeRepo.findByOwner(email);
    }

    @Override
    public List<Client> getPendentRequests(String nif) {
        List<String> emails =  clientStoresRepo.findPendentRequestsOfStore(nif);
        List<Client> clients = new ArrayList<>();
        emails.iterator().forEachRemaining( email -> {
            Person p = personRepo.findOne(email);
            if( p instanceof  Client)
                clients.add((Client) p);
        });
        return clients;
    }

    @Override
    public List<Booking> getServiceDisp(String nif, int id) {
        return bookingRepo.findByStoreAndService(nif, id);
    }
}
