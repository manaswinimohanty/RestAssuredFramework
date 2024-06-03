package restutils;

import com.aventstack.extentreports.markuputils.MarkupHelper;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import reporting.ExtentReportManager;
import reporting.ListenerTest;

import java.util.*;

public class AssertionUtils {
    public static void assertExpectedValueWithJsonPath(Response response, Map<String,Object> expectedvaluesMap){

        List<AssertionKeys> actualValueMap=new ArrayList<>();
        actualValueMap.add(new AssertionKeys("JSON_PATH","EXPECTED_VALUE","ACTUAL_VALUE","RESULT"));
        boolean allMatched=true;

        Set<String> jsonPaths=expectedvaluesMap.keySet();
        for(String jsonPath:jsonPaths){
            Optional <Object>  actualValue=Optional.ofNullable(response.jsonPath().get(jsonPath));
            if(actualValue.isPresent()){
                Object value=actualValue.get();
                if(value.equals(expectedvaluesMap.get(jsonPath))){
                    actualValueMap.add(new AssertionKeys(jsonPath,expectedvaluesMap.get(jsonPath),value,"MATCHED"));
                }
                else {
                    allMatched=false;
                    actualValueMap.add(new AssertionKeys(jsonPath,expectedvaluesMap.get(jsonPath),value,"NOT_MATCHED"));
                }

            }
            else {
                allMatched=false;
                actualValueMap.add(new AssertionKeys(jsonPath,expectedvaluesMap.get(jsonPath),"VALUE_NOT_FOUND","NOT_MATCHED"));
            }
        }
        if(allMatched)
            ExtentReportManager.logPassDetails("all assertions passed");
        else
            ExtentReportManager.logPassDetails("all assertions are not passed");

       String[][]finalAssertionsMap= actualValueMap.stream().map(assertions->new String[]{assertions.getJsonPath(),String.valueOf(assertions.getExpectedValue()),String.valueOf(assertions.getActualValue()),assertions.getResult()}).toArray(String[][]::new);

        ListenerTest.extentTest.get().info(MarkupHelper.createTable(finalAssertionsMap,"table-sm"));

    }

    public static void assertExpectedValueWithJsonPath(JsonPath json, Map<String,Object> expectedvaluesMap){

        List<AssertionKeys> actualValueMap=new ArrayList<>();
        actualValueMap.add(new AssertionKeys("JSON_PATH","EXPECTED_VALUE","ACTUAL_VALUE","RESULT"));
        boolean allMatched=true;
//in expectedvaluesMap is a map where keys are jsonpath and values are expected value
        Set<String> jsonPaths=expectedvaluesMap.keySet();
        for(String jsonPath:jsonPaths){
            //Nullpointer exception handling
            Optional <Object>  actualValue=Optional.ofNullable(json.get(jsonPath));
            if(actualValue.isPresent()){
                Object value=actualValue.get();
                if(value.equals(expectedvaluesMap.get(jsonPath))){
                    actualValueMap.add(new AssertionKeys(jsonPath,expectedvaluesMap.get(jsonPath),value,"MATCHED"));
                }
                else {
                    allMatched=false;
                    actualValueMap.add(new AssertionKeys(jsonPath,expectedvaluesMap.get(jsonPath),value,"NOT_MATCHED"));
                }

            }
            else {
                allMatched=false;
                actualValueMap.add(new AssertionKeys(jsonPath,expectedvaluesMap.get(jsonPath),"VALUE_NOT_FOUND","NOT_MATCHED"));
            }
        }
        if(allMatched)
            ExtentReportManager.logPassDetails("all assertions passed");
        else
            ExtentReportManager.logPassDetails("all assertions are not passed");

        String[][]finalAssertionsMap= actualValueMap.stream().map(assertions->new String[]{assertions.getJsonPath(),String.valueOf(assertions.getExpectedValue()),String.valueOf(assertions.getActualValue()),assertions.getResult()}).toArray(String[][]::new);

        ListenerTest.extentTest.get().info(MarkupHelper.createTable(finalAssertionsMap,"table-sm"));

    }
}
