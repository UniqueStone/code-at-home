package com.expedia.interview.ex1;

import org.junit.Test;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Exercise1 {

    String timeInWords(int h, int m) {
        String minuteUnit;
        String hour;
        String minute;
        String connStr;
        String result;

        // minutes == 0
        if (m == 0) {
            connStr = " o'clock";
            hour = transferTheNum(h);

            result = hour + connStr;
        } else if (1 <= m && m <= 30) { // 1 <= minutes <= 30
            connStr = " past ";
            hour = transferTheNum(h);

            if (m == 15) {
                minute = "quarter";
            } else if (m == 30) {
                minute = "half";
            } else {
                minuteUnit = getMinuteUnit(m);
                minute = transferTheNum(m) + minuteUnit;
            }

            result = minute + connStr + hour;
        } else {    // minutes > 30
            connStr = " to ";
            // Judge h, if h == 2, the value of hour should be 1
            hour = (h == 12) ? transferTheNum(1) : transferTheNum(h);

            if (m == 45) {
                minute = "quarter";
            }else {
                m = 60 - m;
                minuteUnit = getMinuteUnit(m);
                minute = transferTheNum(m) + minuteUnit;
            }

            result = minute + connStr + hour;
        }

        return result;
    }

    /**
     * Get minute or minutes
     * @param m
     * @return
     */
    String getMinuteUnit(int m) {
        return (m != 1) ? " minutes" : " minute";
    }

    String digits[] = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    String tenDigits[] = {"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
    String tyDigits[] = {"twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};
    /**
     * Transfer the num to English
     * @param num
     * @return
     */
    String transferTheNum(int num) {
        StringBuilder result = new StringBuilder();
        if (num < 10) {
            result.append(digits[num - 1]);
        }
        else if (10 <= num && num < 20) {
            result.append(tenDigits[(num % 10)]);
        }
        else if (num >= 20) {
            // If the num is multiple of 10
            result.append(tyDigits[(num / 10 - 2)]);
            if ((num % 10) != 0) {
                result.append(" " + digits[(num % 10 - 1)]);
            }
        }

        return result.toString();
    }

    /**
     * Judge the time whether the input time matches the regex.
     * @param time
     * @return
     */
    boolean judgeTime(String time) {
        if (null != time && !"".equals(time)) {
            // declare the time regex
            String timeRegex = "([1-9]|1[0-2]):([0-5][0-9])";
            Pattern timePattern = Pattern.compile(timeRegex);
            Matcher timeMatcher = timePattern.matcher(time);

            return timeMatcher.matches();
        }

        return false;
    }

    @Test
    public void test() {
        String time = "22:59";

        if (judgeTime(time)) {
            String [] times = time.split(":");
            String words = timeInWords(Integer.parseInt(times[0]), Integer.parseInt(times[1]));
            System.out.println(words);
        } else {
            System.out.println("It's not a valid time");
        }
    }
}