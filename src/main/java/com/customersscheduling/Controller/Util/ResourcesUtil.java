package com.customersscheduling.Controller.Util;

import com.customersscheduling.HALObjects.ServiceResource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.core.EmbeddedWrapper;
import org.springframework.hateoas.core.EmbeddedWrappers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ResourcesUtil {

    public  static <T> Resources<T> getResources(Class<T> klass, List<T> array, Link link){
        Resources<T> resources = new Resources<T>(array, link);
        if(array.size()==0){
            EmbeddedWrappers wrappers = new EmbeddedWrappers(false);
            EmbeddedWrapper wrapper = wrappers.emptyCollectionOf(klass);
            return new Resources<T>((Iterable<T>) Arrays.asList(wrapper), link);
        }
        return resources;
    }
}
