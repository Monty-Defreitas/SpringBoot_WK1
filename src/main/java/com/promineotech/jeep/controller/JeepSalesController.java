package com.promineotech.jeep.controller;

import com.promineotech.jeep.entity.Jeep;
import com.promineotech.jeep.entity.JeepModel;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@RequestMapping("/Jeeps")
@OpenAPIDefinition(info = @Info(title = "Jeep Sales Service"), servers = {@Server(url = "http://localhost:8080", description = "Local Server.")})
public interface JeepSalesController {

    @Operation(
            summary = "Returns a list of jeeps ",
            description = " Returns a list of jeeps given an optional model and/or trim",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = " A list of jeeps is returned",
                            content = @Content(mediaType = " Application/json",
                                    schema = @Schema(implementation = Jeep.class))),
                    @ApiResponse(responseCode = "400",
                            description = "The request parameters are invalid",
                            content = @Content(mediaType = " Application/json")),
                    @ApiResponse(responseCode = "404",
                            description = "No jeeps were found with the input criteria",
                            content = @Content(mediaType = " Application/json")),
                    @ApiResponse(responseCode = "500",
                            description = "An unplanned error occured.",
                            content = @Content(mediaType = " Application/json"))
            },
            parameters = {
                    @Parameter(name = "model", allowEmptyValue = false,
                            required = false,
                            description = "The model name (i.e. 'Wrangler')"),
                    @Parameter(name = "trim", allowEmptyValue = false,
                            required = false,
                            description = " The trim level (i.e. 'Sport')")
            }
    )

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    List<Jeep> fetchJeeps(@RequestParam JeepModel model,
                          @RequestParam String trim);

}
