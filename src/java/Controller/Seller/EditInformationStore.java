/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Seller;

import DAO.UserDAO;
import Model.User.Store;
import Model.User.User;
import jakarta.servlet.ServletContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;


@MultipartConfig(fileSizeThreshold = 1024 * 1024, //1mb
        maxFileSize = 1024 * 1024 * 10,//10mb
        maxRequestSize = 1024 * 1024 * 11// 11mb
)
public class EditInformationStore extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EditInformationStore</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditInformationStore at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        HttpSession session = request.getSession();
        UserDAO ud = new UserDAO();
        Store st = new Store();
        User user = (User) session.getAttribute("s_u_tikilazapee");
        if (user != null) {
            st = ud.getStoreByStoreId(user.getUser_id());
            session.setAttribute("inforStore", st);
        }
        ServletContext context = request.getServletContext();
        context.getRequestDispatcher("/view/SellerView/InformationStore.jsp").forward(request, response);
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
        HttpSession session = request.getSession();
        String storeName = request.getParameter("store_name");
        String storePhone = request.getParameter("store_phone");
        String storeAddress = request.getParameter("store_address");
        String storeImage = "image//image_store//vector-shop-icon.jpg";
        String uploadFile = getServletContext().getRealPath("image/image_store");
        User user = (User) session.getAttribute("s_u_tikilazapee");
        if (user != null) {
            Store store = new Store();
            store.setUser_id(user.getUser_id());
            store.setStore_name(storeName);
            store.setStore_phone(storePhone);
            store.setStore_address(storeAddress);

            try {
                 Part part = request.getPart("store_image");
            if (part != null && part.getSize() > 0) {
                part.write(uploadFile + "/" + part.getSubmittedFileName());
                storeImage = "image/image_store/" + part.getSubmittedFileName();
                store.setStore_image(storeImage);
            }
                UserDAO userDAO = new UserDAO();
                userDAO.updateStore(store);
                session.setAttribute("inforStore", store);
                request.getRequestDispatcher("/view/SellerView/InformationStore.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

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
