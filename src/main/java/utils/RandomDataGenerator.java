package utils;

import net.datafaker.Faker;
import org.apache.commons.lang3.RandomStringUtils;

public class RandomDataGenerator {
    public static Faker faker = new Faker();

    public static String getRandomDataFor(RandomDataTypeNames dataTypeNames) {

      return  switch (dataTypeNames) {
            case FIRSTNAME -> faker.name().firstName();
            case LASTNAME -> faker.name().lastName();
            case FULLNAME -> faker.name().fullName();
            case COUNTRYNAME -> faker.address().country();
            default -> "invalid";
        };
    }


    public static int getRandomNumber(int min,int max){
       return faker.number().numberBetween(min,max);
    }

    public static String getRandomAlphabetic(int count){
        return RandomStringUtils.randomAlphabetic(count);
    }

    public static String getRandomWebsite(int count){
        return "http://"+RandomDataGenerator.getRandomAlphabetic(count)+".com";
    }
}
