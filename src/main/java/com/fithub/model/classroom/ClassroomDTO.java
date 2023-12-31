package com.fithub.model.classroom;

import lombok.Data;

@Data
public class ClassroomDTO {

	private int classroomId;

	private String classroomName;

	private String classroomStatus;

	private int classroomPrice;
	
	private String classroomPic;

	public ClassroomDTO(int classroomId, String classroomName, String classroomStatus,int classroomPrice,String classroomPic) {
		this.classroomId = classroomId;
		this.classroomName = classroomName;
		this.classroomStatus = classroomStatus;
		this.classroomPrice = classroomPrice;
		this.classroomPic = classroomPic;
		
	}
}