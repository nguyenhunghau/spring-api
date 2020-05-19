package controller;

import component.MyJson;
import constant.MyConstant;
import model.DeliveryNote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.DeliveryNoteService;

import java.util.List;

@RestController
@RequestMapping(path = "/delivery-note")
public class DeliveryNoteController {

    @Autowired
    private DeliveryNoteService deliveryNoteService;

    @RequestMapping(path = "getAll", method = RequestMethod.GET)
    public List<DeliveryNote> getDeliveryList(@RequestParam(defaultValue = "1") int pageNumber, @RequestParam(defaultValue = "10") int pageSize) {
        return deliveryNoteService.findAll(pageNumber, pageSize);
    }
    
    @RequestMapping(path = "search", method = RequestMethod.GET)
    public List<DeliveryNote> search(@RequestParam String dateStart, @RequestParam String dateEnd) {
        return deliveryNoteService.search(dateStart, dateEnd);
    }

    @RequestMapping(path = "add", method = RequestMethod.POST)
    @ResponseBody
    public String addReceiving(@RequestBody DeliveryNote note) {
        int id = deliveryNoteService.save(note);
        if (id != MyConstant.ERROR_INSERT) {
            return MyJson.createJsonObject(id, MyConstant.ADD_SUCCESS);
        } else {
            return MyJson.createJsonObject(id, MyConstant.ADD_SUCCESS);
        }
    }

    @RequestMapping(path = "update", method = RequestMethod.POST)
    @ResponseBody
    public String updateReceiving(@RequestBody DeliveryNote note) {
        if (deliveryNoteService.update(note)) {
            return MyConstant.UPDATE_SUCCESS;
        } else {
            return MyConstant.UPDATE_FAIL;
        }
    }

    @RequestMapping(path = "delete", method = RequestMethod.POST)
    public String deleteStaff(@RequestParam int id) {
        if (deliveryNoteService.delete(id)) {
            return MyConstant.DELETE_SUCCESS;
        } else {
            return MyConstant.DELETE_FAIL;
        }
    }
}
