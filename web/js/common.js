/*
 * @(#)common.js 1.02016/04/12
 *
 * Copyright (c) 2016 WOWPMD Co., Inc. All rights reserved.
 */

/**
 * 공통 기능을 지원하는 스크립트이다.
 *
 * 날짜
 *
 * =============================================================================
 * Name             Description
 * -----------------------------------------------------------------------------
 * lastDate         마지막 일자를 반환한다.
 * parseDate        파싱한 날짜를 반환한다.
 * formatDate       포맷한 날짜를 반환한다.
 * =============================================================================
 *
 * 문자열
 *
 * =============================================================================
 * Name             Description
 * -----------------------------------------------------------------------------
 * bytes            문자열의 바이트 배열길이를 반환한다.
 * ellipsis         문자열의 일정한 길이만큼만 반환한다.
 * quota            문자열의 전역의 인용부호를 치환한다.
 * meta             문자열의 정규식 특수문자를 치환한다.
 * trim             문자열의 측면의 특정문자를 제거한다.
 * btrim            문자열의 양측의 특정문자를 제거한다.
 * ltrim            문자열의 좌측의 특정문자를 제거한다.
 * rtrim            문자열의 우측의 특정문자를 제거한다.
 * strip            문자열의 전역의 특정문자를 제거한다.
 * pad              문자열의 측면에 특정문자를 덧붙인다.
 * lpad             문자열의 좌측에 특정문자를 덧붙인다.
 * rpad             문자열의 우측에 특정문자를 덧붙인다.
 * mask             문자열을 일정한 패턴으로 마스킹한다.
 * isEmpty          문자열이 널 문자열인지 확인한다.
 * isBlank          문자열이 빈 문자열인지 확인한다.
 * isBytes          문자열의 바이트 배열길이를 확인한다.
 * isLength         문자열의 캐릭터 배열길이를 확인한다.
 * isNumeric        문자열이 숫자인지 확인한다.
 * isInteger        문자열이 정수인지 확인한다.
 * isDecimal        문자열이 실수인지 확인한다.
 * isDate           문자열이 날짜인지 확인한다.
 * isTime           문자열이 시간인지 확인한다.
 * isPhone          문자열이 유선전화번호인지 확인한다.
 * isMobile         문자열이 무선전화번호인지 확인한다.
 * isEmail          문자열이 전자우편주소인지 확인한다.
 * isPostcode       문자열이 배달우편번호인지 확인한다.
 * isResRegNo       문자열이 주민등록번호인지 확인한다.
 * isCorRegNo       문자열이 법인등록번호인지 확인한다.
 * isForRegNo       문자열이 외국인등록번호인지 확인한다.
 * isBizRegNo       문자열이 사업자등록번호인지 확인한다.
 * isAlpha          문자열이 영어인지 확인한다.
 * isKorean         문자열이 한글인지 확인한다.
 * isAlphaNumeric   문자열이 영어와 숫자인지 확인한다.
 * isKoreanNumeric  문자열이 한글과 숫자인지 확인한다.
 * isImage          문자열이 이미지 파일인지 확인한다.
 * isUpload         문자열이 업로드 파일인지 확인한다.
 * isIp             문자열이 아이피인지 확인한다.
 * isMac            문자열이 맥주소인지 확인한다.
 * toNumeric        문자열을 숫자로 변환한다.
 * toInteger        문자열을 정수로 변환한다.
 * toDecimal        문자열을 실수로 변환한다.
 * toCurrency       문자열을 통화로 변환한다.
 * toDate           문자열을 날짜로 변환한다.
 * toTime           문자열을 시간으로 변환한다.
 * toPhone          문자열을 유선전화번호로 변환한다.
 * toMobile         문자열을 무선전화번호로 변환한다.
 * toPostcode       문자열을 배달우편번호로 변환한다.
 * toResRegNo       문자열을 주민등록번호로 변환한다.
 * toCorRegNo       문자열을 법인등록번호로 변환한다.
 * toForRegNo       문자열을 외국인등록번호로 변환한다.
 * toBizRegNo       문자열을 사업자등록번호로 변환한다.
 * toMac            문자열을 맥주소로 변환한다.
 * =============================================================================
 *
 * 플러그인
 * =============================================================================
 * Name             Description
 * -----------------------------------------------------------------------------
 * decimal          실수마스크 플러그인을 생성한다.
 * =============================================================================
 *
 * 기타
 * =============================================================================
 * Name             Description
 * -----------------------------------------------------------------------------
 * openWindow       윈도우를 띄운다.
 * =============================================================================
 *
 * @author 이동엽
 * @version 1.0 2016/04/12
 */

/**
 * 네임스페이스
 */
var com = { wow:{ fn:{} } };

////////////////////////////////////////////////////////////////////////////////
// 날짜
////////////////////////////////////////////////////////////////////////////////
/**
 * 마지막 일자를 반환한다.
 *
 * Usage: com.wow.fn.lastDate(date)
 *
 * @param d {Date} 날짜
 * @returns {Number} 마지막 일자
 */
com.wow.fn.lastDate = function(d) {
    var date = d ? d : new Date();

    var month = date.getMonth() + 1;

    switch (month) {
        case 2:
            var year = date.getFullYear();

            if (year % 4 == 0 && year % 100 != 0) {
                return 29;
            }

            if (year % 400 == 0) {
                return 29;
            }

            return 28;
        case 4:
        case 6:
        case 9:
        case 11:
            return 30;
    }

    return 31;
};

/**
 * 파싱한 날짜를 반환한다.
 *
 * Usage: com.wow.fn.parseDate(string)
 *        com.wow.fn.parseDate(string, parsePattern)
 *
 * Pattern:
 * =============================================================================
 * Letters          Component
 * -----------------------------------------------------------------------------
 * yyyy             Year
 * MM               Month
 * dd               Date
 * HH               Hour
 * mm               Minute
 * ss               Second
 * SSS              Milli-Second
 * =============================================================================
 *
 * @param s {String} 문자열
 * @param p {String} 파싱패턴
 * @returns {Date} 날짜
 */
com.wow.fn.parseDate = function(s, p) {
    var string        = s ? s : "";
    var parsePattern  = p ? p : "yyyy-MM-dd";

    if (string.length != parsePattern.length) {
        return null;
    }

    var year        = parsePattern.indexOf("yyyy") >= 0 ? string.substr(parsePattern.indexOf("yyyy"), 4) : "";
    var month       = parsePattern.indexOf("MM")   >= 0 ? string.substr(parsePattern.indexOf("MM"),   2) : "";
    var day         = parsePattern.indexOf("dd")   >= 0 ? string.substr(parsePattern.indexOf("dd"),   2) : "";
    var hour        = parsePattern.indexOf("HH")   >= 0 ? string.substr(parsePattern.indexOf("HH"),   2) : "";
    var minute      = parsePattern.indexOf("mm")   >= 0 ? string.substr(parsePattern.indexOf("mm"),   2) : "";
    var second      = parsePattern.indexOf("ss")   >= 0 ? string.substr(parsePattern.indexOf("ss"),   2) : "";
    var millisecond = parsePattern.indexOf("SSS")  >= 0 ? string.substr(parsePattern.indexOf("SSS"),  3) : "";

    if (!com.wow.fn.isDate(year + month + day, "yyyyMMdd")) {
        return null;
    }

    var iYear        = com.wow.fn.toInteger(year);
    var iMonth       = com.wow.fn.toInteger(month) - 1;
    var iDay         = com.wow.fn.toInteger(day);
    var iHour        = !com.wow.fn.isBlank(hour)        ? com.wow.fn.toInteger(hour)        : 0;
    var iMinute      = !com.wow.fn.isBlank(minute)      ? com.wow.fn.toInteger(minute)      : 0;
    var iSecond      = !com.wow.fn.isBlank(second)      ? com.wow.fn.toInteger(second)      : 0;
    var iMillisecond = !com.wow.fn.isBlank(millisecond) ? com.wow.fn.toInteger(millisecond) : 0;

    return new Date(iYear, iMonth, iDay, iHour, iMinute, iSecond, iMillisecond);
};

/**
 * 포맷한 날짜를 반환한다.
 *
 * Usage: com.wow.fn.formatDate(date)
 *        com.wow.fn.formatDate(date, pattern)
 *
 * Pattern:
 * =============================================================================
 * Letters          Component
 * -----------------------------------------------------------------------------
 * yyyy             Year
 * MM               Month
 * dd               Date
 * HH               Hour
 * mm               Minute
 * ss               Second
 * SSS              Milli-Second
 * =============================================================================
 *
 * @param d {Date} 날짜
 * @param p {String} 패턴
 * @returns {String} 날짜
 */
com.wow.fn.formatDate = function(d, p) {
    var date    = d ? d : new Date();
    var pattern = p ? p : "yyyy-MM-dd";

    return pattern.replace(new RegExp("(yyyy|MM|dd|HH|mm|ss|SSS)", "g"), function($1) {
        switch ($1) {
            case "yyyy":
                var year = date.getFullYear();

                return com.wow.fn.lpad(year.toString(), 4);
            case "MM":
                var month = date.getMonth() + 1;

                return com.wow.fn.lpad(month.toString(), 2);
            case "dd":
                var day = date.getDate();

                return com.wow.fn.lpad(day.toString(), 2);
            case "HH":
                var hour = date.getHours();

                return com.wow.fn.lpad(hour.toString(), 2);
            case "mm":
                var minute = date.getMinutes();

                return com.wow.fn.lpad(minute.toString(), 2);
            case "ss":
                var second = date.getSeconds();

                return com.wow.fn.lpad(second.toString(), 2);
            case "SSS":
                var millisecond = date.getMilliseconds();

                return com.wow.fn.lpad(millisecond.toString(), 3);
        }
    });
};

////////////////////////////////////////////////////////////////////////////////
// 문자열
////////////////////////////////////////////////////////////////////////////////
/**
 * 문자열의 바이트 배열길이를 반환한다.
 *
 * Usage: com.wow.fn.bytes(string)
 *
 * @param s {String} 문자열
 * @returns {Number} 길이
 */
com.wow.fn.bytes = function(s) {
    var string = s ? s : "";

    return string.length + (escape(string) + "%u").match(new RegExp("%u", "g")).length - 1;
};

/**
 * 문자열의 일정한 길이만큼만 반환한다.
 *
 * Usage: com.wow.fn.ellipsis(string, length)
 *        com.wow.fn.ellipsis(string, length, mark)
 *
 * @param s {String} 문자열
 * @param l {Number} 길이
 * @param m {String} 생략 부호
 * @returns {String} 문자열
 */
com.wow.fn.ellipsis = function(s, l, m) {
    var string = s ? s : "";
    var length = l ? l : string.length;
    var mark   = m ? m : "...";

    if (string.length > length) {
        return string.substr(0, length) + mark;
    }

    return string;
};

/**
 * 문자열의 전역의 인용부호를 치환한다.
 *
 * Usage: com.wow.fn.quota(string)
 *
 * @param s {String} 문자열
 * @returns {String} 문자열
 */
com.wow.fn.quota = function(s) {
    var string = s ? s : "";

    return string.replace(new RegExp("\"", "g"), "&#34;").replace(new RegExp("\'", "g"), "&#39;");
};

/**
 * 문자열의 정규식 특수문자를 치환한다.
 *
 * Usage: com.wow.fn.meta(string)
 *
 * @param s {String} 문자열
 * @returns {String} 문자열
 */
com.wow.fn.meta = function(s) {
    var string = s ? s : "";

    var replace = "";

    var pattern = new RegExp("([\\$\\(\\)\\*\\+\\.\\[\\]\\?\\\\\\^\\{\\}\\|]{1})", "");

    for (var i = 0; i < string.length; i++) {
        if (pattern.test(string.charAt(i))) {
            replace += string.charAt(i).replace(pattern, "\\$1");
        }
        else {
            replace += string.charAt(i);
        }
    }

    return replace;
};

/**
 * 문자열의 측면의 특정문자를 제거한다.
 *
 * Usage: com.wow.fn.trim(string)
 *        com.wow.fn.trim(string, character)
 *        com.wow.fn.trim(string, character, "both|left|right")
 *
 * @param s {String} 문자열
 * @param c {String} 문자
 * @param d {String} 방향
 * @returns {String} 문자열
 */
com.wow.fn.trim = function(s, c, d) {
    var string    = s ? s : "";
    var character = c ? c : "\\s";
    var direction = d ? d : "both";

    switch (direction) {
        case "both":
            return com.wow.fn.btrim(string, character);
        case "left":
            return com.wow.fn.ltrim(string, character);
        case "right":
            return com.wow.fn.rtrim(string, character);
    }

    return string;
};

/**
 * 문자열의 양측의 특정문자를 제거한다.
 *
 * Usage: com.wow.fn.btrim(string)
 *        com.wow.fn.btrim(string, character)
 *
 * @param s {String} 문자열
 * @param c {String} 문자
 * @returns {String} 문자열
 */
com.wow.fn.btrim = function(s, c) {
    var string    = s ? s : "";
    var character = c ? c : "\\s";

    character = character == "\\s" ? character : com.wow.fn.meta(character);

    return string.replace(new RegExp("(^" +character + "*)|(" + character + "*$)", "g"), "");
};

/**
 * 문자열의 좌측의 특정문자를 제거한다.
 *
 * Usage: com.wow.fn.ltrim(string)
 *        com.wow.fn.ltrim(string, character)
 *
 * @param s {String} 문자열
 * @param c {String} 문자
 * @returns {String} 문자열
 */
com.wow.fn.ltrim = function(s, c) {
    var string    = s ? s : "";
    var character = c ? c : "\\s";

    character = character == "\\s" ? character : com.wow.fn.meta(character);

    return string.replace(new RegExp("(^" + character + "*)", "g"), "");
};

/**
 * 문자열의 우측의 특정문자를 제거한다.
 *
 * Usage: com.wow.fn.rtrim(string)
 *        com.wow.fn.rtrim(string, character)
 *
 * @param s {String} 문자열
 * @param c {String} 문자
 * @returns {String} 문자열
 */
com.wow.fn.rtrim = function(s, c) {
    var string    = s ? s : "";
    var character = c ? c : "\\s";

    character = character == "\\s" ? character : com.wow.fn.meta(character);

    return string.replace(new RegExp("(" + character + "*$)", "g"), "");
};

/**
 * 문자열의 전역의 특정문자를 제거한다.
 *
 * Usage: com.wow.fn.strip(string)
 *        com.wow.fn.strip(string, character)
 *
 * @param s {String} 문자열
 * @param c {String} 문자
 * @returns {String} 문자열
 */
com.wow.fn.strip = function(s, c) {
    var string    = s ? s : "";
    var character = c ? c : "\\s";

    character = character == "\\s" ? character : com.wow.fn.meta(character);

    return string.replace(new RegExp("[" + character + "]", "g"), "");
};

/**
 * 문자열의 측면에 특정문자를 덧붙인다.
 *
 * Usage: com.wow.fn.pad(string, length)
 *        com.wow.fn.pad(string, length, character)
 *        com.wow.fn.pad(string, length, character, "left|right")
 *
 * @param s {String} 문자열
 * @param l {Number} 길이
 * @param c {String} 문자
 * @param d {String} 방향
 * @returns {String} 문자열
 */
com.wow.fn.pad = function(s, l, c, d) {
    var string    = s ? s : "";
    var length    = l ? l : 0;
    var character = c ? c : "0";
    var direction = d ? d : "left";

    switch (direction) {
        case "left":
            return com.wow.fn.lpad(string, length, character);
        case "right":
            return com.wow.fn.rpad(string, length, character);
    }

    return string;
};

/**
 * 문자열의 좌측에 특정문자를 덧붙인다.
 *
 * Usage: com.wow.fn.lpad(string, length)
 *        com.wow.fn.lpad(string, length, character)
 *
 * @param s {String} 문자열
 * @param l {Number} 길이
 * @param c {String} 문자
 * @returns {String} 문자열
 */
com.wow.fn.lpad = function(s, l, c) {
    var string    = s ? s : "";
    var length    = l ? l : 0;
    var character = c ? c : "0";

    var padding = "";

    if (string.length < length) {
        for (var i = 0; i < length - string.length; i++) {
            padding += character;
        }
    }

    return padding + string;
};

/**
 * 문자열의 우측에 특정문자를 덧붙인다.
 *
 * Usage: com.wow.fn.rpad(string, length)
 *        com.wow.fn.rpad(string, length, character)
 *
 * @param s {String} 문자열
 * @param l {Number} 길이
 * @param c {String} 문자
 * @returns {String} 문자열
 */
com.wow.fn.rpad = function(s, l, c) {
    var string    = s ? s : "";
    var length    = l ? l : 0;
    var character = c ? c : "0";

    var padding = "";

    if (string.length < length) {
        for (var i = 0; i < length - string.length; i++) {
            padding += character;
        }
    }

    return string + padding;
};

/**
 * 문자열을 일정한 패턴으로 마스킹한다.
 *
 * Usage: com.wow.fn.mask(string, pattern)
 *        com.wow.fn.mask(string, pattern, delimiter)
 *
 * @param s {String} 문자열
 * @param p {String} 패턴
 * @param d {String} 구분자
 * @returns {String} 문자열
 */
com.wow.fn.mask = function(s, p, d) {
    var string    = s ? s : "";
    var pattern   = p ? p : "";
    var delimiter = d ? d : "-";

    var masked    = "";

    if (string.length == pattern.replaceAll(delimiter, "").length) {
        var array = pattern.split(delimiter);

        var index = 0;

        for (var i = 0; i < array.length; i++) {
            if (i > 0) {
                masked += delimiter;
            }

            var length = array[i].length;

            masked += string.substring(index, index + length);

            index += length;
        }
    }
    else {
        masked = string
    }

    return masked;
}

/**
 * 문자열이 널 문자열인지 확인한다.
 *
 * Usage: com.wow.fn.isEmpty(string)
 *
 * @param s {String} 문자열
 * @returns {Boolean} 널 문자열 여부
 */
com.wow.fn.isEmpty = function(s) {
    var string = s ? s : "";

    return string.length == 0;
};

/**
 * 문자열이 빈 문자열인지 확인한다.
 *
 * Usage: com.wow.fn.isBlank(string)
 *
 * @param s {String} 문자열
 * @returns {Boolean} 빈 문자열 여부
 */
com.wow.fn.isBlank = function(s) {
    var string = s ? s : "";

    return com.wow.fn.trim(string).length == 0;
};

/**
 * 문자열의 바이트 배열길이를 확인한다.
 *
 * Usage: com.wow.fn.isBytes(string, minimum)
 *        com.wow.fn.isBytes(string, minimum, maximum)
 *
 * @param s {String} 문자열
 * @param n {Number} 최소값
 * @param x {Number} 최대값
 * @returns {Boolean} 길이 확인 여부
 */
com.wow.fn.isBytes = function(s, n, x) {
    var string  = s ? s : "";
    var minimum = n ? n : 0;
    var maximum = x ? x : 0;

    if (minimum > 0 && com.wow.fn.bytes(string) < minimum) {
        return false;
    }

    if (maximum > 0 && com.wow.fn.bytes(string) > maximum) {
        return false;
    }

    return true;
};

/**
 * 문자열의 캐릭터 배열길이를 확인한다.
 *
 * Usage: com.wow.fn.isLength(string, minimum)
 *        com.wow.fn.isLength(string, minimum, maximum)
 *
 * @param s {String} 문자열
 * @param n {Number} 최소값
 * @param x {Number} 최대값
 * @returns {Boolean} 길이 확인 여부
 */
com.wow.fn.isLength = function(s, n, x) {
    var string  = s ? s : "";
    var minimum = n ? n : 0;
    var maximum = x ? x : 0;

    if (minimum > 0 && string.length < minimum) {
        return false;
    }

    if (maximum > 0 && string.length > maximum) {
        return false;
    }

    return true;
};

/**
 * 문자열이 숫자인지 확인한다.
 *
 * Usage: com.wow.fn.isNumeric(string)
 *
 * @param s {String} 문자열
 * @returns {Boolean} 숫자 여부
 */
com.wow.fn.isNumeric = function(s) {
    var string = s ? s : "";

    return new RegExp("^[0-9]+$", "").test(string);
};

/**
 * 문자열이 정수인지 확인한다.
 *
 * Usage: com.wow.fn.isInteger(string)
 *
 * @param s {String} 문자열
 * @returns {Boolean} 정수 여부
 */
com.wow.fn.isInteger = function(s) {
    var string = s ? s : "";

    return new RegExp("^\\-?[0-9]+$", "").test(string);
};

/**
 * 문자열이 실수인지 확인한다.
 *
 * Usage: com.wow.fn.isDecimal(string)
 *
 * @param s {String} 문자열
 * @returns {Boolean} 실수 여부
 */
com.wow.fn.isDecimal = function(s) {
    var string = s ? s : "";

    return new RegExp("^\\-?[0-9]*(\\.[0-9]*)?$", "").test(string);
};

/**
 * 문자열이 날짜인지 확인한다.
 *
 * Usage: com.wow.fn.isDate(string)
 *        com.wow.fn.isDate(string, pattern)
 *
 * Pattern:
 * =============================================================================
 * Letters          Component
 * -----------------------------------------------------------------------------
 * yyyy             Year
 * MM               Month
 * dd               Date
 * =============================================================================
 *
 * @param s {String} 문자열
 * @param p {String} 패턴
 * @returns {Boolean} 날짜 여부
 */
com.wow.fn.isDate = function(s, p) {
    var string  = s ? s : "";
    var pattern = p ? p : "yyyy-MM-dd";

    if (string.length != pattern.length) {
        return false;
    }

    var year  = pattern.indexOf("yyyy") >= 0 ? parseInt(string.substr(pattern.indexOf("yyyy"), 4), 10) : 0;
    var month = pattern.indexOf("MM")   >= 0 ? parseInt(string.substr(pattern.indexOf("MM"),   2), 10) : 0;
    var day   = pattern.indexOf("dd")   >= 0 ? parseInt(string.substr(pattern.indexOf("dd"),   2), 10) : 0;

    if (year < 1) {
        return false;
    }
    if (month < 1) {
        return false;
    }
    if (month > 12) {
        return false;
    }
    if (day < 1) {
        return false;
    }
    if (day > 31) {
        return false;
    }

    switch (month) {
        case 2:
            if (year % 4 == 0 && year % 100 != 0) {
                return day <= 29;
            }

            if (year % 400 == 0) {
                return day <= 29;
            }

            return day <= 28;
        case 4:
        case 6:
        case 9:
        case 11:
            return day <= 30;
    }

    return day <= 31;
};

/**
 * 문자열이 시간인지 확인한다.
 *
 * Usage: com.wow.fn.isTime(string)
 *        com.wow.fn.isTime(string, pattern)
 *
 * Pattern:
 * =============================================================================
 * Letters          Component
 * -----------------------------------------------------------------------------
 * HH               Hour
 * mm               Minute
 * ss               Second
 * =============================================================================
 *
 * @param s {String} 문자열
 * @param p {String} 패턴
 * @returns {Boolean} 시간 여부
 */
com.wow.fn.isTime = function(s, p) {
    var string  = s ? s : "";
    var pattern = p ? p : "HH:mm";

    if (string.length != pattern.length) {
        return false;
    }

    var hour   = pattern.indexOf("HH") >= 0 ? parseInt(string.substr(pattern.indexOf("HH"), 2), 10) : 0;
    var minute = pattern.indexOf("mm") >= 0 ? parseInt(string.substr(pattern.indexOf("mm"), 2), 10) : 0;
    var second = pattern.indexOf("ss") >= 0 ? parseInt(string.substr(pattern.indexOf("ss"), 2), 10) : 0;

    if (hour < 0) {
        return false;
    }
    if (hour > 23) {
        return false;
    }
    if (minute < 0) {
        return false;
    }
    if (minute > 59) {
        return false;
    }
    if (second < 0) {
        return false;
    }
    if (second > 59) {
        return false;
    }

    return true;
};

/**
 * 문자열이 유선전화번호인지 확인한다.
 *
 * Usage: com.wow.fn.isPhone(string)
 *        com.wow.fn.isPhone(string, delimiter)
 *
 * @param s {String} 문자열
 * @param d {String} 구분자
 * @returns {Boolean} 유선전화번호 여부
 */
com.wow.fn.isPhone = function(s, d) {
    var string    = s ? s : "";
    var delimiter = d ? d : "";

    delimiter = com.wow.fn.meta(delimiter);

    return new RegExp("(02|0[3-9]{1}[0-9]{1})" + delimiter + "[1-9]{1}[0-9]{2,3}" + delimiter + "[0-9]{4}$", "").test(string);
};

/**
 * 문자열이 무선전화번호인지 확인한다.
 *
 * Usage: com.wow.fn.isMobile(string)
 *        com.wow.fn.isMobile(string, delimiter)
 *
 * @param s {String} 문자열
 * @param d {String} 구분자
 * @returns {Boolean} 무선전화번호 여부
 */
com.wow.fn.isMobile = function(s, d) {
    var string    = s ? s : "";
    var delimiter = d ? d : "";

    delimiter = com.wow.fn.meta(delimiter);

    return new RegExp("01[016789]" + delimiter + "[1-9]{1}[0-9]{2,3}" + delimiter + "[0-9]{4}$", "").test(string);
};

/**
 * 문자열이 전자우편주소인지 확인한다.
 *
 * Usage: com.wow.fn.isEmail(string)
 *
 * @param s {String} 문자열
 * @returns {Boolean} 전자우편주소 여부
 */
com.wow.fn.isEmail = function(s) {
    var string = s ? s : "";

    return new RegExp("\\w+([\\-\\+\\.]\\w+)*@\\w+([\\-\\.]\\w+)*\\.[a-zA-Z]{2,4}$", "").test(string);
};

/**
 * 문자열이 배달우편번호인지 확인한다.
 *
 * Usage: com.wow.fn.isPostcode(string)
 *        com.wow.fn.isPostcode(string, delimiter)
 *
 * @param s {String} 문자열
 * @param d {String} 구분자
 * @returns {Boolean} 배달우편번호 여부
 */
com.wow.fn.isPostcode = function(s, d) {
    var string    = s ? s : "";
    var delimiter = d ? d : "-";

    delimiter = com.wow.fn.meta(delimiter);

    return new RegExp("^[0-9]{3}" + delimiter +"[0-9]{3}$", "").test(string);
};

/**
 * 문자열이 주민등록번호인지 확인한다.
 *
 * Usage: com.wow.fn.isResRegNo(string)
 *        com.wow.fn.isResRegNo(string, delimiter)
 *
 * @param s {String} 문자열
 * @param d {String} 구분자
 * @returns {Boolean} 주민등록번 여부
 */
com.wow.fn.isResRegNo = function(s, d) {
    var string    = s ? s : "";
    var delimiter = d ? d : "-";

    delimiter = com.wow.fn.meta(delimiter);

    if (new RegExp("[0-9]{2}[01]{1}[0-9]{1}[0123]{1}[0-9]{1}" + delimiter + "[1234]{1}[0-9]{6}$", "").test(string)) {
        var number = com.wow.fn.toNumeric(string);

        var birthdate = number.substr(0, 6);

        var sex = number.charAt(6);

        switch (sex) {
            case "1":
            case "2":
                birthdate = "19" + birthdate;
                break;
            case "3":
            case "4":
                birthdate = "20" + birthdate;
                break;
            default:
                return false;
        }

        if (!com.wow.fn.isDate(birthdate, "yyyyMMdd")) {
            return false;
        }

        var checksum = 0;

        for (var i = 0; i < 12; i++) {
            checksum += parseInt(number.charAt(i), 10) * (i % 8 + 2);
        }

        return (11 - checksum % 11) % 10 == parseInt(number.charAt(12), 10);
    }

    return false;
};

/**
 * 문자열이 법인등록번호인지 확인한다.
 *
 * Usage: com.wow.fn.isCorRegNo(string)
 *        com.wow.fn.isCorRegNo(string, delimiter)
 *
 * @param s {String} 문자열
 * @param d {String} 구분자
 * @returns {Boolean} 법인등록번호 여부
 */
com.wow.fn.isCorRegNo = function(s, d) {
    var string    = s ? s : "";
    var delimiter = d ? d : "-";

    delimiter = com.wow.fn.meta(delimiter);

    if (new RegExp("[0-9]{6}" + delimiter + "[0-9]{7}$", "").test(string)) {
        var number = com.wow.fn.toNumeric(string);

        var checksum = 0;

        for (var i = 0; i < 12; i++) {
            checksum += parseInt(number.charAt(i), 10) * (i % 2 + 1);
        }

        return (10 - checksum % 10) % 10 == parseInt(number.charAt(12), 10);
    }

    return false;
};

/**
 * 문자열이 외국인등록번호인지 확인한다.
 *
 * Usage: com.wow.fn.isForRegNo(string)
 *        com.wow.fn.isForRegNo(string, delimiter)
 *
 * @param s {String} 문자열
 * @param d {String} 구분자
 * @returns {Boolean} 외국인등록번호 여부
 */
com.wow.fn.isForRegNo = function(s, d) {
    var string    = s ? s : "";
    var delimiter = d ? d : "-";

    delimiter = com.wow.fn.meta(delimiter);

    if (new RegExp("[0-9]{2}[01]{1}[0-9]{1}[0123]{1}[0-9]{1}" + delimiter + "[5678]{1}[0-9]{1}[02468]{1}[0-9]{2}[6789]{1}[0-9]{1}$", "").test(string)) {
        var number = com.wow.fn.toNumeric(string);

        var birthdate = number.substr(0, 6);

        var sex = number.charAt(6);

        switch (sex) {
            case "5":
            case "6":
                birthdate = "19" + birthdate;
                break;
            case "7":
            case "8":
                birthdate = "20" + birthdate;
                break;
            default:
                return false;
        }

        if (!com.wow.fn.isDate(birthdate, "yyyyMMdd")) {
            return false;
        }

        if (parseInt(number.substr(7, 2), 10) % 2 != 0) {
            return false;
        }

        var checksum = 0;

        for (var i = 0; i < 12; i++) {
            checksum += parseInt(number.charAt(i), 10) * (i % 8 + 2);
        }

        return ((11 - checksum % 11) % 10 + 2) %10 == parseInt(number.charAt(12), 10);
    }

    return false;
};

/**
 * 문자열이 사업자등록번호인지 확인한다.
 *
 * Usage: com.wow.fn.isBizRegNo(string)
 *        com.wow.fn.isBizRegNo(string, delimiter)
 *
 * @param s {String} 문자열
 * @param d {String} 구분자
 * @returns {Boolean} 사업자등록번호 여부
 */
com.wow.fn.isBizRegNo = function(s, d) {
    var string    = s ? s : "";
    var delimiter = d ? d : "-";

    delimiter = com.wow.fn.meta(delimiter);

    if (new RegExp("[0-9]{3}" + delimiter + "[0-9]{2}" + delimiter + "[0-9]{5}$", "").test(string)) {
        var number = com.wow.fn.toNumeric(string);

        var checksum = parseInt(number.charAt(0), 10);

        for (var i = 1; i < 8; i++) {
            checksum += parseInt(number.charAt(i), 10) * ((i % 3) * (i % 3 + 1) + 1) % 10;
        }

        checksum += Math.floor(parseInt(number.charAt(8), 10) * 5 / 10);

        checksum += parseInt(number.charAt(8), 10) * 5 % 10;

        checksum += parseInt(number.charAt(9), 10);

        return checksum % 10 == 0;
    }

    return false;
};

/**
 * 문자열이 영어인지 확인한다.
 *
 * Usage: com.wow.fn.isAlpha(string)
 *        com.wow.fn.isAlpha(string, ignores)
 *
 * @param s {String} 문자열
 * @param i {String} 허용된 문자
 * @returns {Boolean} 영어 여부
 */
com.wow.fn.isAlpha = function(s, i) {
    var string  = s ? s : "";
    var ignores = i ? i : "";

    return new RegExp("^[a-zA-Z]+$", "").test(com.wow.fn.strip(string, ignores));
};

/**
 * 문자열이 한글인지 확인한다.
 *
 * Usage: com.wow.fn.isKorean(string)
 *        com.wow.fn.isKorean(string, ignores)
 *
 * @param s {String} 문자열
 * @param i {String} 허용된 문자
 * @returns {Boolean} 한글 여부
 */
com.wow.fn.isKorean = function(s, i) {
    var string  = s ? s : "";
    var ignores = i ? i : "";

    return new RegExp("^[ㄱ-ㅎㅏ-ㅣ가-힣]+$", "").test(com.wow.fn.strip(string, ignores));
};

/**
 * 문자열이 영어와 숫자인지 확인한다.
 *
 * Usage: com.wow.fn.isAlphaNumeric(string)
 *        com.wow.fn.isAlphaNumeric(string, ignores)
 *
 * @param s {String} 문자열
 * @param i {String} 허용된 문자
 * @returns {Boolean} 영어와 숫자 여부
 */
com.wow.fn.isAlphaNumeric = function(s, i) {
    var string  = s ? s : "";
    var ignores = i ? i : "";

    return new RegExp("^[0-9a-zA-Z]+$", "").test(com.wow.fn.strip(string, ignores));
};

/**
 * 문자열이 한글과 숫자인지 확인한다.
 *
 * Usage: com.wow.fn.isKoreanNumeric(string)
 *        com.wow.fn.isKoreanNumeric(string, ignores)
 *
 * @param s {String} 문자열
 * @param i {String} 허용된 문자
 * @returns {Boolean} 한글과 숫자 여부
 */
com.wow.fn.isKoreanNumeric = function(s, i) {
    var string  = s ? s : "";
    var ignores = i ? i : "";

    return new RegExp("^[0-9ㄱ-ㅎㅏ-ㅣ가-힣]+$", "").test(com.wow.fn.strip(string, ignores));
};

/**
 * 문자열이 이미지 파일인지 확인한다.
 *
 * Usage: com.wow.fn.isImage(string)
 *        com.wow.fn.isImage(string, extensions)
 *
 * @param s {String} 문자열
 * @param e {Array} 이미지 확장자
 * @returns {Boolean} 이미지 파일 여부
 */
com.wow.fn.isImage = function(s, e) {
    var string     = s ? s : "";
    var extensions = e ? e : [ "jpeg", "jpg", "gif", "png", "bmp" ];

    var extension = "";

    for (var i = 0; i < extensions.length; i++) {
        if (i > 0) {
            extension += "|";
        }

        extension += com.wow.fn.meta(extensions[i]);
    }

    return new RegExp("\\.(" + extension + ")$", "i").test(string);
};

/**
 * 문자열이 업로드 파일인지 확인한다.
 *
 * Usage: com.wow.fn.isUpload(string)
 *        com.wow.fn.isUpload(string, extensions)
 *
 * @param s {String} 문자열
 * @param e {Array} 제한된 확장자
 * @returns {Boolean} 업로드 파일 여부
 */
com.wow.fn.isUpload = function(s, e) {
    var string     = s ? s : "";
    var extensions = e ? e : [ "jsp", "php", "php3", "php5", "phtml", "asp", "aspx", "asc", "ascx", "cfm", "cfc", "pl", "bat", "exe", "dll", "reg", "cgi" ];

    var extension = "";

    for (var i = 0; i < extensions.length; i++) {
        if (i > 0) {
            extension += "|";
        }

        extension += com.wow.fn.meta(extensions[i]);
    }

    return !new RegExp("\\.(" + extension + ")$", "i").test(string);
};

/**
 * 문자열이 아이피인지 확인한다.
 *
 * Usage: com.wow.fn.isIp(string)
 *
 * @param s {String} 문자열
 * @returns {Boolean} 아이피 여부
 */
com.wow.fn.isIp = function(s) {
    var string = s ? s : "";

    return new RegExp("\\b(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\b", "").test(string);
};

/**
 * 문자열이 맥주소인지 확인한다.
 *
 * Usage: com.wow.fn.isMac(string)
 *        com.wow.fn.isMac(string, delimiter)
 *
 * @param s {String} 문자열
 * @param d {String} 구분자
 * @returns {Boolean} 맥주소 여부
 */
com.wow.fn.isMac = function(s, d) {
    var string    = s ? s : "";
    var delimiter = d ? d : "-";

    delimiter = com.wow.fn.meta(delimiter);

    return new RegExp("^([0-9a-fA-F][0-9a-fA-F]" + delimiter + "){5}([0-9a-fA-F][0-9a-fA-F])$", "").test(string);
};

/**
 * 문자열을 숫자로 변환한다.
 *
 * Usage: com.wow.fn.toNumeric(string)
 *
 * @param s {String} 문자열
 * @returns {String} 숫자
 */
com.wow.fn.toNumeric = function(s) {
    var string = s ? s : "";

    return string.replace(new RegExp("[^0-9]", "g"), "");
};

/**
 * 문자열을 정수로 변환한다.
 *
 * Usage: com.wow.fn.toInteger(string)
 *        com.wow.fn.toInteger(string, radix)
 *
 * @param s {String} 문자열
 * @param r {Number} 진법
 * @return {Number} 정수
 */
com.wow.fn.toInteger = function(s, r) {
    var string = s ? s : "";
    var radix  = r ? r : 10;

    return parseInt(string.replace(new RegExp("[^\\-0-9\\.]", "g"), ""), radix);
};

/**
 * 문자열을 실수로 변환한다.
 *
 * Usage: com.wow.fn.toDecimal(string)
 *        com.wow.fn.toDecimal(string, radix)
 *
 * @param s {String} 문자열
 * @param r {Number} 진법
 * @return {Number} 실수
 */
com.wow.fn.toDecimal = function(s, r) {
    var string = s ? s : "";
    var radix  = r ? r : 10;

    return parseFloat(string.replace(new RegExp("[^\\-0-9\\.]", "g"), ""), radix);
};

/**
 * 문자열을 통화로 변환한다.
 *
 * Usage: com.wow.fn.toCurrency(string)
 *        com.wow.fn.toCurrency(string, fixed)
 *        com.wow.fn.toCurrency(string, fixed, "round|floor|ceil")
 *
 * @param s {String} 문자열
 * @param f {Number} 소수점 자리수
 * @param r {String} 반올림
 * @return {String} 통화
 */
com.wow.fn.toCurrency = function(s, f, r) {
    var string = s ? s : "";
    var fixed  = f ? f : 0;
    var round  = r ? r : "round";

    var sign = 1;

    var decimal = com.wow.fn.toDecimal(string);

    if (decimal < 0) {
        sign = -1;

        decimal = Math.abs(decimal);
    }

    var power = Math.pow(10, fixed);

    switch (round) {
        case "round":
            decimal = Math.round(decimal * power) / power * sign;
            break;
        case "floor":
            decimal = Math.floor(decimal * power) / power * sign;
            break;
        case "ceil":
            decimal = Math.ceil(decimal * power) / power * sign;
            break;
        default:
            return string;
    }

    var number = decimal.toString();

    var extra = "";

    var index = number.indexOf(".");

    if (index > 0) {
        extra = number.substring(index + 1);

        number = number.substring(0, index);
    }

    if (fixed > 0) {
        extra = "." + com.wow.fn.rpad(extra, fixed, "0");
    }

    var pattern = new RegExp("(\\-?[0-9]+)([0-9]{3})", "");

    while (pattern.test(number)) {
        number = number.replace(pattern, "$1,$2");
    }

    return number + extra;
};

/**
 * 문자열을 날짜로 변환한다.
 *
 * Usage: com.wow.fn.toDate(string)
 *        com.wow.fn.toDate(string, parsePattern)
 *        com.wow.fn.toDate(string, parsePattern, formatPattern)
 *
 * Pattern:
 * =============================================================================
 * Letters          Component
 * -----------------------------------------------------------------------------
 * yyyy             Year
 * MM               Month
 * dd               Date
 * =============================================================================
 *
 * @param s {String} 문자열
 * @param p {String} 파싱패턴
 * @param f {String} 포맷패턴
 * @returns {String} 날짜
 */
com.wow.fn.toDate = function(s, p, f) {
    var string        = s ? s : "";
    var parsePattern  = p ? p : "yyyyMMdd";
    var formatPattern = f ? f : "yyyy-MM-dd";

    if (string.length != parsePattern.length) {
        return string;
    }

    var year  = parsePattern.indexOf("yyyy") >= 0 ? string.substr(parsePattern.indexOf("yyyy"), 4) : "";
    var month = parsePattern.indexOf("MM")   >= 0 ? string.substr(parsePattern.indexOf("MM"),   2) : "";
    var day   = parsePattern.indexOf("dd")   >= 0 ? string.substr(parsePattern.indexOf("dd"),   2) : "";

    return formatPattern.replace("yyyy", year).replace("MM", month).replace("dd", day);
};

/**
 * 문자열을 시간으로 변환한다.
 *
 * Usage: com.wow.fn.toTime(string)
 *        com.wow.fn.toTime(string, parsePattern)
 *        com.wow.fn.toTime(string, parsePattern, formatPattern)
 *
 * Pattern:
 * =============================================================================
 * Letters          Component
 * -----------------------------------------------------------------------------
 * HH               Hour
 * mm               Minute
 * ss               Second
 * =============================================================================
 *
 * @param s {String} 문자열
 * @param p {String} 파싱패턴
 * @param f {String} 포맷패턴
 * @returns {String} 시간
 */
com.wow.fn.toTime = function(s, p, f) {
    var string        = s ? s : "";
    var parsePattern  = p ? p : "HHmm";
    var formatPattern = f ? f : "HH:mm";

    if (string.length != parsePattern.length) {
        return string;
    }

    var hour   = parsePattern.indexOf("HH") >= 0 ? string.substr(parsePattern.indexOf("HH"), 2) : "";
    var minute = parsePattern.indexOf("mm") >= 0 ? string.substr(parsePattern.indexOf("mm"), 2) : "";
    var second = parsePattern.indexOf("ss") >= 0 ? string.substr(parsePattern.indexOf("ss"), 2) : "";

    return formatPattern.replace("HH", hour).replace("mm", minute).replace("ss", second);
};

/**
 * 문자열을 유선전화번호로 변환한다.
 *
 * Usage: com.wow.fn.toPhone(string)
 *        com.wow.fn.toPhone(string, delimiter)
 *
 * @param s {String} 문자열
 * @param d {String} 구분자
 * @returns {String} 유선전화번호
 */
com.wow.fn.toPhone = function(s, d) {
    var string    = s ? s : "";
    var delimiter = d ? d : "-";

    var number = com.wow.fn.toNumeric(string);

    if (number.indexOf("02") == 0) {
        if (number.length == 10) {
            return number.substr(0, 2) + delimiter + number.substr(2, 4) + delimiter + number.substr(6, 4);
        }
        if (number.length == 9) {
            return number.substr(0, 2) + delimiter + number.substr(2, 3) + delimiter + number.substr(5, 4);
        }
    }
    else {
        if (number.length == 11) {
            return number.substr(0, 3) + delimiter + number.substr(3, 4) + delimiter + number.substr(7, 4);
        }
        if (number.length == 10) {
            return number.substr(0, 3) + delimiter + number.substr(3, 3) + delimiter + number.substr(6, 4);
        }
    }

    return string;
};

/**
 * 문자열을 무선전화번호로 변환한다.
 *
 * Usage: com.wow.fn.toMobile(string)
 *        com.wow.fn.toMobile(string, delimiter)
 *
 * @param s {String} 문자열
 * @param d {String} 구분자
 * @returns {String} 무선전화번호
 */
com.wow.fn.toMobile = function(s, d) {
    var string    = s ? s : "";
    var delimiter = d ? d : "-";

    var number = com.wow.fn.toNumeric(string);

    if (number.length == 11) {
        return number.substr(0, 3) + delimiter + number.substr(3, 4) + delimiter + number.substr(7, 4);
    }
    if (number.length == 10) {
        return number.substr(0, 3) + delimiter + number.substr(3, 3) + delimiter + number.substr(6, 4);
    }

    return string;
};

/**
 * 문자열을 배달우편번호로 변환한다.
 *
 * Usage: com.wow.fn.toPostcode(string)
 *        com.wow.fn.toPostcode(string, delimiter)
 *
 * @param s {String} 문자열
 * @param d {String} 구분자
 * @returns {String} 배달우편번호
 */
com.wow.fn.toPostcode = function(s, d) {
    var string    = s ? s : "";
    var delimiter = d ? d : "-";

    var number = com.wow.fn.toNumeric(string);

    if (number.length == 6) {
        return number.substr(0, 3) + delimiter + number.substr(3, 3);
    }

    return string;
};

/**
 * 문자열을 주민등록번호로 변환한다.
 *
 * Usage: com.wow.fn.toResRegNo(string)
 *        com.wow.fn.toResRegNo(string, delimiter)
 *
 * @param s {String} 문자열
 * @param d {String} 구분자
 * @returns {String} 주민등록번호
 */
com.wow.fn.toResRegNo = function(s, d) {
    var string    = s ? s : "";
    var delimiter = d ? d : "-";

    var number = com.wow.fn.toNumeric(string);

    if (number.length == 13) {
        return number.substr(0, 6) + delimiter + number.substr(6, 7);
    }

    return string;
};

/**
 * 문자열을 법인등록번호로 변환한다.
 *
 * Usage: com.wow.fn.toCorRegNo(string)
 *        com.wow.fn.toCorRegNo(string, delimiter)
 *
 * @param s {String} 문자열
 * @param d {String} 구분자
 * @returns {String} 법인등록번호
 */
com.wow.fn.toCorRegNo = function(s, d) {
    var string    = s ? s : "";
    var delimiter = d ? d : "-";

    var number = com.wow.fn.toNumeric(string);

    if (number.length == 13) {
        return number.substr(0, 6) + delimiter + number.substr(6, 7);
    }

    return string;
};

/**
 * 문자열을 외국인등록번호로 변환한다.
 *
 * Usage: com.wow.fn.toForRegNo(string)
 *        com.wow.fn.toForRegNo(string, delimiter)
 *
 * @param s {String} 문자열
 * @param d {String} 구분자
 * @returns {String} 외국인등록번호
 */
com.wow.fn.toForRegNo = function(s, d) {
    var string    = s ? s : "";
    var delimiter = d ? d : "-";

    var number = com.wow.fn.toNumeric(string);

    if (number.length == 13) {
        return number.substr(0, 6) + delimiter + number.substr(6, 7);
    }

    return string;
};

/**
 * 문자열을 사업자등록번호로 변환한다.
 *
 * Usage: com.wow.fn.toBizRegNo(string)
 *        com.wow.fn.toBizRegNo(string, delimiter)
 *
 * @param s {String} 문자열
 * @param d {String} 구분자
 * @returns {String} 사업자등록번호
 */
com.wow.fn.toBizRegNo = function(s, d) {
    var string    = s ? s : "";
    var delimiter = d ? d : "-";

    var number = com.wow.fn.toNumeric(string);

    if (number.length == 10) {
        return number.substr(0, 3) + delimiter + number.substr(3, 2) + delimiter + number.substr(5, 5);
    }

    return string;
};

/**
 * 문자열을 맥주소로 변환한다.
 *
 * Usage: com.wow.fn.toMac(string)
 *        com.wow.fn.toMac(string, delimiter)
 *
 * @param s {String} 문자열
 * @param d {String} 구분자
 * @returns {String} 맥주소
 */
com.wow.fn.toMac = function(s, d) {
    var string    = s ? s : "";
    var delimiter = d ? d : "-";

    var letters = string.replace(new RegExp("[^0-9a-fA-F]", "g"), "").toUpperCase();

    if (letters.length == 12) {
        return letters.substr( 0, 2) + delimiter +
                letters.substr( 2, 2) + delimiter +
                letters.substr( 4, 2) + delimiter +
                letters.substr( 6, 2) + delimiter +
                letters.substr( 8, 2) + delimiter +
                letters.substr(10, 2);
    }

    return string;
};


/*
 * 플러그인을 생성한다.
 */
(function($) {
    // 실수마스크 플러그인
    $.mask.rules["#"] = /[\.0-9]/;

    $.fn.decimal = function(options) {
        var precision = options.precision ? options.precision : 16;
        var scale     = options.scale     ? options.scale     : 10;

        var mask = "";

        for (var i = 0; i < precision; i++) {
            mask += "#";
        }

        this.each(function(element) {
            $(this).bind("keydown", function(event) {
                var value = $(this).val();

                if (value == "" && event.which == 190) {
                    $(this).val("0.");
                    return false;
                }

                if (value == "0" && event.which == 48) {
                    return false;
                }

                if (value.indexOf(".") >= 0 && event.which == 190) {
                    return false;
                }
            });

            $(this).setMask({ mask:mask, fixedChars:"[(),:/ -]" });
        });
    };
})(jQuery);

/**
 * 윈도우를 띄운다.
 *
 * @param url {String} URL
 * @param target {String} 대상
 * @param options {Object} 옵션
 * @param params {Object} 파라메터
 * @returns {Object} 윈도우
 */
function openWindow(url, target, options, params) {

    var popX = window.screenLeft + (window.outerWidth - (options.width != null ? options.width : "400"))/2;
    var popY = window.screenTop + (window.outerHeight - (options.height != null ? options.height : "300"))/2;

    var feature = "";

    if (options) {
        feature += "channelmode=" + (options.channelmode != null ? options.channelmode : "no" ) + ",";
        feature += "fullscreen="  + (options.fullscreen  != null ? options.fullscreen  : "no" ) + ",";
        feature += "titlebar="    + (options.titlebar    != null ? options.titlebar    : "no" ) + ",";
        feature += "menubar="     + (options.menubar     != null ? options.menubar     : "no" ) + ",";
        feature += "toolbar="     + (options.toolbar     != null ? options.toolbar     : "no" ) + ",";
        feature += "location="    + (options.location    != null ? options.location    : "no" ) + ",";
        feature += "directories=" + (options.directories != null ? options.directories : "no" ) + ",";
        feature += "status="      + (options.status      != null ? options.status      : "no" ) + ",";
        feature += "resizable="   + (options.resizable   != null ? options.resizable   : "no" ) + ",";
        feature += "scrollbars="  + (options.scrollbars  != null ? options.scrollbars  : "no" ) + ",";
        feature += "width="       + (options.width       != null ? options.width       : "400") + ",";
        feature += "height="      + (options.height      != null ? options.height      : "300") + ",";
    }

    feature += "top="           + popY + ",";
    feature += "left="          + popX ;

    var w = null;

    if (params) {
        if ($("#data-post-form").length == 0) {
            $("body").append("<form id=\"data-post-form\" name=\"data-post-form\" method=\"post\" style=\"display:none;\"></form>");
        }

        var form = $("#data-post-form");

        form.find("input:hidden").each(function(index, element) {
            $(this).remove();
        });

        for (var key in params) {
            form.append("<input type=\"hidden\" name=\"" + key + "\" value=\"" + params[key] + "\" />");
        }

        var t = target || "anonymousPopup";

        var w = window.open("", target, feature);

        form.attr("action", url).attr("target", target).submit();
    }
    else {
        var w = window.open(url, target || "", feature);
    }

    return w;
}
function winResize(){
    var pWidth = $("#popup").outerWidth();
    var pHeight = $("#popup").outerHeight();
    window.resizeTo(pWidth+30, pHeight+100);
}
/**
 * 데이터를 처리한다.
 *
 * @param options {Object} 옵션
 */
function doPost(options) {
    options.url = options.url || "";

    if (com.wow.fn.isEmpty(options.url)) {
        return;
    }

    options.before = options.before || function(options) {
        return {
            // Nothing to do.
        };
    };
    options.after  = options.after  || function(data) {
        // Nothing to do.
    };

    var data = options.before(options);

    if (data) {
        $.post(
            options.url,
            data,
            function(data, status, request) {
                handleResponse(data, status, options);
            },
            "json"
        ).error(function(request, status, error) {
            handleErrorMessage(status, error);
        });
    }
}

/**
 * 데이터를 처리한다.
 *
 * @param options {Object} 옵션
 */
function doSubmit(options) {
    options.form = options.form || "";
    options.url  = options.url  || "";

    if (com.wow.fn.isEmpty(options.form)) {
        return;
    }
    if (com.wow.fn.isEmpty(options.url)) {
        return;
    }

    options.before = options.before || function(options) {
        return true;
    };
    options.after  = options.after  || function(data) {
        // Nothing to do.
    };

    var success = options.before(options);

    if (success) {
        $("#" + options.form).ajaxSubmit({
            beforeSubmit:function(data, form, options) {
                return true;
            },
            url:options.url,
            dataType:"json",
            success:function(data, status, request, form) {
                handleResponse(data, status, options);
            },
            error:function(request, status, error) {
                handleErrorMessage(status, error);
            }
        });
    }
}

/**
 * 응답을 처리한다.
 *
 * @param data {Object} 데이터
 * @param status {String} 상태
 * @param options {Object} 옵션
 */
function handleResponse(data, status, options) {
    // 페이지가 있는 경우
    if (data.page) {
        // 페이지를 처리한다.
        handlePage(data.pages, data.page, options);

        // 데이터를 처리한다.
        handleData(data.data, options);

        // 카운트를 처리한다.
        handleCount(data.total, data.count, options);

        return;
    }

    // 데이터가 있는 경우
    if (data.data) {
        // 데이터를 처리한다.
        handleData(data.data, options);

        return;
    }

    // 처리가 완료된 경우
    if (data.success) {
        // 메시지를 처리한다.
        handleMessage(data.success, options);

        return;
    }

    // 오류가 발생한 경우
    if (data.error) {
        // 오류를 처리한다.
        handleErrorMessage(status, data.error);

        return;
    }
}

/**
 * 페이지를 처리한다.
 *
 * @param pages {Number} 전체 페이지
 * @param page {Number} 페이지 번호
 * @param options {Object} 옵션
 */
function handlePage(pages, page, options) {
    options.pager = options.pager || "search-pager";

    var pager = $("#" + options.pager);

    pager.empty();

    var first = Math.floor((page - 1) / 10) * 10 + 1;
    var last  = first + 10 + 1;

    last = pages > 0 ? last > pages ? pages : last : first;

    var division = "";

    division += "<div style=\"width:100%; padding:10px 0; text-align:center;\">";

    if (pages > 0 && first > 1) {
        division += "<a href=\"#\" class=\"first\"><span class=\"btn btn1_txt\">맨앞</span></a>&nbsp;";
    }
    else {
        division += "<span class=\"btn btn1_txt\">맨앞</span>&nbsp;";
    }

    if (pages > 0 && page > 1) {
        division += "<a href=\"#\" class=\"previous\"><span class=\"btn btn1_txt\">이전</span></a>&nbsp;";
    }
    else {
        division += "<span class=\"btn btn1_txt\">이전</span>&nbsp;";
    }

    for (var i = 0, n = first; i < 10; i++, n++) {
        if (i > 0) {
            division += "&nbsp;";
        }

        if (n == page) {
            division += "<a href=\"#\" class=\"selected\"><span class=\"btn btn_num_over\">" + n + "</span></a>";
            /*division += "<font color=\"#ff0000\" class=\"selected\">" + n + "</font>";*/
        }
        else {
            division += "<a href=\"#\" class=\"number\"><span class=\"btn btn_num\">" + n + "</span></a>";
            /*division += "<a href=\"#\" class=\"number\">" + n + "</a>";*/
        }

        if (n >= pages) {
            break;
        }
    }

    if (pages > 0 && page < pages) {
        division += "&nbsp;<a href=\"#\" class=\"next\"><span class=\"btn btn1_txt\">다음</span></a>";
    }
    else {
        division += "&nbsp;<span class=\"btn btn1_txt\">다음</span>";
    }

    if (pages > 0 && last < pages) {
        division += "&nbsp;<a href=\"#\" class=\"last\"><span class=\"btn btn1_txt\">맨뒤</span></a>";
    }
    else {
        division += "&nbsp;<span class=\"btn btn1_txt\">맨뒤</span>";
    }

    division += "</div>";

    pager.append(division);

    // 맨앞 버튼에 클릭 이벤트를 바인딩한다.
    pager.find(".first").bind("click", {
        url:options.url,
        page:"1",
        before:options.before,
        after:options.after,
        pager:options.pager
    }, function(event) {
        doPost(event.data);
        return false;
    });

    // 이전 버튼에 클릭 이벤트를 바인딩한다.
    pager.find(".previous").bind("click", {
        url:options.url,
        page:(page - 1).toString(),
        before:options.before,
        after:options.after,
        pager:options.pager
    }, function(event) {
        doPost(event.data);
        return false;
    });

    // 번호 링크에 클릭 이벤트를 바인딩한다.
    pager.find(".number").each(function(index, element) {
        $(this).bind("click", {
            url:options.url,
            page:$(this).text(),
            before:options.before,
            after:options.after,
            pager:options.pager
        }, function(event) {
            doPost(event.data);
            return false;
        });
    });

    // 다음 버튼에 클릭 이벤트를 바인딩한다.
    pager.find(".next").bind("click", {
        url:options.url,
        page:(page + 1).toString(),
        before:options.before,
        after:options.after,
        pager:options.pager
    }, function(event) {
        doPost(event.data);
        return false;
    });

    // 맨뒤 버튼에 클릭 이벤트를 바인딩한다.
    pager.find(".last").bind("click", {
        url:options.url,
        page:pages.toString(),
        before:options.before,
        after:options.after,
        pager:options.pager
    }, function(event) {
        doPost(event.data);
        return false;
    });
}

/**
 * 데이터를 처리한다.
 *
 * @param data {Object} 데이터
 * @param options {Object} 옵션
 */
function handleData(data, options) {
    options.after(data);
}

/**
 * 카운트를 처리한다.
 *
 * @param total {Number} 전체 카운트
 * @param count {Number} 검색 카운트
 * @param options {Object} 옵션
 */
function handleCount(total, count, options) {
    var counter = options.counter || {};

    counter.total = counter.total || "search-total";
    counter.count = counter.count || "search-count";

    $("#" + counter.total).text(total);
    $("#" + counter.count).text(count);
}

/**
 * 메시지를 처리한다.
 *
 * @param messages {Object} 메시지
 * @param options {Object} 옵션
 */
function handleMessage(messages, options) {
    if (messages.message) {
        alert(messages.message);
    }

    options.after(messages);
}

/**
 * 오류를 처리한다.
 *
 * @param status {String} 상태
 * @param error {Object} 오류
 */
function handleErrorMessage(status, error) {
    // 서비스 오류인 경우
    if (status == "success") {
        alert(error.message);
    }
    // 시스템 오류인 경우
    else {
        alert("시스템 오류가 발생하였습니다.");
    }
}