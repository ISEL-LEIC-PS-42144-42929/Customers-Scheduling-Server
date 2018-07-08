package com.customersscheduling.Service;

import com.customersscheduling.Domain.*;
import com.customersscheduling.ExceptionHandling.CustomExceptions.ResourceNotFoundException;
import com.customersscheduling.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
@Transactional(rollbackFor = ResourceNotFoundException.class)
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
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED )
    public StaffServices insertStaffForService(StaffServices s) {
        personRepo.save(s.getPk().getStaff());
        staffServicesRepo.save(s);
        int id = s.getPk().getStoresServices().getPk().getService().getId();
        String email = s.getPk().getStaff().getEmail();
        String nif = s.getPk().getStoresServices().getPk().getStore().getNif();
        return new StaffServices(new StaffServicesPK((Staff)personRepo.findByEmail(email), new StoreServices(new StoreServicesPK(storeRepo.findByNif(nif), servicesRepo.findById(id)))));
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED )
    public StoreServices insertServiceForStore(StoreServices s) {
        Service serv = s.getPk().getService();
        Service s1 = servicesRepo.findService(serv.getDescription(), serv.getPrice(), serv.getTitle(), serv.getDuration());
        if(s1!=null) s.getPk().getService().setId(s1.getId());
        servicesRepo.save(s.getPk().getService());
        return storeServicesRepo.save(s);
    }


    @Override
    @Cacheable(value = "books")
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = true)
    public List<Booking> getServiceDisp(int id) {
        List<Booking> list = bookingRepo.findByService_Id(id);
        for (Booking booking : list) {
            booking.setService(servicesRepo.findById(booking.getService().getId()));
            booking.setStore(storeRepo.findByNif(booking.getStore().getNif()));
        }
        return list;
    }

    @Override
    @Cacheable(value = "staffs")
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = true )
    public List<Staff> getStaffOfService(int id, String nif) {
        List<StaffServices> ss = staffServicesRepo.getByPk_StoresServices_Pk_Service_IdAndPk_StoresServices_Pk_Store_Nif(id, nif);
        return ss.stream()
                .map( i -> i.getPk().getStaff())
                .collect(Collectors.toList());
    }

    @Override
    @Cacheable(value = "services")
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = true )
    public Service getService(int id) {
        Service serv = servicesRepo.findById(id);
        if(serv == null) throw new ResourceNotFoundException("Service with the id - "+id+" - doesn't exists.");
        return serv;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public StoreServices updateService(StoreServices ss, int id) {
        Service serv = getService(id);
        ss.getPk().getService().setId(id);
        servicesRepo.save(ss.getPk().getService());
        return storeServicesRepo.save(ss);
    }

    @Override
    @Transactional
    public Service deleteService(String nif, int id) {
        Service s = getService(id);
        StoreServices ss = storeServicesRepo.findByPk_Service_Id(id);
        if(ss!=null)
            storeServicesRepo.delete(ss);
        else
            throw new ResourceNotFoundException("Service with the ID - "+id+" - doesn't belong to the Store with the NIF - "+nif);
        return s;
    }
}
