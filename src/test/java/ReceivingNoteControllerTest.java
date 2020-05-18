import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import component.MyConnection;
import constant.MyConstant;
import model.*;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ReceivingNoteControllerTest {

    private static int noteId;

    @Test
    public void testGetReceivingNote() throws IOException {
        String data = MyConnection.callGetMethod("receiving-note/getAll");
        Assert.assertTrue(data.length() > 0);
    }

    @Test
    public void test1Add() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<ReceivingNoteDetail> detailList = new ArrayList<>();
        Product product = new Product();
        product.setId(2);
        detailList.add(new ReceivingNoteDetail(20, product));

        Customer provider = new Customer();
        provider.setId(3);
        ReceivingNote note = new ReceivingNote(noteId, provider, detailList);
        String body = mapper.writeValueAsString(note);
        System.out.println(body);

        String data = MyConnection.callPostMethod("receiving-note/add", body);
        if (data.isEmpty()) {
            Assert.assertTrue(false);
        }
        JsonNode node = new ObjectMapper().readTree(data);
        if (node.has("id")) {
            noteId = node.get("id").asInt();
            System.out.println(noteId);
            Assert.assertTrue(true);
        } else {
            Assert.assertTrue(false);
        }
    }

    @Test
    public void test2Update() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<ReceivingNoteDetail> detailList = new ArrayList<>();
        Product product = new Product();
        product.setId(1);
        detailList.add(new ReceivingNoteDetail(40, product));

        Customer provider = new Customer();
        provider.setId(1);
        ReceivingNote note = new ReceivingNote(noteId, provider, detailList);
        String body = mapper.writeValueAsString(note);
        System.out.println(body);

        String data = MyConnection.callPostMethod("receiving-note/update", body);
        Assert.assertTrue(data.equals(MyConstant.UPDATE_SUCCESS));
    }

    @Test
    public void test3Delete() throws IOException {
        String data = MyConnection.callPostMethod("receiving-note/delete?id=" + noteId, "");
        Assert.assertTrue(data.equals(MyConstant.DELETE_SUCCESS));
    }
}
