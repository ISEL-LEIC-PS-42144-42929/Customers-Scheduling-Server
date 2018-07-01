package com.customersscheduling.Service;

import com.customersscheduling.Domain.*;
import com.customersscheduling.ExceptionHandling.CustomExceptions.ResourceNotFoundException;
import com.customersscheduling.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookingService implements IBookingService {


    @Autowired
    PersonRepository personRepo;

    @Autowired
    BookingRepository bookingRepo;

    @Autowired
    StoreServicesRepository storeServRepo;

    @Autowired
    StaffServicesRepository staffServicesRepo;

    @Autowired
    StaffTimetableRepository staffTimetableRepo;


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
    public void updateBookingOfStore(StaffServices staffServs) {
        int i= 0;
        while(i<=30){
            List<Date> freeSlots = getSlotsForDay(dayFromNow(i), staffServs);
            Staff currStaff = staffServs.getPk().getStaff();
            StoreServicesPK pk = staffServs.getPk().getStoresServices().getPk();
            Store s = pk.getStore();
            List<Booking> books = freeSlots.stream()
                                            .map(da -> new Booking(s, currStaff, null, pk.getService(), da))
                                            .collect(Collectors.toList());
            bookingRepo.saveAll(books);
            ++i;
        }
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
        int interval = s.getDuration(); //interval in minutes
        List<Date> allSlots = new ArrayList<>();
        int hour = (int) t.getOpenHour();
        int mins = (int)((hour - t.getOpenHour())*10);
        boolean secondHalf = false;
        while(hour < t.getCloseHour()){
            Calendar cal = Calendar.getInstance();
            cal.setTime(d);
            cal.set(Calendar.HOUR_OF_DAY, hour);
            cal.set(Calendar.MINUTE,mins);
            allSlots.add(cal.getTime());
            mins += interval;
            if(mins>=60){
                mins=0;
                hour++;
            }
            if(!secondHalf && Double.parseDouble(hour + "." + mins/60) >= t.getInitBreak()){
                hour = (int)t.getFinishBreak();
                mins = (int)((hour - t.getFinishBreak())*10);
                secondHalf = true;
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
