package com.techm.repository;

import org.springframework.data.repository.CrudRepository;

//import com.techm.entity.Driver;
import com.techm.entity.Vehicle;
//import com.techm.entity.VehicleType;

public interface VecRepository extends CrudRepository<Vehicle, Long>{
	Vehicle findByDriverId(int id);
	Vehicle findByVehicleTypeId(int id);
	Vehicle findByVehicleRegNo(String regNo);

//	Vehicle findBytbl_vehicle_type_id(long gettbl_vehicle_type_id);
	//Vehicle findBytbl_vehicle_type_id(int gettbl_vehicle_type_id);

	//Vehicle findBytbl_vehicle_type_idName(int tbl_vehicle_type_id);
	//Vehicle findByVecName(String name);
	//Vehicle findBytbl_driver_id(Integer id);
	//Vehicle findBytbl_vehicle_type_id(Integer id);
}
