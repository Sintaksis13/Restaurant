package com.rp.healthcheck;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/restaurant/api/health")
@Api(value = "/api/health")
public class HealthCheckController {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Check server status", response = String.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Server status is OK"),
            @ApiResponse(code = 500, message = "Server status is not OK")
    })
    public String checkServerStatus() {
        return "OK";
    }

}
