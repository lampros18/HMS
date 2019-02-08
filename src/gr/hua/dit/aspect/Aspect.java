package gr.hua.dit.aspect;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import gr.hua.dit.entity.Student;
import gr.hua.dit.entity.User;
//import gr.hua.dit.mail.MailService;
import gr.hua.dit.security.Crypto;
//import gr.hua.dit.service.StudentService;

@org.aspectj.lang.annotation.Aspect
@Component
@EnableAspectJAutoProxy
public class Aspect {

	@Autowired
	private Crypto crypto;

	@Autowired
	private SessionFactory sessionFactory;

//	@Autowired
//	private StudentService service;
//
//	@Autowired
//	private MailService mailService;
	

	@AfterReturning(pointcut = "execution(* gr.hua.dit.service.UserService.getUsers())", returning = "result")
	public void decryptUsers(List<User> result) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		for(User user:result) {
			
			currentSession.evict(user);
			if(crypto.isEncrypted(user.getUsername())) {
				
				user.setUsername(crypto.decrypt(user.getUsername()));
			}
		}
		
	}

	@AfterReturning(pointcut = "execution(* gr.hua.dit.dao.StudentDAOImpl.getStudents(..))", returning = "result")
	public void decryptGetStudents(JoinPoint joinPoint, List<Student> result) {

		Session currentSession = sessionFactory.getCurrentSession();

		for (Student student : result) {

			currentSession.evict(student);

			currentSession.evict(student.getUser());

			if (crypto.isEncrypted(student.getUser().getUsername())) {

				student.getUser().setUsername(crypto.decrypt(student.getUser().getUsername()));
			}

			if (crypto.isEncrypted(student.getName())) {

				student.setName(crypto.decrypt(student.getName()));
			}

			if (crypto.isEncrypted(student.getSurname())) {

				student.setSurname(crypto.decrypt(student.getSurname()));
			}

			if (crypto.isEncrypted(student.getDepartment())) {

				student.setDepartment(crypto.decrypt(student.getDepartment()));
			}

			if (crypto.isEncrypted(student.getPhone())) {

				student.setPhone(crypto.decrypt(student.getPhone()));
			}

			if (crypto.isEncrypted(student.getAddress())) {

				student.setAddress((crypto.decrypt(student.getAddress())));
			}

			if (crypto.isEncrypted(student.getCreatedBy())) {

				student.setCreatedBy(crypto.decrypt(student.getCreatedBy()));
			}

		}

	}

	@Before("execution(* gr.hua.dit.service.StudentServiceImplementation.insertStudent(..))")
	public void encryptInsertStudent(JoinPoint joinPoint) {

		System.out.println("\nPointCut Before inserting o 'insertStudent'\n");

		Object[] objects = joinPoint.getArgs();

		Student student = (Student) objects[0];

		if (!crypto.isEncrypted(student.getSurname())) {

			student.setSurname(crypto.encrypt(student.getSurname()));
		}

		if (!crypto.isEncrypted(student.getAddress())) {

			student.setAddress(crypto.encrypt(student.getAddress()));
		}
		
		if (!crypto.isEncrypted(student.getName())) {

			student.setName(crypto.encrypt(student.getName()));
		}
		

		if (!crypto.isEncrypted(student.getPhone())) {

			student.setPhone(crypto.encrypt(student.getPhone()));
		}
		

		if (!crypto.isEncrypted(student.getDepartment())) {

			student.setDepartment(crypto.encrypt(student.getDepartment()));
		}
		

		// student.setBirthdate(crypto.encrypt(student.getBirthdate()));

		if (!crypto.isEncrypted(student.getCreatedBy())) {

			student.setCreatedBy(crypto.encrypt(student.getCreatedBy()));
		}

		

		User user = student.getUser();

		// System.out.println(user.toString());
		
		if (!crypto.isEncrypted(user.getUsername())) {

			user.setUsername(crypto.encrypt(user.getUsername()));
		}

		

	}

	@Around("execution(* gr.hua.dit.service.UserServiceImplementation.findUserByUsername(..))")
	public Object decryptFindUserByUserNameSearch(ProceedingJoinPoint joinPoint) {

		System.out.println("PointCut around findUserByUsername");

		Object[] objects = joinPoint.getArgs();

		Object[] plainTextUser = new Object[] { objects[0] };

		Object[] encryptedTextUser = new Object[] { crypto.encrypt((String) objects[0]) };

		try {
			Object result = joinPoint.proceed(plainTextUser);

			if (result == null) {

				result = joinPoint.proceed(encryptedTextUser);

			}

			return result;
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Before("execution(* gr.hua.dit.service.UserServiceImplementation.createUser(..))")
	public void encryptUser(JoinPoint joinPoint) {

		System.out.println("\nPointCut encrypting 'UserServiceImplementation.createUser(..)' nt'\n");

		Object[] objects = joinPoint.getArgs();

		User user = (User) objects[0];

		user.setUsername(crypto.encrypt(user.getUsername()));

	}

	@Around("execution(* gr.hua.dit.service.StudentService.findStudentByUsername(String))")
	public Object decryptFindStudentByUserNameSearch(ProceedingJoinPoint joinPoint) {

		System.out.println("PointCut around findStudentByUsername");

		Object[] objects = joinPoint.getArgs();

		Object[] plainTextUser = new Object[] { objects[0] };

		Object[] encryptedTextUser = new Object[] { crypto.encrypt((String) objects[0]) };

		try {
			Object result = joinPoint.proceed(plainTextUser);

			if (result == null) {

				result = joinPoint.proceed(encryptedTextUser);

			}

			return result;
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

//	
//	@Before("execution(* org.springframework.stereotype.*.()")
//	public void sercurityAspect(JoinPoint joinpoint) {
//		
//		Object[] objects=joinpoint.getArgs();
//		
//		if(objects!=null && objects.length>0) {
//			
//			System.out.println(Object.class);
//			
//			System.out.println(objects.length);
//			
//			System.out.println("\n\n"+objects[0].toString()+"\n\n");
//			org.springframework.security.authentication.UsernamePasswordAuthenticationToken 
//			authenticationToken=new UsernamePasswordAuthenticationToken(null, null);
//			
//		
//		}
//		
//		
//		
//		//System.out.println("some method in the security");
//	}

	


}