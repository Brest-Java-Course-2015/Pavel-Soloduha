package com.epam.brest.course2015.web;

import com.epam.brest.course2015.domain.Detail;
import com.epam.brest.course2015.service.DetailService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by pavel on 3/15/16.
 */
public class DetailController {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    DetailService detailService;

    @RequestMapping("/")
    public String init() {
        return "redirect:/detailList";
    }

    @RequestMapping("/detailList")
    public ModelAndView getDetails() {
        List<Detail> detailList = detailService.getAllDetails();
        LOGGER.debug("detailList.size = " + detailList.size());
        return new ModelAndView("detailList", "detailList", detailList);
    }

    @RequestMapping("/inputDetail")
    public ModelAndView formDetail() {
        return new ModelAndView("inputForm", "detail", new Detail());
    }

//    @RequestMapping("/submitUser")
//    public String addUser(@RequestParam("login") String login, @RequestParam("password") String password) {
//        User user = new User();
//        user.setLogin(login);
//        user.setPassword(password);
//        userService.addUser(user);
//        return "redirect:/userList";
//    }
}
