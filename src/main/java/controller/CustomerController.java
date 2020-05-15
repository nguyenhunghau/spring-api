package controller;

import component.MyJson;
import constant.MyConstant;
import model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.CustomerService;

import java.util.List;

@RestController
@RequestMapping(path = "/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(path = "getAll", method = RequestMethod.GET)
    public List<Customer> getCustomerList() {
        return customerService.findAll();
    }

    @RequestMapping(path = "add", method = RequestMethod.POST)
    @ResponseBody
    public String add(@RequestBody Customer customer) {
        int id = customerService.save(customer);
        if (id != MyConstant.ERROR_INSERT) {
            return MyJson.createJsonObject(id, MyConstant.ADD_SUCCESS);
        } else {
            return MyJson.createJsonObject(id, MyConstant.ADD_SUCCESS);
        }
    }
}
