package controller;

import component.MyJson;
import constant.MyConstant;
import model.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.OrderService;
import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
@RequestMapping(path = "/order")
public class OrderController {

    @Autowired
    private OrderService orderservice;

    @RequestMapping(path = "getAll", method = RequestMethod.GET)
    public List<Orders> getOrderList() {
        return orderservice.findAll();
    }
    
    @RequestMapping(path = "search", method = RequestMethod.GET)
    public List<Orders> search(@RequestParam String dateStart, @RequestParam String dateEnd) {
        return orderservice.search(dateStart, dateEnd);
    }

    @RequestMapping(path = "add", method = RequestMethod.POST)
    @ResponseBody
    public String addOrder(@RequestBody Orders order) {
        int id = orderservice.save(order);
        if (id != MyConstant.ERROR_INSERT) {
            return MyJson.createJsonObject(id, MyConstant.ADD_SUCCESS);
        } else {
            return MyJson.createJsonObject(id, MyConstant.ADD_SUCCESS);
        }
    }
    
    @RequestMapping(path = "update", method = RequestMethod.POST)
    @ResponseBody
    public String updateOrder(@RequestBody Orders order) {
         if (orderservice.update(order)) {
            return MyConstant.UPDATE_SUCCESS;
        } else {
            return MyConstant.UPDATE_FAIL;
        }
    }
    
    @RequestMapping(path = "delete", method = RequestMethod.POST)
    public String deleteStaff(@RequestParam int id) {
        if (orderservice.delete(id)) {
            return MyConstant.DELETE_SUCCESS;
        } else {
            return MyConstant.DELETE_FAIL;
        }
    }
}
