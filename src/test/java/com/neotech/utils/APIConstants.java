package com.neotech.utils;

public class APIConstants {

	public static final String BASE_URI = "https://neo-api.azurewebsites.net";

	public static final String GENERATE_TOKEN_ENDPOINT = "/api/TokenAuth/Authenticate";

	public static final String GET_ALL_STUDENTS_ENDPOINT = "/api/services/app/Student/GetAll";
	public static final String GET_ONE_STUDENT_ENDPOINT = "/api/services/app/Student/Get";
	public static final String CREATE_STUDENT_ENDPOINT = "/api/services/app/Student/Create";
	public static final String UPDATE_STUDENT_ENDPOINT = "/api/services/app/Student/Update";
	public static final String DELETE_STUDENT_ENDPOINT = "/api/services/app/Student/Delete";
	public static final String ADD_STUDENT_TO_CLASS_ENDPOINT = "/api/services/app/Class/AddStudent";
	public static final String REMOVE_STUDENT_FROM_CLASS_ENDPOINT = "/api/services/app/Class/RemoveStudent";
	public static final String GET_CLASS_ROSTER_ENDPOINT = "/api/services/app/Class/GetStudents";

	public static final String GET_ALL_CLASSES_ENDPOINT = "/api/services/app/Class/GetAll";
	public static final String GET_ONE_CLASS_ENDPOINT = "/api/services/app/Class/Get/{Id}";
	public static final String CREATE_ONE_CLASS_ENDPOINT = "/api/services/app/Class/Create";
}
