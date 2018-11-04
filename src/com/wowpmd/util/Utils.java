package com.wowpmd.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.kr.framework.util.Closer;
import com.wowpmd.constants.Constants;

/**
 *
 * 클래스명: <code>Utils</code>
 *
 * <pre>
 * 일반적인 Utils를 저장한다.
 * </pre>
 */
public class Utils {

    /**
     * <pre>
     *  exception 을 던지면 StackTrace문자열을 리턴한다.
     * </pre>
     * @param e
     * @return
     */
    public static String getExceptionTraceToString(Throwable e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        StringBuffer buffer = sw.getBuffer();
        Closer.close(new Object[] { sw, pw });

        return buffer != null ? buffer.toString() : "null";
    }

    /**
     * <pre>
     *  parameterTypes를 Object[]로  리턴
     * </pre>
     * @param parameterTypes
     * @return
     */
    public static Object[] getParam(Object... parameterTypes) {
        return parameterTypes;
    }

    /**
     * <pre>
     * 자바네이밍이름을 디비필드명으로 변경하여 리턴
     * </pre>
     * @param key
     * @return
     */
    public static String javaVariableNamingToDbNaming(String key) {

        if (key == null) {
            return "null";
        }

        StringBuilder buffer = new StringBuilder(key.length() + 4);
        for (int i = 0; i < key.length(); i++) {
            char ch = key.charAt(i);
            if (Character.isUpperCase(ch)) {
                buffer.append("_");
                buffer.append(ch);
            } else {
                buffer.append(Character.toUpperCase(ch));
            }
        }

        return buffer.toString();
    }

    /**
     * <pre>
     * yyyymmddhhmmss -> Date로 변경
     * </pre>
     * @param yyyymmddhhmmss
     * @return
     */
    public static Date string2Date(String yyyymmddhhmmss) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            return sdf.parse(yyyymmddhhmmss);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <pre>
     * Date -> yyyymmddhhmmss문자열로 변경
     * </pre>
     * @param time
     * @return
     */
    public static String date2String(Date time) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sf.format(time);
    }

    /**
     * <pre>
     * Date -> yyyymmddhhmmss문자열로 변경
     * </pre>
     * @param time
     * @return
     */
    public static String date2String(Date time, String format) {
        SimpleDateFormat sf = new SimpleDateFormat(format);
        return sf.format(time);
    }

    /**
     * <pre>
     * 배열을 리스트로 변경
     * </pre>
     * @param themaCategoryIdArray
     * @return
     */
    public static <T> List<T> arrayToList(T[] themaCategoryIdArray) {
        if (themaCategoryIdArray == null) {
            return new ArrayList<T>(1);
        } else {
            List<T> items = new ArrayList<T>(themaCategoryIdArray.length);
            for (T t : themaCategoryIdArray) {
                items.add(t);
            }
            return items;
        }
    }

    /**
     * <pre>
     * UrlEncoding
     * </pre>
     * @param param
     * @return
     */
    public static String encode(String param) {
        try {
            return URLEncoder.encode(param, Constants.WAS_CHARSET);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <pre>
     * UrlEncoding
     * </pre>
     * @param param
     * @return
     */
    public static String decode(String param) {
        try {
            return URLDecoder.decode(param, Constants.WAS_CHARSET);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <pre>
     * 배열의 마지막값을 리턴
     * </pre>
     * @param categoryIds
     * @param defaultValue
     * @return
     */
    public static String lastValue(String[] categoryIds, String defaultValue) {
        String result = null;
        if (null != categoryIds) {
            for (String categoryId : categoryIds) {
                result = categoryId;
            }
        }
        return result == null ? defaultValue : result;
    }

    /**
     * <pre>
     * 시작일자와 종료일자를 넣었을때 현상황을 리턴한다.
     *
     * 진행전 -1
     * 진행중 0
     * 진행종료 1
     * </pre>
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static int progress(String startDate, String endDate) {
        String today = getYyyymmdd();
        int result = 0;
        if (today.compareTo(startDate) < 0) {
            result = -1;
        } else {
            if (today.compareTo(endDate) > 0) {
                result = 1;
            } else {
                result = 0;
            }
        }

        return result;
    }

    /**
     * <pre>
     * 시작일자와 종료일자를 넣었을때 현상황을 리턴한다.
     *
     * 진행전 -1
     * 진행중 0
     * 진행종료 1
     * </pre>
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static int progressDtm(String startDate, String endDate) {
        String today = getYyyymmddHhmmss();
        int result = 0;
        if (today.compareTo(startDate) < 0) {
            result = -1;
        } else {
            if (today.compareTo(endDate) > 0) {
                result = 1;
            } else {
                result = 0;
            }
        }

        return result;
    }

    /**
     * <pre>
     * 시작일자와 종료일자를 넣었을때 현상황을 리턴한다.
     *
     * 진행전 -1
     * 진행중 0
     * 진행종료 1
     * </pre>
     * @param startDate
     * @param endDate
     * @return
     */
    public static int progress(Date startDate, String endDate) {
        return progress(getYyyymmdd(startDate), endDate);
    }

    /**
     * <pre>
     * 시작일자와 종료일자를 넣었을때 현상황을 리턴한다.
     *
     * 진행전 -1
     * 진행중 0
     * 진행종료 1
     * </pre>
     * @param startDate
     * @param endDate
     * @return
     */
    public static int progress(String startDate, Date endDate) {
        return progress(startDate, getYyyymmdd(endDate));
    }

    /**
     * <pre>
     * 시작일자와 종료일자를 넣었을때 현상황을 리턴한다.
     *
     * 진행전 -1
     * 진행중 0
     * 진행종료 1
     * </pre>
     * @param startDate
     * @param endDate
     * @return
     */
    public static int progress(Date startDate, Date endDate) {
        return progress(getYyyymmdd(startDate), getYyyymmdd(endDate));
    }

    /**
     * <pre>
     * 시작일자와 종료일자를 넣었을때 현상황을 리턴한다.
     * 시분초까지 비교~
     * 진행전 -1
     * 진행중 0
     * 진행종료 1
     * </pre>
     * @param startDate
     * @param endDate
     * @return
     */
    public static int progressDtm(Date startDate, Date endDate) {
        return progressDtm(getYyyymmddHhmmss(startDate), getYyyymmddHhmmss(endDate));
    }

    /**
     * <pre>
     * </pre>
     * @return
     */
    public static String getYyyymmdd() {
        return new SimpleDateFormat("yyyyMMdd").format(new Date());
    }


    public static String getYyyymm() {
    	return new SimpleDateFormat("yyyyMM").format(new Date());
    }

    public static String getYyyymm(String pattern) {
    	if(StringUtils.isEmpty(pattern)) {
    		pattern = "yyyyMM";
    	}

    	return new SimpleDateFormat(pattern).format(new Date());
    }

    /**
     * <pre>
     * </pre>
     * @return
     */
    public static String getYyyymmddHhmmss() {
        return new SimpleDateFormat("yyyyMMdd HH:mm:ss", Locale.KOREA).format(new Date());
    }

    /**
     * <pre>
     * </pre>
     * @param date
     * @return
     */
    public static String getYyyymmdd(Date date) {
        return new SimpleDateFormat("yyyyMMdd").format(date);
    }

    /**
     * <pre>
     * </pre>
     *
     * 시분초
     * @param date
     * @return
     */
    public static String getYyyymmddHhmmss(Date date) {
        return new SimpleDateFormat("yyyyMMdd HH:mm:ss", Locale.KOREA).format(date);
    }

    /**
     * <pre>
     * 문자열을 Boolean으로
     * </pre>
     * @param value
     * @return
     */
    public static boolean stringToBoolean(String value) {
        if (value == null) {
            return false;
        }
        if (value.equals("true")) {
            return true;
        }
        if (value.equals("on")) {
            return true;
        }
        if (value.equals("yes")) {
            return true;
        }
        if (value.equals("1")) {
            return true;
        }
        if (value.equals("Y")) {
            return true;
        }
        return false;
    }

    /**
     * <pre>
     * inputStream을 넣으면
     * </pre>
     * @param inputStream
     * @return
     */
    public static String getString(InputStream inputStream) {
        StringBuffer out = new StringBuffer();
        byte[] b = new byte[4096];
        try {
            for (int n; (n = inputStream.read(b)) != -1;) {
                out.append(new String(b, 0, n));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return out.toString();
    }

    /**
     * 리스트 쪼개기
     *
     * @param sources
     * @param divideCount
     * @return
     */
    public static <T> List<List<T>> divide(List<T> sources, int divideCount) {
        Map<Integer, List<T>> index = new HashMap<Integer, List<T>>(divideCount);
        for (int i = 0; i < divideCount; i++) {
            index.put(new Integer(i), new ArrayList<T>());
        }

        int key = 0;
        for (T item : sources) {
            List<T> localImages = index.get(key++ % divideCount);
            localImages.add(item);
        }

        List<List<T>> results = new ArrayList<List<T>>(divideCount);
        Iterator<Integer> keys = index.keySet().iterator();
        while (keys.hasNext()) {
            results.add(index.get(keys.next()));
        }

        return results;
    }

    /**
     * 임의 패스워드 구하기
     * @return
     */
    public static String getRandomPassword() {
        String[] array = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0",
                "1", "2", "3", "4", "5", "6", "7", "8", "9" };

        return StringUtils.substring(StringUtils.join(shuffle(array)), 0, 10);
    }

    /**
     * 스트링 배열 섞기
     * @param strArray
     * @return
     */
    private static String[] shuffle(String[] strArray) {
        int rIndex = 0;
        String temp = null;

        for (int i = 0; i < strArray.length - 1; i++) {
            do {
                rIndex = getRandomIndex(strArray.length);
            } while (rIndex <= i);

            temp = strArray[i];
            strArray[i] = strArray[rIndex];
            strArray[rIndex] = temp;
        }

        return strArray;
    }

    /**
     * 임의 인덱스 구하기
     * @param length
     * @return
     */
    private static int getRandomIndex(int length) {
        return ((int) (Math.random() * 100)) % length;
    }

    /**
     * Set -> List
     *
     * @param set
     * @return
     */
    public static <T> List<T> setToList(HashSet<T> set) {
        List<T> result = new ArrayList<T>(set.size());
        for (T item : set) {
            result.add(item);
        }

        return result;
    }

    /**
     * 리스트 줄이기
     *
     * @param items
     * @param cutCount
     * @return
     */
    public static <T> List<T> listCut(List<T> items, int cutCount) {
        List<T> result = null;
        if (items == null) {
            return new ArrayList<T>(1);
        } else if (items.size() <= cutCount) {
            return items;
        } else {
            result = new ArrayList<T>(cutCount);
            int size = 0;
            for (T item : items) {
                if (size++ < cutCount) {
                    result.add(item);
                } else {
                    break;
                }
            }
        }

        return result;
    }

    public static void printMark(int i) {
        System.out.print("#");
        System.out.flush();
        if (++i % 300 == 0) {
            System.out.println();
        }
    }

    /**
     * <pre>
     * </pre>
     * @param items
     * @param index
     * @return
     */
    public static <T> T nullTo(List<T> items, int index) {
        try {
            return items == null ? null : items.get(index);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public static int getGuessAmt(boolean isMain, int credPymntAmt, int totalPaymentAmt, int orderCount, int snglPrdtDscntPrice) {
        if (isMain) {
            return (int) Math.ceil((snglPrdtDscntPrice * 100 * orderCount) / (double) totalPaymentAmt * credPymntAmt / orderCount) / 100;
        } else {
            return (int) Math.round((snglPrdtDscntPrice * 100 * orderCount) / (double) totalPaymentAmt * credPymntAmt / orderCount) / 100;
        }
    }

    public static String toAscii(String str) {
        String result = null;
        if (str != null) {
            try {
                result = new String(str.getBytes(), "ISO-8859-1");
            } catch (UnsupportedEncodingException e) {
                result = str;
            }
        }

        return result;
    }

    public static String toUtf8(String str) {
        String result = null;
        if (str != null) {
            try {
                result = new String(str.getBytes("ISO-8859-1"), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                result = str;
            }
        }

        return result;
    }

    @SuppressWarnings("rawtypes")
    public static String getCurrentEnvironmentNetworkIp() {
        Enumeration netInterfaces = null;
        try {
            netInterfaces = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e) {
            return getLocalIp();
        }

        while (netInterfaces.hasMoreElements()) {
            NetworkInterface ni = (NetworkInterface) netInterfaces.nextElement();
            Enumeration address = ni.getInetAddresses();
            if (address == null) {
                return getLocalIp();
            }
            while (address.hasMoreElements()) {
                InetAddress addr = (InetAddress) address.nextElement();
                if (!addr.isLoopbackAddress() && !addr.isSiteLocalAddress() && !addr.isAnyLocalAddress()) {
                    String ip = addr.getHostAddress();
                    if (ip.indexOf(".") != -1 && ip.indexOf(":") == -1) {
                        return ip;
                    }
                }
            }
        }
        return getLocalIp();
    }

    public static String getLocalIp() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            return null;
        }
    }

    /**
     * <pre>
     *   4자리 숫자 0채워넣기 (ex : 0001, 0100, 0013)
     * </pre>
     * @param items
     * @param index
     * @return
     */
    public static String returnNumber(int serial) {
        String suffix = String.format("%04d", serial);
        return suffix;
    }

    public static boolean checkPattern(String pattern, String str){
    	boolean okPattern = false;
    	String regex = null;

    	if(StringUtils.isEmpty(str)){
    		return false;
    	}

    	pattern = pattern.trim();

    	//숫자 체크
    	if(StringUtils.equals("num", pattern)){
    		regex = "^[0-9]*$";
    	}

    	//영문 체크
    	if(StringUtils.equals("eng", pattern)){
    		regex = "^[a-zA-Z]*$";
    	}

    	//이메일 체크
    	if(StringUtils.equals("email", pattern)){
    		regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
    	}

    	//전화번호 체크
    	if(StringUtils.equals("tel", pattern)){
    		regex = "^\\d{2,3}-\\d{3,4}-\\d{4}$";
    	}

    	//휴대폰번호 체크
    	if(StringUtils.equals("phone", pattern)){
    		regex = "^01(?:0|1[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$";
    	}

    	okPattern = Pattern.matches(regex, str);

    	return okPattern;
    }

//특수문자 제거 하기
	public static String stringReplace(String str){
		String match = "[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]";
		str =str.replaceAll(match, "");
		return str;
	}


	public static boolean urlCall(String requesturl) {
		URL url = null;
		BufferedReader input = null;
		boolean returnUrlFlag = false;

		try {
			url = new URL(requesturl);
			input = new BufferedReader(new InputStreamReader(url.openStream()));
			while((input.readLine()) != null){
				returnUrlFlag = true;
				//requestMsg += line;
			}
		} catch (Exception e) {
			returnUrlFlag = false;
			//e.printStackTrace();
		}
		return returnUrlFlag;
	}


	public static int patternCount(String str, String type) {

		int count = 0;
		Pattern pattern = Pattern.compile(type);
		Matcher matcher = pattern.matcher(str);

		for(int i=0; matcher.find(i); i=matcher.end()){
			count++;
		}

		return count;


	}

}
