package gr.hua.dit.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gr.hua.dit.entity.GeneralVariables;
import gr.hua.dit.job.EndingDateJob;

@SuppressWarnings("deprecation")
@Repository
public class GeneralVariablesDAOImplementation implements GeneralVariablesDAO {

	@Autowired
	SessionFactory session;

	@Override
	public HashMap<String, String> getGeneralVariables() {
		Session curSession = session.getCurrentSession();
		Query<GeneralVariables> query = curSession.createQuery("from GeneralVariables", GeneralVariables.class);

		HashMap<String, String> map = new HashMap<>();
		for (GeneralVariables gv : query.getResultList()) {
			map.put(gv.getKey(), gv.getValue());
		}

		return map;
	}

	@Override
	public int setApplicationsLimit(String department, int limit) {
		Session curSession = session.getCurrentSession();
		@SuppressWarnings("rawtypes")
		Query query = null;
		int res = -1;
		try {
			switch (department) {
			case "Home economics and ecology":
				query = curSession.createQuery("update GeneralVariables gv set gv.value =: value where gv.key =: key");
				if (query == null)
					return -1;
				query.setParameter("value", "" + limit);
				query.setParameter("key", ECOLOGY_DEPARTMENT_DB_KEY);
				res = query.executeUpdate();
				break;
			case "Geography":
				query = curSession.createQuery("update GeneralVariables gv set gv.value =: value where gv.key =: key");
				if (query == null)
					return -1;
				query.setParameter("value", "" + limit);
				query.setParameter("key", GEOGRAPHY_DEPARTMENT_DB_KEY);
				res = query.executeUpdate();
				break;
			case "International master of sustainable tourism development":
				query = curSession.createQuery("update GeneralVariables gv set gv.value =: value where gv.key =: key");
				if (query == null)
					return -1;
				query.setParameter("value", "" + limit);
				query.setParameter("key", TOURISM_DEPARTMENT_DB_KEY);
				res = query.executeUpdate();
				break;
			case "Nutrition and dietics":
				query = curSession.createQuery("update GeneralVariables gv set gv.value =: value where gv.key =: key");
				if (query == null)
					return -1;
				query.setParameter("value", "" + limit);
				query.setParameter("key", NUTRIRION_DEPARTMENT_DB_KEY);
				res = query.executeUpdate();
				break;
			case "Informatics and Telematics":
				query = curSession.createQuery("update GeneralVariables gv set gv.value =: value where gv.key =: key");
				if (query == null)
					return -1;
				query.setParameter("value", "" + limit);
				query.setParameter("key", INFORMATICS_DEPARTMENT_DB_KEY);
				res = query.executeUpdate();
				break;
			}
			return res;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public int setStartingDate(String startingDate) {
		Session curSession = session.getCurrentSession();
		@SuppressWarnings("rawtypes")
		Query query = curSession.createQuery("update GeneralVariables gv set gv.value =: value where gv.key =: key");
		query.setParameter("value", startingDate);
		query.setParameter("key", STARTING_DATE_DB_KEY);
		try {
			return query.executeUpdate();
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public int setEndingDate(String endingDate) {
		Thread job = new Thread() {
			public void run() {
				addJob(endingDate);
			}
		};
		job.start();

		Session curSession = session.getCurrentSession();
		@SuppressWarnings("rawtypes")
		Query query = curSession.createQuery("update GeneralVariables gv set gv.value =: value where gv.key =: key");
		query.setParameter("value", endingDate);
		query.setParameter("key", ENDING_DATE_DB_KEY);
		try {
			return query.executeUpdate();
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public int getApplicationsLimit(String department) {
		Session currentSession = session.getCurrentSession();
		Query<GeneralVariables> query;
		switch (department) {
		case "Home economics and ecology":
			query = currentSession.createQuery("from GeneralVariables where key=:key", GeneralVariables.class);
			query.setParameter("key", ECOLOGY_DEPARTMENT_DB_KEY);

			try {
				int val = Integer.parseInt(query.getSingleResult().getValue());
				return val;
			} catch (Exception e) {
				return -1;
			}
		case "Geography":
			query = currentSession.createQuery("from GeneralVariables where key=:key", GeneralVariables.class);
			query.setParameter("key", GEOGRAPHY_DEPARTMENT_DB_KEY);

			try {
				int val = Integer.parseInt(query.getSingleResult().getValue());
				return val;
			} catch (Exception e) {
				return -1;
			}
		case "International master of sustainable tourism development":
			query = currentSession.createQuery("from GeneralVariables where key=:key", GeneralVariables.class);
			query.setParameter("key", TOURISM_DEPARTMENT_DB_KEY);

			try {
				int val = Integer.parseInt(query.getSingleResult().getValue());
				return val;
			} catch (Exception e) {
				return -1;
			}
		case "Nutrition and dietics":
			query = currentSession.createQuery("from GeneralVariables where key=:key", GeneralVariables.class);
			query.setParameter("key", NUTRIRION_DEPARTMENT_DB_KEY);

			try {
				int val = Integer.parseInt(query.getSingleResult().getValue());
				return val;
			} catch (Exception e) {
				return -1;
			}
		case "Informatics and Telematics":
			query = currentSession.createQuery("from GeneralVariables where key=:key", GeneralVariables.class);
			query.setParameter("key", INFORMATICS_DEPARTMENT_DB_KEY);

			try {
				int val = Integer.parseInt(query.getSingleResult().getValue());
				return val;
			} catch (Exception e) {
				return -1;
			}
		default:
			return -1;
		}
	}

	@Override
	public HashMap<String, String> getDateVariables() {
		Session currentSession = session.getCurrentSession();
		Query<GeneralVariables> query;
		query = currentSession.createQuery("from GeneralVariables where key=:startingDate OR key=:endingDate",
				GeneralVariables.class);
		query.setParameter("startingDate", STARTING_DATE_DB_KEY);
		query.setParameter("endingDate", ENDING_DATE_DB_KEY);
		List<GeneralVariables> dateVars = query.getResultList();
		if (dateVars.size() == 0)
			return null;
		HashMap<String, String> dateVariables = new HashMap<>();
		for (GeneralVariables gv : dateVars) {
			if (gv.getKey().equals(STARTING_DATE_DB_KEY))
				dateVariables.put(STARTING_DATE_DB_KEY, gv.getValue());
			else
				dateVariables.put(ENDING_DATE_DB_KEY, gv.getValue());
		}
		return dateVariables;
	}

	@Override
	public boolean checkRemaining() {
		HashMap<String, String> generalVariables = getGeneralVariables();
		SimpleDateFormat dateFormatGmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
		if (dateFormatGmt.format(new Date()).compareTo(generalVariables.get("ending_date")) > 0) {
			return true;
		}
		return false;
	}

	private void addJob(String endingDate) {
		
		SimpleDateFormat dateFormatLocal = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date2 = new Date();
		try {
			Date date = dateFormatLocal.parse(endingDate + " 23:59:59");
			dateFormatLocal.setTimeZone(TimeZone.getTimeZone("GMT"));
			
			SimpleDateFormat dateFormatLocal2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			dateFormatLocal2.setTimeZone(TimeZone.getDefault());
			date2 = dateFormatLocal.parse(dateFormatLocal2.format(date));
			//dateFormatLocal.format(date2)
			//System.out.println(dateFormatLocal.format(date2) + "    database");
		} catch (java.text.ParseException e) {
			e.printStackTrace();
			return;
		}

		int year = date2.getYear()+1900;
		int month = date2.getMonth()+1;
		int day = date2.getDate();
		
		int hour = date2.getHours();
		JobDetail job1 = JobBuilder.newJob(EndingDateJob.class).withIdentity("job1", "group1").build();

		Trigger trigger1 = TriggerBuilder.newTrigger().withIdentity("cronTrigger1", "group1")
				// Seconds Minutes Hours Day-of-Month Month Day-of-Week Year
				// 59 1 19 * 11 ? 2019
				.withSchedule(CronScheduleBuilder.cronSchedule("59 59 " + hour + " "+ day + " " + month + " ? " + year)).build();

		Scheduler scheduler1;
		try {
			scheduler1 = new StdSchedulerFactory().getScheduler();
			scheduler1.start();
			scheduler1.scheduleJob(job1, trigger1);
		} catch (SchedulerException e) {
//TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
