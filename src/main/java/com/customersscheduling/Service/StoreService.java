package com.customersscheduling.Service;

import com.customersscheduling.DTO.Booking;
import com.customersscheduling.DTO.Store;
import com.customersscheduling.DTO.StoreServices;
import com.customersscheduling.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    @Override
    public void insertStore(Store store) {
        storeRepo.save(store);
    }

    @Override
    public void insertServiceForStore(StoreServices s) {
        com.customersscheduling.DTO.Service serv = s.getPk().getService();
        com.customersscheduling.DTO.Service s1 = servicesRepo.findServiceByParams(serv.getDescription(), serv.getPrice(), serv.getTitle(), serv.getDuration());
        if(s1!=null) s.getPk().getService().setId(s1.getId());
        storeServicesRepo.save(s);
    }

    @Override
    public void insertBook(Booking booking) {
        bookingRepo.save(booking);
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
}
