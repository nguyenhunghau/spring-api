package controller;

import component.MyJson;
import constant.MyConstant;
import model.ReceivingNote;
import model.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.RecevingNoteService;
import service.StaffService;

import java.util.List;
import model.ReceivingNoteDetail;

@RestController
@RequestMapping(path = "/receiving-note")
public class RecevingNoteController {
    @Autowired
    private RecevingNoteService recevingNoteService;

    @RequestMapping(path = "getAll", method = RequestMethod.GET)
    public List<ReceivingNote> ferReceivingList(@RequestParam(defaultValue = "1") int pageNumber, @RequestParam(defaultValue = "10") int pageSize) {
        return recevingNoteService.findAll(pageNumber, pageSize);
    }
    
    @RequestMapping(path = "search", method = RequestMethod.GET)
    public List<ReceivingNote> search( @RequestParam String dateStart, @RequestParam String dateEnd) {
        return recevingNoteService.search(dateStart, dateEnd);
    }

    @RequestMapping(path = "add", method = RequestMethod.POST)
    @ResponseBody
    public String addReceiving(@RequestBody ReceivingNote note) {
        int id = recevingNoteService.save(note);
        if (id != MyConstant.ERROR_INSERT) {
            return MyJson.createJsonObject(id, MyConstant.ADD_SUCCESS);
        } else {
            return MyJson.createJsonObject(id, MyConstant.ADD_SUCCESS);
        }
    }
    
    @RequestMapping(path = "add-detail", method = RequestMethod.POST)
    @ResponseBody
    public String addDetailReceiving(@RequestBody ReceivingNoteDetail note) {
        int id = recevingNoteService.saveDetail(note);
        if (id != MyConstant.ERROR_INSERT) {
            return MyJson.createJsonObject(id, MyConstant.ADD_SUCCESS);
        } else {
            return MyJson.createJsonObject(id, MyConstant.ADD_SUCCESS);
        }
    }

    @RequestMapping(path = "update", method = RequestMethod.POST)
    @ResponseBody
    public String updateReceiving(@RequestBody ReceivingNote note) {
        if (recevingNoteService.update(note)) {
            return MyConstant.UPDATE_SUCCESS;
        } else {
            return MyConstant.UPDATE_FAIL;
        }
    }

    @RequestMapping(path = "delete", method = RequestMethod.POST)
    public String deleteStaff(@RequestParam int id) {
        if (recevingNoteService.delete(id)) {
            return MyConstant.DELETE_SUCCESS;
        } else {
            return MyConstant.DELETE_FAIL;
        }
    }
}
