package Airlines;

import utils.JsonUtils;

import java.io.IOException;
import java.util.Map;

/**
 * this class is used to set up all common things required for all test case
 */
public class Base {
public static Map<String,Object>endpointAsMap;
    static{

        String env= System.getProperty("env")==null?"qa":System.getProperty("env");
        System.out.println(env);
        try {
            endpointAsMap = JsonUtils.getJsonEndpointAsMap("/airlinesEnv/" + env + "/airlinesApiData.json");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
