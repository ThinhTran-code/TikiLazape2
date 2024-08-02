package Controller.Admin;

import DAO.CategoryDAO;
import DAO.ProductDAO;
import DAO.UserDAO;
import Model.Product.Category;
import Model.User.User;
import jakarta.servlet.ServletContext;
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
import java.io.File;
import java.util.List;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, // 1mb
        maxFileSize = 1024 * 1024 * 10, // 10mb
        maxRequestSize = 1024 * 1024 * 11 // 11mb
)
public class EditCategoryForAdmin extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EditCategoryForAdmin</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditCategoryForAdmin at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        UserDAO userDAO = new UserDAO();
        String username = null;
        String password = null;
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("c_s_u_tikilazapee")) {
                    username = cookie.getValue();
                }
                if (cookie.getName().equals("c_s_p_tikilazapee")) {
                    password = cookie.getValue();
                }
            }
        }
        String id = request.getParameter("category_id");
        request.setAttribute("category_id", id);
        ServletContext context = request.getServletContext();
        context.getRequestDispatcher("/view/AdminView/EditCategoryFormAdmin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CategoryDAO categoryDAO = new CategoryDAO();
        int category_id = Integer.parseInt(request.getParameter("category_id"));
        String txtName = request.getParameter("categoryName");
        String category_image_default = request.getParameter("image_category_01");
        String category_image = "image/image_category/";
        String uploadFile = getServletContext().getRealPath("image/image_category");

        Category category = new Category();
        category.setCategory_name(txtName);
        category.setCategory_id(category_id);
        try {
            Part part = request.getPart("txtImages");
            if (part != null && part.getSubmittedFileName() != null && !part.getSubmittedFileName().isEmpty()) {
                part.write(uploadFile + File.separator + part.getSubmittedFileName());
                category_image += part.getSubmittedFileName();
                if (category_image.equals("image/image_category/")) {
                    category.setNameImage(category_image_default);
                } else {
                    category.setNameImage(category_image);
                }
            } else {
                category.setNameImage(category_image_default);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(txtName + " " + category_image);
       categoryDAO.updateCategory(category);
       response.sendRedirect("managecategory");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
