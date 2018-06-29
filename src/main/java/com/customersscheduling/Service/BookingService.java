package com.customersscheduling.Service;

import com.customersscheduling.Domain.*;
import com.customersscheduling.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookingService implements IBookingService {

    @Autowired
    BookingRepository bookingRepo;

    @Autowired
    StoreServicesRepository storeServRepo;

    @Autowired
    StaffServicesRepository staffServicesRepo;

    @Autowired
    StaffTimetableRepository staffTimetableRepo;

    @Override
    public void dailyUpdate() {
        List<Booking> books = bookingRepo.findByDate(dayFromNow(-1));
        List<Store> stores =books.stream()
                                    .map(b -> b.getStore())
                                    .distinct()
                                    .collect(Collectors.toList());
        stores.forEach( s -> updateBookingOfStore(s, dayFromNow(30)));
        books.forEach( b -> bookingRepo.delete(b));
    }

    @Override
    public void updateBookingOfStore(Store s, Date d) {
        List<StoreServices> services = storeServRepo.findByPk_Store(s);
        services.forEach( ss -> {
            List<StaffServices> staff = staffServicesRepo.getByPk_StoresServices(ss);
            staff.forEach(staffServ -> {
                List<Date> slotsForDay = getSlotsForDay(d, staffServ);
                List<Booking> books = slotsForDay.stream()
                                                    .map(da -> new Booking(s, staffServ.getPk().getStaff(), null, staffServ.getPk().getStoresServices().getPk().getService(), da))
                                                    .collect(Collectors.toList());
                bookingRepo.saveAll(books);
            });
        });
    }

    private List<Date> getSlotsForDay(Date d, StaffServices ss){
        List<StaffTimetable> tt = staffTimetableRepo.findByPk_Staff(ss.getPk().getStaff());
        List<StaffTimetable> ttfiltered = tt.stream()
                .filter(stt -> stt.getPk().getTimetable().getWeekDay() == getWeekDay(d))
                .collect(Collectors.toList());
        Iterator<StaffTimetable> iterator = ttfiltered.iterator();
        if(iterator.hasNext()){
            StaffTimetable timetable = iterator.next();
            List<Date> totDates = new ArrayList<>();
            //TO DO
            return totDates;
        }else {
            return null;
        }
    }

    private Date dayFromNow(int step) {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, step);
        return cal.getTime();
    }

    private int getWeekDay(Date d){
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        return c.get(Calendar.DAY_OF_WEEK);
    }
}
