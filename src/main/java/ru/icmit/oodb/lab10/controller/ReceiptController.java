package ru.icmit.oodb.lab10.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.icmit.oodb.lab10.entity.Apartment;
import ru.icmit.oodb.lab10.entity.Receipt;
import ru.icmit.oodb.lab10.repository.ApartmentRepository;
import ru.icmit.oodb.lab10.repository.ReceiptRepository;
import ru.icmit.oodb.lab10.repository.ServiceRepository;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ReceiptController {
    @Autowired
    private ApartmentRepository apartmentRepository;
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private ReceiptRepository receiptRepository;

    @RequestMapping("/receipts")
    public String services(HttpServletRequest request,
                           @ModelAttribute("model") ModelMap model) {
        String path = request.getContextPath();
        model.addAttribute("app_path", path);
        model.addAttribute("services", serviceRepository.findAll());
        model.addAttribute("apartments", apartmentRepository.findAll());
        model.addAttribute("receipts", receiptRepository.findAll());
        return "/receipts";
    }

    @RequestMapping("/receipt-action")
    public String serviceAction(HttpServletRequest request,
                                @RequestParam("id") long id,
                                @RequestParam("apartment") long apartment_id,
                                @RequestParam("service") long service_id,
                                @RequestParam("value") Double value,
                                @RequestParam("action") String action,
                                @ModelAttribute("model") ModelMap model) {
        if (action.equals("delete")) {
            receiptRepository.delete(receiptRepository.findById(id));
        } else {
            Receipt receipt = receiptRepository.findById(id);
            receipt.setService(serviceRepository.findById(service_id));
            receipt.setApartment(apartmentRepository.findById(apartment_id));
            receipt.setValue(value);
            receiptRepository.update(receipt);
        }

        String path = request.getContextPath();
        model.addAttribute("app_path", path);
        model.addAttribute("services", serviceRepository.findAll());
        model.addAttribute("apartments", apartmentRepository.findAll());
        model.addAttribute("receipts", receiptRepository.findAll());
        return "/receipts";
    }

    @RequestMapping("/receipt-add")
    public String serviceAction(HttpServletRequest request,
                                @RequestParam("apartment") long apartment_id,
                                @RequestParam("service") long service_id,
                                @RequestParam("value") Double value,
                                @RequestParam("action") String action,
                                @ModelAttribute("model") ModelMap model) {
        if (action.equals("save")) {
            receiptRepository.save(new Receipt(serviceRepository.findById(service_id), value, apartmentRepository.findById(apartment_id)));
            model.addAttribute("receipts", receiptRepository.findAll());
        } else {
            model.addAttribute("receipts", receiptRepository.findByApartmentId(apartment_id));
        }
        String path = request.getContextPath();
        model.addAttribute("app_path", path);
        model.addAttribute("services", serviceRepository.findAll());
        model.addAttribute("apartments", apartmentRepository.findAll());
        return "/receipts";
    }
}
