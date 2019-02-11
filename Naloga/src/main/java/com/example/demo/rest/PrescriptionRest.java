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

	
	/*
	@RequestMapping("/getAll2")
	public List<Prescription> getAll2() throws Exception{

		try {
			DbCon db = new DbCon();
			
			ResultSet result = db.conn().executeQuery("SELECT * FROM PRESCRIPTION");
			
			List<Prescription> prescriptions = new ArrayList();
			
			
			while(result.next())
			{
				Prescription p = new Prescription();
				p.setId(result.getInt("id"));
				p.setMedName(result.getString("med_name"));
				p.setUnit(result.getString("unit"));
				p.setDosage(result.getInt("dosage"));
				p.setStart_time(result.getTimestamp("start_time"));
				p.setEnd_time(result.getTimestamp("end_time"));
				p.setTiming_hours_period(result.getInt("timing_hours_period"));
				prescriptions.add(p);
			}
			
			
			return prescriptions;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value="/create", method = RequestMethod.POST)
	public ResponseEntity<Prescription> vnos(Prescription p) {
		
		if(p != null)
			{		
				try {
					DbCon db = new DbCon();				
					db.conn().executeUpdate("INSERT INTO PRESCRIPTION(id, med_name, dosage, unit, timing_hours_period, start_time, end_time) VALUES (" + 
					p.getId() +", '" + 
					p.getMedName()+ "' ," +
					p.getDosage() + " , '" +
					p.getUnit() + "' , " +
					p.getTiming_hours_period() + " , '" +
					p.getStart_time() + "' , '" +
					p.getEnd_time() + "')" );
					return new ResponseEntity<Prescription>(p, HttpStatus.OK);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		return null;
	}
		
	
	@RequestMapping(value="/update", method = RequestMethod.PUT)
	public String update(Prescription p) {
		DbCon db = new DbCon();
			try {
				db.conn().executeUpdate("UPDATE PRESCRIPTION SET MED_NAME='"+p.getMedName() +"' WHERE ID="+ p.getId());
				return "updated";
				
			} catch (Exception e) {
				e.printStackTrace();
				
			}
		return "Prazen obj " + p.getMedName() + p.getId();
	}
	
	
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public String delete(int id) {
		DbCon db = new DbCon();
		try {
			db.conn().executeUpdate("DELETE FROM PRESCRIPTION WHERE ID=" + id);
			return "deleted";
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return "not deleted";
	}
	
	
	@RequestMapping("/getInTime")
	public List<Prescription> getAll(Timestamp starttime, Timestamp endtime) throws Exception{

		try {
			DbCon db = new DbCon();
			
			ResultSet result = db.conn().executeQuery("SELECT * FROM PRESCRIPTION WHERE END_TIME > '" +
					starttime + "' AND END_TIME >= '" + endtime +"'");
			
			List<Prescription> prescriptions = new ArrayList();
			
			
			while(result.next())
			{
				Prescription p = new Prescription();
				
				p.setStart_time(result.getTimestamp("start_time"));
				p.setEnd_time(result.getTimestamp("end_time"));
				p.setTiming_hours_period(result.getInt("timing_hours_period"));
				
				Timestamp timeForAddmision = p.getStart_time();
				
				while (timeForAddmision.getTime() >= starttime.getTime() && timeForAddmision.getTime() <= endtime.getTime()) {
					
					p.setStart_time(result.getTimestamp("start_time"));
					p.setEnd_time(result.getTimestamp("end_time"));
					p.setTiming_hours_period(result.getInt("timing_hours_period"));
					p.setId(result.getInt("id"));
					p.setMedName(result.getString("med_name"));
					p.setUnit(result.getString("unit"));
					p.setDosage(result.getInt("dosage"));
					
					//izracunamo nov cas zadoziranje
					timeForAddmision.setTime(timeForAddmision.getTime() + (((p.getTiming_hours_period() * 60 * 60 )) * 1000));
					
					prescriptions.add(p);
				}
			}
			
			
			return prescriptions;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
*/

	
	
}