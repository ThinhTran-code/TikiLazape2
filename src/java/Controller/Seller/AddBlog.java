/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Seller;

import DAO.BlogDAO;
import DAO.BrandDAO;
import DAO.CategoryDAO;
import DAO.StoreDAO;
import DAO.UserDAO;
import Model.Blog.Blog;
import Model.Product.Category;
import Model.User.User;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.IOException;
import java.util.List;


@MultipartConfig(fileSizeThreshold = 1024 * 1024, //1mb
        maxFileSize = 1024 * 1024 * 10,//10mb
        maxRequestSize = 1024 * 1024 * 11// 11mb
)
/**
 *
 * @author sktnb
 */
public class AddBlog extends HttpServlet {

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
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        UserDAO ub = new UserDAO();
        String username = null;
        String password = null;
        Cookie[] cookie = req.getCookies();
        if (cookie != null) {
            for (Cookie i : cookie) {
                if (i.getName().equals("c_s_u_tikilazapee")) {
                    username = i.getValue();
                }
                if (i.getName().equals("c_s_p_tikilazapee")) {
                    password = i.getValue();
                }
            }
        }
        HttpSession session = req.getSession();
        User user = ub.getUser(username, password);
        StoreDAO sd = new StoreDAO();
        CategoryDAO cd = new CategoryDAO();
        BrandDAO bd = new BrandDAO();
        List<Category> listc = cd.getAllCategory();
        request.setAttribute("listc", listc);
        request.setAttribute("id", user.getUser_id());
        ServletContext context = request.getServletContext();
        context.getRequestDispatcher("/view/SellerView/AddBlog.jsp").forward(request, response);
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
        
            // Lấy dữ liệu từ form
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("s_u_tikilazapee");
            BlogDAO dao = new BlogDAO();
            Blog blog = new Blog();
             String blog_image = "image//image_blog";
            String txtName = request.getParameter("txtName");
            String txtContent = request.getParameter("txtContent");
            int categoryID = Integer.parseInt(request.getParameter("category"));
           String uploadFile = getServletContext().getRealPath("image/image_blog");
            blog.setBlog_content(txtContent);
            blog.setBlog_title(txtName);
            blog.setUser_id(user.getUser_id());
            blog.setCategory_id(categoryID);
            try {
            Part part = request.getPart("txtImages");
            part.write(uploadFile + "/" + part.getSubmittedFileName());
            blog_image = "image/image_blog/" + part.getSubmittedFileName();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
            blog.setBlog_image(blog_image);
            dao.AddBlogForSeller(blog);
            response.sendRedirect("manageblog");
}
}