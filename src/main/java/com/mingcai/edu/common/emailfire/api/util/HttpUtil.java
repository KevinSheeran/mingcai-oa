package com.mingcai.edu.common.emailfire.api.util;

import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

public class HttpUtil {

	private static int connectTimeOut = 10 * 1000;

	public static String doPost(String url, List<NameValuePair> nvps,List<Header> headers) throws Exception {

		HttpParams httpParameters = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParameters,connectTimeOut);
		HttpConnectionParams.setSoTimeout(httpParameters, connectTimeOut);

		HttpClient httpclient = new DefaultHttpClient(httpParameters);

		HttpPost httpost = new HttpPost(url);

		httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));

		if (headers != null && headers.size() > 0) {
			httpclient.getParams().setParameter("http.default-headers", headers);
		}

		HttpResponse response = httpclient.execute(httpost);
		String responseStr = EntityUtils.toString(response.getEntity());

		httpclient.getConnectionManager().shutdown();

		return responseStr;
	}

	public static String doPost(String url, List<NameValuePair> nvps) throws Exception {
		return doPost(url, nvps, null);
	}

	public static String doGet(String url, List<Header> headers) throws Exception {

		HttpParams httpParameters = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParameters,connectTimeOut);
		HttpConnectionParams.setSoTimeout(httpParameters, connectTimeOut);
		HttpClient httpclient = new DefaultHttpClient(httpParameters);

		if (headers != null && headers.size() > 0) {
			httpclient.getParams().setParameter("http.default-headers", headers);
		}

		HttpGet httpget = new HttpGet(url);

		HttpResponse response = httpclient.execute(httpget);

		String responseString = EntityUtils.toString(response.getEntity());

		httpclient.getConnectionManager().shutdown();

		return responseString;
	}

	public static String doGet(String url) throws Exception {
		return doGet(url, null);
	}

	public static String doPostXmlRequest(String actionUrl, String xmlString,List<Header> headers) throws Exception {
		
		HttpParams httpParameters = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParameters,connectTimeOut);
		HttpConnectionParams.setSoTimeout(httpParameters, connectTimeOut);

		HttpClient httpclient = new DefaultHttpClient(httpParameters);
		HttpPost httpost = new HttpPost(actionUrl);

		if (headers != null && headers.size() > 0) {
			for (Header header : headers) {
				httpost.setHeader(header);
			}
		}

		StringEntity myEntity = new StringEntity(xmlString, "UTF-8");
		httpost.setEntity(myEntity);

		HttpResponse response = httpclient.execute(httpost);

		String responseStr = EntityUtils.toString(response.getEntity());

		httpclient.getConnectionManager().shutdown();

		return responseStr;

	}
}
