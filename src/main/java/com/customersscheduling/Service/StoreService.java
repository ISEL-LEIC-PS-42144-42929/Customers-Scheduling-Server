package com.customersscheduling.Service;

import com.customersscheduling.Domain.*;
import com.customersscheduling.HALObjects.*;
import com.customersscheduling.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StoreService implements IStoreService {

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
    ClientStoresRepository clientStoresRepo;

    @Autowired
    PersonRepository personRepo;

    @Autowired
    TimetableRepository timetableRepo;

    @Autowired
    StoreTimetableRepository storeTimetableRepo;


    @Override
    public StoreResource insertStore(Store store) {
        return storeRepo.save(store).toResource();
    }

    @Override
    public ServiceResource insertStafForService(StaffServices s) {
        staffServicesRepo.save(s);
        return servicesRepo.findById(s.getPk().getStoresServices().getPk().getService().getId()).toResource(null);
    }

    @Override
    public ServiceResource insertServiceForStore(StoreServices s) {
        com.customersscheduling.Domain.Service serv = s.getPk().getService();
        com.customersscheduling.Domain.Service s1 = servicesRepo.findService(serv.getDescription(), serv.getPrice(), serv.getTitle(), serv.getDuration());
        if(s1!=null) s.getPk().getService().setId(s1.getId());
        servicesRepo.save(s.getPk().getService());
        storeServicesRepo.save(s);
        return new ServiceResource(s.getPk().getService(), getStoreByNif(s.getPk().getStore().getNif()));
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
    public StoreResource getStoreByNif(String nif) {
        return storeRepo.findByNif(nif).toResource();
    }

    @Override
    public List<StoreResource> getStoresOfUser(String email) {
        return storeRepo.findByOwnerEmail(email)
                        .stream()
                        .map( i -> i.toResource())
                        .collect(Collectors.toList());
    }

    @Override
    public List<ClientResource> getPendentRequests(String nif) {
        List<ClientStores> emails =  clientStoresRepo.findByPk_Store_NifAndAccepted(nif, false);
        return emails.stream()
                .map( cs -> cs.getPk().getClient().toResource())
                .collect(Collectors.toList());
    }

    @Override
    public List<Booking> getServiceDisp(int id) {
        return bookingRepo.findById(id);
    }

    @Override
    public StoreResource insertClientForStore(ClientStores cs) {
        clientStoresRepo.save(cs);
        return storeRepo.findByNif(cs.getPk().getStore().getNif()).toResource();
    }

    @Override
    public StoreResource insertStoreTimetable(StoreTimetable storeTimetable) {
        Timetable tt = storeTimetable.getPk().getTimetable();
        Timetable onDb = null;
        if((onDb=timetableRepo.findByTimetableDay(tt.getOpenHour(), tt.getCloseHour(), tt.getInitBreak(), tt.getFinishBreak(), tt.getWeekDay()))!=null){
            tt.setId(onDb.getId());
        }
        timetableRepo.save(storeTimetable.getPk().getTimetable());
        storeTimetableRepo.save(storeTimetable);
        Store store = storeRepo.findByNif(storeTimetable.getPk().getStore().getNif());
        return store.toResource();
    }

    @Override
    public StoreTimetableResource getStoreTimetable(String nif) {
        Store store = storeRepo.findByNif(nif);
        List<StoreTimetable> stafftts = storeTimetableRepo.findByPk_Store(store);
        List<Timetable> tts = new ArrayList<>();
        stafftts.forEach( i -> tts.add(i.getPk().getTimetable()));
        return new StoreTimetableResource(tts, store.toResource());
    }

    @Override
    public List<ServiceResource> getServicesOfStore(String  nif) {
        Store store = storeRepo.findByNif(nif);
        List<StoreServices> ss = storeServicesRepo.findByPk_Store(store);
        List<ServiceResource> services = new ArrayList<>();
        ss.forEach(i -> services.add(i.getPk().getService().toResource(store.toResource())));
        return services;
    }
}
