/**
 * @Author Sergey "yakudza" Tatarnikov 
 * @Date 30.02.2013
 * @Please Vinnitsa ITA
 * @Version 1.0
 *  
 */
package com.intita.service;

import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.intita.dao.ResiverDAO;
import com.intita.domain.Exam;
import com.intita.domain.Resiver;
import com.intita.domain.User;

@Service
public class ResiverServiceImpl implements ResiverService {
	@Autowired
	private ResiverDAO resiverDAO;
	
	
	
	public ResiverDAO getResiverDAO() {
		return resiverDAO;
	}
  
	public void setResiverDAO(ResiverDAO resiverDAO) {
		this.resiverDAO = resiverDAO;
	}
	
	@Override
	@Transactional
	public void AddResiver(Resiver resiver) {
	resiverDAO.create(resiver);
	}
	@Override
	@Transactional
	public List<Resiver> readExamById(Integer id) {
		
		return resiverDAO.readExamById(id);
	
	}
	
	@Override
	@Transactional
	public List<Resiver> readByGroupId(Integer id) {
		
		return resiverDAO.readByGroupId(id);
	
	}
	
	@Transactional
	@Override
	public void update(Resiver resiver) 
	{
		resiverDAO.update(resiver);		
	}

	@Transactional
	@Override
	public Resiver readUserByRowId(Integer id)
	{
		return resiverDAO.readUserByRowId(id);
	}
}
