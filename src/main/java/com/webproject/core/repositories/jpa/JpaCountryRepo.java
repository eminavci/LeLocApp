//package com.webproject.core.repositories.jpa;
//
//import java.util.List;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.stereotype.Repository;
//
//import com.webproject.core.models.entities.Country;
//import com.webproject.core.repositories.CountryRepo;
//
//@Repository
//public class JpaCountryRepo implements CountryRepo{
//	
//	@PersistenceContext
//    private EntityManager em;
//
//	@Override
//	public void deleteAllInBatch() {
//	}
//
//	@Override
//	public void deleteInBatch(Iterable<Country> arg0) {
//	}
//
//	@Override
//	public List<Country> findAll() {
//		return null;
//	}
//
//	@Override
//	public List<Country> findAll(Sort arg0) {
//		return null;
//	}
//
//	@Override
//	public List<Country> findAll(Iterable<Long> arg0) {
//		return null;
//	}
//
//	@Override
//	public void flush() {		
//	}
//
//	@Override
//	public Country getOne(Long arg0) {
//		return null;
//	}
//
//	@Override
//	public <S extends Country> List<S> save(Iterable<S> arg0) {
//		return null;
//	}
//
//	@Override
//	public <S extends Country> S saveAndFlush(S arg0) {
//		return null;
//	}
//
//	@Override
//	public Page<Country> findAll(Pageable arg0) {
//		return null;
//	}
//
//	@Override
//	public long count() {
//		return 0;
//	}
//
//	@Override
//	public void delete(Long arg0) {
//	}
//
//	@Override
//	public void delete(Country arg0) {
//	}
//
//	@Override
//	public void delete(Iterable<? extends Country> arg0) {
//	}
//
//	@Override
//	public void deleteAll() {
//	}
//
//	@Override
//	public boolean exists(Long arg0) {
//		return false;
//	}
//
//	@Override
//	public Country findOne(Long arg0) {
//		return null;
//	}
//
//	@Override
//	public <S extends Country> S save(S arg0) {
//		return null;
//	}
//
//	@Override
//	public Country findByFullName(String name) {
//	
//		return null;
//	}
//	
//
//}
