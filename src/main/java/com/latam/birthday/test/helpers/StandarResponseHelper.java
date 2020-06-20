package com.latam.birthday.test.helpers;

import com.google.gson.GsonBuilder;
import java.util.HashMap;
import java.util.LinkedHashMap;


/**
 *
 *      This contain a standar response structure for API
 *
 * @author Jorge Silva Aguilera
 */
public class StandarResponseHelper {

    /**
     * Instantiates a new message error helper.
     */
    private StandarResponseHelper(){
        super();
    }



    /**
     *
     *      Make resonse standar format.
     *
     * @param o Any object to return in json response
     * @param message string with info to consumer
     * @return String Json
     * @author Jorge Silva Aguilera
     */
    public static final String success(Object o, String message) {
        HashMap<String, Object> response = new LinkedHashMap<>();
        response.put("status", "success");
        response.put("message", message);
        response.put("data", o);
        return new GsonBuilder().setPrettyPrinting().create().toJson(response);
    }

    /**
     *
     *      Make resonse standar format with error
     *
     * @param errorMessage string with info to consumer
     * @return String Json
     * @author Jorge Silva Aguilera
     */
    public static final String error(Object errorMessage) {
        HashMap<String, Object> response = new LinkedHashMap<>();
        response.put("status", "error");
        response.put("message", errorMessage);
        return new GsonBuilder().setPrettyPrinting().create().toJson(response);
    }
}
