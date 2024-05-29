package projet.blog.controllers;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import projet.blog.entities.Customer;
import projet.blog.enums.CustomerState;
import projet.blog.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @RequestMapping("/createCustomer")
    public String createCustomer(){
        return "CreateCustomer";
    }
    @RequestMapping("/success")
    public String success(){
        return "success";
    }
    @RequestMapping("/saveCustomer")
    public String saveCustomer(@Valid Customer customer, BindingResult bindingResult){
        if (bindingResult.hasErrors()) return "success";
        customer.setCustomerState(CustomerState.Active);
        Customer saveCustomer = customerService.saveCustomer(customer);
        return "CreateCustomer";
    }
    @RequestMapping("/customersList")
    public String customersList(ModelMap modelMap,
                                @RequestParam(name = "page", defaultValue = "0") int page,
                                @RequestParam(name = "size", defaultValue = "2") int size
    ){
        Page<Customer> customers = customerService.getCustomersByPage(page, size);
        modelMap.addAttribute("customers", customers);
        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("pages", new int[customers.getTotalPages()]);
        return "CustomersList";
    }
    @RequestMapping("/deleteCustomer")
    public String deleteCustomer(@RequestParam("id") Long id, ModelMap modelMap,
                                 @RequestParam(name = "page", defaultValue = "0") int page,
                                 @RequestParam(name = "size", defaultValue = "2") int size
    ){
        customerService.deleteCustomerById(id);
        Page<Customer> customers = customerService.getCustomersByPage(page, size);
        modelMap.addAttribute("customers", customers);
        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("pages", new int[customers.getTotalPages()]);
        return "CustomersList";

    }
    // Affiche le formulaire de mise à jour du client
    @GetMapping("/editCustomer")
    public String showEditCustomerForm(@RequestParam Long id, ModelMap modelMap) {
        Customer customer = customerService.getCustomerById(id);
        modelMap.addAttribute("customerView", customer);
        return "EditCustomer"; // Nom du fichier HTML Thymeleaf
    }

    // Traite la soumission du formulaire de mise à jour du client
    @PostMapping("/updateCustomer")
    public String updateCustomer(Customer customer) {
        customerService.updateCustomer(customer);
        return "redirect:/customersList"; // Redirige vers la liste des clients après la mise à jour
    }
}
