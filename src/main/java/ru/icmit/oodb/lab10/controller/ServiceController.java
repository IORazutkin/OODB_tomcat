package ru.icmit.oodb.lab10.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.icmit.oodb.lab10.entity.Service;
import ru.icmit.oodb.lab10.repository.ServiceRepository;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ServiceController {
    @Autowired
    private ServiceRepository serviceRepository;

    @RequestMapping("/services")
    public String services(HttpServletRequest request,
                           @ModelAttribute("model") ModelMap model) {
        String path = request.getContextPath();
        model.addAttribute("app_path", path);
        model.addAttribute("services", serviceRepository.findAll());
        return "/services";
    }

    @RequestMapping("/service-action")
    public String serviceAction(HttpServletRequest request,
                                @RequestParam("id") long id,
                                @RequestParam("title") String title,
                                @RequestParam("price") Double price,
                                @RequestParam("unit") String unit,
                                @RequestParam("action") String action,
                                @ModelAttribute("model") ModelMap model) {
        if (action.equals("delete")) {
            serviceRepository.delete(serviceRepository.findById(id));
        } else {
            Service service = serviceRepository.findById(id);
            service.setTitle(title);
            service.setPrice(price);
            service.setUnit(unit);
            serviceRepository.update(service);
        }

        String path = request.getContextPath();
        model.addAttribute("app_path", path);
        model.addAttribute("services", serviceRepository.findAll());
        return "/services";
    }

    @RequestMapping("/service-add")
    public String serviceAction(HttpServletRequest request,
                                @RequestParam("title") String title,
                                @RequestParam("price") Double price,
                                @RequestParam("unit") String unit,
                                @ModelAttribute("model") ModelMap model) {
        serviceRepository.save(new Service(title, price, unit));
        String path = request.getContextPath();
        model.addAttribute("app_path", path);
        model.addAttribute("services", serviceRepository.findAll());
        return "/services";
    }
}
