import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import component.MyConnection;
import constant.MyConstant;
import model.Customer;
import model.Staff;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.IOException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CustomerControllerTest {
    private static int customerId;

    @Test
    public void testGetCustomerList() throws IOException {
        String data = MyConnection.callGetMethod("customer/getAll");
        Assert.assertTrue(data.length() > 0);
    }

    @Test
    public void test1Add() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Customer customer = new Customer(customerId, "Alex", "USA", "3243546546", "23232",
                "Neli", "alex@mail", 1);
        String body = mapper.writeValueAsString(customer);
        String data = MyConnection.callPostMethod("customer/add", body);
        if (data.isEmpty()) {
            Assert.assertTrue(false);
        }
        JsonNode node = new ObjectMapper().readTree(data);
        if (node.has("id")) {
            customerId = node.get("id").asInt();
            System.out.println(customerId);
            Assert.assertTrue(true);
        } else {
            Assert.assertTrue(false);
        }
    }

    @Test
    public void test2Update() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Customer customer = new Customer(customerId, "Alex", "USA", "3243546546", "23232",
                "Neli", "alex@mail", 1);
        String body = mapper.writeValueAsString(customer);
        String data = MyConnection.callPostMethod("customer/update", body);
        System.out.println("update: " + data);
        Assert.assertTrue(data.equals(MyConstant.UPDATE_SUCCESS));
    }

    @Test
    public void test3Delete() throws IOException {
        String data = MyConnection.callPostMethod("customer/delete?id=" + customerId, "");
        System.out.println("delete: " + data);
        Assert.assertTrue(data.equals(MyConstant.DELETE_SUCCESS));
    }
}
