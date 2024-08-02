/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller.Admin;

import DAO.BrandDAO;
import DAO.CategoryDAO;
import DAO.ImageDAO;
import DAO.ProductDAO;
import DAO.StoreDAO;
import DAO.TypeDAO;
import Model.Product.Product;
import jakarta.servlet.ServletContext;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author Asus
 */
public class AdminManagerProduct extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AdminManagerProduct</title>"); 
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminManagerProduct at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String index = request.getParameter("index");
        if(index == null){
            index = "1";
        }
            int i = Integer.parseInt(index);
          
        ImageDAO imageDb = new ImageDAO();
       ProductDAO productDb = new ProductDAO();
        CategoryDAO categoryDb = new CategoryDAO();
        BrandDAO brandDb = new BrandDAO();
        StoreDAO storeDb = new StoreDAO();
        TypeDAO typeDb = new TypeDAO();
           int numberPage = productDb.getNumberPageForListAllProduct();
            List<Product> list = productDb.getListProductForAdmin(i);
             request.setAttribute("numPage", numberPage);
            request.setAttribute("index", i);
            request.setAttribute("listProduct", list);
            request.setAttribute("categoryDb", categoryDb);
            request.setAttribute("brandDb", brandDb);
             request.setAttribute("imageDb", imageDb);
             request.setAttribute("storeDb", storeDb);
             request.setAttribute("typeDb", typeDb);
              ServletContext context = request.getServletContext();
        context.getRequestDispatcher("/view/AdminView/adminManagerProduct.jsp").forward(request, response);
        
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//    throws ServletException, IOException {
//       int valueOption = Integer.parseInt(request.getParameter("optionSort"));
//        List<Product> product;
//                ImageDAO imageDb = new ImageDAO();
//       ProductDAO db = new ProductDAO();
//        CategoryDAO categoryDb = new CategoryDAO();
//        BrandDAO brandDb = new BrandDAO();
////        if(valueOption  == 0){
////            product = db.getListProductForAdmin();
////        }
////        else if(valueOption == 1){
////            product = db.getListProductSortByPrice();
////        }
////        else if(valueOption == 2){
////            product = db.getListProductSortByNameAZ();
////        }
////        else {
////            product = db.getListProductSortByQuantity();
////        }
//        request.setAttribute("valueOption", valueOption);
//        request.setAttribute("listProduct", product);
//                    request.setAttribute("categoryDb", categoryDb);
//            request.setAttribute("brandDb", brandDb);
//             request.setAttribute("imageDb", imageDb);
//              ServletContext context = request.getServletContext();
//        context.getRequestDispatcher("/view/AdminView/adminManagerProduct.jsp").forward(request, response);
//    }
    
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */




