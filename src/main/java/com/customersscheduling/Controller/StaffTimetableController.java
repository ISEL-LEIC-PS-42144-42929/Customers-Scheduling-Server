package com.customersscheduling.Controller;

import com.customersscheduling.Controller.InputModels.TimetableInputModel;
import com.customersscheduling.Domain.Staff;
import com.customersscheduling.Domain.StaffTimetable;
import com.customersscheduling.Domain.StaffTimetablePK;
import com.customersscheduling.Domain.Timetable;
import com.customersscheduling.ExceptionHandling.CustomExceptions.InvalidBodyException;
import com.customersscheduling.OutputResources.StaffResource;
import com.customersscheduling.OutputResources.StaffTimetableResource;
import com.customersscheduling.Service.IStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value="/person", produces = "application/hal+json")
public class StaffTimetableController {

    @Autowired
    private IStaffService personService;

    @PutMapping(value = "/staff/{email}/timetable")
    public Resource<StaffResource> updateStaffTimetable(@PathVariable String email, @RequestParam("weekDay") int weekDay, @RequestBody TimetableInputModel timetable) {
        if(timetable.week_day!=weekDay) throw new InvalidBodyException("Weekday specified on query isn't coerent with the body's one ( "+weekDay+"!="+timetable.week_day+").");
        Staff staff = new Staff(); staff.setEmail(email);
        Timetable tt = timetable.toDto();
        StaffResource staffResource = personService.updateStaffTimetable(new StaffTimetable(new StaffTimetablePK(tt, staff))).toResource();
        Link link = linkTo(methodOn(StaffTimetableController.class).updateStaffTimetable(email, weekDay, timetable)).withSelfRel();
        staffResource.add(link);
        return new Resource<>(staffResource);
    }

    @PostMapping(value = "/staff/{email}/timetable")
    public Resource<StaffResource> insertStaffTimetable(@PathVariable String email, @RequestBody TimetableInputModel timetable, HttpServletResponse resp) {
        Staff staff = new Staff(); staff.setEmail(email);
        Timetable tt = timetable.toDto();
        StaffResource staffResource = personService.insertStaffTimetable(new StaffTimetable(new StaffTimetablePK(tt, staff))).toResource();
        Link link = linkTo(methodOn(StaffTimetableController.class).insertStaffTimetable(email, timetable, null)).withSelfRel();
        resp.setStatus(HttpStatus.CREATED.value());
        staffResource.add(link);
        return new Resource<>(staffResource);
    }

    @GetMapping(value = "/staff/{email}/timetable")
    public Resource<StaffTimetableResource> getStaffTimetable(@PathVariable String email) {
        List<StaffTimetable> tt = personService.getStaffTimetable(email);
        Iterator<StaffTimetable> iterator = tt.iterator();
        Staff s = null;
        if(iterator.hasNext())
            s = iterator.next().getPk().getStaff();
        List<Timetable> listtt = tt.stream()
                .map( stt -> stt.getPk().getTimetable())
                .collect(Collectors.toList());

        StaffTimetableResource ttResource = new StaffTimetableResource(listtt, s.toResource());
        Link link = linkTo(methodOn(StaffTimetableController.class).getStaffTimetable(email)).withSelfRel();
        return new Resource<>(ttResource, link);
    }
}
