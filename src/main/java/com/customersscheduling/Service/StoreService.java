package com.customersscheduling.Service;

import com.customersscheduling.Domain.*;
import com.customersscheduling.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Store insertStore(Store store) {
        return storeRepo.save(store);
    }

    @Override
    public StaffServices insertStafForService(StaffServices s) {
        personRepo.save(s.getPk().getStaff());
        return staffServicesRepo.save(s);
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
    public Booking insertBook(Booking booking) {
        return bookingRepo.save(booking);
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
        return storeRepo.findByOwnerEmail(email);
    }

    @Override
    public List<Client> getPendentRequests(String nif) {
        return  clientStoresRepo.findByPk_Store_NifAndAccepted(nif, false)
                                .stream()
                                .map( cs -> cs.getPk().getClient())
                                .collect(Collectors.toList());
    }

    @Override
    public List<Booking> getServiceDisp(int id) {
        return bookingRepo.findById(id);
    }

    @Override
    public Store insertClientForStore(ClientStores cs) {
        clientStoresRepo.save(cs);
        return storeRepo.findByNif(cs.getPk().getStore().getNif());
    }

    @Override
    public Store insertStoreTimetable(StoreTimetable storeTimetable) {
        Timetable tt = storeTimetable.getPk().getTimetable();
        Timetable onDb = null;
        if((onDb=timetableRepo.findByTimetableDay(tt.getOpenHour(), tt.getCloseHour(), tt.getInitBreak(), tt.getFinishBreak(), tt.getWeekDay()))!=null){
            tt.setId(onDb.getId());
        }
        timetableRepo.save(storeTimetable.getPk().getTimetable());
        storeTimetableRepo.save(storeTimetable);
        return storeRepo.findByNif(storeTimetable.getPk().getStore().getNif());
    }

    @Override
    public List<StoreTimetable> getStoreTimetable(String nif) {
        return storeTimetableRepo.findByPk_Store_Nif(nif);
    }

    @Override
    public List<StoreServices> getServicesOfStore(String  nif) {
        return storeServicesRepo.findByPk_Store_Nif(nif);
    }
}
