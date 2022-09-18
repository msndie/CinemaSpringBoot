package edu.school21.cinema.controllers.admin.panel;

import edu.school21.cinema.models.Hall;
import edu.school21.cinema.services.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin/panel/halls")
public class HallsManage {

    private HallService hallService;

    @Autowired
    public void setHallService(HallService hallService) {
        this.hallService = hallService;
    }

    @GetMapping
    public String getHalls(@ModelAttribute("model") ModelMap model) {
        model.addAttribute("HallsList", hallService.findAll());
        return "admin/panel/halls";
    }

    @PostMapping
    public String postHall(@ModelAttribute("model") ModelMap model, HttpServletRequest request) {
        Long serialNumber = null;
        Integer numberOfSeats = null;
        try {
            serialNumber = Long.parseLong(request.getParameter("serialNumber"));
            numberOfSeats = Integer.parseInt(request.getParameter("numberOfSeats"));
        } catch (NumberFormatException ignored) {
        }
        if (serialNumber != null && numberOfSeats != null
            && serialNumber > 0 && numberOfSeats > 0) {
            Hall hall = new Hall();
            hall.setSerialNumber(serialNumber);
            hall.setNumberOfSeats(numberOfSeats);
            hallService.add(hall);
        }
        return "redirect:/admin/panel/halls";
    }
}
