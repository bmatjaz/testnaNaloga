package com.example.demo.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="PRESCRIPTION")
public class Prescription {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="med_name")
	private String medName;
	
	@Column(name="dosage")
	private int dosage;
	
	@Column(name="unit")
	private String unit;
	
	@Column(name="start_time")
	private Timestamp start_time;
	
	@Column(name="end_time")
	private Timestamp end_time;
	
	@Column(name="timing_hours_period")
	private int timing_hours_period;

	public String getMedName() {
		return medName;
	}

	public void setMedName(String medName) {
		this.medName = medName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getDosage() {
		return dosage;
	}

	public void setDosage(int dosage) {
		this.dosage = dosage;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Timestamp getStart_time() {
		return start_time;
	}

	public void setStart_time(Timestamp start_time) {
		this.start_time = start_time;
	}

	public Timestamp getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Timestamp end_time) {
		this.end_time = end_time;
	}

	public int getTiming_hours_period() {
		return timing_hours_period;
	}

	public void setTiming_hours_period(int timing_hours_period) {
		this.timing_hours_period = timing_hours_period;
	}
}
