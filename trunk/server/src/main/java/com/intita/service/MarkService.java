/**
 * 
 */
package com.intita.service;

import antlr.collections.List;

import com.intita.domain.Mark;

/**
 * @author ����
 * @param <Subject>
 *
 */
public interface MarkService<Subject> {
	java.util.List<Mark> readAllSubjects();
	void addMark (Mark mark);
	Mark readMark (Integer id);

}
