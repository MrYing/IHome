package com.ihome.utils;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.TimeZone;
import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Appender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import Decoder.BASE64Decoder;
import Decoder.BASE64Encoder;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;


public class Util {
	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
        "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	private final static Whitelist user_content_filter = Whitelist.relaxed();  
	static {  
	    user_content_filter.addTags("embed","object","param","span","div");  
	    user_content_filter.addAttributes(":all", "style", "class", "id", "name");  
	    user_content_filter.addAttributes("object", "width", "height","classid","codebase");      
	    user_content_filter.addAttributes("param", "name", "value");  
	    user_content_filter.addAttributes("embed", "src","quality","width","height","allowFullScreen","allowScriptAccess","flashvars","name","type","pluginspage");  
	}
	public static Logger logger=null;
	public static String currentDay="2015-11-11";
	public static String getDate()
	{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		sdf.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
		return sdf.format(new Date());
	}
	public static String getMonth()
	{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
		return sdf.format(new Date());
	}
	public static int getDay()
	{
		SimpleDateFormat sdf=new SimpleDateFormat("dd");
		return Integer.parseInt(sdf.format(new Date()));
	}
	public static String getTime()
	{
		SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss");
		return sdf.format(new Date());
	}
	public static String getDateTime()
	{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
		return sdf.format(new Date());
	}
	public static String getWeekday()
	{
		SimpleDateFormat sdf=new SimpleDateFormat("E");
		return sdf.format(new Date());
	}
	public static String getTimeStamp()
	{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(new Date());
	}
	public static String getEndTimeStamp()
	{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(new Date(System.currentTimeMillis()+1000*60*6));
	}
	public static String getJsonText(String text)
	{
		return "{\"Msg\":\""+text+"\"}";
	}
	public static String getSuccessResult()
	{
		return getResult("0000","SUCCESS");
	}
	public static String getResult(String code,String Msg)
	{
		return "{\"RespCode\":\""+code+"\",\"RespMsg\":\""+Msg+"\"}";
	}
	public static String getWebServiceReturn()
	{
		return "{\"result\":\"00\",\"error\":\"\"}";
	}
	public static String getWebServiceReturn(String code,String msg)
	{
		return "{\"result\":\""+code+"\",\"error\":\""+msg+"\"}";
	}
	private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n = 256 + n;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

	private static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

	public static String Md5(String str){
        //确定计算方法
        MessageDigest md5;
		try {
			md5 = MessageDigest.getInstance("MD5");
	      //  BASE64Encoder base64en = new BASE64Encoder();
	        //加密后的字符串
	        String newstr=byteArrayToHexString(md5.digest(str.getBytes("utf-8"))).toUpperCase();   
	        return newstr;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;    
    }
	public static String SHA1(String decript) {
        try {
            MessageDigest digest = java.security.MessageDigest
                    .getInstance("SHA-1");
            digest.update(decript.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();
 
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
	public static String getTradeNo()
	{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
		String tn=sdf.format(new Date())+getRandomString(10);
//		while(Trades.dao.findFirst("select tid from trades where tradeNo=?",tn)!=null)
//		{
//			tn=sdf.format(new Date())+getRandomString(10);
//		}
		return tn;
	}
	public static String getRefundNo()
	{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
		String tn="r"+sdf.format(new Date())+getRandomString(9);
		return tn;
	}
	public static String getRandomString(int length) { //length表示生成字符串的长度  
	    String base = "0123456789"; 
	    Random random = new Random();     
	    StringBuffer sb = new StringBuffer();     
	    for (int i = 0; i < length; i++) {     
	        int number = random.nextInt(base.length());     
	        sb.append(base.charAt(number));     
	    }     
	    return sb.toString();     
	 } 
	public static String getRandomString() { //length表示生成字符串的长度  
	    String base = "abcdefghijklmnopqrstuvwxyz0123456789"; 
	    int length=16;
	    Random random = new Random();     
	    StringBuffer sb = new StringBuffer();     
	    for (int i = 0; i < length; i++) {     
	        int number = random.nextInt(base.length());     
	        sb.append(base.charAt(number));     
	    }     
	    return sb.toString();     
	 } 
	public static String getEncodeText(String text)
	{
		String str="";
		try {
			str=URLEncoder.encode(text, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}
	public static String getImgPath()
	{
		return System.getProperty("user.dir")+"/imgs/";
	}
	public static String getBathPath()
	{
		return System.getProperty("user.dir");
	}
	/**
	 * XSS过滤
	 * @param html
	 * @return
	 */
	public static String filterUserInputContent(String html) {  
	    if(html==null || html.equals("")) return "";  
	    return Jsoup.clean(html, user_content_filter);  
	    //return filterScriptAndStyle(html);  
	}
	public static String getBasicHtmlandimage(String html) {
	    if (html == null)
	        return null;
	    return Jsoup.clean(html, Whitelist.basicWithImages());  //Whitelist.basicWithImages()
	}
	public static Logger getLogger()
	{
		String day=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		if(day.equals(currentDay))
			return logger;
		else {
			currentDay=day;
			Logger dayLogger = Logger.getLogger("dayLogger");  	  
			Layout layout = new PatternLayout("%d %p [%c] - %m%n");  	  
			Appender appender;
			try {
				appender = new FileAppender(layout, "./logs/"+currentDay+".log");				  
				dayLogger.addAppender(appender); 
				logger=dayLogger;
				return logger;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			return null;
		}
	}
	public static String getUUID(){ 
        String s = UUID.randomUUID().toString(); 
        //去掉“-”符号 
        return s.replaceAll("-", ""); 
    }
	public static String getSuccessScript(String msg,String url)
	{
		return "<script>alert('"+msg+"');window.location='"+url+"';</script>";
	}
	public static String getBackScript(String msg)
	{
		return "<script>alert('"+msg+"');window.history.back(-1);</script>";
	}
	public static String getErrorScript(String url)
	{
		return "<script>alert('页面不存在');window.location='"+url+"';</script>";
	}
	/**
	 * 
	 * @param imgURL  base64编码
	 * @param suffix  后缀
	 * @return
	 */
	public static String imgURLtoJPG(String imgURL,String suffix)
	{
		 try {  
			    BASE64Decoder decoder = new BASE64Decoder();
			 // Base64解码
	            byte[] bytes = decoder.decodeBuffer(imgURL);
	            for (int i = 0; i < bytes.length; ++i)
	            {
	                if (bytes[i] < 0)
	                {// 调整异常数据
	                    bytes[i] += 256;
	                }
	            }
	            // 生成jpeg图片
	            String imgName=getUUID()+"-"+suffix+".jpg";
	            String imgFilePath=getImgPath()+imgName;
	            OutputStream out = new FileOutputStream(imgFilePath);
	            out.write(bytes);
	            out.flush();
	            out.close();
	            return "/imgs/"+imgName;    
	        } catch (IOException e) {    
	            e.printStackTrace();    
	        } 
		 return null;
	}
	
	public static Thread[] findAllThreads()
	{
	    ThreadGroup group = Thread.currentThread().getThreadGroup();
	    ThreadGroup topGroup = group;

	    /* 遍历线程组树，获取根线程组 */
	    while ( group != null )
	    {
	        topGroup    = group;
	        group        = group.getParent();
	    }
	    /* 激活的线程数加倍 */
	    int estimatedSize = topGroup.activeCount() * 2;
	    Thread[] slackList = new Thread[estimatedSize];

	    /* 获取根线程组的所有线程 */
	    int actualSize = topGroup.enumerate( slackList );
	    /* copy into a list that is the exact size */
	    Thread[] list = new Thread[actualSize];
	    System.arraycopy( slackList, 0, list, 0, actualSize );
	    return (list);
	}
	
	public static boolean isThreadCreated(Thread[] threads,String name)
	{
		for (Thread thread : threads) {
			if(name.equals(thread.getName()))
				return true;
		}
		return false;
	}
	

}
