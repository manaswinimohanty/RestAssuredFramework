package Airlines;

import Pojo.AirlinePojo;
import io.restassured.response.Response;
import restutils.RestUtils;

import java.util.LinkedHashMap;
import java.util.Map;


public class AirlinesAPIs {

    public Response createAirlineAPI(Map<String,Object>createAirlinePayload)
    {

            String endpoint= (String) Base.endpointAsMap.get("createAirlineEndpoint");
            return   RestUtils.performPost(endpoint,createAirlinePayload,new LinkedHashMap<String,String>()) ;
    }

    public Response createAirlineAPI( AirlinePojo createAirlinePayload)
    {

        String endpoint= (String) Base.endpointAsMap.get("createAirlineEndpoint");
        return   RestUtils.performPost(endpoint,createAirlinePayload,new LinkedHashMap<String,String>()) ;
    }
}
