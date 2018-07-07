package com.customersscheduling;

import com.customersscheduling.Service.BookingService;
import com.customersscheduling.Service.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.servlet.Filter;

import static org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType.HAL;

//@EnableHypermediaSupport(type = HAL)
@Configuration
@EnableScheduling
@EnableCaching
@EnableTransactionManagement
@ComponentScan({"com.customersscheduling.Controller","com.customersscheduling.Service", "com.customersscheduling.ExceptionHandling"})
public class MvcConfig extends WebMvcConfigurationSupport {

    @Autowired
    private BookingService bookingService;

    //Update Booking table everyday
    @Scheduled(fixedRate = 5000/*1000 * 60 * 60 * 24*/) //daily rate
    public void dailyBookingUpdate() {
        //bookingService.dailyUpdate();
    }

    @Bean
    public Filter etagFilter(){
        return new ShallowEtagHeaderFilter();
    }


}
