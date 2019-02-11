package com.example.demo.dao;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.example.demo.model.Prescription;

@Repository
public class PrescriptionDAO {
	
	@PersistenceContext
	private EntityManager entityManager;

	public void create (Prescription p) {
		entityManager.persist(p);
	}
	
	public void update(Prescription p) {
		entityManager.merge(p);
	}
	
	public Prescription getPrescriptionById(int id) {
		return entityManager.find(Prescription.class, id);
	}
	
	public void delete(int id) {
		Prescription p = getPrescriptionById(id);
		if(p != null)
			entityManager.remove(p);
	}
	
	public List<Prescription> getAll() {
		return entityManager.createNativeQuery("SELECT * FROM PRESCRIPTION", Prescription.class).getResultList();
		//return (List<Prescription>) entityManager.createNativeQuery("SELECT * FROM PRESCRIPTION", Prescription.class);
		//return p;
	}
	
	public List<Prescription> getBetween(Timestamp startTime, Timestamp endTime){
		return (List<Prescription>) entityManager.createNativeQuery("SELECT * FROM PRESCRIPTION WHERE START_TIME<='" +
						endTime + "' AND START_TIME <= '" + endTime +"'", Prescription.class).getResultList();
	}
	
	

}
