package com.customersscheduling.Controller;

import com.customersscheduling.Controller.InputModels.TimetableInputModel;
import com.customersscheduling.Domain.Store;
import com.customersscheduling.Domain.StoreTimetable;
import com.customersscheduling.Domain.StoreTimetablePK;
import com.customersscheduling.Domain.Timetable;
import com.customersscheduling.ExceptionHandling.CustomExceptions.InvalidBodyException;
import com.customersscheduling.ExceptionHandling.CustomExceptions.ResourceNotFoundException;
import com.customersscheduling.OutputResources.StoreResource;
import com.customersscheduling.OutputResources.StoreTimetableResource;
import com.customersscheduling.Service.IStoreService;
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
@RequestMapping(value="/store", produces = "application/hal+json")
public class StoreTimetableController {

    @Autowired
    private IStoreService storeService;

    @PostMapping(value = "/{nif}/timetable")
    public Resource<StoreResource> insertStoreTimetable(@PathVariable String nif, @RequestBody TimetableInputModel timetable, HttpServletResponse resp) {
        Store s = new Store();
        s.setNif(nif);
        Timetable tt = timetable.toDto();
        StoreResource storeResource = storeService.insertStoreTimetable(new StoreTimetable(new StoreTimetablePK(tt, s))).toResource(storeService.getScore(nif));
        Link link = linkTo(methodOn(StoreTimetableController.class).insertStoreTimetable(nif,timetable, null)).withSelfRel();
        storeResource.add(link);
        resp.setStatus(HttpStatus.CREATED.value());
        return new Resource<StoreResource>(storeResource);
    }

    @PutMapping(value = "/{nif}/timetable")
    public Resource<StoreResource> updateStoreTimetable(@PathVariable String nif,  @RequestBody TimetableInputModel timetable) {
        Store s = new Store();
        s.setNif(nif);
        Timetable tt = timetable.toDto();
        StoreResource storeResource = storeService.updateStoreTimetable(new StoreTimetable(new StoreTimetablePK(tt, s))).toResource(storeService.getScore(nif));
        Link link = linkTo(methodOn(StoreTimetableController.class).updateStoreTimetable(nif, timetable)).withSelfRel();
        storeResource.add(link);
        return new Resource<>(storeResource);
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
        stResource.add(link);
        return new Resource<StoreTimetableResource>(stResource);
    }
}
