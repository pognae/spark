package com.wowpmd.util;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Enumeration;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


/**
 * <p>Subsystem		:  </p>
 * <p>Title			: 기본적인 유틸함수 </p>
 * <p>Description	: 기본적인 유틸함수를 구현하여 제공. </p>
 * <p>관련 TABLE		: </p>
 * @author 이동엽
 * @version 1.0	2014.03.10 <br/>
 */
public class SoftUtil{

	/* spring security 에서 로그인 유무 체크 */
	public static boolean checkLogin(String isLogin){

		//String isLogin = SoftUtil.print(config.getProperty("login.isLogin"));

		boolean flag = "true".equals(isLogin)?true:false;

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName(); //get logged in username

		//로그인 프로퍼티 값이 true 이고 로그인을 하지 않을 상태라면 false 로그인 했으면 true
		if("true".equals(isLogin)){
			if("anonymousUser".equals(name)) flag = false;
			else flag = true;
		} else {
			flag = true;
		}

		return flag;
	}

	/**
		null을 ""으로
	 */
	public  static String print(Object o){
		if (o==null){
			return "";
		}

		return o.toString();
	}

	/**
		null을 특정문자로 바꾸어서 나타낸다.
	 */
	public  static String print(Object o, String rep){
		if (isEmpty(o)){
			return rep;
		}
		return o.toString();
	}


	/**
		Object가 NULL인지를 확인한다.
	 */
	public static boolean isEmpty(Object obj){
		if(obj ==null)
			return true;
		if( obj instanceof String && "".equals(((String)obj).trim())  )
			return true;
		return false;
	}


	/**
	   숫자(금액)를 문자열로 변환하여 반환(단, 숫자는 long type)
	 */
	public  static String money(long n){
		DecimalFormat df = new DecimalFormat("#,##0");

		return df.format(n);

	}




	/**
	 * String 포멧에 맞게 변환
	 * @param int div, String divChr
	 * @return String
	 */
	public static String setNumFormat(String numStr) {

		int li_strLen;
		String ls_result;

		li_strLen = numStr.length();

		switch(li_strLen){
		// 우편번호
		case 6:
			ls_result = numStr.substring(0,3) + "-" + numStr.substring(3,6);
			break;
			// 사업자등록번호
		case 10:
			ls_result = numStr.substring(0,3) + "-" + numStr.substring(3,5) + "-" + numStr.substring(5,10);
			break;
			// 주민등록번호
		case 13:
			ls_result = numStr.substring(0,6) + "-" + numStr.substring(6,13);
			break;
		default:
			ls_result = numStr;
			break;
		}


		return ls_result;
	}


	/**
	정수변환 : 문자열 입력을 정수로 변환하여 반환
	 */
	public  static int str2int(String str)  {
		int li = 0 ;

		try {
			if (str == null || str.equals("")) return 0;
			li = Integer.parseInt(str);

		} catch (Exception e) {
			System.out.println(e);
		}

		return li ;
	}


	/**
	long 변환 : 문자열 입력을 long으로 변환하여 반환
	 */
	public  static long str2long(String str)  {
		long li = 0 ;

		try {
			if (str == null || str.equals("")) return 0;
			li = Long.parseLong(str);

		} catch (Exception e) {
			System.out.println(e);
		}

		return li ;
	}

	/**
	array 변환 : 문자열 입력을 구분자로 구분하여 배열로 변환하여 반환
	 */
	public  static String[] str2strs(String str, String delim)  {
		String[] strs= null;
		if(!isEmpty(str)){ //배열에 담는다.
			StringTokenizer st= new StringTokenizer(str,delim);
			int size= st.countTokens();
			int idx= 0;
			strs= new String[size];
			while(st.hasMoreTokens()){
				strs[idx]= st.nextToken();
				idx++;
			}
		}

		return strs;
	}

	/**
	 * Key값을 boolean으로 리턴한다.
	 * true/false의 대소문자는 고려하지 않는다.
	 */
	public  static boolean str2Boolean(String srt) {

		if("TRUE".equalsIgnoreCase(srt) == true)
			return true;
		else
			return false;

	}

	/**
	 * "0"는 공백으로 처리
	 */
	public  static String str2NotZero(String srt) {

		if(srt.equals("0") )
			return "";
		else
			return srt;

	}

	/**
	 * Key값 Y,N 을 boolean으로 리턴한다.
	 * Y,N 의 대소문자는 고려하지 않는다.
	 */
	public  static boolean yn2Boolean(String strYN) {

		if("Y".equalsIgnoreCase(strYN) == true)
			return true;
		else
			return false;

	}
	/**
	 * 문자 인코딩 변환(8859_1==>utf-8)
	 * @param s
	 * @return
	 */
	public static String eng2utf8(String s) {
		if(s == null) return s;
		try{
			return new String(s.getBytes("8859_1"), "utf-8");
		}catch(Exception e){
		}
		return s;
	}

	/**
	 * 문자 인코딩 변환(8859_1==>ksc5601)
	 * @param s
	 * @return
	 */
	public static String eng2kor(String s) {
		if(s == null) return s;
		try{
			return new String(s.getBytes("8859_1"), "ksc5601");
		}catch(Exception e){
		}
		return s;
	}

	/**
	 * 문자 인코딩 변환(ksc5601==>utf-8)
	 * @param s
	 * @return
	 */
	public static String kor2utf8(String s) {
		if(s == null) return s;
		try{
			return new String(s.getBytes("ksc5601"), "utf-8");
		}catch(Exception e){
		}
		return s;
	}

	/**
	 * 문자 인코딩 변환(utf-8==>ksc5601)
	 * @param s
	 * @return
	 */
	public static String utf82kor(String s) {
		if(s == null) return s;
		try{
			return new String(s.getBytes("utf-8"), "ksc5601");
		}catch(Exception e){
		}
		return s;
	}

	/**
	 * 문자 인코딩 변환(utf-8==>8859_1)
	 * @param s
	 * @return
	 */
	public static String kor2eng(String s) {
		if(s == null) return s;
		try{
			return new String(s.getBytes("ksc5601"), "8859_1");
		}catch(Exception e){
		}
		return s;
	}

	/**
	 * html 형태로 소스 변환
	 * @param s
	 * @return
	 */
	public static String java2html(String s) {
		if ( s == null ) return null;

		StringBuffer buf = new StringBuffer();
		char[] c = s.toCharArray();
		int len = c.length;
		for ( int i=0; i < len; i++) {
			if      ( c[i] == '&' ) buf.append("&amp;");
			else if ( c[i] == '<' ) buf.append("&lt;");
			else if ( c[i] == '>' ) buf.append("&gt;");
			else if ( c[i] == '"' ) buf.append("&quot;");
			else if ( c[i] == '\'') buf.append("&#039;");
			else if ( c[i] == '\n') buf.append("<br>");
			else buf.append(c[i]);
		}
		return buf.toString();
	}




	/**
	 *	 request로 부터 모든 parameter 이름(key)과 해당 값(value)을 얻어
	 *	name1 = vaue1
	 *	name2 = vaue2
	 *   형태로 출력해준다
	 *	@param : HttpServletRequest request
	 *	@return: String
	 */
	public static String reportParameters(HttpServletRequest request){

		StringBuffer l_tStringBuffer = null;
		Enumeration names = null;

		l_tStringBuffer = new StringBuffer();
		names = request.getParameterNames();

		l_tStringBuffer.append("\n");

		if(names.hasMoreElements()) {
			while(names.hasMoreElements()) {
				String name = (String)names.nextElement();
				String lss_values[] = request.getParameterValues(name);
				int i = 0;
				while(i < lss_values.length)
				{
					l_tStringBuffer.append("    ");
					l_tStringBuffer.append(name);
					l_tStringBuffer.append(" = ");
					l_tStringBuffer.append(lss_values[i]);
					l_tStringBuffer.append("\n");
					i++;
				}
			}
		}

		return l_tStringBuffer.toString();
	}


	/**
	 * 수학의 차집합을 배열로 반환
	 * @param String[] firstArray, String[] secondArray
	 * @return   String[]
	 * @throws Exception
	 */
	public static String[] getDifferenceOfSets(String[] firstArray, String[] secondArray)  {

		Vector temp=new Vector();
		for (int i=0; i < firstArray.length ; i++){
			boolean isSame=false;
			if (secondArray !=null ){
				for (int j=0;j< secondArray.length; j++){
					if (firstArray[i].equals(secondArray[j])){
						isSame=true;
						break ;
					}
				}
			}

			if (!isSame){
				temp.add(firstArray[i]);
			}
		}//end for (int i=0; i < firstArray.length ; i++)
		String[] result= (String[])temp.toArray(new String[0]);
		return result;

	}


	/**
	 *  코드 칸 맞추기 - f_code_make
	 * @param
	 * @return  DataEntity
	 */
	public static String f_code_make(String as_code, int ai_size) throws Exception {
		StringBuffer zero = new StringBuffer();
		try {
			if (!(as_code == null || as_code.equals(""))) {
				int li_code_size = ai_size - as_code.length();
				for(int i=0; i<li_code_size; i++){
					zero.append("0");
				}
				return zero.toString() + as_code;
			} else {
				return "";
			}
		}catch (Exception e) {
			throw e;
		}
	}

	/**
	 * html 인코딩 내용을 html 코드로 치환
	 * @param str
	 * @return
	 */
	public static String viewRemark(String str) {

		str = rplc(str,"''","'");
		str = rplc(str,"&quot;","\"");
		str = rplc(str," ","&nbsp;");
		str = rplc(str,"\n","<br>");

		return str;
	}

	/**
	 * html 인코딩 내용을 html 코드로 치환( br==>\r\n 으로 \r\n==>br로 변환)
	 * @param str
	 * @return
	 */
	public static String viewRemark02(String str) {
		str = rplc(str, "\r\n","<br>");
		str = rplc(str, "<br>","\\r\\n");
		return str;
	}


	/**
	 * 자바스크립트에 쓰일 문자를 변환한다.
	 * @param str
	 * @return
	 */
	public static String transRemarkJavaScript( String str ){

		if(str== null || (str.indexOf("'")==-1 && str.indexOf("\"")==-1) ) return str;

		StringBuffer sb= new StringBuffer();

		int cnt= str.length();
		String ch= "";
		for(int i=0;i<cnt;i++){
			ch= str.charAt(i)+"";
			if(ch.equals( "'"))  ch="\\'";
			if(ch.equals( "\""))  ch="\\'";
			sb.append(ch);
		}

		return sb.toString();
	}


	/**
	 * 문자열에서 특정 패턴 치환
	 * @param str
	 * @param pattern
	 * @param replace
	 * @return
	 */
	public static String rplc(String str, String pattern, String replace) {
		int s = 0;
		int e = 0;
		StringBuffer result = new StringBuffer();

		while ((e = str.indexOf(pattern, s)) >= 0) {
			result.append(str.substring(s, e));
			result.append(replace);
			s = e + pattern.length();
		}
		result.append(str.substring(s));
		return result.toString();
	}


	/**
	 * 제목 자르기
	 * @param title
	 * @param maxNum
	 * @return
	 */
	public static String getTitleLimit(String title,int maxNum) {
		int blankLen = 0;
		int tLen =title.length();
		int count = 0;
		char c;
		int s=0;
		for(s=0;s<tLen;s++){
			c = title.charAt(s);
			if((count) > maxNum-blankLen){
				break;
			}

			if(c>127) count +=2 ;
			else count ++;
		}
		return (tLen >s)? title.substring(0,s)+".." : title;
	}


	/**
	 *
	 * 매개변수로 들어온 문자열에서 '-'을 찾아 제거하고 	//CH03
	 * '-'이 없는 연결된 문자열을 Return한다.

	 */
	public static String stripHyphen(String org){

		if (org == null) {
			org = "";
		} else {
			for(;;){
				int index = org.indexOf("-");

				if(index >=0){
					org=org.substring(0,index)+org.substring(index+1);
				}else{
					break;
				}
			}
		}

		return org;
	}


	/**
	 * 파일 사이즈를 반환한다.
	 * 바이트로 입력된 값을 K,M,G로 반환
	 * @param fileSize
	 * @return
	 */
	public static String getFileSize(String fileSize){

		long fsize = str2long(fileSize);

		long ONE_KByte = 1024;
		long ONE_MByte = ONE_KByte * 1024;
		long ONE_GByte = ONE_MByte * 1024;
		long ONE_TByte = ONE_GByte * 1024;

		if(fsize == 0){
			return "0KB";
		}else if(fsize < ONE_KByte){
			return "1KB";
		}else if(fsize < ONE_MByte){
			return ((int) Math.ceil(fsize/ONE_KByte) )+"KB";
		}else if(fsize < ONE_TByte){
			return ((int) Math.ceil(fsize/ONE_MByte) )+"MB";
		}else{
			return ((int) Math.ceil(fsize/ONE_GByte) )+"GB";
		}

	}

	/**
	 * BASE64 Encoder
	 *
	 * @param str
	 * @return
	 * @throws java.io.IOException
	 */
	public static String base64Encode(String str) {
		String result = "";
		sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
		byte[] b1 = str.getBytes();
		result = encoder.encode(b1);
		return result;
	}

	/**
	 * BASE64 Decoder
	 *
	 * @param str
	 * @return
	 * @throws java.io.IOException
	 */
	public static String base64Decode(String str) {
		String result = "";
		try {
			sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
			byte[] b1 = decoder.decodeBuffer(str);
			result = new String(b1);
		} catch (IOException ex) {
		}
		return result;
	}



	/*
	 * 한글 포함 유무 조사 한글이면 true
	 * @param str
	 * @return boolean
	 */
	public static boolean isHangle(String in) {
		boolean flag = false;
		String[] str = new String[in.length()];

		for(int i=0; i<in.length();i++){
			str[i] = in.substring(i,i+1);
		}

		for (int i=0;i<str.length;i++) {
			char[] charArray=str[i].toCharArray();
			for (int j=0; j<charArray.length; j++) {

				if (charArray[j]>='\uAC00' && charArray[j]<='\uD7A3')
					flag = true;
			}
		}

		return flag;
	}

}
