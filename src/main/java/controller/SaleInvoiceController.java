package controller;

import com.google.gson.Gson;
import component.MyJson;
import constant.MyConstant;
import model.DeliveryNote;
import model.SaleInvoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.SaleInvoiceService;

import java.util.List;

@RestController
@RequestMapping(path = "/invoice")
public class SaleInvoiceController {

    @Autowired
    private service.SaleInvoiceService SaleInvoiceService;

    @RequestMapping(path = "getAll", method = RequestMethod.GET)
    public List<SaleInvoice> getInvoiceList(@RequestParam(defaultValue = "1") int pageNumber, @RequestParam(defaultValue = "10") int pageSize) {
        return SaleInvoiceService.findAll(pageNumber, pageSize);
    }

    @RequestMapping(path = "search", method = RequestMethod.GET)
    public List<SaleInvoice> searchInvoice(@RequestParam Integer customerId, @RequestParam Integer staffId, @RequestParam String dateStart, @RequestParam String dateEnd) {
        return SaleInvoiceService.searchInvoice(customerId, staffId, dateStart, dateEnd);
    }

    @RequestMapping(path = "revenue-staff", method = RequestMethod.GET)
    public String findRevenueByStaff(@RequestParam String dateStart, @RequestParam String dateEnd) {
        return new Gson().toJson(SaleInvoiceService.findRevenueByStaff(dateStart, dateEnd));
    }

    @RequestMapping(path = "revenue-customer", method = RequestMethod.GET)
    public String findRevenueByCustomer(@RequestParam String dateStart, @RequestParam String dateEnd) {
        return new Gson().toJson(SaleInvoiceService.findRevenueByCustomer(dateStart, dateEnd));
    }


    @RequestMapping(path = "add", method = RequestMethod.POST)
    @ResponseBody
    public String addReceiving(@RequestBody SaleInvoice note) {
        int id = SaleInvoiceService.save(note);
        if (id != MyConstant.ERROR_INSERT) {
            return MyJson.createJsonObject(id, MyConstant.ADD_SUCCESS);
        } else {
            return MyJson.createJsonObject(id, MyConstant.ADD_SUCCESS);
        }
    }

    @RequestMapping(path = "update", method = RequestMethod.POST)
    @ResponseBody
    public String updateReceiving(@RequestBody SaleInvoice note) {
        if (SaleInvoiceService.update(note)) {
            return MyConstant.UPDATE_SUCCESS;
        } else {
            return MyConstant.UPDATE_FAIL;
        }
    }

    @RequestMapping(path = "delete", method = RequestMethod.POST)
    public String deleteStaff(@RequestParam int id) {
        if (SaleInvoiceService.delete(id)) {
            return MyConstant.DELETE_SUCCESS;
        } else {
            return MyConstant.DELETE_FAIL;
        }
    }
}
