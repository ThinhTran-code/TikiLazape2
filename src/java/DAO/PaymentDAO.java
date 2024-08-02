/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Order.Payment;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author hbtth
 */
public class PaymentDAO extends dbConfig {

    public PaymentDAO() {
        super();
    }

    public void savePaymentBill(Payment payment) {
        String sql = "INSERT INTO [dbo].[Payment]\n"
                + "           ([vnp_Amount]\n"
                + "           ,[vnp_BankCode]\n"
                + "           ,[vnp_BankTranNo]\n"
                + "           ,[vnp_PayDate]\n"
                + "           ,[vnp_OrderInfo]\n"
                + "           ,[vnp_TransactionStatus]\n"
                + "           ,[vnp_TxnRef])\n"
                + "     VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, payment.getVnp_Amount());
            ps.setString(2, payment.getVnp_BankCode());
            ps.setString(3, payment.getVnp_BankTranNo());
            ps.setString(4, payment.getVnp_PayDate());
            ps.setString(5, payment.getVnp_OrderInfo());
            ps.setString(6, payment.getVnp_TransactionStatus());
            ps.setString(7, payment.getVnp_TxnRef());
            int x = ps.executeUpdate();           
        } catch (SQLException ex) {
            Logger.getLogger(PaymentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
