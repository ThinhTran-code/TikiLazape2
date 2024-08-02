/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Customer;
import DAO.*;


import DAO.FilterDAO;
import DAO.ProductDAO;
import Model.Image.ImageSlider;
import Model.Product.Category;
import Model.Product.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author ACER
 */
public class listProductController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
             ImageDAO di = new ImageDAO();
            FilterDAO df =new FilterDAO();
            ProductDAO db = new ProductDAO();
            CategoryDAO dc = new CategoryDAO();
            int category_id = Integer.parseInt(request.getParameter("category_id"));
            String filter = request.getParameter("filter_id");
            
            
           
           
            List<Category> listC = dc.getListCategory();
            request.setAttribute("listC", listC);
            request.setAttribute("df", df);
            List<ImageSlider> listm = di.getListImageOfSlider(category_id);
            request.setAttribute("listm", listm);
//            for(Product i : list){
//                System.out.println(i.getProduct_id());
//                System.out.println(i.getCategory_id());
//                System.out.println(i.getProduct_name());
//                System.out.println(i.getProduct_originPrice());
//                System.out.println(i.getProduct_percenSale());
//                System.out.println(i.getListImage().get(0).getImageProduct_url());
//               
//            }
            List<Product> list = null;
            if (filter != null) {
                int filter_id = Integer.parseInt(filter);
                list = db.getListProductByFilter(filter_id);
            } else {
                list = db.getListProductByCategoriesId(category_id);
            }
            request.setAttribute("db", db);
            request.setAttribute("listProduct", list);
          request.getRequestDispatcher("view/CustomerView/listProductOfCategories.jsp").forward(request, response);
        } 
            
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
