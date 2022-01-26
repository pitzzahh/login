import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class EnrollmentSystem {
		
	   static int units = 0 ;
	   static int rate = 0 ;
	   static int athFee = 0 ;
	   static int libFee = 0 ;
	   static int labFee = 0 ; 
	   static int idFee = 0;
	   static int uniform = 0;
	   static int others = 0; 
	   static int tuition = 0 ;
	   static int mscFee = 0 ;
	   static int cash = 0;
	   static int total = 0;
	   
	   static String courseName = "";
	   static String studName = "";
	   static String section = "";
	   static String schedule = "";
	   static String studLRN = "";
	   static String specializationVal = null;
	   
	   static boolean inputChckr = true; 
	   static boolean mop = true ;
	   
	   static double discount = 0 ;
	   static double interest = 0 ;
	   static double downPayment = 0d ;
	   static double balance = 0 ;
	   static double totalAss = 0 ;
	   static double change = 0;
	   static char paymentMode = ' ';
	   static char specialize = ' ';
	   static float gwa = 0;
	   static int yearLevel = 1 ;
	   
  public static void main(String [] args) throws IOException, InterruptedException{
	  
	  Scanner sc = new Scanner (System.in); 
	  Date dateEnrolled = new Date();
	  SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	  
	  createLines();
		 
	  System.out.println("\n================================|WELCOME TO HELL UNIVERSITY|================================");
	  
	  createLines();
	  
	  boolean wholeCond = true;
	  
	  while (wholeCond) {
		  
		  boolean adminUser = false;
		  boolean studentUser = false;
		  boolean facultyCond = false;
		  boolean formCond = false;
		  boolean studentInfoCond = false;
		  boolean menuCond = true;
		  boolean listEnrolled = false;
		  boolean validationCond = false;
		  char choices;
		  
		  
		  System.out.println("\n\nWHO IS THE USER?");
		  System.out.println("\n[A] STUDENT \n[B] ADMIN");
		  System.out.print(": ");
		  choices = sc.next().charAt(0);
		  sc.nextLine();
		  
		  
		  while(menuCond) {
			  
			  switch (choices) {
			  
			  case 'A' : case 'a':
				  studentUser = true;
				  menuCond = false;
				  break;
				  
			  case 'B' : case 'b':
				  adminUser = true;
				  menuCond = false;
				  break;
				  default:
					  System.out.println("invalid choice, choose again!");
					  menuCond = false;
			  }
		  }
		  menuCond = true;
		  
		  
		  
		  while(studentUser) {
			  
			  createLines();
			  
			  System.out.println("\n\t\t\t    |HELL UNIVERSITY's STUDENTS MAIN MENU|");
			  
			  createLines();
			  
			  while(menuCond) {
			  
			  System.out.println("\n\nChoose System Task");
			 
			  System.out.println("\n[A] Create Enrollment Form  \n[B] View Your Information \n[C] Back to User's Menu");
			  System.out.print(": ");
			  choices = sc.next().charAt(0);
			  sc.nextLine();
			  
			  
				  
				  switch (choices) {
				  
				  case 'A' : case 'a':
					  formCond = true;
					  menuCond = false;
					  break;
				  case 'B' : case 'b':
					  studentInfoCond = true;
					  menuCond = false;
					  break;
				  case 'C' : case 'c':
					  studentUser = false;
					  menuCond = false;
					  createLines();
					  break;
					  default:
						  
						  System.out.println("invalid choice, choose again!");
						  
				  }
				  
			  }
			  menuCond = true;
			  

			  while(formCond) { // for another enrolls
				   
				  createLines();
				  
				  System.out.println("\n\t\t\t\t     | ENROLLMENT FORM |");
			   
				  createLines();
				  
				  char createForm ;
				  
				  File availability = new File("school files\\available slots\\slots.txt");
				  Scanner content = new Scanner(availability);
				  
				  int numStud = content.nextInt();
				  
				  System.out.println("\nAvailable Slots: [ '" + numStud + "' ]");
				  
				  if(numStud == 0) {
			    		
					  System.out.println("sorry, the system has reached the maximum student that can enroll.");
					  formCond = false;
			    		
				  }
				  
					  	double gwa = 0.0d;
					 	int firstQuar;
					 	int secQuar;
					 	int thirdQuar;
					 	int fourthQuar;
					  
					  String studName, email, mother, father, guardian, 
				  		 address, birthDay = null, birthMonth = null, birthYear = null, studentContact = null,
				  		 motherContact = null, fatherContact = null, guardianContact = null,
				  		 genderVal = null;

					  char gender, course;  
					  
					  boolean yearChckr = true ;
					  
					  
					  while (inputChckr) {
						  
						  System.out.print("\n\nStudent No.: ");
						  studLRN = sc.nextLine();
						  
						  
						  if (studLRN.matches("[0-9]+") && ((studLRN.length() == 12))) {
							  
							  inputChckr = false;
							  
						  }
						  else {
							  
							  System.out.println("invalid! check if you completely type 12 digits");
						 
						  }
						  
					  }
					  inputChckr = true;
					  
					  
					  System.out.print("Student Name: ");
					  studName = sc.nextLine();
					  
			 		 	System.out.println("\nAvailable Courses:");
			 		 	viewCourseChoices();
			   
			   
			 		 	while(inputChckr) {
			   
			 		 		System.out.println("\nChoose your Course ");
			 		 		System.out.print("Course: ");
			 		 		course = sc.next() .charAt(0);
			 
			 		 		assignValue(course);
			 		 		
			 		 	}
			 		 	inputChckr = true;
			 		 	
			 		 	System.out.println("");
			 		 	
			 		 	boolean q = true;
			 		 	boolean r = false;
			 		 	
			 		 	System.out.println("Choose your specialization");
			 		 	viewSpecialization(courseName, q, r);
			 		 	q = false;
			 		 	 	
			 		 	while(inputChckr) {
			 		 		
			 		 		
			 		 		System.out.print("\nSpecialization: ");
			 		 		specialize = sc.next() .charAt(0);
				 		 	sc.nextLine();
				 		 	
				 		 	switch (specialize) {
				 		 	case 'A' : case 'a':
				 		 		inputChckr = false;
				 		 		break;
				 		 	case 'B' : case 'b':
				 		 		inputChckr = false;
				 		 		break;
				 		 	case 'C' : case 'c':
				 		 		inputChckr = false;
				 		 		break;
				 		 	case 'D' : case 'd':
				 		 		inputChckr = false;
				 		 		break;	
				 		 	default:
				 		 		System.out.println("invalid choice, choose again! \n");
				 		 	
				 		 	}
			 		 		
			 		 	}
			 		 	inputChckr = true;
			 		 	
			 		 	r = true;
			 		 	
			 		 	viewSpecialization(courseName, q, r);
			 		 	
			 		 	System.out.println("\nYear Level: ");
		 		 		System.out.println(" [1]");
		 		 		System.out.println(" [2]");
		 		 		System.out.println(" [3]");
		 		 		System.out.println(" [4]");
		 		 		System.out.print(": ");
			 		 	
			 		 	while (yearChckr) {
			 		 		
			 		 		while (inputChckr) {
			 		 			
			 		 			try {
				 		 			
				 		 			yearLevel = sc.nextInt();
				 		 			
				 		 			inputChckr = false;
				 		 		} catch(InputMismatchException e) {
				 		 			
				 		 			System.out.println("\nyou can't type letter here, try again!");
				 		 			System.out.print(": ");
				 		 			sc.nextLine();
				 		 		}
			 		 			
			 		 			
			 		 		}
			 		 		inputChckr = true;
			 		 		
			 		 		
			 		 		System.out.println("\nAre you Transferee or Not?");
			 		 		char studApply = ' ';
			 		 		
			 		 		while( inputChckr) {
			 		 			
			 		 			System.out.println("[A] Transferee \n[B] Not");
			 		 			System.out.print(":");
				 		 		studApply = sc.next().charAt(0);
				 		 		
				 		 		switch(studApply) {
				 		 		case 'A' : case 'a':
				 		 			 inputChckr = false;
				 		 			 break;
				 		 		case 'B' : case 'b':
				 		 			inputChckr = false;
				 		 			break;
				 		 			default:
				 		 				System.out.println("invalid choice, choose again!\n");
				 		 		}
			 		 			
			 		 		}
			 		 		inputChckr = true;
			 		 		
			 		 		sc.nextLine();
			 		 		
			 		 		viewPrerequesites(yearLevel, studApply);
			 		 		
			 		 		if (yearLevel <= 4) {
			 	 		
			 		 			if(yearLevel >= 1) {
			 		 				
			 		 				yearChckr = false;
			 		 				
			 		 			}
			 		 			else {
			 		 				
			 		 				System.out.println("invalid year level, please choose again. \n");
			 		 				System.out.print(": ");
			 		 			
			 		 			}
			 		 			
			 		 		}
			 		 		else {
			 		 			
			 		 			System.out.println("invalid year level, please choose again. \n");
			 		 			System.out.print(": ");
			 		 		
			 		 		}
			 		 		
			 		 	}
			 	
			 		 	
			 		 	if(yearLevel == 1) {
			 		
			 		 		System.out.println("Enter your quarterly average last school year");
			 	 	
			 		 		System.out.print("First Quarter: ");
			 		 		firstQuar = sc.nextInt();
			 		 		sc.nextLine();
			 	 	
			 		 		System.out.print("Second Quarter: ");
			 		 		secQuar = sc.nextInt();
			 		 		sc.nextLine();
			 	 	
			 		 		System.out.print("Third Quarter: ");
			 		 		thirdQuar = sc.nextInt();
			 		 		sc.nextLine();
			 		 		
			 		 		System.out.print("Fourth Quarter: ");
			 		 		fourthQuar = sc.nextInt();	 		
			 		 		sc.nextLine();
			 		 		
			 		 		gwa = ((firstQuar + secQuar + thirdQuar + fourthQuar) / 4) / 100 * 5;
			 		
			 		 	}
			 		 	else if(yearLevel > 1) {
			 		 		
			 		 		
			 		 		while(inputChckr) {
			 		 			
			 		 			System.out.print("Enter your GWA[3.75/5.00]: ");
			 	 		 		gwa = sc.nextDouble();
			 	 		 		sc.nextLine();
			 	 		 		if(gwa <= 5.00) {
			 	 		 			
			 	 		 			if (gwa >= 3.75 ) {
			 	 		 				
			 	 		 				inputChckr = false;
			 	 		 				
			 	 		 			}
			 	 		 			else {
			 	 		 				
			 	 		 				System.out.println("\nsorry, your grade does not meet the required gwa. \n");
			 	 		 				
			 	 		 			}
			 	 		 			
			 	 		 		}
			 	 		 		else {
				 		 				System.out.println("\nsorry, your grade does not meet the required gwa.\n ");
				 		 		}
			 		 		}
			 		 		
			 		 	}
			 		 	inputChckr = true;
			 		 	
			 		 	assignSectionSchedule(courseName, yearLevel, gwa);
			 		 	
			 	createLines();
			   
			 	System.out.print("\n\nAddress: ");
				address = sc.nextLine();
				
				
					while (inputChckr) {
						
						System.out.println("\nBirthday ");
					 	System.out.print("Month: ");
						birthMonth = sc.nextLine();
						
						System.out.print("Day: ");
					 	birthDay = sc.nextLine();
					 	
					 	System.out.print("Year: ");
					 	birthYear = sc.nextLine();
					 	
					 	if (birthMonth.matches("[0-9]+")&& birthDay.matches("[0-9]+") && birthYear.matches("[0-9]+") ) {
					 		
					 		inputChckr = false;
					 		
					 	}
					 	else {
					 		
					 		System.out.println("invalid format, use number");
					 		
					 	}
						
					}
					inputChckr = true;
			 	
			   
			 		 while(inputChckr) {
			   
			 			 System.out.print("\nGender(M/F): ");
			 			 gender = sc.next() .charAt(0);
			 			 sc.nextLine();
			   
			 			 if (gender == 'M' || gender == 'm' ){
			     
			 				 genderVal = "Male" ;
			 				 inputChckr = false;
			 			 }
			 			 else if (gender == 'F' || gender == 'f' ){
			     
			 				 genderVal = "Female" ;
			 				 inputChckr = false;
			 			 }
			 			 else {   
			 				 System.out.println("invalid choice, choose again!");
			 			 }
			    
			 		 }
			 		 inputChckr = true;
			 		 
			   while(inputChckr) {
				   
				   System.out.print("Contact: ");
				   studentContact = sc.nextLine();
				   
				   if(studentContact.matches("[0-9]+") && studentContact.length() == 11) {
					   inputChckr = false;
				   }
				   else {
					   System.out.println("invalid! please check your mobile numbers length (09*********)");
				   }
			   }
			   inputChckr = true;
			   
			   
			   System.out.print("Email Address: ");
			   email = sc.nextLine();
			   
			   System.out.print("Mother's Name: ");
			   mother = sc.nextLine();
			   
			   while(inputChckr) {
				   
				   System.out.print("Contact: ");
				   motherContact = sc.nextLine();
				   
				   if(motherContact.matches("[0-9]+") && motherContact.length() == 11) {
					
					   inputChckr = false;
				   
				   }
				   else {
					   
					   System.out.println("invalid! please check if you entered 11 digits or you inputted characters");
				   
				   }
				   
			   }
			   inputChckr = true;
			   
			   System.out.print("Father's Name: ");
			   father = sc.nextLine();
			   
			   while(inputChckr) {
				   
				   System.out.print("Contact: ");
				   fatherContact = sc.nextLine();
				   
				   if(fatherContact.matches("[0-9]+") && fatherContact.length() == 11) {
					   inputChckr = false;
				   }
				   else {
					   System.out.println("invalid! please check if you entered 11 digits or you inputted characters");
				   }
			   }
			   inputChckr = true;
			   
			   System.out.print("Guardian Name: ");
			   guardian = sc.nextLine();
			   
			   while(inputChckr) {
				   
				   System.out.print("Contact: ");
				   guardianContact = sc.nextLine();
				   
				   if(guardianContact.matches("[0-9]+") && guardianContact.length() == 11) {
					   inputChckr = false;
				   }
				   else {
					   System.out.println("invalid! please check if you entered 11 digits or you inputted characters");
				   }
			   }
			   inputChckr = true;
			   
			   viewAssessment(); //a method created to print the assessment of the student
			   
			   createLines();
			   	
			   		while(mop){
			   			
			   			System.out.println("\n\nChoose your payment method:");
			   			System.out.println("[A] - Cash");
			   			System.out.println("[B] - Installment");
			   			
			   			System.out.print("Payment: ");
			   			paymentMode = sc.next().charAt(0);
			   			
			   			if (paymentMode == 'A' || paymentMode == 'a') {
			   				
			   				System.out.println("\nEnter your cash");
			   	   			
			   				System.out.print("Cash: ");
			   	   			cash = sc.nextInt();
			   	   			sc.nextLine();
			   	   			
			   	   			totalAss = tuition - discount + mscFee ;
			   	   			
			   	   		if (cash < totalAss) {
		   	   				
		   	   				System.out.println("your cash is less than total assessment");
		   	   				
		   	   			}
		   	   			else {
		   	   				
		   	   				processPayment(cash);
		   	   			
		   	   			}
			   	   		
			   			}
			   			
			   			else {
			   				
			   				downPayment = tuition * .5 ;
			   				System.out.println("\ndownpayment [" + downPayment + "]");
			   				System.out.println("Enter your cash");
			   				
			   				System.out.print("Cash: ");
			   	   			cash = sc.nextInt();
			   	   			sc.nextLine();
			   	   			
			   	   			if (cash < downPayment) {
			   	   				
			   	   				System.out.println("your cash is less than downpayment required!");
			   	   				
			   	   			}
			   	   			else {
			   	   				
			   	   				processPayment(cash);
			   	   			
			   	   			}
			   			
			   			}
			   			
			   		}
			   	
			   		createLines();
			   		
			   		System.out.println("\n\t\t\t\t  |Student's Receipt|");
			   		
			   		createLines();
			   	
			   	System.out.println("\n\nTotal assessment: [" + totalAss + "]");
			    System.out.println("\nDiscount(10%): " + discount);
			    System.out.println("Interest(5%): " + interest);
			    System.out.println("Downpayment: " + downPayment);
			    System.out.println("Cash Tendered: [" + cash + "] | Change: [" + change + "]");
			    System.out.println("Balance: " + balance + "\n");
			    	
			    createLines();
			    
			    boolean t = true;
			    
			    File studentInfo = new File("school files\\student informations\\" + courseName + "\\YearLevel\\" + yearLevel + "\\Sections\\" + section + "\\" + studName + ".txt");

			    if(!studentInfo.exists()) {
			    
					studentInfo.createNewFile();
					
					write("Date Enrolled: [" + dateFormat.format(dateEnrolled) + "]\n", studentInfo,t);
					write("[STUDENT PERSONAL INFORMATION]", studentInfo, t);
				    write("Name: " + studName, studentInfo, t);
				    write("LRN: " + studLRN, studentInfo, t);
				    write("Gender: " + genderVal, studentInfo, t);
				    write("Birthday: " + birthMonth + "/" + birthDay + "/" + birthYear , studentInfo, t);
				    write("Contact: " + studentContact, studentInfo, t);
				    write("Email: " + email, studentInfo, t);
				    write("Address: " + address + "\n", studentInfo, t);
				    
				    write("[CLASS INFORMATION]", studentInfo, t);
				    write("Grade inputted in enrollment form: " + gwa, studentInfo, t);
				    write("Course: " + courseName, studentInfo, t);
				    write("Specialization: " + specializationVal,studentInfo, t);
				    write("Section: " + section, studentInfo, t);
				    write("Schedule: " + schedule + "\n", studentInfo, t);
				    
				    write("[STUDENT REMAINING BALANCE]", studentInfo, t);
				    write("Balance: " + balance + "\n", studentInfo, t);
				    
				    write("[GUARDIAN AND PARENTS CONTACT]", studentInfo, t);
				    write("Mother: " + mother, studentInfo, t);
				    write("Contact: " + motherContact, studentInfo, t);
				    write("Father: " + father, studentInfo, t);
				    write("Contact: " + fatherContact, studentInfo, t);
				    write("Guardian: " + guardian, studentInfo, t);
				    write("Contact: " + guardianContact, studentInfo, t);
				    
				    System.out.println("\n\nCreate your account to view your information when you need.");
				    System.out.println("\nCreate username");
				    System.out.print("username: ");
				    String username = sc.nextLine();
				    
				    Random pin = new Random();
				    String pinCode = "";
					
				    for (int x = 1 ; x <= 4 ; x++) {
				    	
				    	int a = pin.nextInt(9) + 1;
				    	pinCode += String.valueOf(a);
				    	
				    }
				    
				    System.out.println("[" + pinCode + "] is your pincode, do not share to others");
				    
				    String accessPath = studentInfo.getPath();
				    
				    File newAccountFolder = new File ("accounts\\" + studName);
				    newAccountFolder.mkdirs();
				    
				    File newUsername = new File ("accounts\\" + studName + "\\username.txt");
				    File newPassword = new File ("accounts\\" + studName + "\\password.txt");
				    File newPath = new File ("accounts\\" + studName + "\\access path.txt");
				    File newLoginAttempt = new File ("accounts\\" + studName + "\\login attempt.txt");
				    
				    write(username, newUsername, t);
				    write(pinCode, newPassword, t);
				    write(accessPath, newPath, t);
				    write("3", newLoginAttempt, t);
				    
				    String statusVal = "PENDING";
				    File status = new File("accounts\\" + studName + "\\status.txt");
				    write(statusVal, status, true);
				    
				    System.out.println("\nNOTE! Your enrollment form has been saved to our system, but your \nstatus is still pending."
				    		+ " The admin will set your status to approved \nwhen they validated the prerequisites you hand over to them."
				    		+ " You can \ncheck your status on the student information system.");
				    
				}
			    else {
			    	
			    	System.out.println("\nthis student already enrolled\n");
			    	
			    }
			    
			    numStud += -1; // this will update the availability of slots in enrollment system 
			    t = false; // this is set to false to avoid the text append to the file in number of availability
			    
			    write(String.valueOf(numStud), availability, t); // this will save the availability to a file
			    
			    System.out.println("\n\n[A] Create Another Enrollment Form \n[B] Back to Student's Main Menu \n[C] Back to User's Menu ");	// this will ask the user if 
			    System.out.print(": ");
			    createForm = sc.next().charAt(0);														// they want to make another enrollment form											
			    sc.nextLine();																			// or go back to the main menu of the program
			   
			    
			    while (menuCond) {
			    	
			    	if ((createForm == 'A' || createForm == 'a') && (numStud != 0)) {
				    	
				    	System.out.print("creating new enrollment form");
				    	pause();
				    	menuCond = false;
				    	
				    }
				    else if(createForm == 'B' || createForm == 'b') {
				    	
				    	formCond = false;
				    	menuCond = false;
				    }
				    else if(createForm == 'C' || createForm == 'c') {
				    	
				    	formCond = false;
				    	studentUser = false;
				    	menuCond = false;
				    	
				    }
				    else {
				    	
				    	System.out.println("\ninvalid choice, choose again!\n");
				    	
				    }
			    	
			    	
			    }
			    menuCond = true;
			 
			    reset();
			    
			    content.close(); 
			    
			  }
			  
			  
			  
			  while (studentInfoCond) {
				  
				  	boolean existenceChck = true;
					boolean accountLogin = false;
					boolean access = false;
					String name = "";
					String path = "";
					char confirmation;
					
					createLines();
					
					System.out.println("\n\t\t\t\t|STUDENT INFORMATION SYSTEM|");
					
					createLines();
					
					while(existenceChck) {
						
						System.out.println("\nEnter your complete name");
						System.out.print("Name: ");
						name = sc.nextLine();
						
						File account = new File("accounts//" + name );
						
						if (account.exists()) {
							
							existenceChck = false;
							accountLogin = true;
						
						}
						else {
							System.out.println("\nstudent does not exist!\n");
							
							System.out.println("[A] Student's Menu \n[B] Retry");
							System.out.print(": ");
							confirmation = sc.next().charAt(0);
							sc.nextLine();
							
							if (confirmation == 'A' || confirmation == 'a') {
								
								studentInfoCond = false;
								existenceChck = false;
								
							}
							else if (confirmation == 'B' || confirmation == 'b') {
								
								System.out.print("retrying");
								pause();
								
							}
						}
					}
					
					//login attempt
					// payment cash bugs
					
					while (accountLogin) {
						
						try { 
							
							File updateAttempt = new File("accounts\\" + name + "\\login attempt.txt");
							
							Scanner updaterAtt = new Scanner(updateAttempt);
							int count = updaterAtt.nextInt();
							
							updaterAtt.close();
							
							System.out.println("\nLogin your account  || Login Attempts [ " + count +" ]");
							
							if (count == 0) {
								
								System.out.println("\nWARNING!!! This account has reached the maximum login attempt. \n"
												  + "The system thinks that this account does not belong to you. If this account belongs to you,\n"
												  + "you can talk to the admin, bring your ID and request a new pincode.\n");
								
								System.out.print("Proceeding to Student's Menu");
								pause();
								
								accountLogin = false;
								
							}
							else {
								
								System.out.print("username: ");
								String username = sc.nextLine();
								
								System.out.print("password: ");
								String password = sc.nextLine();
								
								File userN = new File ("accounts\\" + name + "\\username.txt");
								File userP = new File ("accounts\\" + name + "\\password.txt");
								
								Scanner validatingUsername = new Scanner(userN);
								Scanner validatingPass = new Scanner(userP);
								
								String UserN_Chck =  validatingUsername.nextLine();
								String UserP_Chck =  validatingPass.nextLine();
						
								validatingUsername.close();
								validatingPass.close();
								
								if (username.equals(UserN_Chck) && password.equals(UserP_Chck)) {
									
									File studentInfo = new File ("accounts\\" + name + "\\access path.txt");
									
									Scanner accessInfo = new Scanner(studentInfo);
									
									path = accessInfo.nextLine();
									accessInfo.close();
									
									accountLogin = false;
									studentInfoCond = false;
									access = true;
									
								}
								else {
								
									System.out.println("\nincorrect username or password\n");
						
									System.out.println("[A] Retry \n[B] Back to Student's Menu");
									System.out.print(": ");
									confirmation = sc.next().charAt(0);
									sc.nextLine();
									
									if (confirmation == 'A' || confirmation == 'a') {
										
										count += - 1;
										
										write(String.valueOf(count), updateAttempt, false);
										System.out.print("\nretrying");
										pause();
										
									}
									else if (confirmation == 'B' || confirmation == 'b') {
										
										studentInfoCond = false;
										existenceChck = false;
										accountLogin = false;
										
									}
								
								}
								
							}
							
							
						} catch(IOException e) {
							
							System.out.println("\ncan't find some of files needed, can't proceed\n");
							
							System.out.println("[A] Retry \n[B] Student's Main Menu");
							confirmation = sc.next().charAt(0);
							sc.nextLine();
							
							if (confirmation == 'A' || confirmation == 'a') {
							
								System.out.print("\nretrying");
								pause();
														
							}
							else if (confirmation == 'B' || confirmation == 'b') {
								
								studentInfoCond = false;
								existenceChck = false;
								accountLogin = false;
								
							}
						}
					}
						
					
					while (access) {
						
						File updateAttempt = new File("accounts\\" + name + "\\login attempt.txt");
						write("10", updateAttempt, false);
						
						File studentInfo = new File(path);
						File status = new File("accounts//" + name + "//status.txt");
						Scanner viewInfo = new Scanner(studentInfo);
						Scanner viewStatus = new Scanner(status);
						
						createLines();
						System.out.print("\n Status: ");
						while (viewStatus.hasNextLine()) {
							
							System.out.println(viewStatus.nextLine());
							infoPause();
							
						}
						
						while (viewInfo.hasNextLine()) {
							
							System.out.println(viewInfo.nextLine());
							infoPause();
							
						}
						System.out.println("\n");
						createLines();
						
						System.out.println("\n[A] View Another Information \n[B] Back to Student's Menu \n[C] Back to User's Menu ");
						System.out.print(": ");
						confirmation = sc.next().charAt(0);
						sc.nextLine();
						
						if (confirmation == 'A' || confirmation == 'a') {
							
							studentInfoCond = true;
							accountLogin = false;
							
						}
						else if (confirmation == 'B' || confirmation == 'b') {
							
							studentInfoCond = false;
							
						}
						else if (confirmation == 'C' || confirmation == 'c' ) {
							
							studentInfoCond = false;
							studentUser = false;
							createLines();
						}
						
						viewInfo.close();
						viewStatus.close();
						access = false;
					}
					
				}
			  
			  
		  } //end of student user bracket
		  
		  
		  
		  boolean credentialCond = true;
		  menuCond = false;
		  
		  while(adminUser) {
			  
			  createLines();
			  
			  System.out.println("\n\t\t\t    |HELL UNIVERSITY's ADMIN's MAIN MENU|");
			  
			  createLines();
			  
			  String username, password;
			  
			  while(credentialCond) {
				  
				  System.out.println("\n\nLogin Admin's Account");
				  System.out.print("username: ");
				  
				  username = sc.nextLine();
				  
				  System.out.print("password: ");
				  password = sc.nextLine();
				  
				  if (username.equals("admin") && password.equals("admin")) {
					  
					  System.out.print("\nlogin check");
					  pause();
					  System.out.println("\nAccess granted!");
					  credentialCond = false;
					  menuCond = true;
				  }
				  else {
					  
					  System.out.println("\nincorrect username or password!\n");
					  
					  System.out.println("[A] Try Again  ||  [B] Back to User's Menu");
					  System.out.print(": ");
					  char selection = sc.next().charAt(0);
					  sc.nextLine();
					  
					  menuCond = true;
					  
					  while(menuCond) {
					  
						  switch (selection) {
						  
						  case 'A' : case 'a':
							  
							  System.out.print("\nretrying");
							  pause();
							  menuCond = false;
							  
							  break;
						  case 'B' : case 'b':
							  menuCond = false;
							  adminUser = false;
							  credentialCond = false;
							  createLines();
						  }
					  }
				  }  
			  }
			  
			  while(menuCond) {
				  
				  System.out.println("\nChoose System Task\n");
				  System.out.println("[A] View Faculty Information \n[B] View Enrolled/Generate New Pincode and Unenroll Students \n[C] Validate Students Enrollment \n[D] Back to User's Menu");
				  System.out.print(": ");
				  choices = sc.next().charAt(0);
				  sc.nextLine();
				  
				  switch (choices) {
				  
				  case 'A' : case 'a':
					  facultyCond = true;
					  menuCond = false;
					  break;
					  
				  case 'B' : case 'b':
					  listEnrolled = true;
					  menuCond = false;
					  break;
				  case 'C' : case 'c':
					  
					  menuCond = false;
					  validationCond = true;
					  
					  break;
				  case 'D' : case 'd':
					  adminUser = false;
					  menuCond = false;
					  createLines();
					  break;
					  default:
						  
						  System.out.println("invalid choice, choose again!");
				  
				  }
			  }
			  menuCond = true;
			  
			  
			  while (facultyCond) {
				  
				 
				  System.out.println("\n\t\t\t\t    |FACULTY INFORMATION|");
				  
				  FacultyInformation();
				  
				  char option;
				  System.out.println("");
				  createLines();
				  
				  menuCond = true;
				  while (menuCond) {
					  
					  System.out.println("\n[A] Back to Admin's Menu");
					  System.out.print(": ");
					  option = sc.next().charAt(0);
					  sc.nextLine();
					  
					  switch(option) {
					  
					  case 'A': case 'a':
						  facultyCond = false;
						  menuCond = false;
						  break;
						  
						  default:
							  System.out.println("\ninvalid choice, choose again!\n"); 
					  }		
					  
					  System.out.println("");
				  }
			  }
			  menuCond = true;
			  
			  
			  
			  
			  while(listEnrolled) {
				  
				  char decision = ' ';
				  boolean viewListCond = true;
				  int selection;
				  
				  System.out.println("\n\t\t\t\t|LIST OF ENROLLED STUDENT|");
				  
				  while(viewListCond) {
					  
					  File list = new File("accounts");
					  
					  try {
						  
						  String [] studentFiles = list.list();
						  
						  if (studentFiles.length == 0) {
							  
							  System.out.println("There's no student enrolled\n");
							  System.out.print("Proceeding to Admin's Menu");
							  pause();
							  viewListCond = false;
							  listEnrolled = false;
							  
						  }
						  
						   System.out.println();
						   viewStudentStatus();
						 
						  if (studentFiles.length != 0) {
							  
							  System.out.println("\n[A] Unenroll Student \n[B] Generate New Password for Student \n[C] Admin's Main Menu ");
							  System.out.print(": ");
							  decision = sc.next() .charAt(0);
							  sc.nextLine();
							  
							  
						  if (decision == 'A' || decision == 'a') {
							  
						  
						  try {
							  
							  System.out.println("\nSelect student");
							  System.out.println();
							  viewStudentStatus();
							  
							  System.out.print(": ");
							  selection = sc.nextInt();
							  sc.nextLine();
							
							  System.out.println("\ndo you really want to unenroll [ " + studentFiles[selection - 1] + " ] ?");
							  System.out.println("[A] Confirm  ||  [B] Cancel ");
							  char confirmation = sc.next().charAt(0);
							  
							  if (confirmation == 'A' || confirmation == 'a') {
								  
								  File infoDeletion = new File ("accounts//" + studentFiles[selection - 1] + "//access path.txt");
								  File accDeletion = new File ("accounts//" +  studentFiles[selection - 1]);
								  
								  if (infoDeletion.length() != 0) {
									  
									  Scanner deleteInfo = new Scanner(infoDeletion);
									  
									  String path = deleteInfo.nextLine();
										  
									  deleteInfo.close();
										  
									  File studentInfo = new File(path);
									  studentInfo.delete();
									  
								  }
								  
								  if (accDeletion.isDirectory()) {
									  
									  deleteDirectory(accDeletion);
									  
								  }
								  else {
									  accDeletion.delete();
								  }
								  
								  
								  if (!accDeletion.exists()) {
								  		
								  		System.out.println("\nunenroll succeed!\n");

								  		File availabilityUpdate = new File ("school files//available slots//slots.txt");
								  		
										  Scanner read = new Scanner(availabilityUpdate);
										   
										   int update = read.nextInt();
										   
										   read.close();
										   
										   if (update != 200) {
											   
											   update++;
											   
											   write(String.valueOf(update), availabilityUpdate, false);
											   
										   }

								  	}
								  	else {
								  		
								  		System.out.println("\nunenroll failed!\n");
								  		
								  	} 
								  
							  }
							  else if(confirmation == 'B' || confirmation == 'b') {
								  
								  System.out.println("\nunenroll canceled!");
								  
							  }
							  else {
								  
								  System.out.println("\ninvalid choice!");
								  
							  }
							  
						  
						  } catch(InputMismatchException e) {
							  
							  System.out.println("you can't input character here!");
							  sc.nextLine();
							  
						  }
					  }
						 
					  else if (decision == 'B' || decision == 'b') {
						  
						  System.out.println("\nSelect student");
						  System.out.println();
						  viewStudentStatus();
						  
						  try {
							  
							  System.out.print(": ");
							  selection = sc.nextInt();
							  sc.nextLine();
							    
						  File changePincode = new File ("accounts//" + studentFiles[selection - 1] + "//password.txt");
						  File updateAttempt = new File("accounts//" + studentFiles[selection - 1] + "//login attempt.txt");
						  String newPincode = "";
						  boolean t = false;
						  
						  Random pinGen = new Random();
						  
						  for (int b = 0 ; b < 4 ; b++) {
							  
							  int x = pinGen.nextInt(9) + 0;
							  
							  newPincode += String.valueOf(x);
							    
						  }
						  	System.out.println("[" + newPincode + "] is the new pincode of " + studentFiles[selection - 1]);
						  	
						    write(newPincode, changePincode, t);
						    write("10", updateAttempt, t);
						  } catch(InputMismatchException e) {
							  
							  System.out.println("\nyou can't input character here!\n");
							  sc.nextLine();
						  }
						  
					  }
					  
					  else if (decision == 'C' || decision == 'c') {
						  
						 listEnrolled = false;
						 viewListCond = false;
						  
					  }
				  
				  }
			 }
					   catch (ArrayIndexOutOfBoundsException e) {
					  
					   System.out.println("invalid choice!");
					   
					   }
					     
				  }
				  
			  }
			  
			  
			   while(validationCond) {
				   
				   File list = new File("accounts");
				   String proving = "APPROVED";
				   try {
					   
					   String [] studList = list.list();
					   
					   int selection;
					   char choice;
					   
					   System.out.println("\n\t\t\t\t    | STUDENT VALIDATION |");
					   
					   if (studList.length == 0) {
						   
						   System.out.println("\nThere's no student Enrolled!\n");
						   System.out.print("Proceeding to Admin's Menu");
						   pause();
						   validationCond = false;
						   
					   }
					   else {
						   System.out.println();
						   viewStudentStatus();
						   
						   try {
							   
							   System.out.print(": ");
							   selection = sc.nextInt();
							   sc.nextLine();					   
							   
							   File updateStatus = new File ("accounts//" + studList[selection - 1] + "//status.txt");
							   
							   System.out.print("\nvalidating");
							   pause();
							   
							   write(proving, updateStatus, false);
							   
							   System.out.println("\n[ " + studList[selection - 1] + " ] validated successfully!");
							   
							   System.out.println("\n[A] Validate More \n[B] Admin's Menu \n[C] User's Menu");
							   System.out.print(": ");
							   choice = sc.next().charAt(0);
							   sc.nextLine();					   
							   
							   while(inputChckr) {
								   
								   switch(choice) {
								   case 'A' : case 'a':
									   inputChckr = false;
									   break;
								   case 'B' : case 'b':
									   validationCond = false;
									   inputChckr = false;
									   break;
								   case 'C' : case 'c':
									   validationCond = false;
									   adminUser = false;
									   inputChckr = false;
									   createLines();
									   break;
									   default:
										   System.out.println("\ninvalid choice, choose again!\n");
								   }
								   
							   }
							   inputChckr = true;
							   
						   } catch (InputMismatchException e) {
							   System.out.println("\nyou can't type character here!\n");
							   sc.nextLine();
						   }
						   
						   
					   }
					   
				   } catch(ArrayIndexOutOfBoundsException e) {
					  
					   System.out.println("\ninvalid choice, choose again!\n");
					   
				   }
				   
				  
			   }
			   
		  } //admin's end bracket
		  
		  
	  } // whole condition end bracket
	  
	  sc.close();  
  
  }// main method end bracket
	

  public static void pause() throws InterruptedException {
	  
	  for (int a = 1 ; a <= 3 ; a++) {
		  TimeUnit.MILLISECONDS.sleep(500);
		  System.out.print(".");
	  }
	  
	  TimeUnit.MILLISECONDS.sleep(500);
	  System.out.println("");
  
  }
  
  public static void infoPause() throws InterruptedException {
	  	  
	  TimeUnit.MILLISECONDS.sleep(80);

 
  }
  
  public static void viewCourseChoices() {
	  
	  String [] courses = {
			  			   "[A] BSIT", "[B] BSIS", "[C] BSCE",
	  					  }; 
	  
	  for (int a = 0 ; a < courses.length ; a++) {
		  
		  System.out.println(courses[a]);
		  
	  }
	  
  }
  
  public static void viewSpecialization(String course, boolean q, boolean r) {
	  
	  String choice1 = "", choice2 = "", choice3 = "", choice4 = "";
	  
	  switch (course) {
	  
	  case "BSIT":
		  choice1 = "Multimedia";
		  choice2 = "Network & Information Security";
		  choice3 = "Web Application";
		  choice4 = "Game Development";
		  break;
	  case "BSIS":
		  choice1 = "Data Science";
		  choice2 = "Software Engineering";
		  choice3 = "Network Admistration";
		  choice4 = "Data Analytics";
		  break;
	  case "BSCE":
		  choice1 = "Mobile Device Design";
		  choice2 = "Embedded Systems Design";
		  choice3 = "Microprocessor Design";
		  choice4 = "Academic Research";
		  break;
		  
	  }
	  
	  
	  String [] viewSpecialization = {"[A] " + choice1, "[B] " + choice2,
			  				  		  "[C] " + choice3, "[D] " + choice4
			  				 };
	  
	  while(q) {
		  
		  for (int a = 0 ; a < viewSpecialization.length ; a++) {
			  
			  System.out.println(viewSpecialization[a]);
			  
		  }
		  
		  q = false;
	  
	  }
	  
	  while(r) {
		  
		  switch(specialize) {
		  	case 'A' : case 'a':
		  		specializationVal = choice1;
		  	break;
		  	case 'B' : case 'b':
		  		specializationVal = choice2;
		  	break;
		  	case 'C' : case 'c':
		  		specializationVal = choice3;
		  	break;
		  	case 'D' : case 'd':
		  		specializationVal = choice4;
		  	break;
		  
		  }
		  
		  r = false;
	  }
	  
  }
  
  public static void deleteDirectory(File file) {
  	
  	File [] directoryDel = file.listFiles();
  	
  	for (File y : directoryDel) {
  		
  		if (!y.isDirectory()) {
  			y.delete();
  		}
  		
  	}
  	
  	if (directoryDel.length != 0) {
  		
  		for (File x : directoryDel) {
  			
  			if (x.isDirectory()) {
  				
  				deleteDirectory(x);
  				x.delete();
  				
  			}
  			else {
  				
  				x.delete();
  			
  			}	
  			
  		}
  		
  		file.delete();	
  		
  	}
  	else {
  		
  		file.delete();
  		
  	}	
  	
  }
  
  public static void viewStudentStatus() throws FileNotFoundException, InterruptedException {
	  int spaces = 30;
	  
	  File students = new File ("accounts");
	  String [] studList = students.list();
	   
	   for (int a = 0 ; a < studList.length ; a++) {
		   
		   File viewStatus = new File("accounts//" + studList[a] + "//status.txt");
		   
		   Scanner status = new Scanner(viewStatus);
		    
		   System.out.print("[" + (a + 1) + "] " + studList[a]);
		   
		   if (a < 9) {
			   
			   infoPause();
			   for (int c = 0 ; c <= spaces - studList[a].length() ; c++) {
				   
				   System.out.print(" ");
				   
			   }
		   }
		   
		   if (a >= 9 && a <= 98) {
				   
			   for (int c = 0 ; c <= spaces - studList[a].length() - 1 ; c++) {
					   
				   System.out.print(" ");
					   
			   }
				   
		   }
		   
		   if (a >= 99) {
			   
			   for (int c = 0 ; c <= spaces - studList[a].length() - 2 ; c++) {
				   
				   System.out.print(" ");
					   
			   }
			   
		   }
			   
		   while(status.hasNext()) {
			   
			   System.out.println("  | STATUS |  " + status.nextLine());
			   
		   }
		   
		   status.close();
	   }
	  
  }
  
  public static void createLines() {
	  
	  for (int a = 1 ; a <= 92 ; a++) {
		     System.out.print("=");
	  }
	 
  }
  
  public static void write(String writer, File file , boolean t) throws IOException {
	  
	  FileWriter fw = new FileWriter(file, t);
	  PrintWriter pw = new PrintWriter(fw);
	  
	  pw.println(writer);
	  pw.close();
	  
  }
  
  public static void FacultyInformation() throws IOException, InterruptedException {
	 
	  File faculty = new File("school files//faculty information//info.txt");
	  Scanner reader = new Scanner(faculty);
					 
		 while (reader.hasNextLine()) {
			
			 System.out.println(reader.nextLine());
			 infoPause();
		 }

	  reader.close();
 
  }
  
  public static void viewPrerequesites(int yearLevel, char studApply) {
	  
	  boolean a = true;
	  
	  while(a) {
		 
		  
		  switch(studApply) {
		  case 'A' : case 'a':
			  
			  System.out.println("\nPrerequisites:");
			  System.out.println("1. Honorable Dismissal");
			  System.out.println("2. Transcript of Records\n");
			  a = false;
			  break;
		  case 'B' : case 'b':
			  
			  if (yearLevel == 1) {
				  
				  System.out.println("\nPrerequisite:");
				  System.out.println("1. Form 138");
				  System.out.println("2. Form 137");
				  System.out.println("3. 2x2 Picture\n");
				  a = false;
				  
			  }
			  else if (yearLevel >= 2) {
				  
				  System.out.println("\nPrerequisite:");
				  System.out.println("1. Form 138\n");
				  a = false;
				  
			  }
			  break;
		  }
		  
	  }
	  
  }
  
  public static void processPayment(int cashInput) {
	  
	  if (paymentMode == 'A' || paymentMode == 'a') {
		  
		   discount = tuition * .10 ;
		   downPayment = 0 ;
		   cash = cashInput;  
		   change = cash - totalAss ;
		   mop = false;
		   
	   }
  
	   else if(paymentMode == 'B' || paymentMode == 'b') {
	   
		   	discount = 0 ;
	   		interest = tuition * .05 ;
			downPayment = tuition * .5 ;
	   		totalAss = tuition + interest + mscFee ;
	   		cash = cashInput;  
	   		balance = totalAss - downPayment ;
	   		change = cash - downPayment ;
	   		mop = false;
	   }
  
	   else {
		   System.out.println("invalid mode of payment, try again!");
	   }
  }
  
  public static void assignValue(char course) {
	  
	  switch (course){
	     case 'A' : case 'a' : 
	    	 units = 4 ;
	    	 rate = 1250 ;
	    	 athFee = 500 ;
	    	 libFee = 350 ;
	    	 labFee = 850 ;
	    	 idFee = 600 ;
	    	 uniform = 1500 ;
	    	 others = 2500 ;   
	    	 courseName = "BSIT" ;
	    	 inputChckr = false;    	 
	     break;
	     
	     case 'B' : case 'b' :
	    	 units = 4;
	    	 rate = 1110 ;
	    	 athFee = 550 ;
	    	 libFee = 350 ;
	    	 labFee = 950 ;
	    	 idFee = 800 ;
	    	 uniform = 1300 ;
	    	 others = 2500 ;
	    	 courseName = "BSIS" ;
	    	 inputChckr = false;
	     break;
	     
	     case 'C' : case 'c' :
	    	 units = 5 ;
	    	 rate = 1550 ;
	    	 athFee = 500 ;
	    	 libFee = 350 ;
	    	 labFee = 850 ;
	    	 idFee = 500 ;
	    	 uniform = 1400 ;
	    	 others = 2500 ;
	    	 courseName = "BSCE" ;
	    	 inputChckr = false;
	     break;
	     
	     default:
	    	 System.out.println("invalid choice, choose again \n: ");
	   }
	  
  }
 
  
  public static void assignSectionSchedule(String course, int yearLevel, double gwa) {
	  
	  
	  if (course.equals("BSIT")) {
		  
		  
		 
		  switch(yearLevel) {
		  
		  case 1: 
			  if (gwa >= 4.3) {
				  section = "Apple";
				  schedule = "6:00am to 12:00pm"; 
			  }
			  else if (gwa <= 4.25) {
				  section = "Linux";
				  schedule = "1:00pm to 7:00pm";
			  }
			  break;
		  case 2:
			  if (gwa >= 4.3) {
				  section = "Acer";
				  schedule = "6:00am to 12:00pm"; 
			  }
			  else if (gwa <= 4.25) {
				  section = "Babbage";
				  schedule = "1:00pm to 7:00pm";
			  }
			  break;
		  case 3:
			  if (gwa >= 4.3) {
				  section = "Copper";
				  schedule = "6:00am to 12:00pm"; 
			  }
			  else if (gwa <= 4.25) {
				  section = "Bronze";
				  schedule = "1:00pm to 7:00pm";
			  }
			  break;
		  case 4:
			  if (gwa >= 4.3) {
				  section = "Ford";
				  schedule = "6:00am to 12:00pm"; 
			  }
			  else if (gwa <= 4.25) {
				  section = "Nexon";
				  schedule = "1:00pm to 7:00pm";
			  }
			  break;
			  
			default:
				System.out.println("System Error!");
		  }
		  
	  }
	  
	  else if (course.equals("BSCE")) {
		  
		  switch(yearLevel) {
		  
		  case 1:
			  if (gwa >= 4.3) {
				  section = "Charlie";
				  schedule = "6:00am to 12:00pm"; 
			  }
			  else if (gwa <= 4.25) {
				  section = "Echo";
				  schedule = "1:00pm to 7:00pm";
			  }
			  break;
			  
		  case 2:
			  if (gwa >= 4.3) {
				  section = "Golf";
				  schedule = "6:00am to 12:00pm"; 
			  }
			  else if (gwa <= 4.25) {
				  section = "Lima";
				  schedule = "1:00pm to 7:00pm";
			  }
			  break;
			  
		  case 3:
			  if (gwa >= 4.3) {
				  section = "Whiskey";
				  schedule = "6:00am to 12:00pm"; 
			  }
			  else if (gwa <= 4.25) {
				  section = "Zulu";
				  schedule = "1:00pm to 7:00pm";
			  }
			  break;
		  
		  case 4:
			  if (gwa >= 4.3) {
				  section = "Vector";
				  schedule = "6:00am to 12:00pm"; 
			  }
			  else if (gwa <= 4.25) {
				  section = "Yankee";
				  schedule = "1:00pm to 7:00pm";
			  }
			  break;
			  
			default:
				System.out.println("System Error!");
		  }  
		  
	  }
	  
	  else if(course.equals("BSIS")) {
		  
		  switch(yearLevel) {
		  
		  case 1:
			  
			  if (gwa >= 4.3) {
				  section = "Abrams";
				  schedule = "6:00am to 12:00pm"; 
			  }
			  else if (gwa <= 4.25) {
				  section = "Kali";
				  schedule = "1:00pm to 7:00pm";
			  }
			  break;
		  case 2:
			  if (gwa >= 4.3) {
				  section = "Dragon";
				  schedule = "6:00am to 12:00pm"; 
			  }
			  else if (gwa <= 4.25) {
				  section = "Kefua";
				  schedule = "1:00pm to 7:00pm";
			  }
			  break;
		  case 3:
			  if (gwa >= 4.3) {
				  section = "Alpha";
				  schedule = "6:00am to 12:00pm"; 
			  }
			  else if (gwa <= 4.25) {
				  section = "Omega";
				  schedule = "1:00pm to 7:00pm";
			  }
			  break;
		  case 4:
			  if (gwa >= 4.3) {
				  section = "Delta";
				  schedule = "6:00am to 12:00pm"; 
			  }
			  else if (gwa <= 4.25) {
				  section = "Foxtrot";
				  schedule = "1:00pm to 7:00pm";
			  }
			  break;
			  
			default:
				System.out.println("System Error!");
		  } 
	  } 
  } 

  public static void viewAssessment() {
		
		tuition = units * rate ;
		mscFee = labFee + libFee + athFee + idFee + uniform +  others ;
		total = mscFee + tuition;
		
		createLines();
		
		System.out.println("\n\t\t\t\t      |ASSESSMENT|");
		
		createLines();
		
		System.out.println("\n\nNo. of Units: " + units + "  ||  Rate Per Unit:" + rate);
		System.out.println("Tuition Fee: " + tuition);
		System.out.println("Athletic Fee: " + athFee + "  ||  Library Fee: " + libFee);
		System.out.println("Laboratory Fee: " + labFee);
		System.out.println("ID Fee: " + idFee + "  ||  Uniform: " + uniform);
		System.out.println("Others: " + others);
		System.out.println("Misceleneous Fee: " + mscFee);
		System.out.println("TOTAL: [ '" + total + "' ]");
  }
 
  public static void reset() {
		
	  units = 0 ;
	  rate = 0 ;
	  athFee = 0 ;
	  libFee = 0 ;
	  labFee = 0 ; 
	  idFee = 0;
	  uniform = 0;
	  others = 0; 
	  tuition = 0 ;
	  mscFee = 0 ;
	  cash = 0;
	  total = 0;
	   
	  courseName = "";
	  section = "";
	  schedule = "";
	  studLRN = "";
	  specializationVal = null;
	   
	  inputChckr = true; 
	  mop = true ;
	   
	  discount = 0 ;
	  interest = 0 ;
	  downPayment = 0 ;
	  balance = 0 ;
	  totalAss = 0 ;
	  change = 0;
	  paymentMode = ' ';
	  specialize = ' ';
	  gwa = 0;
	  yearLevel = 1 ;
			
  }
  
}