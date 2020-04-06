package com.example.demo.rest;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DbCon;
import com.example.demo.model.Prescription;
import com.example.demo.service.PrescriptionService;

import java.sql.Timestamp;
import java.util.*;

@RestController
@EnableAutoConfiguration
public class PrescriptionRest {

	
	@Autowired
	private PrescriptionService prescriptionService;

	
	//gets one prescription with selected ID
	@RequestMapping(value="/getOne", method = RequestMethod.GET)
	public Prescription getOne(int id) {
				return prescriptionService.getPrescriptionById(id);
	}
	
	//returns all table (prescriptions) that have been inserted
	@RequestMapping(value="/getAll", method = RequestMethod.GET)
	public List<Prescription> getAll() {
			return prescriptionService.getAll();
	}
	
	//creates new prescription
	@RequestMapping(value="/create", method = RequestMethod.POST)
	public String create(Prescription p) {
			prescriptionService.create(p);
			return "uspesno";
	}
	
	//takes ID and deletes item
	@RequestMapping(value="/delete", method = RequestMethod.DELETE)
	public String delete(int id) {
			prescriptionService.delete(id);
			return "uspesno";
	}
	
	//takes prescription and updates it with new data
	@RequestMapping(value="/update", method = RequestMethod.PUT)
	public String update(Prescription p) {
			prescriptionService.update(p);
			return "uspesno dodan";
	}
	
	
	//call /getBetween returns how many times there need to be admission for the prescription in two given times (dates)
	@RequestMapping(value="/getBetween", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Prescription> getBetween (Timestamp startTime, Timestamp endTime){
			List<Prescription> seznam = prescriptionService.getAll();
			List<Prescription> seznam_vseh = new ArrayList<Prescription>();
			
			Calendar start = Calendar.getInstance();
			start.setTimeInMillis(startTime.getTime());
			
			Calendar end = Calendar.getInstance();
			end.setTimeInMillis(endTime.getTime());
			
			
			for (Prescription p : seznam)
			{			
				start.setTimeInMillis(startTime.getTime());
				
				Calendar pStartTime = Calendar.getInstance();
				pStartTime.setTimeInMillis(p.getStart_time().getTime());
				
				Calendar pEndTime = Calendar.getInstance();
				pEndTime.setTimeInMillis(p.getEnd_time().getTime());

				
				while(pStartTime.getTimeInMillis() < start.getTimeInMillis() || 
						end.getTimeInMillis() >= pStartTime.getTimeInMillis())
				{
					if(pStartTime.getTimeInMillis() >= start.getTimeInMillis())
					{
						seznam_vseh.add(p);
						pStartTime.add(Calendar.HOUR, p.getTiming_hours_period());
					}
					else
						pStartTime.add(Calendar.HOUR, p.getTiming_hours_period());
				}
			}
		return seznam_vseh;
	}	
}
