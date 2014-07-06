package com.letter.tcp;

import com.letter.domain.AdItem;
import com.letter.service.AdService;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by berty on 04.07.14.
 */
public class JsonServer {

    private static final Logger log = LoggerFactory.getLogger(JsonServer.class);

    @Autowired
    private AdService adService;


    public String get(String input) {
        if ("GET".equals(input)) {
            List<AdItem> list = adService.getAll();
            ObjectMapper mapper = new ObjectMapper();
            String result = "FAIL";
            try {
                result = mapper.writeValueAsString(list);
            } catch (Exception e) {
                log.error("JsonServer.get", e);
            }
            return result;
        }
        return "ECHO: " + input;
    }

}
