package com.zoo.controller;

import static com.zoo.util.ClientMessageUtil.CREATION_FAILED;
import static com.zoo.util.ClientMessageUtil.CREATION_SUCCESSFUL;
import static com.zoo.util.ClientMessageUtil.DELETION_FAILED;
import static com.zoo.util.ClientMessageUtil.DELETION_SUCCESSFUL;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zoo.models.ClientMessage;
import com.zoo.models.HabitatType;
import com.zoo.services.HabitatTypeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(value= "HabitatTypeRestController", description = "REST controller related to Habitat Type Enteties")
public class HabitatTypeController {
	
	private static Logger log = Logger.getLogger(HabitatTypeController.class);
	
	@Autowired
	private HabitatTypeService typev;
	
	@ApiOperation(value="Find animal by name", notes="Provide a name to lookup a specific habitatType from the API", response = HabitatType.class)
	@GetMapping(path = "/animals/type")
	public @ResponseBody HabitatType getByName(@RequestParam(value = "name", name = "name")String name) {
		
		log.info("finding habitat type by name in controller...");
		System.out.println("Test: " + typev.getTypeByName(name));
		return typev.getTypeByName(name);
	}
	
	@GetMapping("/animals/type")
	@ApiOperation(value="find all habitatTypes")
	public @ResponseBody List<HabitatType> getAll(){
		
		log.info("looking up all habitatTypes in controller...");
		return typev.getAllTypes();
	}
	
	@PostMapping("/animals/type")
	@ApiOperation(value="Create new habitatType")
	public @ResponseBody ClientMessage createHabitatType(@RequestBody HabitatType habitatType){
		
		log.info("creating new habitatType in controller..." + habitatType);
		return typev.createType(habitatType) ? CREATION_SUCCESSFUL : CREATION_FAILED;
	}
	
	@PutMapping("/animals/type")
	@ApiOperation(value="Update habitat type entity")
	public @ResponseBody ClientMessage updateType(@RequestBody HabitatType habitatType) {
		
		log.info("update habitat type " + habitatType);
		return typev.updateType(habitatType) ? CREATION_SUCCESSFUL : CREATION_FAILED;
	}
	
	@DeleteMapping("/animals/type")
	@ApiOperation(value="Remove habitat type entity")
	public @ResponseBody ClientMessage deleteType(@RequestBody HabitatType habitatType) {
		
		log.info("deleting an habitat type in controller...");
		return typev.deleteType(habitatType) ? DELETION_SUCCESSFUL : DELETION_FAILED;
	}
}
