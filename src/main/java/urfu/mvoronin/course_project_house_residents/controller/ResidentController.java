package urfu.mvoronin.course_project_house_residents.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import urfu.mvoronin.course_project_house_residents.dto.ResidentDto;
import urfu.mvoronin.course_project_house_residents.repository.ResidentRepository;

import javax.validation.Valid;
import java.util.stream.Collectors;

@Slf4j
@Controller
public class ResidentController {

    private ResidentRepository residentRepository;

    public ResidentController(ResidentRepository residentRepository) {
        this.residentRepository = residentRepository;
    }

    @GetMapping("/residents")
    public ModelAndView getAllResidents() {
        log.info("/list -> connection");
        var mav = new ModelAndView("list-residents");
        mav.addObject("residents", residentRepository
                .findAll()
                .stream()
                .map(resident -> ResidentDto.FromEntity(resident))
                .collect(Collectors.toList()));
        return mav;
    }

    @GetMapping("/addResidentForm")
    public ModelAndView addResidentForm() {
        var mav = new ModelAndView("add-resident-form");
        var resident = new ResidentDto();
        mav.addObject("resident", resident);
        return mav;
    }

    @PostMapping("/saveResident")
    public String saveResident(@Valid @ModelAttribute("resident") ResidentDto resident,
                               BindingResult result,
                               Model model) {
        if (result.hasErrors()) {
            model.addAttribute("resident", resident);
            return "add-resident-form";
        }

        residentRepository.save(resident.AsEntity());
        return "redirect:/residents";
    }

    @GetMapping("/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam Long residentId) {
        var mav = new ModelAndView("add-resident-form");
        var optionalStudent = residentRepository.findById(residentId);
        var resident = new ResidentDto();
        if (optionalStudent.isPresent()) {
            resident = ResidentDto.FromEntity(optionalStudent.get());
        }
        mav.addObject("resident", resident);
        return mav;
    }

    @GetMapping("/deleteResident")
    public String deleteResident(@RequestParam Long residentId) {
        residentRepository.deleteById(residentId);
        return "redirect:/residents";
    }
}
