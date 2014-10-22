package com.stek101.projectzulu.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.stek101.projectzulu.common.core.DefaultProps;

public class UpdateMonitor {

	
	public static void checkForUpdates()
	{
		int currentVersion = DefaultProps.Version_Code;
		int nextVersion = getLatest();
		if (currentVersion < nextVersion)
		{
			//ProjectZulu_Core.Updates = getUpdate(nextVersion);
			ProjectZulu_Core.modOutDated = true;
		}
		else
		{
			ProjectZulu_Core.modOutDated = false;
		}
	}
	
	public static int getLatest()
	{
		try
			{
			URL url = new URL("http://soultek101.weebly.com/uploads/4/1/2/0/41209035/versioncontrol.txt");			
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.setRequestProperty("Content-Language", "en-US");
			connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.56 Safari/535.11");
			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);
			
			InputStream is = connection.getInputStream();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line;
			StringBuffer response = new StringBuffer();
			
			while ((line = br.readLine()) != null)
			{
				response.append(line);
			}
				br.close();
				return Integer.parseInt(response.toString());
			}
		catch (IOException e)
			{
			e.printStackTrace();
			}
		return -1;
		}
	
 /*	private static String getUpdate(int version)
	{
	try
		{
			URL url = new URL("http://soultek101.weebly.com/uploads/4/1/2/0/41209035/versioncontrol.txt");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.setRequestProperty("Content-Language", "en-US");
			connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.56 Safari/535.11");
			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);
			
			InputStream is = connection.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line;
			StringBuffer response = new StringBuffer();
			
			while ((line = br.readLine()) != null)
			{
				response.append(line);
			}
			br.close();
			return response.toString();
		}
	catch (IOException e)
	{
		e.printStackTrace();
	}
		return "Error";
	} */
	
}
