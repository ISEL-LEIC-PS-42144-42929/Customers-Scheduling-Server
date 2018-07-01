package com.customersscheduling.Service;

import com.customersscheduling.Domain.*;
import com.customersscheduling.ExceptionHandling.CustomExceptions.ResourceNotFoundException;
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
    public Booking insertBook(int id, String email) {
        Booking booking = bookingRepo.findById(id);
        if(booking == null) throw new ResourceNotFoundException("Book with the id - "+id+" - doesn't exists.");
        Client c = (Client)personRepo.findByEmail(email);
        if(c==null) throw new ResourceNotFoundException("Client with the email - "+email+" - doesn't exists.");
        booking.setClient(c);
        return bookingRepo.save(booking);
    }

    @Override
    public Booking getBookingById(int id) {
        Booking book = bookingRepo.getOne(id);
        if(book == null) throw new ResourceNotFoundException("Book with the id - "+id+" - doesn't exists.");
        return book;
    }

    @Override
    public Store getStoreByNif(String nif) {
        Store store = storeRepo.findByNif(nif);
        if(store == null) throw new ResourceNotFoundException("Store with the NIF - "+nif+" - doesn't exists.");
        return store;
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
        return bookingRepo.findByService_Id(id);
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
