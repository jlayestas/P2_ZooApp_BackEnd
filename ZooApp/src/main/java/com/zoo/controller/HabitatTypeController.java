package com.zoo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zoo.services.HabitatTypeService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api")
@Api(value= "HabitatTypeRestController", description = "REST controller related to Habitat Type Enteties")
public class HabitatTypeController {
	
	@Autowired
	private HabitatTypeService typev;
	
	@ApiOperation()

}
