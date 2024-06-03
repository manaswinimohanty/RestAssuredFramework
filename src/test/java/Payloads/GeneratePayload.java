package Payloads;

import Pojo.AirlinePojo;
import net.datafaker.Faker;
import utils.DateUtils;
import utils.RandomDataGenerator;
import utils.RandomDataTypeNames;

import java.util.LinkedHashMap;
import java.util.Map;

public class GeneratePayload {

    public static String getPayloadAsString(int id,String name,String country){
        String payload="{\n" +
                "\"id\":"+id+",\n" +
                "\"name\":\""+name+"\",\n" +
                "\"country\":\""+country+"\"\n" +
                "}";

        return payload;

    }

    public static Map<String,Object> getPayloadAsMap(int id, String name, String country){

        Map<String,Object>payload=new LinkedHashMap<>();
        Faker faker=new Faker();
        payload.put("id",RandomDataGenerator.getRandomNumber(1,10)); //it will through through exception if number is out of range for integer data type
        payload.put("name", RandomDataGenerator.getRandomDataFor(RandomDataTypeNames.FULLNAME));
        payload.put("country",RandomDataGenerator.getRandomDataFor(RandomDataTypeNames.COUNTRYNAME));
        payload.put("logo", RandomDataGenerator.getRandomAlphabetic(10));
        payload.put("website",RandomDataGenerator.getRandomWebsite(15));
        payload.put("established",RandomDataGenerator.getRandomNumber(1990, DateUtils.getCurrentDate()));
        return payload;
    }

    public static AirlinePojo getCreateAirlinePayloadPojo(){
        return AirlinePojo.builder()
                .id(RandomDataGenerator.getRandomNumber(1,10))
                .name(RandomDataGenerator.getRandomDataFor(RandomDataTypeNames.FULLNAME))
                .country(RandomDataGenerator.getRandomDataFor(RandomDataTypeNames.COUNTRYNAME))
                .logo(RandomDataGenerator.getRandomAlphabetic(10))
                .website(RandomDataGenerator.getRandomWebsite(15))
                .established(RandomDataGenerator.getRandomNumber(1990, DateUtils.getCurrentDate()))
                .build();
    }


}

