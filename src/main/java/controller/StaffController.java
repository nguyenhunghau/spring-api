package controller;

import component.MyJson;
import constant.MyConstant;
import model.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.StaffService;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RestController
@RequestMapping(path = "/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;

    @RequestMapping(path = "getAll", method = RequestMethod.GET)
    public List<Staff> getStaffList(@RequestParam(defaultValue = "1") int pageNumber, @RequestParam(defaultValue = "10") int pageSize) {
        return staffService.findAll(pageNumber, pageSize);
    }

    @RequestMapping(path = "add", method = RequestMethod.POST)
	@ResponseBody
    public String addStaff(@RequestBody Staff staff) {
        int id = staffService.save(staff);
        if (id != MyConstant.ERROR_INSERT) {
            return MyJson.createJsonObject(id, MyConstant.ADD_SUCCESS);
        } else {
            return MyJson.createJsonObject(id, MyConstant.ADD_SUCCESS);
        }
    }

    @RequestMapping(path = "update", method = RequestMethod.POST)
	@ResponseBody
    public String updateStaff(@RequestBody Staff staff) {
        if (staffService.update(staff)) {
            return MyConstant.UPDATE_SUCCESS;
        } else {
            return MyConstant.UPDATE_FAIL;
        }
    }

    @RequestMapping(path = "delete", method = RequestMethod.POST)
    public String deleteStaff(@RequestParam int id) {
        if (staffService.delete(id)) {
            return MyConstant.DELETE_SUCCESS;
        } else {
            return MyConstant.DELETE_FAIL;
        }
    }

}
