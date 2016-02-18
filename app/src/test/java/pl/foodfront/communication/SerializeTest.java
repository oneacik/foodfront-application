package pl.foodfront.communication;

import com.google.gson.GsonBuilder;

import org.junit.Test;

import pl.foodfront.serialized.Login;
import pl.foodfront.serialized.iSerialize;

import static org.junit.Assert.assertEquals;

/**
 * Created by Michał Stobiński on 2016-02-18.
 */

public class SerializeTest {

    private iSerialize serialize;

    @Test
    public void shouldReturnCorrectJson() {

        // given
        serialize = new Login("dada", "usos");
        String jsonBefore = new GsonBuilder().create().toJson(serialize, Login.class);

        // when
        String jsonConverted = serialize.serializeToJson();

        // then
        assertEquals(jsonBefore, jsonConverted);
    }

}
