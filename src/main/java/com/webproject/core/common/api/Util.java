package com.webproject.core.common.api;

import java.io.File;
import java.io.FilenameFilter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.codec.binary.Base64;

public class Util {
	
	private static final String PLAIN_ASCII =
		      "AaEeIiOoUu"    // grave
		    + "AaEeIiOoUuYy"  // acute
		    + "AaEeIiOoUuYy"  // circumflex
		    + "AaOoNn"        // tilde
		    + "AaEeIiOoUuYy"  // umlaut
		    + "Aa"            // ring
		    + "Cc"            // cedilla
		    + "OoUu"          // double acute
		    ;
	private static final String UNICODE =
		     "\u00C0\u00E0\u00C8\u00E8\u00CC\u00EC\u00D2\u00F2\u00D9\u00F9"             
		    + "\u00C1\u00E1\u00C9\u00E9\u00CD\u00ED\u00D3\u00F3\u00DA\u00FA\u00DD\u00FD" 
		    + "\u00C2\u00E2\u00CA\u00EA\u00CE\u00EE\u00D4\u00F4\u00DB\u00FB\u0176\u0177" 
		    + "\u00C3\u00E3\u00D5\u00F5\u00D1\u00F1"
		    + "\u00C4\u00E4\u00CB\u00EB\u00CF\u00EF\u00D6\u00F6\u00DC\u00FC\u0178\u00FF" 
		    + "\u00C5\u00E5"                                                             
		    + "\u00C7\u00E7" 
		    + "\u0150\u0151\u0170\u0171" 
		    ;

		   

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private static final String PASSWORD_PATTERN = "^(?=.*\\d)(?=.*[a-zA-Z]).{6,20}$";
	private static Pattern emailPattern = Pattern.compile(EMAIL_PATTERN);
	private static Pattern passwordPattern = Pattern.compile(PASSWORD_PATTERN);
	
	public static boolean isValidEmail(String email){ 
		if(email == null)
			return false;
		Matcher matcher = emailPattern.matcher(email.trim());
		return matcher.matches();
	}
	
	public static boolean isValidPassword(String password){
		if(password == null)
			return false;
		Matcher matcher = passwordPattern.matcher(password);
		return matcher.matches();
	}
	
	public static String encode(byte[] b){
		return new String(Base64.encodeBase64(b));
	}
	
	public static byte[] decode(String s){
		return Base64.decodeBase64(s);
	}
	
	/** if isNextOrPrev = 1 prev Date as Day AND if isNextOrPrev = 0 next Date as Day
	 * @param day
	 * @param isNextOrPrev
	 * @return Date
	 */
	public static Date getNextPrevDateForDay(int day, int isNextOrPrev){
		Calendar cal = Calendar.getInstance();
		if (isNextOrPrev == 1) 
			cal.add(Calendar.DATE, -day);
		else
			cal.add(Calendar.DATE, day);
		return cal.getTime();
	}
	
	public static String onlyOneWhiteSpace(String string){
		return (string != null) ? string.replaceAll("\\s+", " ") : string;
	}
	
	public static String getFormattedCurDate(){
		return new SimpleDateFormat("MM-dd-yy").format(new Date());
	}
	
	/**traditional UUD class of java
	 */
	public static String getAnID(){
		return UUID.randomUUID().toString();
	}
	
	public static String truncateString(String string, int length){
		return string.length() > length ? string.substring(0, length - 3) + ".." : string;
	}
	
	 // remove accentued from a string and replace with ascii equivalent
    public static String convertNonAscii(String s) {
       if (s == null) return null;
       s = s.replaceAll("-", "_").replaceAll("'", "").replaceAll(" ", "_");
       StringBuilder sb = new StringBuilder();
       int n = s.length();
       for (int i = 0; i < n; i++) {
          char c = s.charAt(i);
          int pos = UNICODE.indexOf(c);
          if (pos > -1){
              sb.append(PLAIN_ASCII.charAt(pos));
          }
          else {
              sb.append(c);
          }
       }
       return sb.toString().toLowerCase();
    }
    
    public static boolean isInteger(String s) {
        return isInteger(s,10);
    }

    public static boolean isInteger(String s, int radix) {
        if(s.isEmpty()) return false;
        for(int i = 0; i < s.length(); i++) {
            if(i == 0 && s.charAt(i) == '-') {
                if(s.length() == 1) return false;
                else continue;
            }
            if(Character.digit(s.charAt(i),radix) < 0) return false;
        }
        return true;
    }
    
    /**
     * @param dirName /home/emin/perso...
     * @param extention  ".csv", ".txt" gibi
     * @return
     */
    public static File[] filesFinder( String dirName, String extention){
    	File dir = new File(dirName);

    	return dir.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String filename) {
				return filename.endsWith(extention);
			}
		});
    }
    
    public static String f(int dL, int sL){
    	return String.format("%" + (dL - sL) + "s", "");
    }
    
    public static String formatMsgForDet(String msg){
    	
    	if(msg == null || msg.isEmpty())
    		return "default";
    	msg = msg.replaceAll("&", "");
    	msg = msg.replaceAll("\\s+", "");			
    			
    	msg = msg.toLowerCase();
    	msg = msg.length()>40 ? msg.substring(0, 40) : msg;
    	return msg ;
    }
    
    public static String generateNo(){
    	Random random = new Random();
    	return String.valueOf(100000000 + random.nextInt(900000000));
    }
}
