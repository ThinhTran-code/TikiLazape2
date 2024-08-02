/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Customer;

import DAO.OrderDAO;
import DAO.PaymentDAO;
import DAO.ProductTypeColorDAO;
import Model.Order.Payment;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author hbtth
 */
public class PaymentInforHandler extends HttpServlet {

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
            HttpSession session = request.getSession();
            List<Integer> list = (List<Integer>) session.getAttribute("ListNewOrderID");
            session.removeAttribute("ListNewOrderID");
            String vnp_Amount = request.getParameter("vnp_Amount");
            String vnp_BankCode = request.getParameter("vnp_BankCode");
            String vnp_BankTranNo = request.getParameter("vnp_BankTranNo");
            String vnp_PayDate = request.getParameter("vnp_PayDate");
            String vnp_OrderInfo = request.getParameter("vnp_OrderInfo");
            String vnp_TransactionStatus = request.getParameter("vnp_TransactionStatus");
            String vnp_TxnRef = request.getParameter("vnp_TxnRef");
            OrderDAO db = new OrderDAO();
            PaymentDAO pd = new PaymentDAO();
            if (vnp_TransactionStatus.equals("00")) {
                Payment payment = new Payment(0, (Long.parseLong(vnp_Amount) / 100), vnp_BankCode, vnp_BankTranNo, vnp_PayDate,
                        vnp_OrderInfo, vnp_TransactionStatus, vnp_TxnRef);
                pd.savePaymentBill(payment);
            } else {
                Payment payment = new Payment(0, (Long.parseLong(vnp_Amount) / 100), vnp_BankCode, vnp_BankTranNo, vnp_PayDate,
                        vnp_OrderInfo, vnp_TransactionStatus, vnp_TxnRef);
                list.stream().forEach(action -> {
                    db.cancelOrderByID(action);
                    db.handleOrderCanceled(action);
                });
                pd.savePaymentBill(payment);
            }
            response.sendRedirect("myorder");
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
