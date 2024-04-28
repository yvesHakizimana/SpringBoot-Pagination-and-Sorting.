package com.code.advancedpagination.paginatinon;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;


    @GetMapping("/")
    public String getEmployees(
            Model model,
            @RequestParam(name = "page", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(name = "size", defaultValue = "10", required = false) int pageSize){
        model.addAttribute("employees",  employeeService.getAllEmployees(pageNumber, pageSize));
        return "index";
    }

    @GetMapping("/addnew")
    public String addNewEmployee(Model model){
        model.addAttribute("employee", new Employee());
        return "new-employee";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee){
        employeeService.save(employee);
        return "redirect:/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String updateForm(@PathVariable(value = "id") Integer id, Model model){
        var employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "update";
    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable(value = "id") Integer id){
        employeeService.deleteEmployeeById(id);
        return "redirect:/";
    }
}