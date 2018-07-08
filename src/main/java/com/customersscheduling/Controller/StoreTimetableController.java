package com.customersscheduling.Controller;

import com.customersscheduling.Controller.InputModels.TimetableInputModel;
import com.customersscheduling.Domain.Store;
import com.customersscheduling.Domain.StoreTimetable;
import com.customersscheduling.Domain.StoreTimetablePK;
import com.customersscheduling.Domain.Timetable;
import com.customersscheduling.ExceptionHandling.CustomExceptions.InvalidBodyException;
import com.customersscheduling.ExceptionHandling.CustomExceptions.ResourceNotFoundException;
import com.customersscheduling.HALObjects.StoreResource;
import com.customersscheduling.HALObjects.StoreTimetableResource;
import com.customersscheduling.Service.IStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


@RestController
@RequestMapping(value="/store", produces = "application/hal+json")
public class StoreTimetableController {

    @Autowired
    private IStoreService storeService;

    @PostMapping(value = "/{nif}/timetable")
    public Resource<StoreResource> insertStoreTimetable(@PathVariable String nif, @RequestBody TimetableInputModel timetable) {
        Store s = new Store();
        s.setNif(nif);
        Timetable tt = timetable.toDto();
        StoreResource storeResource = storeService.insertStoreTimetable(new StoreTimetable(new StoreTimetablePK(tt, s))).toResource(storeService.getScore(nif));
        Link link = linkTo(methodOn(StoreTimetableController.class).insertStoreTimetable(nif,timetable)).withSelfRel();
        return new Resource<StoreResource>(storeResource, storeResource.getLinks(link));
    }

    @PutMapping(value = "/{nif}/timetable")
    public Resource<StoreResource> updateStoreTimetable(@PathVariable String nif, @RequestParam("weekDay") int weekDay,  @RequestBody TimetableInputModel timetable) {
        if(timetable.week_day!=weekDay) throw new InvalidBodyException("Weekday specified on query isn't coerent with the body's one ( "+weekDay+"!="+timetable.week_day+").");
        Store s = new Store();
        s.setNif(nif);
        Timetable tt = timetable.toDto();
        StoreResource storeResource = storeService.updateStoreTimetable(new StoreTimetable(new StoreTimetablePK(tt, s))).toResource(storeService.getScore(nif));
        Link link = linkTo(methodOn(StoreTimetableController.class).insertStoreTimetable(nif,timetable)).withSelfRel();
        return new Resource<>(storeResource, storeResource.getLinks(link));
    }

    @GetMapping(value = "/{nif}/timetable")
    public Resource<StoreTimetableResource> getStoreTimetable(@PathVariable String nif) {
        List<StoreTimetable> st = storeService.getStoreTimetable(nif);
        Iterator<StoreTimetable> iterator = st.iterator();
        Store s = null;
        if(iterator.hasNext())
            s = iterator.next().getPk().getStore();
        else
            throw new ResourceNotFoundException("Store with NIF - "+nif+" - doesn't have any timetable specified");
        List<Timetable> listtt = st.stream()
                .map( stt -> stt.getPk().getTimetable())
                .collect(Collectors.toList());

        StoreTimetableResource stResource = new StoreTimetableResource(listtt, s.toResource(storeService.getScore(nif)));
        Link link = linkTo(methodOn(StoreTimetableController.class).getStoreTimetable(nif)).withSelfRel();
        return new Resource<StoreTimetableResource>(stResource, stResource.getLinks(link));
    }
}
