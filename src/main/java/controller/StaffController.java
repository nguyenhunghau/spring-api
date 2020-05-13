package controller;

import model.Staff;
import model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.StaffService;
import service.StudentService;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RestController
@RequestMapping(path = "/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;

    @RequestMapping(path = "getAll", method = RequestMethod.GET)
    public List<Staff> getStaffList() {
        return staffService.findAll();
    }

    @RequestMapping(path = "add", method = RequestMethod.POST)
	@ResponseBody
    public String addStaff(@RequestBody Staff staff) {
        if (staffService.save(staff)) {
            return "Add staff success";
        } else {
            return "Add staff failed";
        }
    }

    @RequestMapping(path = "update", method = RequestMethod.POST)
	@ResponseBody
    public String updateStaff(@RequestBody Staff staff) {
        if (staffService.save(staff)) {
            return "Update staff success";
        } else {
            return "Update staff failed";
        }
    }

    @RequestMapping(path = "delete", method = RequestMethod.POST)
    public String deleteStaff(@RequestParam("id") int id) {
        if (staffService.delete(id)) {
            return "Delete staff success";
        } else {
            return "Delete staff failed";
        }
    }

}
