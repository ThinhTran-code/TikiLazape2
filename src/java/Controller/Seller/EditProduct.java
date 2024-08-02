/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Seller;

import DAO.CategoryDAO;
import DAO.ImageDAO;
import DAO.ProductDAO;
import Model.Image.ImageProduct;
import Model.Product.Category;
import Model.Product.Color;
import Model.Product.Product;
import Model.Product.ProductTypeColor;
import Model.Product.Type;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, //1mb
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 20)
/**
 *
 * @author sktnb
 */
public class EditProduct extends HttpServlet {

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
        int id = Integer.parseInt((request.getParameter("pid")));
        ProductDAO db = new ProductDAO();
        Product p = db.getProductByIdProduct(id);
        request.setAttribute("detail", p);
        String paramType = request.getParameter("type");
        String paramColor = request.getParameter("color");
        System.out.println(paramType);

        Type pType = p.getListType().get(0);
        Color pColor = pType.getListColor().get(0);
        if (paramType != null) {
            int type = Integer.parseInt(paramType);
            for (Type i : p.getListType()) {
                if (i.getType_id() == type) {
                    pType = i;
                    System.out.println(true);
                    if (paramColor != null) {
                        int color = Integer.parseInt(paramColor);
                        for (Color j : pType.getListColor()) {
                            if (j.getColor_id() == color) {
                                pColor = j;
                                System.out.println(true);
                                break;
                            }
                        }
                    } else {
                        pColor = pType.getListColor().get(0);
                    }
                    break;
                }
            }
        }
        ArrayList<Color> colorList = (ArrayList<Color>) pType.getListColor();
        request.setAttribute("colorList", colorList);
        request.setAttribute("pType", pType);
        request.setAttribute("pColor", pColor);
        request.setAttribute("db", db);

        CategoryDAO cadb = new CategoryDAO();
        ImageDAO idb = new ImageDAO();
        List<ImageProduct> list = idb.getImageOfProductID(id);
        ImageDAO im = new ImageDAO();
        List<ImageProduct> listi = new ArrayList<>();
        listi = im.getImageOfProductID(id);
        request.setAttribute("cadb", cadb);
        request.setAttribute("listi", listi);
        ServletContext context = request.getServletContext();
        context.getRequestDispatcher("/Template/Edit.jsp").forward(request, response);
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
        try {
            // Lấy các thông tin từ request
            int id = Integer.parseInt(request.getParameter("id"));
            String txtName = request.getParameter("name");
            long txtPrice = Long.parseLong(request.getParameter("price"));
            String txtDescription = request.getParameter("description");
            int percentSale = Integer.parseInt(request.getParameter("sale"));

            // Khởi tạo đối tượng AddImage
            AddImage img = new AddImage();

            // Lấy dữ liệu hình ảnh cũ
            String[] oldImages = request.getParameterValues("OldtxtImages[]");

            // Khởi tạo mảng hình ảnh mới
            String[] txtImages = new String[oldImages.length];
            System.arraycopy(oldImages, 0, txtImages, 0, oldImages.length);

            // Lấy dữ liệu hình ảnh mới
            for (Part part : request.getParts()) {
                if (part.getName().startsWith("txtImages[")) {
                    String fileName = img.extractFileName(part);
                    if (fileName != null && !fileName.isEmpty() && part.getSize() > 0) {
                        fileName = new File(fileName).getName();
                        String uploadPath = request.getServletContext().getRealPath("/") + "image\\image_product\\image_pants" + File.separator + fileName;
                        // Ensure directory exists
                        File uploadDir = new File(uploadPath).getParentFile();
                        if (!uploadDir.exists()) {
                            uploadDir.mkdirs();
                        }
                        part.write(uploadPath);
                        String dbPath = "image\\image_product\\image_pants" + File.separator + fileName;

                        // Lấy chỉ số từ tên trường
                        String indexStr = part.getName().substring(10, part.getName().length() - 1);
                        int index = Integer.parseInt(indexStr);

                        // Cập nhật hình ảnh mới
                        txtImages[index] = dbPath;
                    }
                }
            }

            // Lấy dữ liệu Type, Color và Quantity
            String[] typeIDs = request.getParameterValues("typeIDs[]");
            String[] colorIDs = request.getParameterValues("colorIDs[]");
            String[] quantities = request.getParameterValues("quantities[]");

            List<ProductTypeColor> typeColors = new ArrayList<>();
            for (int i = 0; i < typeIDs.length; i++) {
                int typeID = Integer.parseInt(typeIDs[i]);
                int colorID = Integer.parseInt(colorIDs[i]);
                int quantity = Integer.parseInt(quantities[i]);
                typeColors.add(new ProductTypeColor(0, 0, typeID, colorID, quantity));
            }

            // Gọi phương thức DAO để cập nhật sản phẩm trong cơ sở dữ liệu
            ProductDAO dao = new ProductDAO();
            dao.UpdateProduct(id, txtName, txtPrice, txtDescription, percentSale, txtImages, typeColors.toArray(new ProductTypeColor[0]));

            // Chuyển hướng hoặc hiển thị thông báo thành công
            response.sendRedirect("manageproduct");
        } catch (ServletException | IOException | NumberFormatException e) {
            // Xử lý lỗi ở đây nếu cần thiết
            request.setAttribute("errorMessage", "Error occurred: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
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
