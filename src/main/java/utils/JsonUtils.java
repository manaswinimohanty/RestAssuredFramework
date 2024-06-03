package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * this class is used to read respective json file and fetch the endpoint URL and store it in a map
 */
public class JsonUtils {

    public static ObjectMapper objectMapper=new ObjectMapper();
    public static Map<String,Object>getJsonEndpointAsMap(String jsonFileName){

        String completeJsonPath=System.getProperty("user.dir")+"/src/test/resources"+jsonFileName;
        try {
            Map<String,Object>endpoint= objectMapper.readValue(new File(completeJsonPath), new TypeReference<Map<String,Object>>(){});
            return endpoint;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
       // return endpoint;
    }

}
