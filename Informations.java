package CALENDAR;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Informations {

    static GregorianCalendar cd = new GregorianCalendar();

    public int year() {
        return cd.get(Calendar.YEAR);
    }

    public int month() {
        return cd.get(Calendar.MONTH)+1;
    }
    
    public int date()
    {
        return cd.get(Calendar.DATE);
    }

    public int week_day(int year, int month) {
        int x1, x2, x3;
        x1 = (year - 1) / 4;
        x2 = (year - 1) / 100;
        x3 = (year - 1) / 400;
        int f_day = ((year + x1 - x2 + x3) % 7) + 1;
        int num;
        int total[] = new int[12];
        total[0] = 0;
        total[1] = 31;
        if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
            num = 29;
        } else {
            num = 28;
        }
        total[2] = total[1] + num;
        total[3] = total[2] + 31;
        total[4] = total[3] + 30;
        total[5] = total[4] + 31;
        total[6] = total[5] + 30;
        total[7] = total[6] + 31;
        total[8] = total[7] + 31;
        total[9] = total[8] + 30;
        total[10] = total[9] + 31;
        total[11] = total[10] + 30;
         if (month == 1)
        {
            return f_day;
        }
        int w_day = f_day + (total[month - 1] % 7);
        if (w_day % 7 != 0) 
        {
            w_day = w_day % 7;
        }
        return w_day;
    }
    public int setTotalDays(int y, int m) {
         int total_days=0;
        if (m == 1) {
            total_days = 31;
        } else if (m == 2) {
            if (y % 400 == 0 || (y % 4 == 0 && y % 100 != 0)) {
                total_days = 29;
            } else {
                total_days = 28;
            }
        } else if (m == 3) {
            total_days = 31;

        } else if (m == 4) {
            total_days = 30;

        } else if (m == 5) {
            total_days = 31;

        } else if (m == 6) {
            total_days = 30;

        } else if (m == 7) {
            total_days = 31;

        } else if (m == 8) {
            total_days = 31;

        } else if (m == 9) {
            total_days = 30;

        } else if (m == 10) {
            total_days = 31;

        } else if (m == 11) {
            total_days = 30;

        } else if (m == 12) {
            total_days = 31;
        }
        return total_days;
    }
}
