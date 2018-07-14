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
    StaffRepository personRepo;


    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED )
    public StaffServices insertStaffForService(StaffServices s) {
        int id = s.getPk().getStoresServices().getPk().getService().getId();
        String email = s.getPk().getStaff().getEmail();
        String nif = s.getPk().getStoresServices().getPk().getStore().getNif();
        Store store = storeRepo.findById(nif).orElseThrow(()->
                new ResourceNotFoundException("Store "+nif+" doesn't exists.")
        );
        Service service = servicesRepo.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Service "+id+" doesn't exists.")
        );
        Staff staff = personRepo.findById(email).orElseThrow(()->
                new ResourceNotFoundException("Staff "+email+" doesn't exists.")
        );

        StaffServices staffServices = new StaffServices(
                new StaffServicesPK(staff, new StoreServices(new StoreServicesPK(store, service)))
        );

        personRepo.save(staff);
        staffServicesRepo.save(staffServices);

        return staffServices;
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED )
    public StoreServices insertServiceForStore(StoreServices s) {
        Service serv = s.getPk().getService();
        servicesRepo.findService(serv.getDescription(), serv.getPrice(), serv.getTitle(), serv.getDuration()).ifPresent( service ->
                s.getPk().getService().setId(service.getId())
        );
        servicesRepo.save(s.getPk().getService());
        StoreServices ss = storeServicesRepo.save(s);
        ss.getPk().setStore(storeRepo.findById(ss.getPk().getStore().getNif()).orElseThrow(()->
                new ResourceNotFoundException("Store "+s.getPk().getStore().getNif()+" doesn't exists.")
        ));
        return ss;
    }


    @Override
    @Cacheable(value = "books")
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = true)
    public List<Booking> getServiceDisp(int id) {
        List<Booking> list = bookingRepo.findByService_Id(id);
        for (Booking booking : list) {
            booking.setService(servicesRepo.findById(booking.getService().getId()).orElseThrow(()->
                    new ResourceNotFoundException("Service with the id "+booking.getService().getId()+" doesn't exists."))
            );
            booking.setStore(storeRepo.findById(booking.getStore().getNif()).orElseThrow(()->
                    new ResourceNotFoundException("Store with the nif "+booking.getStore().getNif()+" doesn't exists."))
            );
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
        return servicesRepo.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Service with the id "+id+" doesn't exists.")
        );
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
        StoreServices ss = storeServicesRepo.findByPk_Service_Id(id).orElseThrow(()->
                new ResourceNotFoundException("Service with the ID - "+id+" - doesn't belong to the Store with the NIF - "+nif));

        storeServicesRepo.delete(ss);
        return ss.getPk().getService();
    }

    @Override
    public Service removeStaffOfService(String email, int id) {
        getService(id);
        personRepo.findById(email).orElseThrow(() ->
            new ResourceNotFoundException("Staff with the email "+email+" doesn't exists.")
        );
        StaffServices ss = staffServicesRepo.getByPk_StoresServices_Pk_Service_IdAndPk_Staff_Email(id, email);
        staffServicesRepo.deleteByPk_StoresServices_Pk_Service_IdAndPk_Staff_Email(id, email);
        return ss.getPk().getStoresServices().getPk().getService();
    }
}
