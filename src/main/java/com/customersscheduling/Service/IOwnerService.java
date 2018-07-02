package com.customersscheduling.Service;

import com.customersscheduling.Domain.Owner;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.ResourceSupport;

public interface IOwnerService {

    Owner insertOwner(Owner owner);

    Owner getOwner(String email);
}
