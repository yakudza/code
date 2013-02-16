/**
 * @Author Sergey "yakudza" Tatarnikov 
 * @Date 30.02.2013
 * @Please Vinnitsa ITA
 * @Version 1.0
 *  
 */
package com.intita.service;

import java.util.List;

import org.springframework.stereotype.Service;


import com.intita.domain.Resiver;

@Service
public interface ResiverService {
	
	void AddResiver(Resiver resiver);

	List<Resiver> readExamById(Integer id);
	List<Resiver> readByGroupId(Integer id);

	void update(Resiver resiver);
	Resiver readUserByRowId(Integer id);
	

}
