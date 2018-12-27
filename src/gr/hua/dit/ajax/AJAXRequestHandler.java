package gr.hua.dit.ajax;

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

public class AJAXRequestHandler {
	BufferedReader bufferedReader = null;
	StringBuffer sb = null;

	public Map<String, String> getDataFromRequest(HttpServletRequest request) {
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

			dataList = convertStringBufferToArrayList(sb, "&");
			decodeUTF8(dataList);
			data = convertDataToKeyValuePairs(dataList, "=");
			data.put("createdBy", principal.getName());
			return data;
		} catch (IOException e) {
			return data;
		}
	}

//	private String convert(String text) {
//		return new String(text.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
//	}

	private List<String> convertStringBufferToArrayList(StringBuffer sb, String delim) {
		StringTokenizer stringTokenizer = new StringTokenizer(sb.toString(), delim);
		List<String> data = new ArrayList<>();
		while (stringTokenizer.hasMoreTokens()) {
			data.add(stringTokenizer.nextToken());
		}
		return data;
	}

	private void decodeUTF8(List<String> data) {
		for (int i = 0; i < data.size(); i++) {
			try {
				data.set(i, URLDecoder.decode(data.get(i), "UTF-8"));
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
