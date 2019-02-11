package com.example.demo.service;

import java.util.List;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.PrescriptionDAO;
import com.example.demo.model.Prescription;

@Service
@Transactional
public class PrescriptionService {
	
	@Autowired
	private PrescriptionDAO prescriptionDAO;
	
	public void create(Prescription p) {
		prescriptionDAO.create(p);
	}
	
	public void update(Prescription p) {
		prescriptionDAO.update(p);
	}
	
	public void delete(int id) {
		prescriptionDAO.delete(id);
	}
	
	public List<Prescription> getAll() {
		return (List<Prescription>) prescriptionDAO.getAll();
	}
	
	
	public List<Prescription> getBetween(Timestamp startTime, Timestamp endTime){
		return (List<Prescription>) prescriptionDAO.getBetween(startTime, endTime);
	}
	
	public Prescription getPrescriptionById(int id) {
		return prescriptionDAO.getPrescriptionById(id);
	}
	
}
