package Controller.Admin;

import DAO.CategoryDAO;
import DAO.ProductDAO;
import DAO.UserDAO;
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
import java.io.File;
import java.io.IOException;
import java.util.List;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, // 1MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 11 // 11MB
)
public class AddCategoryForAdmin extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        UserDAO userDAO = new UserDAO();
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
        User user = userDAO.getUser(username, password);
        CategoryDAO categoryDAO = new CategoryDAO();
        ProductDAO productDb = new ProductDAO();
        List<Category> listc = categoryDAO.getAllCategory();
        request.setAttribute("listc", listc);
        request.setAttribute("productDb", productDb);
        request.setAttribute("id", user.getUser_id());
        ServletContext context = request.getServletContext();
        context.getRequestDispatcher("/view/AdminView/AddCategoryFromAdmin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String categoryName = request.getParameter("categoryName");
        String categoryImage = "image/image_category";
        String uploadDir = getServletContext().getRealPath("/image/image_category");
        CategoryDAO categoryDAO = new CategoryDAO();
        Category category = new Category();

        try {
            Part part = request.getPart("txtImages[]");
            String fileName = part.getSubmittedFileName();
            File uploadDirFile = new File(uploadDir);
            if (!uploadDirFile.exists()) {
                uploadDirFile.mkdirs();
            }
            part.write(uploadDir + File.separator + fileName);
            categoryImage = "image/image_category/" + fileName;
            category.setNameImage(categoryImage);

            if (categoryName != null && !categoryName.trim().isEmpty()) {
                categoryDAO.insertCata(categoryName, categoryImage);
                request.setAttribute("successMessage", "Category added successfully.");
            } else {
                request.setAttribute("errorMessage", "Category name cannot be empty.");
            }
        } catch (Exception e) {
            request.setAttribute("errorMessage", "An error occurred while adding the category: " + e.getMessage());
        }

        List<Category> listc = categoryDAO.getAllCategory();
        request.setAttribute("listc", listc);

        ServletContext context = request.getServletContext();
        context.getRequestDispatcher("/view/AdminView/AddCategoryFromAdmin.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
