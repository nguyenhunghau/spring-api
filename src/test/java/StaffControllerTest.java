import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import component.MyConnection;
import constant.MyConstant;
import java.io.IOException;
import model.Staff;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StaffControllerTest {

    private static int staffId;

    @Test
    public void testGetStaffList() throws IOException {
        String data = MyConnection.callGetMethod("staff/getAll");
        Assert.assertTrue(data.length() > 0);
    }

    @Test
    public void test1Add() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Staff staff = new Staff(staffId, "hoang", "HCM", "123456789", "hoang@gmail.com");
        String body = mapper.writeValueAsString(staff);
        String data = MyConnection.callPostMethod("staff/add", body);
        if (data.isEmpty()) {
            Assert.assertTrue(false);
        }
        JsonNode node = new ObjectMapper().readTree(data);
        if (node.has("id")) {
            staffId = node.get("id").asInt();
            System.out.println(staffId);
            Assert.assertTrue(true);
        } else {
            Assert.assertTrue(false);
        }
    }

    @Test
    public void test2Update() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Staff staff = new Staff(staffId, "hoang", "Hanoi", "012348765", "hoang@gmail.com");
        String body = mapper.writeValueAsString(staff);
        String data = MyConnection.callPostMethod("staff/update", body);
        System.out.println("update: " + data);
        Assert.assertTrue(data.equals(MyConstant.UPDATE_SUCCESS));
    }

    @Test
    public void test3Delete() throws IOException {
        String data = MyConnection.callPostMethod("staff/delete?id=" + staffId, "");
        System.out.println("delete: " + data);
        Assert.assertTrue(data.equals(MyConstant.DELETE_SUCCESS));
    }
}
