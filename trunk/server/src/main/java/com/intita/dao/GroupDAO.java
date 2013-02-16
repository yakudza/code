package com.intita.dao;

import com.intita.domain.Group;

/**
 * @author ����
 * @author Dmitriy Pyasetskiy
 */

public interface GroupDAO extends GenericDao<Group, Integer>
{
	Group findById(Integer groupId);
	void deleteGroup(Integer groupId);
}
