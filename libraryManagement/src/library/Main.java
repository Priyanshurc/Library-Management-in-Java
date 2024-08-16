package library;
import java.util.*;

public class Main {
	static Scanner inp;
	static Database database;
	public static void main(String[] args) {
		database = new Database();
		System.out.println("Welcome to Library Management System!");
		
		int num;
		//do {

			System.out.println(
					"0. Exit\n"
					+ "1. Login\n"
					+ "2. New User");
			inp = new Scanner (System.in);
			num = inp.nextInt();
			
			switch(num) {
				case 1: login();
				case 2: newUser(); 
			}
	//	}while(num != 0);
	}
	
	private static void login() {
		System.out.println("Enter phone number: ");
		String phonenumber = inp.next();
		System.out.println("Enter Email: ");
		String email = inp.next();
		int n = database.login(phonenumber, email);
		if( n != -1) {
			User user = database.getUser(n); 
			user.menu(database,user);
		} else {

			System.out.println("User dosen't exist!");
		}
	}
	
	private static void newUser() {
		System.out.println("Enter Name: ");
		String name = inp.next();
		System.out.println("Enter Phone number: ");
		String phonenumber = inp.next();
		System.out.println("Enter Email: ");
		String email = inp.next();
		System.out.println("1. Admin\n 2.Normal User ");
		int n2 = inp.nextInt();
		User user;
		if(n2 == 1) {
			user = new Admin(name , email , phonenumber);
		}else {
			user = new NormalUser(name , email , phonenumber);
		}

		database.AddUser(user);
		user.menu(database,user);
		
		//System.out.println("User Created Successfully!");
	} 

}
