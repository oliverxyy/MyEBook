/*
 * @project		MyEBook
 * @author		Oliver.xyy
 * @file_name	Tools.java
 * @pub_time	2015-6-8 14:39:07
 * @copyright	Oliver.xyy All Rights Reserved.
 */
package util;

public class Tools {
	public static Object[] reverse(Object[] params){
		int length = params.length;
		Object[] rparams = new Object[length];
		for(int i = 0;i < length; i++){
			rparams[length-1-i] = params[i];
			System.out.println(rparams[length-1-i]);
		}
		return rparams;
	}
	
	/**
	 * Gets the Run time message.
	 *
	 * @param m the startTime
	 * @param n the endTime
	 * @return the run time
	 */
	public static String getRunTime(long m,long n){
		long time = n - m;
		String msg = "";
		if(time<1000){msg = "此次运行共花费："+time+"毫秒";}
		else if(time>=1000&&time<1000*60){msg = "此次运行共花费："+time/1000+"秒"+time%1000+"毫秒";}
		else if(time>=1000*60&&time<1000*60*60){msg = "此次运行共花费："+time/(1000*60)+"分"+(time%(1000*60))/1000+"秒"+(time%(1000*60))%1000+"毫秒";}
		else if(time>=1000*60*60&&time<1000*60*60*24){msg = "此次运行共花费："+time/(1000*60*60)+"小时"+(time%(1000*60*60))/(1000*60)+"分"+(time%(1000*60*60))/1000+"秒"+(time%(1000*60*60))%1000+"毫秒";}
		else{
			msg = "此次运行时间超过一天！";
		}
		return msg;
	}
	
	
	
	
	
	
}
