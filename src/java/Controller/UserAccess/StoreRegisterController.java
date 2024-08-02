/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.UserAccess;

import DAO.UserDAO;
import Model.User.Store;
import Model.User.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, //1mb
        maxFileSize = 1024 * 1024 * 10,//10mb
        maxRequestSize = 1024 * 1024 * 11// 11mb
)
public class StoreRegisterController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet StoreRegisterController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet StoreRegisterController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cookie[] cookie = request.getCookies();
        if (cookie != null) {
            for (Cookie i : cookie) {
                if (i.getName().equals("c_s_u_tikilazapee")) {
                    i.setMaxAge(0);
                    response.addCookie(i);
                }
                if (i.getName().equals("c_s_p_tikilazapee")) {
                    i.setMaxAge(0);
                    response.addCookie(i);
                }
            }
        }
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("s_u_r_tikilazapee");
        if(user == null){
            response.sendRedirect("home");
        }
        request.getRequestDispatcher("view/loginView/StoreRegisterPage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String store_name = request.getParameter("store_name");
        String store_phone = request.getParameter("store_phone");
        String store_address = request.getParameter("store_address");
        String store_image = "image//image_store//vector-shop-icon.jpg";
        String uploadFile = getServletContext().getRealPath("image/image_store");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("s_u_r_tikilazapee");
        Store store = new Store();
        store.setUser_id(user.getUser_id());
        store.setStore_name(store_name);
        store.setStore_phone(store_phone);
        store.setStore_address(store_address);
        try {
            Part part = request.getPart("store_image");
            part.write(uploadFile + "/" + part.getSubmittedFileName());
            store_image = "image/image_store/" + part.getSubmittedFileName();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Cookie c_u_tikilazapee = new Cookie("c_s_u_tikilazapee", user.getUsername());
        Cookie c_p_tikilazapee = new Cookie("c_s_p_tikilazapee", user.getPassword());
        c_p_tikilazapee.setMaxAge(60 * 60 * 24 * 3);
        c_u_tikilazapee.setMaxAge(60 * 60 * 24 * 3);
        response.addCookie(c_u_tikilazapee);
        response.addCookie(c_p_tikilazapee);
        store.setStore_image(store_image);
        UserDAO db = new UserDAO();
        db.addStore(store);
        response.sendRedirect("index");
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
