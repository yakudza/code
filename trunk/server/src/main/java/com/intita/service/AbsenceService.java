package com.intita.service;

import com.intita.domain.Absence;

/**
 * @author ����
 *
 */
public interface AbsenceService 
{
	void addAbsence (Absence Abs);
	Absence readAbsence (Integer id);
}
