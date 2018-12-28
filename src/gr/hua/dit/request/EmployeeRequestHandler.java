package gr.hua.dit.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;

import gr.hua.dit.entity.Employee;
import gr.hua.dit.entity.EmployeeJson;

public class EmployeeRequestHandler {
	private BufferedReader bufferedReader = null;
	private StringBuffer sb = null;

	
	public void getDataFromRequest(HttpServletRequest request) {
		sb = new StringBuffer();
		List<String> dataList;
		Map<String, String> data = new HashMap<>();
		Principal principal = request.getUserPrincipal();
		try {
			bufferedReader = request.getReader();
			char[] charBuffer = new char[64];
			int bytesRead;
			while ((bytesRead = bufferedReader.read(charBuffer)) != -1) {
				sb.append(charBuffer, 0, bytesRead);
			}
			
			Gson employeeGson = new Gson();
			EmployeeJson employeeJson = employeeGson.fromJson(sb.toString(), EmployeeJson.class);
			
		//	System.out.println(employeeJson.toString());
			
//			dataList = convertStringBufferToArrayList(sb, "&");
//			data = convertDataToKeyValuePairs(dataList, "=");
//			decodeUTF8(data);
//			data.put("createdBy", principal.getName());
//			return data;
		} catch (IOException e) {
//			return data;
			
		}
	}
	
	/**
	 * Η μέθοδος αυτή , έχει σαν σκοπό να πάρει ένα response
	 * από τον χρήστη που περιέχει πληροφορία σε json Και να την
	 * μετατρέψει σε String
	 * 	 
	 * @param request το Response Που περιέχει jsonString
	 * @return Ένα String Που γίνεται άμεσα jsonObject
	 */
	public static String getSringifiedHttpResponse(HttpServletRequest request) {
		StringBuffer buffer = new StringBuffer();
		BufferedReader delim=null;
		
		
			try {
				delim=request.getReader();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			char[] charBuffer = new char[64];
			int bytesRead;
			try {
				while ((bytesRead = delim.read(charBuffer)) != -1) {
					buffer.append(charBuffer, 0, bytesRead);
				
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return buffer.toString();
	}
	
//	private String convert(String text) {
//		return new String(text.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
//	}

	/**
	 * Προυπόθεση για να δουλέψει αυτός ο getter είναι να έχει κληθεί 
	 * ο constructor της κλάσης με request παράμετρο
	 * @return ένα stringbuffer object που μπορεί να γίνει άμεσαjsonObject
	 */
	public StringBuffer getSb() {
		return sb;
	}


	private List<String> convertStringBufferToArrayList(StringBuffer sb, String delim) {
		StringTokenizer stringTokenizer = new StringTokenizer(sb.toString(), delim);
		List<String> data = new ArrayList<>();
		while (stringTokenizer.hasMoreTokens()) {
			data.add(stringTokenizer.nextToken());
		}
		return data;
	}

	private void decodeUTF8(Map<String, String> data) {
		for (int i = 0; i < data.size(); i++) {
			try {
				for(String key : data.keySet()) {
					data.remove(key);
					key = URLDecoder.decode(key, "UTF-8");
					data.put(key, URLDecoder.decode(data.get(key), "UTF-8"));
				}
			} catch (UnsupportedEncodingException e) {
				System.err.println("gr.hua.dit.decodeUTF8 -> UnsupportedEncodingException");
			}
		}
	}

	private Map<String, String> convertDataToKeyValuePairs(List<String> data, String delim) {
		Map<String, String> dataObject = new HashMap<>();
		List<String> tmp = new ArrayList<>();

		for (String str : data) {
			StringTokenizer stringTokenizer = new StringTokenizer(str, delim);
			while (stringTokenizer.hasMoreTokens()) {
				tmp.add(stringTokenizer.nextToken());
			}
		}
		dataObject.put("enabled", "0");
		for (int i = 0; i < tmp.size(); i += 2) {
			if( i + 2 > tmp.size() ) {
				break;
			}else {	
				dataObject.put(tmp.get(i), tmp.get(i + 1));
			}
		}
		return dataObject;

	}
	
	

}
