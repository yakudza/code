/**
 * @Author Sergey "yakudza" Tatarnikov 
 * @Date 30.02.2013
 * @Please Vinnitsa ITA
 * @Version 1.0
 *  
 */
package com.intita.web;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.intita.domain.Exam;
import com.intita.domain.ExamConnector;
import com.intita.domain.ExamResive;
import com.intita.domain.Group;
import com.intita.domain.Resiver;
import com.intita.domain.Role;
import com.intita.domain.Subject;
import com.intita.domain.User;
import com.intita.service.ExamService;
import com.intita.service.GroupService;
import com.intita.service.ResiverService;
import com.intita.service.ServiceLessons;
import com.intita.service.SubjectService;
import com.intita.service.UserService;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

@Controller
public class ExamController {
	@Autowired
	private ExamService service;
	@Autowired
	private UserService userService;
	@Autowired
	private GroupService groupService;
	@Autowired
	private ResiverService resiverService;
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private ServiceLessons lessonsService;
	@Autowired
	private ExamService examService;
	private String typeUser;

	public ServiceLessons getLessonsService() {
		return lessonsService;
	}

	public void setLessonsService(ServiceLessons lessonsService) {
		this.lessonsService = lessonsService;
	}

	public ExamService getExamService() {
		return examService;
	}

	public void setExamService(ExamService examService) {
		this.examService = examService;
	}

	public SubjectService getSubjectService() {
		return subjectService;
	}

	public void setSubjectService(SubjectService subjectService) {
		this.subjectService = subjectService;
	}

	public ExamService getService() {
		return service;
	}

	public void setService(ExamService service) {
		this.service = service;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public GroupService getGroupService() {
		return groupService;
	}

	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}

	public ResiverService getResiverService() {
		return resiverService;
	}

	public void setResiverService(ResiverService resiverService) {
		this.resiverService = resiverService;
	}

	/*
	 * пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅ
	 * пїЅпїЅпїЅ пїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅ.
	 */
	@RequestMapping(value = "/examGroup")
	// , method = RequestMethod.GET)
	public String listGroup(@ModelAttribute("group") Group group, Model model) {

		// User
		// userR=userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		User user1 = userService.findByEmail(SecurityContextHolder.getContext()
				.getAuthentication().getName());

		for (Role r : user1.getRoles()) {
			if (r.getName().equals("student")) {
				this.typeUser = "student";
				// this.searchId=user1.getGroup().getGroupId();
			} else {
				this.typeUser = "teacher";
				// this.searchId=user1.getUserId();
			}
		}

		if (typeUser.endsWith("teacher")) {
			List<ExamResive> lER = new ArrayList<ExamResive>();
			List<Exam> rezult = examService.readLessonType("exam");
			rezult.addAll(examService.readLessonType("offset"));

			for (Exam exam : rezult) {

				ExamResive obj = new ExamResive();
				Subject sub;
				sub = subjectService.readSubject(exam.getSubjectId());

				Group gro;
				gro = groupService.readGroup(exam.getGroupId());
				obj.setExams(exam);
				obj.setGroups(gro);
				obj.setSubjects(sub);
				lER.add(obj);
			}
			ExamConnector conn = new ExamConnector();
			model.addAttribute(conn);
			model.addAttribute("test", lER);
			return "examTeach";
		} else {
			model.addAttribute("resivers",
					resiverService.readExamById(user1.getUserId()));
			return "examStudent";
		}

		// return "redirect:/home";
	}

	@RequestMapping(value = "/test", method = RequestMethod.POST)
	public String test(
			@ModelAttribute("examConnector") ExamConnector examConnector,
			Model model) {
		//String page=null;
		List<Resiver> examTable = new ArrayList<Resiver>();
		List<Resiver> temp = resiverService.readByGroupId(examConnector.getGroupId());
		for (Resiver resiver : temp) {
			
			if(resiver.getSubjectName().equals(examConnector.getSubjectName()) && resiver.getSubjectType().equals(examConnector.getType()))
			{
			examTable.add(resiver);
			System.out.println(resiver.getSubjectType());
			}
		}
		
		
		if(examTable.isEmpty())
		{
			//System.out.println("пусто");
			ExamConnector resiverList = new ExamConnector();
			model.addAttribute("resiver", resiverList);
			model.addAttribute("examConnector", examConnector);
			//model.addAttribute("users",resiverService.readByGroupId(examConnector.getGroupId()));
			List<User> users = userService.findByGroup(groupService.readGroup(examConnector.getGroupId()));
			model.addAttribute("users", users);
//			Resiver resiver = new Resiver();
//			for (User user : users) {
//				resiver.setGroupId(examConnector.getGroupId());
//				resiver.setName(user.getName());
//				resiver.setSurname(user.getSurname());
//				resiver.setUserId(user.getUserId());
//				resiverService.AddResiver(resiver);
//			}
			
			return"examTEST";
		}else
		{
			if(examTable.get(0).getStatus().equals("close"))
			{	Resiver res=new Resiver();
				model.addAttribute("resiver",res);
				model.addAttribute("groupId",examTable.get(0));
				model.addAttribute("masseng", "This event has been closed");
			return "examMassengPage";
			}else
			{ExamConnector resiverList = new ExamConnector();
				model.addAttribute("resiver", resiverList);
				model.addAttribute("examConnector", examConnector);
				model.addAttribute("users",examTable);
				return "testFile";
			}
		
		}
//		ExamConnector resiverList = new ExamConnector();
//		model.addAttribute("resiver", resiverList);
//		model.addAttribute("examConnector", examConnector);
		//model.addAttribute("users",resiverService.readByGroupId(examConnector.getGroupId()));
//		model.addAttribute("users", userService.findByGroup(groupService.readGroup(examConnector.getGroupId())));
		

	

		// System.out.println(examConnector);
		// System.out.println(examConnector.getGroupName());
		// System.out.println(examConnector.getSubjectName());
		// System.out.println(examConnector.getType());
		// System.out.println(examConnector.getGroupId());
		// System.out.println(examConnector.getTime());

		//return page;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("resiver") ExamConnector examConnector,HttpServletRequest req) {

		String status=null;
		try{
			if(!req.getParameterValues("status").equals("close"))
			status="close";
		   }
		catch (Exception e) {
			  status = "null";
		   }
//		System.out.println(resiverList.getUserMark());
//		System.out.println(resiverList.getRowId());
//		List<Resiver> temp = resiverService.readByGroupId(examConnector.getGroupId());
//		for (Resiver resiver : temp) {
//			
//			if(resiver.getSubjectName().equals(examConnector.getSubjectName()) && resiver.getSubjectType().equals(examConnector.getType()))
//			{
//			examTable.add(resiver);
//			System.out.println(resiver.getSubjectType());
//			}
//		}
//		
//		
//		if(examTable.isEmpty())
//		{
		String[] subjectName = examConnector.getSubjectName().split(",");
		String[] type = examConnector.getType().split(",");
		
		List<Resiver> examTable = new ArrayList<Resiver>();
		List<Resiver> temp = resiverService.readByGroupId(examConnector.getGroupId());
		for (Resiver resiverTable : temp) {
			
			if(resiverTable.getSubjectName().equals(subjectName[0]) && resiverTable.getSubjectType().equals(type[0]))
			{
			examTable.add(resiverTable);
			System.out.println("+1 row to examTalle");
			}
		}
		
		
		if(examTable.isEmpty())
		{	System.out.println("create");
//			String[] subjectName = examConnector.getSubjectName().split(",");
//			String[] type = examConnector.getType().split(",");
			String[] userId = examConnector.getUserId().split(",");
			String[] mark = examConnector.getUserMark().split(",");
			String[] name = examConnector.getName().split(",");
			String[] surname = examConnector.getSurname().split(",");
			Resiver resiver = new Resiver();
			int las = subjectName.length;
		for (int i = 0; i < las; i++) {
			
				resiver.setSurname(surname[i]);
				resiver.setName(name[i]);
				resiver.setUserId(Integer.parseInt(userId[i]));
				resiver.setUserMark(mark[i]);
				resiver.setSubjectName(subjectName[i]);
				resiver.setSubjectType(type[i]);
				resiver.setTime(examConnector.getTime());
				resiver.setGroupId(examConnector.getGroupId());
				resiver.setStatus(status);
					//System.out.println("false");
				  //status = "null";resiverService.update(resiver);
//				resiver.setStatus(status);
//				System.out.println(status);
				//resiverService.update(resiver);
				resiverService.AddResiver(resiver);
			}
				//System.out.println(name[i]);
		
		}
		else {
			System.out.println("update");
			String[] mark = examConnector.getUserMark().split(",");
			String[] rowId = examConnector.getRowId().split(",");
//			Resiver resiver = new Resiver();
			int las = mark.length;
			for (int i = 0; i < las; i++) {
				
				
			Resiver rezult=resiverService.readUserByRowId(Integer.parseInt(rowId[i]));
			
			System.out.println(rezult.getRowId());
			System.out.println(rezult.getUserMark());
			rezult.setStatus(status);
			
			if (rezult.getUserMark().equals("X"))
			rezult.setUserMark(mark[i]);
			else if (!mark[i].equals("X"))
				rezult.setUserMark(mark[i]);
			resiverService.update(rezult);
		
			}
		}
//				resiver.setSurname(surname[i]);
//				resiver.setName(name[i]);
//				resiver.setUserId(Integer.parseInt(userId[i]));
//				resiver.setUserMark(mark[i]);
//				resiver.setSubjectName(subjectName[i]);
//				resiver.setSubjectType(type[i]);
//				resiver.setTime(resiverList.getTime());
//				resiver.setGroupId(resiverList.getGroupId());
//				try{
//					if(!req.getParameterValues("status").equals("close"))
//						resiver.setStatus("close");
//						//System.out.println("true");
//					//status="close";
//				   }
//				catch (Exception e) {
//					resiver.setStatus("null");
//					//System.out.println("false");
//				}  //status = "null";resiverService.update(resiver);
////				resiver.setStatus(status);
////				System.out.println(status);
//				resiverService.update(resiver);
//				//resiverService.AddResiver(resiver);
//			
//		}
		
		return "redirect:/examGroup";
		
		
	}
	@RequestMapping(value ="/show")
	public String show(@ModelAttribute("resiver")Resiver examConn, Model model)
	{
		
		List<Resiver> examTable = new ArrayList<Resiver>();
		List<Resiver> temp = resiverService.readByGroupId(examConn.getGroupId());
		System.out.println(examConn.getGroupId());
		System.out.println(examConn.getSubjectName());
		System.out.println(examConn.getSubjectType());
		for (Resiver resiverTable : temp) {
			
			if(resiverTable.getSubjectName().equals(examConn.getSubjectName()) && resiverTable.getSubjectType().equals(examConn.getSubjectType()))
			{
			examTable.add(resiverTable);
			System.out.println("+1 row to examTalle");
			}
		}
		model.addAttribute("userList", examTable);
		
		return "examShowPage";
	}
}	

