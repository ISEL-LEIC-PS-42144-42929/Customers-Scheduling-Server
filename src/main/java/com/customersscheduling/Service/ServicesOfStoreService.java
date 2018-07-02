package com.customersscheduling.Service;

import com.customersscheduling.Domain.*;
import com.customersscheduling.ExceptionHandling.CustomExceptions.ResourceNotFoundException;
import com.customersscheduling.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class ServicesOfStoreService implements IServicesOfStoreService {

    @Autowired
    StoreRepository storeRepo;

    @Autowired
    StoreServicesRepository storeServicesRepo;

    @Autowired
    ServiceRepository servicesRepo;

    @Autowired
    StaffServicesRepository staffServicesRepo;

    @Autowired
    BookingRepository bookingRepo;

    @Autowired
    PersonRepository personRepo;


    @Override
    public StaffServices insertStaffForService(StaffServices s) {
        personRepo.save(s.getPk().getStaff());
        staffServicesRepo.save(s);
        int id = s.getPk().getStoresServices().getPk().getService().getId();
        String email = s.getPk().getStaff().getEmail();
        String nif = s.getPk().getStoresServices().getPk().getStore().getNif();
        return new StaffServices(new StaffServicesPK((Staff)personRepo.findByEmail(email), new StoreServices(new StoreServicesPK(storeRepo.findByNif(nif), servicesRepo.findById(id)))));
    }


    @Override
    public StoreServices insertServiceForStore(StoreServices s) {
        com.customersscheduling.Domain.Service serv = s.getPk().getService();
        com.customersscheduling.Domain.Service s1 = servicesRepo.findService(serv.getDescription(), serv.getPrice(), serv.getTitle(), serv.getDuration());
        if(s1!=null) s.getPk().getService().setId(s1.getId());
        servicesRepo.save(s.getPk().getService());
        return storeServicesRepo.save(s);
    }


    @Override
    public List<Booking> getServiceDisp(int id) {
        List<Booking> list = bookingRepo.findByService_Id(id);
        for (Booking booking : list) {
            booking.setService(servicesRepo.findById(booking.getService().getId()));
            booking.setStore(storeRepo.findByNif(booking.getStore().getNif()));
        }
        return list;
    }

    @Override
    public List<Staff> getStaffOfService(int id, String nif) {
        List<StaffServices> ss = staffServicesRepo.getByPk_StoresServices_Pk_Service_IdAndPk_StoresServices_Pk_Store_Nif(id, nif);
        return ss.stream()
                .map( i -> i.getPk().getStaff())
                .collect(Collectors.toList());
    }

    @Override
    public com.customersscheduling.Domain.Service getService(int id) {
        com.customersscheduling.Domain.Service serv = servicesRepo.findById(id);
        if(serv == null) throw new ResourceNotFoundException("Service with the id - "+id+" - doesn't exists.");
        return serv;
    }
}
