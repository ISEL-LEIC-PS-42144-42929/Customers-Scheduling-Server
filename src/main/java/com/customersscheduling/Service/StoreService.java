package com.customersscheduling.Service;

import com.customersscheduling.Domain.*;
import com.customersscheduling.ExceptionHandling.CustomExceptions.ResourceNotFoundException;
import com.customersscheduling.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

@Service
@Transactional
public class StoreService implements IStoreService {

    @Autowired
    StoreRepository storeRepo;

    @Autowired
    ClientStoresRepository clientStoreRepo;

    @Autowired
    StoreServicesRepository storeServicesRepo;

    @Autowired
    ClientStoresRepository clientStoresRepo;

    @Autowired
    TimetableRepository timetableRepo;

    @Autowired
    StoreTimetableRepository storeTimetableRepo;

    @Override
    public Store insertStore(Store store) {
        return storeRepo.save(store);
    }


    @Override
    public Store getStore(String nif) {
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
    public Store updateStoreTimetable(StoreTimetable storeTimetable) {
        int weekDay = storeTimetable.getPk().getTimetable().getWeekDay();
        StoreTimetable stt = storeTimetableRepo.findByPk_StoreAndPk_Timetable_WeekDay(storeTimetable.getPk().getStore(), weekDay);
        if(stt==null) throw new ResourceNotFoundException("Timetable with the specified Store and Weekday can't be updated because doesn't exists.");
        Timetable newTimetable = storeTimetable.getPk().getTimetable();
        newTimetable.setId(stt.getPk().getTimetable().getId());
        stt.getPk().setTimetable(newTimetable);
        timetableRepo.save(newTimetable);
        storeTimetableRepo.save(stt);
        return getStore(storeTimetable.getPk().getStore().getNif());
    }

    @Override
    public Store updateStoreAddress(String nif, Address address) {
        Store s = getStore(nif);
        address.setId(s.getAddress().getId());
        s.setAddress(address);
        storeRepo.save(s);
        return getStore(nif);
    }

    @Override
    public Store updateStore(String nif, Store store) {
        Store s = getStore(nif);
        if(store.getCategory() != null)
            s.setCategory(store.getCategory());
        if(store.getStoreName() != null)
            s.setStoreName(store.getStoreName());
        if(store.getContact() != null)
            s.setContact(store.getContact());
        return storeRepo.save(s);
    }

    @Override
    public Store deleteStore(String nif) {
        Store s = getStore(nif);
        storeRepo.delete(s);
        return s;
    }

    @Override
    public double getScore(String nif) {
        List<ClientStores> cs = clientStoresRepo.findByPk_Store_NifAndAccepted(nif, true);
        return cs.stream()
                .mapToInt(i -> i.getScore())
                .filter(i -> i != -1)
                .average()
                .orElse(0);
    }


}
