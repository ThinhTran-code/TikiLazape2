/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.util.ArrayList;
import java.util.List;

public class Feature {

    public static String format(String m) {

        StringBuilder sb = new StringBuilder();
        ArrayList<String> list = new ArrayList();
        int n = m.length() % 3;
        if (n != 0) {
            list.add(m.substring(0, n) + '.');
        }

        for (int i = n; i < m.length() - 3; i += 3) {
            list.add(m.substring(i, i + 3) + '.');
        }
        if (m.length() >= 3) {
            list.add(m.substring(m.length() - 3, m.length()));
        }

        
        for (String string : list) {
            sb.append(string);
        }
        return sb.toString();
    }

    public static long calculateSalePrice(String originPrice, String percentSale) {
        long salePrice = 0;
        if (percentSale != null) {
            // Parse the string inputs to numeric types
            long originalPriceLong = Long.parseLong(originPrice);
            double percentSaleDouble = Double.parseDouble(percentSale);

            // Calculate the sale price
            double discount = originalPriceLong * (percentSaleDouble / 100);
            salePrice = Math.round(originalPriceLong - discount);
        }
        return salePrice;
    }
    public static long calculateSalePriceI(long originPrice, int percentSale){
        if(percentSale != 0){
            originPrice = originPrice -( originPrice * percentSale /100);
        }
        return originPrice;
    }
    public static String formatBlogContext(String context){
        String summary;
        if (context.length() > 100) {
            summary = context.substring(0, 100);
        } else {
            summary = context;
        }
        return summary;
    }
}
