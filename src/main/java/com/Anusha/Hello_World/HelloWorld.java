package com.Anusha.Hello_World;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.collections.MapUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.studio4365.spring.healthcheck.exception.HealthCheckException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.core.*;
import java.io.IOException;

@RestController
public class HelloWorld {
    @RequestMapping("/")
    public String index() {

        return "Hello!";
    }

    @RequestMapping(value="healthz", method=RequestMethod.GET)
    public @ResponseBody
    String healthCheck() {

        Map<String, Object> result = new HashMap<>();
        long startTime = System.currentTimeMillis() - System.nanoTime()/1000000;
        result.put("return_code", 0);
        result.put("Status", "ok");
        String upstring = "up since"+ " "+new Date(startTime);
        result.put("uptime",upstring);
        String version = "0.01";
        if (version != null) {
            result.put("version", version);
        }
        if (MapUtils.getIntValue(result, "return_code") != 0) {
            throw new HealthCheckException(result);
        }
        String json = null;
        try
        {
            //Convert Map to JSON

            json = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(result);
        }

        catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    @ExceptionHandler(HealthCheckException.class)
    @ResponseStatus(value=HttpStatus.SERVICE_UNAVAILABLE)
    public @ResponseBody Map<String, Object> error(HttpServletRequest request, HealthCheckException e) {
        return e.getResponse();
    }

}
