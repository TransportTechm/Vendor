package com.techm.transport.vendor.controller;

import java.util.List;
import java.util.logging.FileHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.techm.transport.vendor.entity.Driver;
import com.techm.transport.vendor.entity.Sample;
import com.techm.transport.vendor.entity.Vehicle;
import com.techm.transport.vendor.exception.DriException;
import com.techm.transport.vendor.service.VehicleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("1.0")
@Api(description="Vehicle operations", tags= {"Vehicles"})
public class VehicleController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    FileHandler fh;  
    
    
    

	
	@Autowired
	private VehicleService service;
	
	
	@ApiOperation(value = "${VehicleController.getVecbyName}", response = Vehicle.class) 
	@ApiResponses(value= {
			@ApiResponse(code = 200, message = "Successfully get resource of given id"),
			@ApiResponse(code = 401, message = "Not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "Not found resource you were trying to get."),
			@ApiResponse(code = 500, message = "Internal server error")
							}
					)
	
	@GetMapping("vec/{regNo}")
	public ResponseEntity<Sample> getVecbyName(@ApiParam(name = "regNo", value = "RegNo of Vehicle", required = true) @PathVariable("regNo") String regNo){
		//LOGGER.info("Getting Vehicle  details of id-" +tbl_vehicle_type_id );
		
		Sample sam = service.getVecbyName(regNo);
		
		
		if(sam== null)
		{
			
			
			throw new DriException("Vehicle not found with id: "+ regNo);
		}
		//return new ResponseEntity<Driver>(dri, HttpStatus.OK);
		
		//return new ResponseEntity<Sample>(sam, HttpStatus.OK);
		return new ResponseEntity<Sample>(sam, HttpStatus.OK);	
	
	}
	
	
	@ApiOperation(value = "${VehicleController.getAllVec}", response = Vehicle.class) 
	@ApiResponses(value= {
			@ApiResponse(code = 200, message = "Successfully get resource of given id"),
			@ApiResponse(code = 401, message = "Not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "Not found resource you were trying to get."),
			@ApiResponse(code = 500, message = "Internal server error")
							}
					)
	
	@GetMapping("vecs")
	public ResponseEntity<List<Vehicle>> getAllVec(){
		LOGGER.info("Getting all Vehicle details-" );

		
		List<Vehicle> Vecs = service.getAllVehicles();
		return new ResponseEntity<List<Vehicle>>(Vecs, HttpStatus.OK);
	}
	
	@ApiOperation(value = "${VehicleController.addVehicle}", response = Vehicle.class) 
	@ApiResponses(value= {
			@ApiResponse(code = 200, message = "Successfully get resource of given id"),
			@ApiResponse(code = 401, message = "Not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "Not found resource you were trying to get."),
			@ApiResponse(code = 500, message = "Internal server error")
							}
					)
	
	@PostMapping("vec")

	public ResponseEntity<Vehicle> addVehicle(@RequestBody Vehicle vec, UriComponentsBuilder builder){
		LOGGER.info("Adding Vehicle  details-"+vec.getVehicleRegNo()+vec.getVerificationStatus());
		
		Vehicle dbVec = service.addVehicle(vec);
		//System.out.println(flag);
		if (dbVec == null) {
			return	new ResponseEntity<Vehicle>(vec, HttpStatus.CONFLICT);
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/1.0/vec/{id}").buildAndExpand(vec.getVehicleRegNo()).toUri());
		return new ResponseEntity<Vehicle>(dbVec,headers, HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "${VehicleController.updateVehicle}", response = Vehicle.class) 
	@ApiResponses(value= {
			@ApiResponse(code = 200, message = "Successfully get resource of given id"),
			@ApiResponse(code = 401, message = "Not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "Not found resource you were trying to get."),
			@ApiResponse(code = 500, message = "Internal server error")
							}
					)
	
   @PutMapping("vec/{regNo}/{status}")
	public ResponseEntity<String> updateVehicle(@PathVariable("regNo") String regNo, @PathVariable("status") String verificationStatus){
		service.updateVehicle(regNo, verificationStatus);
		return new ResponseEntity<String>("updated", HttpStatus.OK);
	}
	
	/*@DeleteMapping("vec/{id}")
	public ResponseEntity<Void> deleteDriver(@PathVariable("id") Integer id){
		service.deleteVehicle(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}*/

}
