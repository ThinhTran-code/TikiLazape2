/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import static DAO.dbConfig.con;
import Model.Order.OrderDetail;
import Model.Product.Feedback;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Asus
 */

public class FeedbackDAO extends dbConfig{
    
    
    public FeedbackDAO(){
        super();
    }
     public int insertFeedBack(int orderDetal_id, String feedback, int rateStar, int customer_id) throws SQLException {
        String sql = "select * \n"
                + "from [OrderDetails]\n" 
                + "where orderDetai_id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, orderDetal_id);
        ResultSet rs = ps.executeQuery();
        
        if (rs.next()) {
            int order_id = rs.getInt(2);
            int product_id = rs.getInt(3);
            int type_id = rs.getInt(4);
            int color_id = rs.getInt(5);
            int unitPrice = rs.getInt(6);
            int quantityPrice = rs.getInt(7);
            long intoPrice = rs.getLong(8);
            boolean isFeedback = rs.getBoolean(9);
            OrderDetail o = new OrderDetail();
            o.setOrder_id(order_id);
            o.setOrderDetail_id(orderDetal_id);
            o.setProduct_id(product_id);
            o.setIntoPrice(intoPrice);
            o.setColor_id(color_id);
            o.setType_id(type_id);
            o.setUnitPrice(unitPrice);
            o.setQuantityProduct(quantityPrice);
            o.setIsFeedback(isFeedback);
            sql = "INSERT INTO Feedbacks ( product_id, feedback_comment,customer_id,feedback_rateStars) VALUES (?, ?,?,?)";
            ps = con.prepareStatement(sql);
          
            ps.setInt(1, product_id);
            ps.setString(2, feedback);
            ps.setInt(3, customer_id);
            ps.setInt(4, rateStar);
            int rowAffected = ps.executeUpdate();
            
            if (rowAffected > 0) {
              
                sql = "UPDATE OrderDetails SET isFeedback = 1 WHERE orderDetai_id = ? AND product_id = ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, orderDetal_id);
                ps.setInt(2, product_id);
                ps.executeUpdate();
                return product_id;
            }
        }
        return 0;
    }
    
     

        
       
     
    public List<Feedback> getCommentForHomepage() {
        List<Feedback> list = new ArrayList<>();
        String sql = "	WITH RankedFeedbacks AS (\n"
                + "		SELECT \n"
                + "			[product_id],\n"
                + "			[feedback_rateStars],\n"
                + "			[feedback_comment],\n"
                + "			[feedback_commentDate],\n"
                + "			[customer_id],\n"
                + "			ROW_NUMBER() OVER (PARTITION BY [product_id] ORDER BY [feedback_commentDate] DESC) AS Rank\n"
                + "		FROM \n"
                + "			[dbo].[Feedbacks] \n"
                + "		WHERE \n"
                + "			[feedback_rateStars] = 5\n"
                + "	)\n"
                + "	SELECT Top 5\n"
                + "		[product_id],\n"
                + "		customer_id,\n"
                + "		[feedback_rateStars],\n"
                + "		[feedback_comment],\n"
                + "		[feedback_commentDate]\n"
                + "	FROM \n"
                + "		RankedFeedbacks\n"
                + "	WHERE \n"
                + "		Rank = 1\n"
                + "	ORDER BY \n"
                + "		[product_id];";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int product_id = rs.getInt(1);
                int customer_id = rs.getInt(2);
                int feedback_rateStars = rs.getInt(3);
                String feedback_comment = rs.getString(4);
                Date feedback_commentDate = rs.getDate(5);
                Feedback feedback = new Feedback();
                feedback.setCustomer_id(customer_id);
                feedback.setFeedback_comment(feedback_comment);
                feedback.setFeedback_commentDate(feedback_commentDate);
                feedback.setFeedback_rateStars(feedback_rateStars);
                feedback.setProduct_id(product_id);
                list.add(feedback);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public List<Feedback> getCommentForViewStore(int user_id) {
        List<Feedback> list = new ArrayList<>();
        String sql = "	select Top 5 customer_id, store_id, f.feedback_rateStars, f.feedback_comment, f.feedback_commentDate, f.product_id from [Feedbacks] f\n"
                + "                join [Products] p on f.product_id = p.product_id\n"
                + "                 where store_id = ? and feedback_rateStars = 5";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, user_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int customer_id = rs.getInt(1);
                int feedback_rateStars = rs.getInt(3);
                String feedback_comment = rs.getString(4);
                Date feedback_commentDate = rs.getDate(5);
                int product_id = rs.getInt(6);
                Feedback feedback = new Feedback();
                feedback.setCustomer_id(customer_id);
                feedback.setFeedback_comment(feedback_comment);
                feedback.setFeedback_commentDate(feedback_commentDate);
                feedback.setFeedback_rateStars(feedback_rateStars);
                feedback.setProduct_id(product_id);
                list.add(feedback);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        FeedbackDAO fb = new FeedbackDAO();
        System.out.println(fb.getCommentForViewStore(3).size());

    }

    public List<Feedback> getListFeedbackByProductId(int product_id) {
        String sql = "select * from Feedbacks WHERE product_id = ?";
        List<Feedback> listComment = new ArrayList();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, product_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int feedback_id = rs.getInt(1), customer_id = rs.getInt(2), feedback_rateStars = rs.getInt(4);
                product_id = rs.getInt(3);
                String feedback_comment = rs.getString(5);
                java.util.Date feedback_commentDate = rs.getDate(6);

                Feedback feedback = new Feedback();
                feedback.setFeedback_id(feedback_id);
                feedback.setFeedback_comment(feedback_comment);
                feedback.setCustomer_id(customer_id);
                feedback.setFeedback_rateStars(feedback_rateStars);
                feedback.setProduct_id(product_id);
                feedback.setFeedback_commentDate((Date) feedback_commentDate);
                listComment.add(feedback);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listComment;
    }

}
