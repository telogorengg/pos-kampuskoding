package mvp.ujang.posmvp.utils;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.icu.text.SimpleDateFormat;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.time.DayOfWeek;
import java.util.Calendar;
import java.util.Date;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.DateFormat;

public class Common {

    public static byte[] convertToByte(String value){
        return Base64.decode(value.replace("data:image/jpeg;base64,",""),Base64.DEFAULT);
    }

    public static String convertToRupiah(String value){
        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');
        kursIndonesia.setDecimalFormatSymbols(formatRp);
        return String.valueOf(kursIndonesia.format(Double.parseDouble(value)));
    }

    public static String getDateByFormat(String format) {
        Calendar callForDate = Calendar.getInstance();
        java.text.SimpleDateFormat currentDate = new java.text.SimpleDateFormat(format);
        return currentDate.format(callForDate.getTime());
    }

    public static String getDateTime() {
        Calendar callForDate = Calendar.getInstance();
        java.text.SimpleDateFormat currentDate = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return currentDate.format(callForDate.getTime());
    }


    public static String getTextDateTime() {
        Calendar callForDate = Calendar.getInstance();
        java.text.SimpleDateFormat currentDate = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
        return currentDate.format(callForDate.getTime());
    }

    public static Bitmap convertImageViewToBitmap(ImageView v){
        try {
            BitmapDrawable drawable = (BitmapDrawable) v.getDrawable();
            Bitmap bitmap = drawable.getBitmap();
            return bitmap;
        }
        catch (Exception e) {
            return null;
        }
    }

    public static String encodeToBase64(Bitmap image, Bitmap.CompressFormat compressFormat, int quality) {
        if (image==null)
            return "";
        ByteArrayOutputStream byteArrayOS = new ByteArrayOutputStream();
        image.compress(compressFormat, quality, byteArrayOS);
        return Base64.encodeToString(byteArrayOS.toByteArray(), Base64.DEFAULT);
    }

    public static String convertDate(String date,String currentFormat,String newFormat){
        java.text.SimpleDateFormat tempDate = new java.text.SimpleDateFormat(currentFormat);
        Date date1 = new Date();
        try {
            date1=tempDate.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        java.text.SimpleDateFormat currentDate = new java.text.SimpleDateFormat(newFormat);
        return currentDate.format(date1);
    }

    public static String convertDateLong(String date){
        java.text.SimpleDateFormat tempDate = new java.text.SimpleDateFormat("yyyy-MM-dd");
        Date date1 = new Date();
        try {
            date1=tempDate.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        java.text.SimpleDateFormat currentDate = new java.text.SimpleDateFormat("EEE, d MMM yyyy");
        return currentDate.format(date1);
    }


    public static String getMonthName(String month){
        java.text.SimpleDateFormat tempDate = new java.text.SimpleDateFormat("MM");
        Date date1 = new Date();
        try {
            date1=tempDate.parse(month);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        java.text.SimpleDateFormat currentDate = new java.text.SimpleDateFormat("MMM");
        return currentDate.format(date1);
    }


    public static String getDayName(String day){
        String weekday = new DateFormatSymbols().getShortWeekdays()[Integer.parseInt(day)-1];
        return weekday;
    }

    public static void printTimeMillis(String TAG,long startTime,long endTime){
        Log.d(TAG,String.valueOf(endTime - startTime)+" ms");
    }


}
