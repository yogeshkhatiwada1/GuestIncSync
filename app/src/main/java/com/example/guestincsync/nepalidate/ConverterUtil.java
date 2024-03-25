package com.example.guestincsync.nepalidate;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ConverterUtil {
    public static String convertADDatetoBS(String adDate){

        String convertedBSDate="";
        String nepaliMonth="";

        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd MM yyyy");
            Date dateE = dateFormat.parse(adDate);
            DateBS nepDate = DateConverter.convertADToBS(dateE);
            int month = nepDate.getMonth() + 1;

            switch (month) {
                case 1:
                    nepaliMonth = "Baisakh";
                    break;
                case 2:
                    nepaliMonth = "Jestha";
                    break;
                case 3:
                    nepaliMonth = "Asar";
                    break;
                case 4:
                    nepaliMonth = "Shrawan";
                    break;
                case 5:
                    nepaliMonth = "Bhadra";
                    break;
                case 6:
                    nepaliMonth = "Asoj";
                    break;
                case 7:
                    nepaliMonth = "Kartik";
                    break;
                case 8:
                    nepaliMonth = "Mangshir";
                    break;
                case 9:
                    nepaliMonth = "Push";
                    break;
                case 10:
                    nepaliMonth = "Magh";
                    break;
                case 11:
                    nepaliMonth = "Falgun";
                    break;
                case 12:
                    nepaliMonth = "Chaitra";
                    break;
                default:
                    break;
            }

            convertedBSDate = nepDate.getYear() + " " + nepaliMonth + " " + nepDate.getDay();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return convertedBSDate;
    }
}
