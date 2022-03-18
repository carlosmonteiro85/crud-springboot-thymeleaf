package br.com.inicial.crud_sprinboot_thymeleaf.model;

public class EndPoints {
	
	public static String ALL_USERS = "/user";
	public static String USER_STATUS_FALSE= "/user/false";
	public static String USER_STATUS_TRUE= "/user/true";
	public static String USER_ROLE_ADM = "/user/all/"+Categoria.ADM.toString();
	public static String USER_ROLE_DEFAULT = "/user/all/"+Categoria.DEFAULT.toString();
	public static String ALL_DELECTED = "/user/delected";
	public static String ALL_ACTIVED = "/user/*";
	public static String USER_ID = "user/*";
}
