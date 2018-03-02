package com.techm.transport.vendor.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name="tbl_vehicle")
public class Vehicle{
	//public String	verificationStatus = "Pending";
	
	@Id
	@Column(name="vehicle_registration_number")
	private String	vehicleRegNo;
	
	@Column(name = "tbl_drivers_id", updatable = false, nullable = false)
	private int driverId;
	
	@Column(name="table_vehicle_type_id")
	public int vehicleTypeId;
	
	@Column(name="verification_status")
	//@ColumnDefault("pending")  
	private String	verificationStatus;
	
	
	/*@Column(name="insurance_status")
	private String	insuranceStatus;*/
	
	
	public Vehicle()
	{
		   
	}
	
	

	public Vehicle(String vehicleRegNo, int driverId, int vehicleTypeId, String verificationStatus) {
		super();
		this.vehicleRegNo = vehicleRegNo;
		this.driverId = driverId;
		this.vehicleTypeId = vehicleTypeId;
		this.verificationStatus = verificationStatus;
	}


	public int getDriverId() {
		return driverId;
	}

	public void setDriverId(int driverId) {
		this.driverId = driverId;
	}
	
	
	public int getVehicleTypeId() {
		return vehicleTypeId;
	}

	public void setVehicleTypeId(int vehicleTypeId) {
		this.vehicleTypeId = vehicleTypeId;
	}
    
	//@ApiModelProperty(hidden  = true)
	public String getVerificationStatus() {
		return verificationStatus;
	}

	public void setVerificationStatus(String verificationStatus) {
		
		
	/*if(verificationStatus.equals(null))
		this.verificationStatus = "pending";
	else*/
		
		this.verificationStatus = verificationStatus;
		
	}

	/*public String getInsuranceStatus() {
		return insuranceStatus;
	}

	public void setInsuranceStatus(String insuranceStatus) {
		this.insuranceStatus = insuranceStatus;
	}
*/
	public String getVehicleRegNo() {
		return vehicleRegNo;
	}

	public void setVehicleRegNo(String vehicleRegNo) {
		this.vehicleRegNo = vehicleRegNo;
	}


	@Override
	public String toString() {
		return "vehicleRegNo, driverId, vehicleTypeId, verificationStatus :"+vehicleRegNo+ driverId+ vehicleTypeId+ verificationStatus;
	}
	
}
