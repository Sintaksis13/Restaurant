package com.epam.configuration.spring;

import com.epam.filter.EncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

public class RestaurantWebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {SpringConfiguration.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {SpringWebConfiguration.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

    //TODO implement encoding filter (filter mapped in two places: web.xml and below)
    @Override
    protected Filter[] getServletFilters() {
        return new Filter[] {new EncodingFilter()};
    }
}
