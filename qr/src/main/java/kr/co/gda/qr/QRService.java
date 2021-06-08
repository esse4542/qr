package kr.co.gda.qr;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class QRService {
   // 장치의 이름
   public String getUserName() {
      return "NamMinJeong"; // 이름
   }
   
   // 생년 월일
   public String getUserBirth() {
	      return "2001-01-01"; // 생년월일
	   }
   
   
   // 장치의 os
   public String getSystemInfo() {
		StringBuffer sf = new StringBuffer();
		String s = System.getProperty("os.name");
		sf.append(s);
		s = System.getProperty("os.version");
		sf.append(","+s);
		return sf.toString();
	}
   
   // 장치의 ip
	public String  getNetworkInfo() throws UnknownHostException {
		StringBuffer sf = new StringBuffer();
		InetAddress address = InetAddress.getLocalHost();
		String s = address.getHostName();
		sf.append(s);
		s = address.getHostAddress();
		sf.append(","+s);
		return sf.toString();
	}
 
   
   
  
}