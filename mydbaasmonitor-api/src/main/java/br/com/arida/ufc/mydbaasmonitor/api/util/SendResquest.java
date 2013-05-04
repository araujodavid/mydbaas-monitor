package main.java.br.com.arida.ufc.mydbaasmonitor.api.util;

import java.io.IOException;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * 
 * @author Daivd Araújo
 * @version 1.0
 * @since March 5, 2013
 * 
 */

public class SendResquest {

	/**
	 * @param url - url to request
	 * @param params - parameters of form
	 * @param charset - encoding
	 * @return an HttpResponse object
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static HttpResponse postRequest(String url, List<NameValuePair> params, String charset) throws ClientProtocolException, IOException {
		HttpClient httpClient = new DefaultHttpClient();
		UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(params, charset);
		HttpPost httpPost = new HttpPost(url);
		httpPost.setEntity(formEntity);		
		HttpResponse response = httpClient.execute(httpPost);
		return response;
	}
}
