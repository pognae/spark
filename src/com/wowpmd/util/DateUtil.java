package com.wowpmd.util;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.kr.framework.util.SoftUtil;


/**
 * <p>Subsystem		:  </p>
 * <p>Title			: 기본적인 유틸함수 </p>
 * <p>Description	: 기본적인 유틸함수를 구현하여 제공. </p>
 * <p>관련 TABLE		: </p>
 * @author ODS
 * @version 1.0	2014.03.25 <br/>
 */
public class DateUtil{

	/**
	 * 날짜 입력을 문자열(년-월-일 시:분:초 1000/1 초)로 변환하여 반환
	 */
	public static String date(Date d){
		if(d == null) return null;
		SimpleDateFormat smf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
		return smf.format(d);
	}

	/**
	 * 날짜 입력을 문자열(년-월-일)로 변환하여 반환
	 */
	public static String dateOnly(Date d){
		if(d == null) return null;
		SimpleDateFormat smf = new SimpleDateFormat("yyyy-MM-dd");
		return smf.format(d);
	}

	/**
      날짜 입력을 문자열(년-월-일 시:분:초)로 변환하여 반환
	 */
	public static String datetimeOnly(Date d){
		if(d == null) return null;
		SimpleDateFormat smf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return smf.format(d);
	}

	/**
      날짜 입력을 문자열(년-월-일 시:분:초)로 변환하여 반환
	 */
	public static String getCurDatetime(){
		SimpleDateFormat smf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return smf.format(new java.util.Date());
	}

	/**
      날짜 입력을 문자열을 패턴에 맞게 변환하여 반환
	 */
	public static String getTimeAsPattern(Date d, String pattern) {
		if(d == null) return null;

		if (SoftUtil.isEmpty(pattern)){
			pattern = "yyyy-MM-dd";
		}

		SimpleDateFormat smf = new SimpleDateFormat(pattern, new Locale("ko","KOREA"));
		return smf.format(d);
	}

	/**
     패턴에 따른 현재시간을 반환
	 */
	public static String getCurTime(String pattern) {
		if (SoftUtil.isEmpty(pattern)){
			pattern = "yyyy-MM-dd";
		}

		Date date = new java.util.Date();

		SimpleDateFormat formatter = new SimpleDateFormat(pattern, new Locale("ko","KOREA"));
		String result = formatter.format(date);

		return result;
	}



	/**
      현재시간을 java.util.Date 반환
	 */
	public static java.util.Date getCurTime() {

		return new java.util.Date();
	}

	/**
	  날짜및 시간 : 문자열 입력을 Date(java.util) 변환하여 반환
	 */
	public static Date str2date(String s , String pattern){
		if(s == null || s.equals("")) return null;

		if (SoftUtil.isEmpty(pattern)){
			pattern = "yyyy-MM-dd";
		}

		try {
			SimpleDateFormat smf = new SimpleDateFormat(pattern);
			return smf.parse(s, new ParsePosition(0));
		} catch (Exception e) {
			return null;
		}
	}

	/**
	  날짜및 시간 : 문자형식의 날짜를  입력 받아 특정 형태의 패턴으로 변환.
	 */
	public static String getChangDatePattern(String s , String dateFormat, String pattern){
		if(s == null || s.equals("")) return null;

		if (SoftUtil.isEmpty(pattern)){
			pattern = "yyyy-MM-dd";
		}

		try {
			SimpleDateFormat smf = new SimpleDateFormat(dateFormat);
			return getTimeAsPattern(smf.parse(s),pattern);
		} catch (Exception e) {
			return null;
		}
	}


	/**
	  날짜및 시간 : 문자형식의 date를  입력 받아 년월일만 잘라서 반환.
	 */
	public static String cutStrDate(String date){
		if(date == null || date.equals("") || date.length()<10) return date;
		return date.substring(0, 10);
	}

	/////////////////////////////////////////////
	// 날짜 차이 구하기
	//
	public static int getDaysDiff(String s1, String s2) {
		int y1 = getDaysFrom21Century(s1);
		int y2 = getDaysFrom21Century(s2);
		return y1 - y2;
	}

	//////////////////////////////////////////////////////////////////////
	// 2000년 1월 1일 부터 지정한 년, 월, 일 까지의 날짜 수를 구한다.
	// 2000년 1월 1일 이전의 경우에는 음수를 리턴한다.
	//
	public static int getDaysFrom21Century(String s) {
		int d, m, y;
		if (s.length() == 8) {
			y = Integer.parseInt(s.substring(0, 4));
			m = Integer.parseInt(s.substring(4, 6));
			d = Integer.parseInt(s.substring(6));
			return getDaysFrom21Century(d, m, y);
		}
		else if (s.length() == 10) {
			y = Integer.parseInt(s.substring(0, 4));
			m = Integer.parseInt(s.substring(5, 7));
			d = Integer.parseInt(s.substring(8));
			return getDaysFrom21Century(d, m, y);
		}
		else if (s.length() == 11) {
			d = Integer.parseInt(s.substring(0, 2));
			String strM = s.substring(3, 6).toUpperCase();
			String[] monthNames = { "JAN", "FEB", "MAR", "APR", "MAY", "JUN",
					"JUL", "AUG", "SEP", "OCT", "NOV", "DEC" };
			m = 0;
			for (int j = 1; j <= 12; j++) {
				if (strM.equals(monthNames[j-1])) {
					m = j;
					break;
				}
			}
			if (m < 1 || m > 12)
				throw new RuntimeException("Invalid month name: " + strM + " in " + s);
			y = Integer.parseInt(s.substring(7));
			return getDaysFrom21Century(d, m, y);
		}
		else
			throw new RuntimeException("Invalid date format: " + s);
	}

	////////////////////////////////////////////////////////////////////////////
	// 지정한 년도, 지정한 월의 총 날짜 수를 구한다.
	//
	public static int getDaysInMonth(int m, int y) {
		if (m < 1 || m > 12)
			throw new RuntimeException("Invalid month: " + m);

		int[] b = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		if (m != 2 && m >= 1 && m <= 12 && y != 1582)
			return b[m - 1];
		if (m != 2 && m >= 1 && m <= 12 && y == 1582)
			if (m != 10)
				return b[m - 1];
			else
				return b[m - 1] - 10;

		if (m != 2)
			return 0;

		// m == 2 (즉 2월)
				if (y > 1582) {
					if (y % 400 == 0)
						return 29;
					else if (y % 100 == 0)
						return 28;
					else if (y % 4 == 0)
						return 29;
					else
						return 28;
				}
				else if (y == 1582)
					return 28;
				else if (y > 4) {
					if (y % 4 == 0)
						return 29;
					else
						return 28;
				}
				else if (y > 0)
					return 28;
				else
					throw new RuntimeException("Invalid year: " + y);
	}


	////////////////////////////////////////////////////////////////////////////
	// 지정한 년도의 지정한 월의 첫날 부터 지정한 날 까지의 날짜 수를 구한다.
	//
	public static int getDaysFromMonthFirst(int d, int m, int y) {
		if (m < 1 || m > 12)
			throw new RuntimeException("Invalid month " + m + " in " + d + "/" + m + "/" + y);

		int max = getDaysInMonth(m, y);
		if (d >= 1 && d <= max)
			return d;
		else
			throw new RuntimeException("Invalid date " + d + " in " + d + "/" + m + "/" + y);
	}

	////////////////////////////////////////////////////////////////////////////
	// 지정한 년도의 첫날 부터 지정한 월의 지정한 날 까지의 날짜 수를 구한다.
	//
	public static int getDaysFromYearFirst(int d, int m, int y) {
		if (m < 1 || m > 12)
			throw new RuntimeException("Invalid month " + m + " in " + d + "/" + m + "/" + y);

		int max = getDaysInMonth(m, y);
		if (d >= 1 && d <= max) {
			int sum = d;
			for (int j = 1; j < m; j++)
				sum += getDaysInMonth(j, y);
			return sum;
		}
		else
			throw new RuntimeException("Invalid date " + d + " in " + d + "/" + m + "/" + y);
	}

	public static String getDateFormat(String format , String dateString) {
		SimpleDateFormat sd = new SimpleDateFormat( format );
		String rtValue = null;
		try{
			Date d = sd.parse( dateString );
			rtValue = sd.format( d );
		}catch( ParseException e ){}
		return rtValue;
	}

	////////////////////////////////////////////////////////////////////////////
	// 지정한 년도의 첫날 부터 지정한 월의 지정한 날 까지의 날짜 수를 구한다.
	//
	public static int getDaysFromYearFirst(String s) {
		int d, m, y;
		if (s.length() == 8) {
			y = Integer.parseInt(s.substring(0, 4));
			m = Integer.parseInt(s.substring(4, 6));
			d = Integer.parseInt(s.substring(6));
			return getDaysFromYearFirst(d, m, y);
		}
		else if (s.length() == 10) {
			y = Integer.parseInt(s.substring(0, 4));
			m = Integer.parseInt(s.substring(5, 7));
			d = Integer.parseInt(s.substring(8));
			return getDaysFromYearFirst(d, m, y);
		}
		else if (s.length() == 11) {
			d = Integer.parseInt(s.substring(0, 2));
			String strM = s.substring(3, 6).toUpperCase();
			String[] monthNames = { "JAN", "FEB", "MAR", "APR", "MAY", "JUN",
					"JUL", "AUG", "SEP", "OCT", "NOV", "DEC" };
			m = 0;
			for (int j = 1; j <= 12; j++) {
				if (strM.equals(monthNames[j-1])) {
					m = j;
					break;
				}
			}
			if (m < 1 || m > 12)
				throw new RuntimeException("Invalid month name: " + strM + " in " + s);
			y = Integer.parseInt(s.substring(7));
			return getDaysFromYearFirst(d, m, y);
		}
		else
			throw new RuntimeException("Invalid date format: " + s);
	}

	////////////////////////////////////////////////////////////////////////////
	// 2000년 1월 1일 부터 지정한 년, 월, 일 까지의 날짜 수를 구한다.
	// 2000년 1월 1일 이전의 경우에는 음수를 리턴한다.
	//
	public static int getDaysFrom21Century(int d, int m, int y) {
		if (y >= 2000) {
			int sum = getDaysFromYearFirst(d, m, y);
			for (int j = y - 1; j >= 2000; j--)
				sum += getDaysInYear(j);
			return sum - 1;
		}
		else if (y > 0 && y < 2000) {
			int sum = getDaysFromYearFirst(d, m, y);
			for (int j = 1999; j >= y; j--)
				sum -= getDaysInYear(y);
			return sum - 1;
		}
		else
			throw new RuntimeException("Invalid year " + y + " in " + d + "/" + m + "/" + y);
	}

	////////////////////////////////////////////////////////////////////////////
	// 지정한 년도의 총 날짜 수를 구한다.
	//
	public static int getDaysInYear(int y) {
		if (y > 1582) {
			if (y % 400 == 0)
				return 366;
			else if (y % 100 == 0)
				return 365;
			else if (y % 4 == 0)
				return 366;
			else
				return 365;
		}
		else if (y == 1582)
			return 355;
		else if (y > 4) {
			if (y % 4 == 0)
				return 366;
			else
				return 365;
		}
		else if (y > 0)
			return 365;
		else
			return 0;
	}

}
