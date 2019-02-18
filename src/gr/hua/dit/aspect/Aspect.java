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

import gr.hua.dit.entity.HousingApplication;
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

		for (User user : result) {

			currentSession.evict(user);
			if (crypto.isEncrypted(user.getUsername())) {

				user.setUsername(crypto.decrypt(user.getUsername()));
			}
		}

	}
	


	@AfterReturning(pointcut = "execution(* gr.hua.dit.service.HousingApplicationServiceImplementation.getHousingApplicationById(int))", returning = "result")
	public void decryptHousingApplication(HousingApplication result) {

		System.out.println(
				"pointcut @AfterReturning gr.hua.dit.service.HousingApplicationServiceImplementation.getHousingApplicationById(int)");
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.evict(result);

		currentSession.evict(result.getStudent());
		
		
		
		

		if (crypto.isEncrypted(result.getStudent().getUser().getUsername())) {

			result.getStudent().getUser().setUsername(result.getStudent().getUser().getUsername());
		}

		if (crypto.isEncrypted(result.getStudent().getAddress())) {

			result.getStudent().setAddress(result.getStudent().getAddress());
		}

		if (crypto.isEncrypted(result.getStudent().getBirthdate())) {

			result.getStudent().setBirthdate((result.getStudent().getBirthdate()));
		}

		if (crypto.isEncrypted(result.getStudent().getCreatedBy())) {

			result.getStudent().setCreatedBy(result.getStudent().getCreatedBy());
		}

		if (crypto.isEncrypted(result.getStudent().getDepartment())) {

			result.getStudent().setDepartment(result.getStudent().getDepartment());
		}

		if (crypto.isEncrypted(result.getStudent().getName())) {

			result.getStudent().setName(result.getStudent().getName());
		}

		if (crypto.isEncrypted(result.getStudent().getPhone())) {

			result.getStudent().setPhone(result.getStudent().getPhone());
		}

		if (crypto.isEncrypted(result.getStudent().getSurname())) {

			result.getStudent().setSurname(result.getStudent().getSurname());
		}

		currentSession.evict(result.getStudent().getUser());

	
		if (crypto.isEncrypted(result.getStudent().getSurname())) {

			result.getStudent().setSurname(crypto.decrypt(result.getStudent().getSurname()));
		}

		if (crypto.isEncrypted(result.getStudent().getPhone())) {

			result.getStudent().setPhone(crypto.decrypt(result.getStudent().getPhone()));
		}
		
		if(crypto.isEncrypted(result.getStudent().getUser().getUsername())) {
			
			result.getStudent().getUser().setUsername(crypto.decrypt(
					result.getStudent().getUser().getUsername()));
		}

	}

	@AfterReturning(pointcut = "execution(* gr.hua.dit.service.HousingApplicationServiceImplementation.getAllUnverifiedHousingApplications())", returning = "result")
	public void decryptHousingApplication(List<HousingApplication> result) {

		System.out.println(
				"Pointcut @AfterReturning gr.hua.dit.service.HousingApplicationServiceImplementation.getAllUnverifiedHousingApplications()");
		Session currentSession = sessionFactory.getCurrentSession();

		for (HousingApplication application : result) {

			currentSession.evict(application);
			
			currentSession.evict(application.getStudent());
			
			currentSession.evict(application.getStudent().getUser());
			
			
			
			if (crypto.isEncrypted(application.getFileType())) {

				application.setCreated_at(crypto.decrypt(application.getCreated_at()));
			}

			if (crypto.isEncrypted(application.getStudent().getAddress())) {

				application.getStudent().setAddress(crypto.decrypt(application.getStudent().getAddress()));
			}

			if (crypto.isEncrypted(application.getStudent().getBirthdate())) {

				application.getStudent().setBirthdate(crypto.decrypt(application.getStudent().getBirthdate()));
			}

			if (crypto.isEncrypted(application.getStudent().getCreatedBy())) {

				application.getStudent().setCreatedBy((crypto.decrypt(application.getStudent().getCreatedBy())));
			}

			if (crypto.isEncrypted(application.getStudent().getDepartment())) {

				application.getStudent().setDepartment((crypto.decrypt(application.getStudent().getDepartment())));
			}

			if (crypto.isEncrypted(application.getStudent().getName())) {

				application.getStudent().setName((crypto.decrypt(application.getStudent().getName())));
			}

			if (crypto.isEncrypted(application.getStudent().getPhone())) {

				application.getStudent().setPhone((crypto.decrypt(application.getStudent().getPhone())));
			}

			if (crypto.isEncrypted(application.getStudent().getSurname())) {

				currentSession.evict(application.getStudent());

				application.getStudent().setSurname(crypto.decrypt(application.getStudent().getSurname()));
			}

			if (crypto.isEncrypted(application.getStudent().getUser().getUsername())) {

				currentSession.evict(application.getStudent().getUser());
				application.getStudent().getUser()
						.setUsername(crypto.decrypt(application.getStudent().getUser().getUsername()));
			}

		}

	}

	@AfterReturning(pointcut = "execution(* gr.hua.dit.service.StudentServiceImplementation.findStudentByUsername(String))", returning = "result")
	public void decryptStudent2(Student result) {

		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.evict(result);

		System.out.println(
				"PointCut After gr.hua.dit.service.StudentServiceImplementation.findStudentByUsername(String)");

		if (crypto.isEncrypted(result.getAddress())) {

			result.setAddress(crypto.decrypt(result.getAddress()));
		}

		if (crypto.isEncrypted(result.getBirthdate())) {

			result.setBirthdate(crypto.decrypt(result.getBirthdate()));
		}

		if (crypto.isEncrypted(result.getCreatedBy())) {

			result.setCreatedBy(crypto.decrypt(result.getCreatedBy()));
		}

		if (crypto.isEncrypted(result.getName())) {
			result.setName(crypto.decrypt(result.getName()));
		}

		if (crypto.isEncrypted(result.getPhone())) {

			result.setPhone(crypto.decrypt(result.getPhone()));
		}

	}

	@AfterReturning(pointcut = "execution(* gr.hua.dit.service.StudentServiceImplementation.getStudentById(int))", returning = "result")
	public void decryptStudent(Student result) {

		System.out.println("PointCut after returning gr.hua.dit.service.StudentService.getStudentById(int) ");
		if (result == null) {
			return;
		}

		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.evict(result);
		
		currentSession.evict(result.getUser());
		
		List<HousingApplication> applications=result.getHousingApplications();
		
		for(HousingApplication application:applications) {
			
			currentSession.evict(application);
		}

		if (crypto.isEncrypted(result.getAddress())) {

			result.setAddress(crypto.decrypt(result.getAddress()));
		}

		if (crypto.isEncrypted(result.getBirthdate())) {

			result.setBirthdate(crypto.decrypt(result.getBirthdate()));
		}

		if (crypto.isEncrypted(result.getName())) {

			result.setName(crypto.decrypt(result.getName()));
		}

		if (crypto.isEncrypted(result.getPhone())) {

			result.setPhone(crypto.decrypt(result.getPhone()));
		}

		if (crypto.isEncrypted(result.getDepartment())) {

			result.setDepartment(crypto.decrypt(result.getDepartment()));
		}

		if (crypto.isEncrypted(result.getCreatedBy())) {

			result.setCreatedBy(crypto.decrypt(result.getCreatedBy()));
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

		if (!crypto.isEncrypted(student.getCreatedBy())) {

			student.setCreatedBy(crypto.encrypt(student.getCreatedBy()));
		}

		User user = student.getUser();

		if (!crypto.isEncrypted(user.getUsername())) {

			user.setUsername(crypto.encrypt(user.getUsername()));
		}

	}
	
	

	@Around("execution(* gr.hua.dit.entity.SUser.setPhone(String))")
	public void encryptPhone(ProceedingJoinPoint joinPoint) {

		System.out.println("PointCut around gr.hua.dit.entity.SUser.setPhone(String)");

		Object[] objects = joinPoint.getArgs();

		String phone = new String((String) objects[0]);

		if (!crypto.isEncrypted(phone)) {

			try {
				joinPoint.proceed(new Object[] { crypto.encrypt(phone) });
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {

			try {
				joinPoint.proceed(new Object[] { phone });
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	


	@Around("execution(* gr.hua.dit.entity.SUser.setAddress(String))")
	public void encryptAddress(ProceedingJoinPoint joinPoint) {

		System.out.println("PointCut around gr.hua.dit.entity.SUser.setPhone(String)");

		Object[] objects = joinPoint.getArgs();

		String phone = new String((String) objects[0]);

		if (!crypto.isEncrypted(phone)) {

			try {
				joinPoint.proceed(new Object[] { crypto.encrypt(phone) });
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {

			try {
				joinPoint.proceed(new Object[] { phone });
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
	
	@Around("execution(* gr.hua.dit.service.UserServiceImplementation.updateUsername(..))")
	public Object encryptUpdateUsername(ProceedingJoinPoint joinPoint) {

		System.out.println("PointCut around updateUsername");

		Object[] objects = joinPoint.getArgs();



		Object[] encryptedUsername = new Object[] { crypto.encrypt((String) objects[1]) };
		try {
			Object result = joinPoint.proceed(new Object[] {objects[0], encryptedUsername[0]});

			return result;
		} catch (Throwable e) {
			System.out.println(e.getMessage());
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

	

	@AfterReturning(pointcut = "execution(* gr.hua.dit.service.HousingApplicationServiceImplementation.getAllHousingApplicationsOrderedDesc())", returning = "result")
	public void decryptHousingApplicationForEmail(List<HousingApplication> result) {

		System.out.println(
				"Pointcut @AfterReturning gr.hua.dit.service.HousingApplicationServiceImplementation.getAllHousingApplicationsOrderedDesc()");
		Session currentSession = sessionFactory.getCurrentSession();

		for (HousingApplication application : result) {

			currentSession.evict(application);
			
			currentSession.evict(application.getStudent());
			
			currentSession.evict(application.getStudent().getUser());
			
			
			
			if (crypto.isEncrypted(application.getFileType())) {

				application.setCreated_at(crypto.decrypt(application.getCreated_at()));
			}

			if (crypto.isEncrypted(application.getStudent().getAddress())) {

				application.getStudent().setAddress(crypto.decrypt(application.getStudent().getAddress()));
			}

			if (crypto.isEncrypted(application.getStudent().getBirthdate())) {

				application.getStudent().setBirthdate(crypto.decrypt(application.getStudent().getBirthdate()));
			}

			if (crypto.isEncrypted(application.getStudent().getCreatedBy())) {

				application.getStudent().setCreatedBy((crypto.decrypt(application.getStudent().getCreatedBy())));
			}

			if (crypto.isEncrypted(application.getStudent().getDepartment())) {

				application.getStudent().setDepartment((crypto.decrypt(application.getStudent().getDepartment())));
			}

			if (crypto.isEncrypted(application.getStudent().getName())) {

				application.getStudent().setName((crypto.decrypt(application.getStudent().getName())));
			}

			if (crypto.isEncrypted(application.getStudent().getPhone())) {

				application.getStudent().setPhone((crypto.decrypt(application.getStudent().getPhone())));
			}

			if (crypto.isEncrypted(application.getStudent().getSurname())) {

				currentSession.evict(application.getStudent());

				application.getStudent().setSurname(crypto.decrypt(application.getStudent().getSurname()));
			}

			if (crypto.isEncrypted(application.getStudent().getUser().getUsername())) {

				currentSession.evict(application.getStudent().getUser());
				application.getStudent().getUser()
						.setUsername(crypto.decrypt(application.getStudent().getUser().getUsername()));
			}

		}

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