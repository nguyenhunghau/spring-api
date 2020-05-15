package component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import constant.MyConstant;
import org.apache.commons.lang3.exception.ExceptionUtils;
import service.LogUtils;

public class MyJson {

    static ObjectMapper mapper = new ObjectMapper();

    public static String createJsonObject(int id, String message) {
        JsonNode node = mapper.createObjectNode();
        ((ObjectNode) node).put("message", message);
        if (id != MyConstant.ERROR_INSERT) {
            ((ObjectNode) node).put("id", id);
        }
        try {
            return mapper.writeValueAsString(node);
        } catch (JsonProcessingException ex) {
            LogUtils.write(ExceptionUtils.getStackTrace(ex));
            return "{}";
        }
    }
}
