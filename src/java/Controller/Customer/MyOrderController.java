/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller.Customer;

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
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author hbtth
 */
public class MyOrderController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MyOrderController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MyOrderController at " + request.getContextPath () + "</h1>");
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
        List<Order> list = db.getOrderByUserID(user.getUser_id());
        request.setAttribute("list", list);
        request.getRequestDispatcher("view/CustomerView/MyOrder.jsp").forward(request, response);
    } 


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        StringBuilder sb =  new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while((line = reader.readLine())!= null){
            sb.append(line);
        }
        JSONObject json = new JSONObject(sb.toString());
        JSONArray jsons = new JSONArray(json.getString("item_id"));
        int payment_method = json.getInt("payment_method");
        String[] list = new String[jsons.length()];
        for(int i =0;i<jsons.length();++i){
            list[i] = String.valueOf(jsons.getInt(i));
        }
        OrderDAO db = new OrderDAO();
        List<Integer> listOrderID = db.placeOrder(list, payment_method);
        HttpSession session = request.getSession();
        session.setAttribute("ListNewOrderID", listOrderID);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = req.getReader();
        String line;
        while((line = reader.readLine())!=null){
            sb.append(line);
        }
        JSONObject json = new JSONObject(sb.toString());
        int order_id = json.getInt("order_id");
        int status = json.getInt("status");
        OrderDAO db = new OrderDAO();
        db.changeStatusOrder(order_id, status);     
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
