package com.fincuro.payload.service.transformer;

import com.bazaarvoice.jolt.Chainr;
import com.bazaarvoice.jolt.JsonUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageTransformer {

    public String transform(String inputJson, String jsonSpecification) {
        if (inputJson.equals("{}") || inputJson.equals("[]")) {
            return null;
        }
        List<Object> chainrSpecJSON = JsonUtils.jsonToList(jsonSpecification);
        // transform input json
        Chainr chainr = Chainr.fromSpec(chainrSpecJSON);
        Object inputJSON = JsonUtils.jsonToObject(inputJson);
        Object transformedOutput = chainr.transform(inputJSON);
        return JsonUtils.toJsonString(transformedOutput);
    }

}
