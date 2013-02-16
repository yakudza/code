/**
 * @Author Sergey "yakudza" Tatarnikov 
 * @Date 30.02.2013
 * @Please Vinnitsa ITA
 * @Version 1.0
 *  
 */
package com.intita.domain;

import java.sql.Timestamp;

public class ExamConnector {
private String type;
private String groupName;
private String subjectName;
private Timestamp time;
private Integer groupId;
private String userMark;
private String userId;
private String name;
private String surname;
private String rowId;




public String getRowId() {
	return rowId;
}
public void setRowId(String rowId) {
	this.rowId = rowId;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getSurname() {
	return surname;
}
public void setSurname(String surname) {
	this.surname = surname;
}
public String getUserMark() {
	return userMark;
}
public void setUserMark(String userMark) {
	this.userMark = userMark;
}
public String getUserId() {
	return userId;
}
public void setUserId(String userId) {
	this.userId = userId;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public String getGroupName() {
	return groupName;
}
public void setGroupName(String groupName) {
	this.groupName = groupName;
}
public String getSubjectName() {
	return subjectName;
}
public void setSubjectName(String subjectName) {
	this.subjectName = subjectName;
}
public Timestamp getTime() {
	return time;
}
public void setTime(Timestamp time) {
	this.time = time;
}
public Integer getGroupId() {
	return groupId;
}
public void setGroupId(Integer groupId) {
	this.groupId = groupId;
}


}
