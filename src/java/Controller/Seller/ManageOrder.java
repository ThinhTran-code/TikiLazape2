/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Seller;

import DAO.OrderDAO;
import Model.Order.Order;
import Model.User.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.util.List;
import org.json.JSONObject;

/**
 *
 * @author hbtth
 */
public class ManageOrder extends HttpServlet {

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
            out.println("<title>Servlet ManageOrder</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ManageOrder at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        OrderDAO db = new OrderDAO();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("s_u_tikilazapee");
        String status = null;
        try {
            status = request.getParameter("status");
        } catch (Exception e) {
            e.printStackTrace();
        }
        int s = -3;
        if (status != null) {
            s = Integer.parseInt(status);
        }
        List<Order> list = db.getListOrderByStore_id(user.getUser_id(), s);
        request.setAttribute("list", list);
        request.setAttribute("status", s);
        getServletContext().getRequestDispatcher("/view/SellerView/ManageOrder.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int order_id = Integer.parseInt(request.getParameter("order_id"));
        OrderDAO db = new OrderDAO();
        boolean isConfirm = db.confirmOrder(order_id);
        if (isConfirm) {
            request.setAttribute("notice", "");
            response.sendRedirect("manageorder");
        } else {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("s_u_tikilazapee");
            List<Order> list = db.getListOrderByStore_id(user.getUser_id(), 0);
            request.setAttribute("order_id", order_id);
            request.setAttribute("list", list);
            request.setAttribute("status", 0);
            request.setAttribute("notice", "Available of this product is not enough!!!");
            getServletContext().getRequestDispatcher("/view/SellerView/ManageOrder.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = req.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        JSONObject json = new JSONObject(sb.toString());
        int order_id = json.getInt("order_id");
        int status = json.getInt("status");
        OrderDAO db = new OrderDAO();
        db.changeStatusOrder(order_id, status);

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
