package ar.com.frupp.challengemutantes.api.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class RequestEntityTests {

    @Test
    public void getDnaMatrix(){
        String[] expected = {
                "AAAA",
                "AAAA",
                "AAAA",
                "AAAA"
        };

        DnaRequestEntity requestEntity = new DnaRequestEntity(true, expected);

        String[] actual = requestEntity.getDnaAsArray();

        Assertions.assertTrue(Arrays.equals(actual, expected));
    }

    @Test
    public void dnaString(){
        String[] dnaMatrix = {
                "AAAA",
                "AAAA",
                "AAAA",
                "AAAA"
        };

        String expected = "AAAA,AAAA,AAAA,AAAA";

        DnaRequestEntity requestEntity = new DnaRequestEntity(true, dnaMatrix);

        String actual = requestEntity.getDna();

        Assertions.assertEquals(actual, expected);
    }
}
