package com.customersscheduling.Service;

import com.customersscheduling.Domain.*;
import com.customersscheduling.ExceptionHandling.CustomExceptions.InvalidRequestException;
import com.customersscheduling.ExceptionHandling.CustomExceptions.NotAuthorizedException;
import com.customersscheduling.ExceptionHandling.CustomExceptions.ResourceNotFoundException;
import com.customersscheduling.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = ResourceNotFoundException.class)
public class StoreService implements IStoreService {

    @Autowired
    StoreRepository storeRepo;

    @Autowired
    StoreServicesRepository storeServicesRepo;

    @Autowired
    ClientStoresRepository clientStoresRepo;

    @Autowired
    TimetableRepository timetableRepo;

    @Autowired
    StoreTimetableRepository storeTimetableRepo;

    @Autowired
    PersonRepository personRepo;

    @Autowired
    StaffServicesRepository staffServsRepo;

    @Autowired
    StaffRepository staffRepo;

    @Autowired
    BookingRepository bookingRepo;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public Store insertStore(Store store) {
        return storeRepo.save(store);
    }


    @Override
    @Cacheable(value = "stores")
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = true )
    public Store getStore(String nif) {
        return  storeRepo.findById(nif).orElseThrow(()->
                new ResourceNotFoundException("Store with the nif "+nif+" doesn't exists.")
        );
    }

    @Override
    //@Cacheable(value = "ownerstores")
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = true )
    public List<Store> getStoresOfUser(String email) {
        return storeRepo.findByOwner_Client_Email(email);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = true )
    public List<Client> getPendentRequests(String nif) {
        return  clientStoresRepo.findByPk_Store_NifAndAccepted(nif, false)
                                .stream()
                                .map( cs -> cs.getPk().getClient())
                                .collect(Collectors.toList());
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public Store insertClientForStore(ClientStores cs) {
        clientStoresRepo.save(cs);
        return getStore(cs.getPk().getStore().getNif());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public Store insertStoreTimetable(StoreTimetable storeTimetable) {
        Timetable tt = storeTimetable.getPk().getTimetable();
        timetableRepo.findByTimetableDay(tt.getOpenHour(), tt.getCloseHour(), tt.getInitBreak(), tt.getFinishBreak(), tt.getWeekDay()).ifPresent(onDb -> tt.setId(onDb.getId()));
        timetableRepo.save(storeTimetable.getPk().getTimetable());
        storeTimetableRepo.save(storeTimetable);
        return getStore(storeTimetable.getPk().getStore().getNif());
    }

    @Override
    @Cacheable(value = "storetimetables")
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = true )
    public List<StoreTimetable> getStoreTimetable(String nif) {
        return storeTimetableRepo.findByPk_Store_Nif(nif);
    }

    @Override
    @Cacheable(value = "storeservices")
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = true )
    public List<StoreServices> getServicesOfStore(String  nif) {
        return storeServicesRepo.findByPk_Store_Nif(nif);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public Store updateStoreTimetable(StoreTimetable storeTimetable) {
        int weekDay = storeTimetable.getPk().getTimetable().getWeekDay();
        StoreTimetable stt = storeTimetableRepo.findByPk_Store_Nif(storeTimetable.getPk().getStore().getNif())
                                                .stream()
                                                .filter( i -> i.getPk().getTimetable().getWeekDay()==weekDay)
                                                .findFirst()
                                                .orElseThrow( () ->
                                                        new ResourceNotFoundException("Timetable with the specified Store and Weekday can't be updated because doesn't exists.")
                                                );
        Timetable newTimetable = storeTimetable.getPk().getTimetable();
        newTimetable.setId(stt.getPk().getTimetable().getId());
        stt.getPk().setTimetable(newTimetable);
        timetableRepo.save(newTimetable);
        storeTimetableRepo.save(stt);
        return getStore(storeTimetable.getPk().getStore().getNif());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public Store updateStoreAddress(String nif, Address address) {
        Store s = getStore(nif);
        address.setId(s.getAddress().getId());
        s.setAddress(address);
        storeRepo.save(s);
        return getStore(nif);
    }

    @Override
    //@CachePut(value = "ownerstores")
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public Store updateStore(String nif, Store store) {
        Store s = getStore(nif);
        store.setOwner(s.getOwner());
        return storeRepo.save(store);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public Store deleteStore(String nif) {
        Store s = getStore(nif);
        clientStoresRepo.deleteByPk_Store_Nif(nif);
        staffServsRepo.deleteByPk_StoresServices_Pk_Store_Nif(nif);
        storeServicesRepo.deleteByPk_Store_Nif(nif);
        staffRepo.deleteByStore_Nif(nif);
        bookingRepo.deleteByStore_Nif(nif);
        storeRepo.delete(s);
        return s;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = true )
    public double getScore(String nif) {
        List<ClientStores> cs = clientStoresRepo.findByPk_Store_Nif(nif);
        return cs.stream()
                .mapToInt(i -> i.getScore())
                .filter(i -> i != -1)
                .average()
                .orElse(0);
    }

    @Override
    //@Cacheable(value = "storesbyname")
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = true )
    public List<Store> getStoresByName(String name) {
        return storeRepo.findByName(name);
    }

    @Override
    @Cacheable(value = "storesbylocationandcategory")
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = true )
    public List<Store> getStoresByLocationAndCategory(String location, String category) {
        return storeRepo.findByAddress_CityAndCategory_Name(location, category);
    }

    @Override
    @Cacheable(value = "clients")
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = true )
    public List<Client> getClientsOfStore(String nif) {
        return clientStoresRepo.findByPk_Store_Nif(nif)
                .stream()
                .map( c -> (Client)personRepo.findByEmail(c.getPk().getClient().getEmail()).orElseThrow(()->new ResourceNotFoundException("Client "+c.getPk().getClient().getEmail()+"doesn't exists.")))
                .collect(Collectors.toList());
    }

    @Override
    //@Cacheable(value = "staff")
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = true )
    public List<Staff> getStaff(String nif) {
        return staffRepo.findByStore_Nif(nif);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public Store deleteClient(String email, String nif) {
        Store s = getStore(nif);
        clientStoresRepo.deleteByPk_Client_EmailAndPk_Store_Nif(email, nif);
        return s;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = true )
    public boolean isAccepted(String email, String nif) {
        ClientStores cs = clientStoresRepo.findByPk_Client_EmailAndPk_Store_Nif(email, nif);
        if(cs == null)
            throw new ResourceNotFoundException("Store with the NIF "+nif+" doesn't have any "+email+" client.");
        return cs.isAccepted();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public Store updateClientForStore(String nif, String email, boolean b) {
        ClientStores cs = clientStoresRepo.findByPk_Client_EmailAndPk_Store_Nif(email, nif);
        ClientStores cs2 = new ClientStores(cs.getPk(), b, cs.getScore());
        clientStoresRepo.save(cs2);
        return cs.getPk().getStore();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public Store updateClientScoreForStore(String nif, String email, int i) {
        Client c = (Client)personRepo.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("Client "+email+"doesn't exists."));
        Store store = getStore(nif);
        ClientStores cs = clientStoresRepo.findByPk_Client_EmailAndPk_Store_Nif(email, nif);
        if(!cs.isAccepted())
            throw new InvalidRequestException("Client must be registered to score a store.");
        cs.getPk().setClient(c);
        cs.getPk().setStore(store);
        cs.setScore(i);
        clientStoresRepo.save(cs);
        return cs.getPk().getStore();
    }


}
