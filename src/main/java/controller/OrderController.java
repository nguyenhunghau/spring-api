package controller;

import model.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.OrderService;

import java.util.List;

@RestController
@RequestMapping(path = "/order")
public class OrderController {

    @Autowired
    private OrderService orderservice;

    @RequestMapping(path = "getAll", method = RequestMethod.GET)
    public List<Orders> getOrderList(){
        return orderservice.findAll();
    }
}
