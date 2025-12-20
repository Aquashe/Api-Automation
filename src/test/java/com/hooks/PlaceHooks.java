package com.hooks;

import com.api.exceptions.ApiException;
import com.api.utils.RuntimeVariable;
import com.base.ApiBaseTest;
import com.context.ContextHolder;
import com.context.ScenarioContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PlaceHooks extends ApiBaseTest {
    @After("@AddPlace")
    public void afterAddPlace() {
        log.info("✅ place_id        : {}", RuntimeVariable.get("placeId", String.class));
        log.info("✅ scope           : {}", RuntimeVariable.get("scope", String.class));
        log.info("✅ reference       : {}", RuntimeVariable.get("reference", String.class));
        log.info("✅ id              : {}", RuntimeVariable.get("id", String.class));
    }

    @Before("@DeletePlace")
    public void beforeDeletePlace(){
        if(!RuntimeVariable.contains("placeId"))
            throw new ApiException("❌ place_id not found. AddPlace scenario not executed.");
    }
}
