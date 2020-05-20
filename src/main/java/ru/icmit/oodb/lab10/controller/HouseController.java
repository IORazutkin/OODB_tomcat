package ru.icmit.oodb.lab10.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.icmit.oodb.lab10.entity.House;
import ru.icmit.oodb.lab10.repository.HouseRepository;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HouseController {
    @Autowired
    private HouseRepository houseRepository;

    @RequestMapping("/houses")
    public String services(HttpServletRequest request,
                           @ModelAttribute("model") ModelMap model) {
        String path = request.getContextPath();
        model.addAttribute("app_path", path);
        model.addAttribute("houses", houseRepository.findAll());
        return "/houses";
    }

    @RequestMapping("/house-action")
    public String serviceAction(HttpServletRequest request,
                                @RequestParam("id") long id,
                                @RequestParam("address") String address,
                                @RequestParam("action") String action,
                                @ModelAttribute("model") ModelMap model) {
        if (action.equals("delete")) {
            houseRepository.delete(houseRepository.findById(id));
        } else {
            House house = houseRepository.findById(id);
            house.setAddress(address);
            houseRepository.update(house);
        }

        String path = request.getContextPath();
        model.addAttribute("app_path", path);
        model.addAttribute("houses", houseRepository.findAll());
        return "/houses";
    }

    @RequestMapping("/house-add")
    public String serviceAction(HttpServletRequest request,
                                @RequestParam("address") String address,
                                @ModelAttribute("model") ModelMap model) {
        houseRepository.save(new House(address));
        String path = request.getContextPath();
        model.addAttribute("app_path", path);
        model.addAttribute("houses", houseRepository.findAll());
        return "/houses";
    }
}
