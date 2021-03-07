package com.sgtesting.api.utility;

import java.io.FileInputStream;
import java.util.Properties;

public class ApplicationIndependent {
	
	/**
	 * Created By:
	 * Created Date:
	 * Modified By:
	 * Reviewed By:
	 * Parameters:
	 * Return Type:
	 * Purpose:
	 * Description:
	 */
	public static Properties properties(String FilePath)
	{
		Properties prop=null;
		FileInputStream fin=null;
		try
		{
			prop=new Properties();
			fin=new FileInputStream(FilePath);
			prop.load(fin);
			fin.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return prop;
	}

	/**
	 * Created By:
	 * Created Date:
	 * Modified By:
	 * Reviewed By:
	 * Parameters:
	 * Return Type:
	 * Purpose:
	 * Description:
	 */
	public static String getReportConfigPath()
	{
		String reportConfigPath=null;
		try
		{
			 String path=System.getProperty("user.dir");
			 Properties properties1 = properties(path+"\\Configs\\Configuration.properties");
			 reportConfigPath=properties1.getProperty("reportConfigPath");
			 if(reportConfigPath!= null) return reportConfigPath;
			 else throw new RuntimeException("Report Config Path not specified in the Configuration.properties file for the Key:reportConfigPath");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return reportConfigPath;
	}

}
