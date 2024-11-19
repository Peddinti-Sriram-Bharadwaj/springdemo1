package com.sriram9217.demo1.controllers;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.sriram9217.demo1.entity.SomeBean;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {
    @RequestMapping("/filtering")
    public MappingJacksonValue retrieveSomeBean(){
        SomeBean someBean = new SomeBean("Sriram", "9999999999", "390000");
        MappingJacksonValue mapping = new MappingJacksonValue(someBean);

        SimpleBeanPropertyFilter filter= SimpleBeanPropertyFilter.filterOutAllExcept("name", "salary");
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter",filter);
        mapping.setFilters(filters);
        return mapping;

    }

    @RequestMapping("/filtering-list")
    public MappingJacksonValue retrieveSomeBeanList(){
        List<SomeBean> list = Arrays.asList(new SomeBean("Sriram", "9999999999", "390000"), new SomeBean("Sriram", "9999999999", "390000"));

        SimpleBeanPropertyFilter filter= SimpleBeanPropertyFilter.filterOutAllExcept("name", "phone");
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter",filter);

        MappingJacksonValue mapping = new MappingJacksonValue(list);
        mapping.setFilters(filters);
        return mapping;
    }
}
