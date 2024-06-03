package Pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import utils.DateUtils;
import utils.RandomDataGenerator;
import utils.RandomDataTypeNames;

import java.util.Arrays;

//@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown=true)
public class AirlinePojo {

//    private String gender= Stream.of("male","female","other").findAny().get();
 //   private String gender= Arrays.asList("male","female","other").get(RandomDataGenerator.getRandomNumber(0,3));
    private Gender gender= Arrays.stream(Gender.values()).findAny().get();
    private int id= RandomDataGenerator.getRandomNumber(1,10);
    private String name=RandomDataGenerator.getRandomDataFor(RandomDataTypeNames.FULLNAME);
    private String country=RandomDataGenerator.getRandomDataFor(RandomDataTypeNames.COUNTRYNAME);

    private String logo=RandomDataGenerator.getRandomAlphabetic(15);
    private String website=RandomDataGenerator.getRandomWebsite(10);
    private int established=RandomDataGenerator.getRandomNumber(1990, DateUtils.getCurrentDate());


}
