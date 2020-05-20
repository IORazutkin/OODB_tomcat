package ru.icmit.oodb.lab10.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.icmit.oodb.lab10.entity.Apartment;
import ru.icmit.oodb.lab10.repository.ApartmentRepository;
import ru.icmit.oodb.lab10.repository.HouseRepository;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ApartmentController {
    @Autowired
    private ApartmentRepository apartmentRepository;
    @Autowired
    private HouseRepository houseRepository;

    @RequestMapping("/apartments")
    public String services(HttpServletRequest request,
                           @ModelAttribute("model") ModelMap model) {
        String path = request.getContextPath();
        model.addAttribute("app_path", path);
        model.addAttribute("houses", houseRepository.findAll());
        model.addAttribute("apartments", apartmentRepository.findAll());
        return "/apartments";
    }

    @RequestMapping("/apartment-action")
    public String serviceAction(HttpServletRequest request,
                                @RequestParam("id") long id,
                                @RequestParam("house") long houseId,
                                @RequestParam("number") Integer number,
                                @RequestParam("action") String action,
                                @ModelAttribute("model") ModelMap model) {
        if (action.equals("delete")) {
            apartmentRepository.delete(apartmentRepository.findById(id));
        } else {
            Apartment apartment = apartmentRepository.findById(id);
            apartment.setHouse(houseRepository.findById(houseId));
            apartment.setNumber(number);
            apartmentRepository.update(apartment);
        }

        String path = request.getContextPath();
        model.addAttribute("app_path", path);
        model.addAttribute("houses", houseRepository.findAll());
        model.addAttribute("apartments", apartmentRepository.findAll());
        return "/apartments";
    }

    @RequestMapping("/apartment-add")
    public String serviceAction(HttpServletRequest request,
                                @RequestParam("house") long id,
                                @RequestParam("number") Integer number,
                                @ModelAttribute("model") ModelMap model) {
        apartmentRepository.save(new Apartment(houseRepository.findById(id), number));
        String path = request.getContextPath();
        model.addAttribute("app_path", path);
        model.addAttribute("houses", houseRepository.findAll());
        model.addAttribute("apartments", apartmentRepository.findAll());
        return "/apartments";
    }
}
