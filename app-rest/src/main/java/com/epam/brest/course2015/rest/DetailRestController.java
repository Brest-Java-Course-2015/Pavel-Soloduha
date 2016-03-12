package com.epam.brest.course2015.rest;

import com.epam.brest.course2015.domain.Detail;
import com.epam.brest.course2015.service.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by pavel on 11/7/15.
 */

@RestController
@RequestMapping(value = "/detail")
public class DetailRestController {

    @Autowired
    private DetailService detailService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.FOUND)
    public List<Detail> getAllDetails() {
        return detailService.getAllDetails();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Integer addDetail(@RequestBody Detail detail) {
        return detailService.addDetail(detail);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody void deleteDetail(@PathVariable(value = "id") Integer detailId) {
        detailService.deleteDetail(detailId);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody void updateDetail(@RequestBody Detail detail) {
        detailService.updateDetail(detail);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.FOUND)
    public Detail getDetailById(@PathVariable(value = "id") Integer detailId) {
        return detailService.getDetailById(detailId);
    }
}