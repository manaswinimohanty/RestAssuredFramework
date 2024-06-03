package Airlines;

import Payloads.GeneratePayload;
import Pojo.AirlinePojo;
import Pojo.Gender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import restutils.AssertionUtils;

import java.util.LinkedHashMap;
import java.util.Map;

@Listeners(reporting.ListenerTest.class)
public class AirlinesTest extends AirlinesAPIs{

    @Test
    public void createAirline() throws JsonProcessingException {


        // String payloadAsString= GeneratePayload.getPayloadAsString(123,"Instantwebttol","IND");
      // Map<String, Object> payloadAsMap=GeneratePayload.getPayloadAsMap(123,"Instantwebttol","IND");
       // Response response=createAirlineAPI(payloadAsMap) ;
        AirlinePojo payloadAsPojo= GeneratePayload.getCreateAirlinePayloadPojo();
        payloadAsPojo=payloadAsPojo.toBuilder().name("manaswini").gender(Gender.female).build();

        //AirlinePojo payloadAsPojo=new AirlinePojo();
        //AirlinePojo payloadAsPojo=new AirlinePojo().toBuilder().name("manaswini").gender(Gender.female).build();
     //   Response response=createAirlineAPI(payloadAsPojo);
//as website is not working, so we will make request as response, we will read request using objectmapper and convert it to a string
        ObjectMapper objectMapper=new ObjectMapper();
        String airlinePojoAsString=objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(payloadAsPojo);
        JsonPath json= JsonPath.from(airlinePojoAsString);



        Map<String,Object> expectedValueMap=new LinkedHashMap<>();
        expectedValueMap.put("id",payloadAsPojo.getId());
        expectedValueMap.put("name",payloadAsPojo.getName());
        expectedValueMap.put("country",payloadAsPojo.getCountry());
        expectedValueMap.put("logo",payloadAsPojo.getLogo());
        expectedValueMap.put("website",payloadAsPojo.getWebsite());
        expectedValueMap.put("established",payloadAsPojo.getEstablished());

        AssertionUtils.assertExpectedValueWithJsonPath(json,expectedValueMap);
      //  AssertionUtils.assertExpectedValueWithJsonPath(response,expectedValueMap);


/*convert the json response to pojo class and compare request pojo class object and response pojo class
to check whether response contains same property and value as in request.
Use object mapper.readvalue() to convert json string to pojo class
then use annotation in pojo class to override the equal and hashcode function, so that we define propery and its value check
instead of reference check
 */
        /*ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        String airlinePojoAsString= objectMapper.writeValueAsString(payloadAsPojo);
        AirlinePojo airlinePojoResponse= objectMapper.readValue(response.getBody().asPrettyString(),AirlinePojo.class);
        AirlinePojo airlinePojoResponse1= objectMapper.readValue(airlinePojoAsString,AirlinePojo.class);
        //airlinePojoResponse.setName("xxxxyyyyyzzzzz");
        Assert.assertEquals(payloadAsPojo,airlinePojoResponse);*/


        //two pojo class comparison using javers
       /* Javers javers= JaversBuilder.javers().withListCompareAlgorithm(ListCompareAlgorithm.AS_SET).build();
        Diff diff=javers.compare(payloadAsPojo,airlinePojoResponse);
        System.out.println(diff.prettyPrint());
        System.out.println(diff.getChanges());*/





    }


}
