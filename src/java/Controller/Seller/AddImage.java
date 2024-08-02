/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.Seller;

import jakarta.servlet.http.Part;
import java.io.File;

/**
 *
 * @author LENOVO
 */
public class AddImage {
    public String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }
    
    
     public File getFolderUploadStaff(String contextPath) {
        File folderUpload = new File(contextPath + File.separator + "image" + File.separator + "image_product" + File.separator + "image_seller" );
        if (!folderUpload.exists()) {
            folderUpload.mkdirs();
        }
        return folderUpload;
    }
    
  
}
