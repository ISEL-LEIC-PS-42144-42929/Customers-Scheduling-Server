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
        List<Store> stores = books.stream()
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
                Staff currStaff = staffServ.getPk().getStaff();
                StoreServicesPK pk = staffServ.getPk().getStoresServices().getPk();
                List<Booking> books = slotsForDay.stream()
                                                 .map(da -> new Booking(s, currStaff, null, pk.getService(), da))
                                                 .collect(Collectors.toList());
                bookingRepo.saveAll(books);
            });
        });
    }

    @Override
    public void updateBookingOfStore(Store s, com.customersscheduling.Domain.Service service, Staff staff) {
        //TO DO 30 days
    }

    private List<Date> getSlotsForDay(Date d, StaffServices ss){
        StaffTimetable tt = staffTimetableRepo.findByPk_StaffAndPk_Timetable_WeekDay(ss.getPk().getStaff(), getWeekDay(d));
        if(tt!=null){
            return getBookingSlots(ss.getPk().getStoresServices().getPk().getService(), tt.getPk().getTimetable(), d);
        }else {
            return new ArrayList<>();
        }
    }

    private List<Date> getBookingSlots(com.customersscheduling.Domain.Service s, Timetable t, Date d){
        int interval = s.getDuration() /60; //interval in hours
        List<Date> allSlots = new ArrayList<>();
        int hour = new Double(t.getOpenHour()).intValue();
        int mins = new Double(Math.floor(hour) * 60).intValue();
        while(hour <= t.getCloseHour()){
            Calendar cal = Calendar.getInstance();
            cal.setTime(d);
            cal.set(Calendar.HOUR_OF_DAY, hour);
            cal.set(Calendar.MINUTE,mins);
            allSlots.add(cal.getTime());
            mins += interval;
            if(mins>60){
                mins=0;
                hour++;
            }
            if(Double.parseDouble(hour + "." + mins/60) >= t.getInitBreak()){
                hour = new Double(t.getFinishBreak()).intValue();
                mins = new Double(Math.floor(hour) * 60).intValue();
            }
        }
        return allSlots;
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
