package com.customersscheduling;

import com.customersscheduling.Filters.ExceptionHandlerFilter;
import com.customersscheduling.Filters.JwtAuthorizationFilter;
import com.customersscheduling.Filters.LogRequestFilter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    private final Log logger = LogFactory.getLog(getClass());

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[0];
    }

    @Override
    protected String[] getServletMappings() {
        return new String[0];
    }

    @Override
    protected Filter[] getServletFilters() {
        try {
            return new Filter[]{new ExceptionHandlerFilter(), new JwtAuthorizationFilter(), new LogRequestFilter()};
        } catch (Exception e) {
            logger.error("Filter couldn't be registered.");
            return new Filter[]{};
        }
    }
}
