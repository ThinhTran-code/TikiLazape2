/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import static DAO.dbConfig.con;
import Model.Blog.Blog;
import Model.Blog.Interaction_Blog;
import Model.Product.Category;
import Model.Blog.Interaction_Blog_Comment;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class BlogDAO extends dbConfig {

    public BlogDAO() {
        super();
    }

    public List<Interaction_Blog> getInteractionBlog(int blogId) throws SQLException {
        String sql = "select interaction_blog_id, user_id, blog_isReaction\n"
                + "from Interaction_Blog\n"
                + "where blog_id = ?;";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, blogId);
        ResultSet rs = ps.executeQuery();
        ArrayList<Interaction_Blog> list = new ArrayList<>();

        while (rs.next()) {
            Interaction_Blog interaction_Blog = new Interaction_Blog();
            interaction_Blog.setBlog_isReaction(rs.getBoolean("blog_isReaction"));
            interaction_Blog.setUser(new UserDAO().getUserById(rs.getInt("user_id")));
            interaction_Blog.setListComment(this.getListComment(rs.getInt("interaction_blog_id")));
            list.add(interaction_Blog);
        }
        return list;
    }

    public List<Interaction_Blog_Comment> getListComment(int interactionBlogId) throws SQLException {
        String sql = "select comment_id, comment, comment_create_day\n"
                + "from Interaction_Blog_Comment\n"
                + "where interaction_blog_id = ?;";
        PreparedStatement ppsm = con.prepareCall(sql);
        ppsm.setInt(1, interactionBlogId);
        ResultSet rs = ppsm.executeQuery();

        ArrayList<Interaction_Blog_Comment> list = new ArrayList<>();
        while (rs.next()) {
            Interaction_Blog_Comment comment = new Interaction_Blog_Comment();
            comment.setComment(rs.getString("comment"));
            comment.setComment_id(rs.getInt("comment_id"));
            comment.setComment_create_day(Date.valueOf(rs.getString("comment_create_day")));
            list.add(comment);

        }
        return list;
    }

    public boolean insertComment(int blogId, int userId, String comment) throws SQLException {
        String sql = "select interaction_blog_id\n"
                + "from Interaction_Blog\n"
                + "where blog_id = ? and user_id = ?;";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, blogId);
        ps.setInt(2, userId);
        ResultSet rs = ps.executeQuery();
        int id = 0;
        if (rs.next()) {
            id = rs.getInt("interaction_blog_id");
        } else {
            id = insertInteraction(blogId, userId);
        }

        sql = "insert into Interaction_Blog_Comment(interaction_blog_id, comment)\n"
                + "values(?, ?);";
        ps = con.prepareCall(sql);
        ps.setInt(1, id);
        ps.setString(2, comment);
        int rowAffected = ps.executeUpdate();
        return rowAffected > 0;

    }

    public int insertInteraction(int blogId, int userId) throws SQLException {
        String sql = "insert into Interaction_Blog(blog_id, user_id)\n"
                + "values(?, ?)";
        PreparedStatement ppsm = con.prepareCall(sql);
        ppsm.setInt(1, blogId);
        ppsm.setInt(2, userId);
        ResultSet rs = ppsm.executeQuery();
        int id = 0;
        while (rs.next()) {
            id = rs.getInt("interaction_blog_id");
        }
        ppsm.close();
        return id;
    }
    

    public List<Blog> getListBlog() {

        List<Blog> listBlog = new ArrayList<>();

        List<Blog> listCata = new ArrayList<>();

        String sql = "select * from [Blog]";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Blog b = new Blog(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDate(7));
                Category category = new CategoryDAO().getCategoryById(rs.getInt(3));
                b.setCategory(category);
                listBlog.add(b);
            }
        } catch (Exception e) {

        }
        return listBlog;
    }


    public List<Blog> searchBlogForAdmin(String txtSearch) {
        List<Blog> listBlog = new ArrayList<>();
        try {
            String sql = "SELECT * from Blog\n"
                    + "	where blog_title like ?\n";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + txtSearch + "%");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int blog_id = rs.getInt(1);
                int user_id = rs.getInt(2);
                int category_id = rs.getInt(3);
                String blog_title = rs.getString(4);
                String blog_content = rs.getString(5);
                String blog_image = rs.getString(6);
                Date blog_date = rs.getDate(7);
                Blog blog = new Blog(blog_id, user_id, category_id, blog_title, blog_content, blog_image, blog_date);
                listBlog.add(blog);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listBlog;
    }
      public List<Blog> getListBlogSortByTitle() {
        List<Blog> listBlog = new ArrayList<>();
        try {
            String sql = "select * from Blog Order by blog_title ASC";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int blog_id = rs.getInt(1);
                int user_id = rs.getInt(2);
                int category_id = rs.getInt(3);
                String blog_title = rs.getString(4);
                String blog_content = rs.getString(5);
                String blog_image = rs.getString(6);
                Date blog_date = rs.getDate(7);
                Blog blog = new Blog(blog_id, user_id, category_id, blog_title, blog_content, blog_image, blog_date);
                listBlog.add(blog);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listBlog;
    }

    public List<Blog> getListBlogForSellerLeft(int user_id) {

        List<Blog> list = new ArrayList<>();
        String sql = "DECLARE @user_id INT = ?;\n"
                + "\n"
                + "DECLARE @total_blogs INT;\n"
                + "\n"
                + "SELECT @total_blogs = COUNT(*) FROM Blog WHERE user_id = @user_id;\n"
                + "\n"
                + "DECLARE @half_blogs INT;\n"
                + "SET @half_blogs = CEILING(@total_blogs / 2.0);\n"
                + "\n"
                + "\n"
                + "WITH Blog_CTE AS (\n"
                + "    SELECT *, ROW_NUMBER() OVER (ORDER BY blog_create_day DESC) AS RowNum\n"
                + "    FROM Blog\n"
                + "    WHERE user_id = @user_id\n"
                + ")\n"
                + "SELECT * \n"
                + "FROM Blog_CTE\n"
                + "WHERE RowNum <= @half_blogs;";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, user_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int blog_id = rs.getInt(1);
                int category_id = rs.getInt(3);
                String blog_title = rs.getString(4);
                String blog_content = rs.getString(5);
                String blog_image = rs.getString(6);
                Date blog_create_day = rs.getDate(7);
                Blog blog = new Blog(blog_id, user_id, category_id, blog_title, blog_content, blog_image, blog_create_day);
                list.add(blog);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<Blog> getListBlogForSellerRight(int user_id) {
        List<Blog> list = new ArrayList<>();
        String sql = "DECLARE @user_id INT = ?;\n"
                + "\n"
                + "DECLARE @total_blogs INT;\n"
                + "\n"
                + "SELECT @total_blogs = COUNT(*) FROM Blog WHERE user_id = @user_id;\n"
                + "\n"
                + "DECLARE @half_blogs INT;\n"
                + "SET @half_blogs = CEILING(@total_blogs / 2.0);\n"
                + "\n"
                + "\n"
                + "WITH Blog_CTE AS (\n"
                + "    SELECT *, ROW_NUMBER() OVER (ORDER BY blog_create_day DESC) AS RowNum\n"
                + "    FROM Blog\n"
                + "    WHERE user_id = @user_id\n"
                + ")\n"
                + "SELECT * \n"
                + "FROM Blog_CTE\n"
                + "WHERE RowNum > @half_blogs;";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, user_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int blog_id = rs.getInt(1);
                int category_id = rs.getInt(3);
                String blog_title = rs.getString(4);
                String blog_content = rs.getString(5);
                String blog_image = rs.getString(6);
                Date blog_create_day = rs.getDate(7);
                Blog blog = new Blog(blog_id, user_id, category_id, blog_title, blog_content, blog_image, blog_create_day);
                list.add(blog);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Blog> getListLatestBlogForSeller(int user_id) {
        List<Blog> list = new ArrayList<>();
        String sql = "SELECT Top 3 [blog_id]  \n"
                + "                        ,[user_id]  \n"
                + "                        ,[category_id]  \n"
                + "                        ,[blog_title]  \n"
                + "                        ,[blog_content]  \n"
                + "                        ,[blog_image]  \n"
                + "                        ,[blog_create_day]  \n"
                + "                    FROM [Tikilazapee].[dbo].[Blog]   \n"
                + "                    Where user_id = ?\n"
                + "                    order by blog_create_day desc";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, user_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int blog_id = rs.getInt(1);
                int category_id = rs.getInt(3);
                String blog_title = rs.getString(4);
                String blog_content = rs.getString(5);
                String blog_image = rs.getString(6);
                Date blog_create_day = rs.getDate(7);
                Blog blog = new Blog(blog_id, user_id, category_id, blog_title, blog_content, blog_image, blog_create_day);
                list.add(blog);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;

    }

    public void AddBlogForSeller(Blog blog) {
        try {
            String sql = "INSERT INTO [dbo].[Blog]\n"
                    + "           ([user_id]\n"
                    + "           ,[category_id]\n"
                    + "           ,[blog_title]\n"
                    + "           ,[blog_content]\n"
                    + "           ,[blog_image]\n"
                    + "           )\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           )";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, blog.getUser_id());
            ps.setInt(2, blog.getCategory_id());
            ps.setString(3, blog.getBlog_title());
            ps.setString(4, blog.getBlog_content());
            ps.setString(5, blog.getBlog_image());
            int x = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
      public void AddBlogForAdmin(Blog blog) {
        try {
            String sql = "INSERT INTO [dbo].[Blog]\n"
                    + "           ([user_id]\n"
                    + "           ,[category_id]\n"
                    + "           ,[blog_title]\n"
                    + "           ,[blog_content]\n"
                    + "           ,[blog_image]\n"
                    + "           )\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           )";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, blog.getUser_id());
            ps.setInt(2, blog.getCategory_id());
            ps.setString(3, blog.getBlog_title());
            ps.setString(4, blog.getBlog_content());
            ps.setString(5, blog.getBlog_image());
            int x = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // hung 
    public Blog getBlogByBlogId(int blog_id) {

        String sql = "select * from Blog where blog_id = ?";
        Blog blog = null;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, blog_id);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                blog_id = rs.getInt("blog_id");
                int category_id = rs.getInt("category_id");
                int user_id = rs.getInt("user_id");
                String blog_title = rs.getString("blog_title");
                String blog_content = rs.getString("blog_content");
                Date blog_create_day = rs.getDate("blog_create_day");
                String blog_image = rs.getString("blog_image");
//                String comment = rs.getString("comment");

                List<Interaction_Blog_Comment> listcomment = new ArrayList();

                blog = new Blog();
                blog.setBlog_id(blog_id);
                blog.setUser_id(user_id);
                blog.setCategory_id(category_id);
                blog.setBlog_title(blog_title);
                blog.setBlog_content(blog_content);
                blog.setBlog_image(blog_image);
                blog.setBlog_create_day(blog_create_day);
//                Interaction_Blog_Comment cm = new Interaction_Blog_Comment();
//                cm.setComment(comment);
//                listcomment.add(cm);
//                blog.setListComment(listcomment);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return blog;

    }

    public int getReactionByBlogId(int blog_id) {
        String sql = "select COUNT(*) from Interaction_Blog\n"
                + "where blog_id = ?";

        int total = 0;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, blog_id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                total = rs.getInt(1);
            }

        } catch (Exception e) {
        }
        return total;
    }

    public List<Blog> getListLatestBlog() {
        List<Blog> list = new ArrayList<>();
        String sql = "SELECT Top 3 [blog_id]  \n"
                + "                        ,[user_id]  \n"
                + "                        ,[category_id]  \n"
                + "                        ,[blog_title]  \n"
                + "                        ,[blog_content]  \n"
                + "                        ,[blog_image]  \n"
                + "                        ,[blog_create_day]  \n"
                + "                    FROM [Tikilazapee].[dbo].[Blog]   \n"
                + "                    order by blog_create_day desc";
        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int user_id = rs.getInt("user_id");
                int blog_id = rs.getInt(1);
                int category_id = rs.getInt(3);
                String blog_title = rs.getString(4);
                String blog_content = rs.getString(5);
                String blog_image = rs.getString(6);
                Date blog_create_day = rs.getDate(7);
                Blog blog = new Blog(blog_id, user_id, category_id, blog_title, blog_content, blog_image, blog_create_day);
                list.add(blog);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;

    }

    public List<Blog> getAllBlogs() {
        List<Blog> listBlog = new ArrayList<>();
        String sql = "select b.blog_id , b.user_id,b.category_id,c.name,b.blog_title,b.blog_content,b.blog_image,b.blog_create_day,u.fullname from Blog b \n"
                + "join Categories c on b.category_id = c.category_id\n"
                + "join Users u on b.user_id = u.user_id \n"
                + "ORDER BY [blog_create_day] DESC;";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int user_id = rs.getInt("user_id");
                int category_id = rs.getInt("category_id");
                String blog_title = rs.getString("blog_title");
                String blog_content = rs.getString("blog_content");
                String blog_image = rs.getString("blog_image");
                Date blog_create_day = rs.getDate("blog_create_day");
                String name = rs.getString("name");

                Blog blog = new Blog();

                blog.setBlog_id(rs.getInt(1));
                blog.setBlog_content(blog_content);
                blog.setBlog_create_day(blog_create_day);
                blog.setUser_id(user_id);
                blog.setBlog_image(blog_image);
                blog.setBlog_title(blog_title);
                blog.setCategory_id(category_id);

                listBlog.add(blog);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listBlog;
    }

    public int DeleteBlog(int blogId, String cid) {
        String sql = "Delete from Blog where blog_id = ? and category_id = ?";
        int total = 0;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, blogId);
            ps.setString(2, cid);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return total;
    }

    public List<Interaction_Blog_Comment> getListCommentByBlogId(int blog_id) {
        List<Interaction_Blog_Comment> listcomment = new ArrayList<>();
        String sql = "select * from Interaction_Blog_Comment\n"
                + "where blog_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, blog_id);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                blog_id = rs.getInt("blog_id");
                int comment_id = rs.getInt("comment_id");
                int interaction_blog_id = rs.getInt("interaction_blog_id");

                Date comment_create_day = rs.getDate("comment_create_day");
                String comment = rs.getString("comment");
                Interaction_Blog_Comment cm = new Interaction_Blog_Comment();
                cm.setBlog_id(blog_id);
                cm.setComment(comment);
                cm.setComment_create_day(comment_create_day);
                cm.setComment_id(comment_id);
                cm.setInteraction_blog_id(interaction_blog_id);
                listcomment.add(cm);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listcomment;
    }
    public void editBlogForSeller(Blog blog, int blog_id) {
        try {
            String sql = "UPDATE Blog \n"
                    + "set category_id=?, blog_title = ?, blog_content= ?, blog_image= ?\n"
                    + "where blog_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, blog.getCategory_id());
            ps.setString(2, blog.getBlog_title());
            ps.setString(3, blog.getBlog_content());
            ps.setString(4, blog.getBlog_image());
            ps.setInt(5, blog_id);
            int x = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
  public void editBlogForAdmin(Blog blog, int blog_id) {
        try {
            String sql = "UPDATE Blog \n"
                    + "set category_id=?, blog_title = ?, blog_image= ?\n"
                    + "where blog_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, blog.getCategory_id());
            ps.setString(2, blog.getBlog_title());
            ps.setString(3, blog.getBlog_image());
            ps.setInt(4, blog_id);
            int x = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void deleteBlogForSeller(int blog_id) {
        try {
            String sql = "DELETE FROM [dbo].[Blog]\n"
                    + "      WHERE blog_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, blog_id);
            int x = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//
//    public Blog getBlogByBlogId(int blog_id) {
//
//        String sql = "select b.blog_id , b.user_id,b.category_id,c.name,b.blog_title,b.blog_content,b.blog_image,b.blog_create_day,u.fullname from Blog b \n"
//                + "join Categories c on b.category_id = c.category_id\n"
//                + "join Users u on b.user_id = u.user_id \n"
//                + "where b.blog_id = ?";
//        Blog blog = null;
//
//        try {
//            PreparedStatement ps = con.prepareStatement(sql);
//            ps.setInt(1, blog_id);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                blog_id = rs.getInt("blog_id");
//                int category_id = rs.getInt("category_id");
//                int user_id = rs.getInt("user_id");
//                String blog_title = rs.getString("blog_title");
//                String blog_content = rs.getString("blog_content");
//                Date blog_create_day = rs.getDate("blog_create_day");
//                String blog_image = rs.getString("blog_image");
////                String comment = rs.getString("comment");
//
//                List<Interaction_Blog_Comment> listcomment = new ArrayList();
//
//                blog = new Blog();
//                blog.setBlog_id(blog_id);
//                blog.setUser_id(user_id);
//                blog.setCategory_id(category_id);
//                blog.setBlog_title(blog_title);
//                blog.setBlog_content(blog_content);
//                blog.setBlog_image(blog_image);
//                blog.setBlog_create_day(blog_create_day);
////                Interaction_Blog_Comment cm = new Interaction_Blog_Comment();
////                cm.setComment(comment);
////                listcomment.add(cm);
////                blog.setListComment(listcomment);
//                
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        return blog;
//    }

    public List<Blog> searchBlogForSeller(String text, int user_id) {
        List<Blog> list = new ArrayList<>();
        String sql = "Select b.blog_id, b.user_id, c.category_id, b.blog_title ,b.blog_content, b.blog_image, b.blog_create_day \n"
                + "	from Blog b join Categories c on b.category_id = c.category_id\n"
                + "	where b.user_id = ? and b.blog_title like ? or c.name like ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, user_id);
            ps.setString(2, "%" + text + "%");
            ps.setString(3, "%" + text + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int blog_id = rs.getInt(1);
                int category_id = rs.getInt(3);
                String blog_title = rs.getString(4);
                String blog_content = rs.getString(5);
                String blog_image = rs.getString(6);
                Date blog_create_day = rs.getDate(7);
                Blog blog = new Blog(blog_id, user_id, category_id, blog_title, blog_content, blog_image, blog_create_day);
                list.add(blog);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public List<Blog> searchBlogForCustomer(String text) {
        List<Blog> list = new ArrayList<>();
        String sql = "Select b.blog_id, b.user_id, c.category_id, b.blog_title ,b.blog_content, b.blog_image, b.blog_create_day \n" +
"	from Blog b join Categories c on b.category_id = c.category_id\n" +
"	where   b.blog_title like ? ";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
           
            ps.setString(1, "%" + text + "%");
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int user_id = rs.getInt("user_id");
                int blog_id = rs.getInt(1);
                int category_id = rs.getInt(3);
                String blog_title = rs.getString(4);
                String blog_content = rs.getString(5);
                String blog_image = rs.getString(6);
                Date blog_create_day = rs.getDate(7);
                Blog blog = new Blog(blog_id, user_id, category_id, blog_title, blog_content, blog_image, blog_create_day);
                list.add(blog);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }


    public static void main(String[] args) {
        BlogDAO db = new BlogDAO();
        
      
    }

////    public List<Blog> getListBlog() {
////        List<Blog> listCata = new ArrayList<>();
////        String sql = "select * from [Blog]";
////        try {
////            PreparedStatement ps = con.prepareStatement(sql);
////            ResultSet rs = ps.executeQuery();
////            while (rs.next()) {
////                Blog b = new Blog(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDate(7));
////                Category category = new CategoryDAO().getCategoryById(rs.getInt(3));
////                b.setCategory(category);
////                listCata.add(b);
////            }
////        } catch (Exception e) {
////
////        }
////        return listCata;
////    }
////
////    public List<Blog> getListBlogForSellerLeft(int user_id) {
////        List<Blog> list = new ArrayList<>();
////        String sql = "DECLARE @user_id INT = ?;\n"
////                + "\n"
////                + "DECLARE @total_blogs INT;\n"
////                + "\n"
////                + "SELECT @total_blogs = COUNT(*) FROM Blog WHERE user_id = @user_id;\n"
////                + "\n"
////                + "DECLARE @half_blogs INT;\n"
////                + "SET @half_blogs = CEILING(@total_blogs / 2.0);\n"
////                + "\n"
////                + "\n"
////                + "WITH Blog_CTE AS (\n"
////                + "    SELECT *, ROW_NUMBER() OVER (ORDER BY blog_create_day DESC) AS RowNum\n"
////                + "    FROM Blog\n"
////                + "    WHERE user_id = @user_id\n"
////                + ")\n"
////                + "SELECT * \n"
////                + "FROM Blog_CTE\n"
////                + "WHERE RowNum <= @half_blogs;";
////        try {
////            PreparedStatement ps = con.prepareStatement(sql);
////            ps.setInt(1, user_id);
////            ResultSet rs = ps.executeQuery();
////            while (rs.next()) {
////                int blog_id = rs.getInt(1);
////                int category_id = rs.getInt(3);
////                String blog_title = rs.getString(4);
////                String blog_content = rs.getString(5);
////                String blog_image = rs.getString(6);
////                Date blog_create_day = rs.getDate(7);
////                Blog blog = new Blog(blog_id, user_id, category_id, blog_title, blog_content, blog_image, blog_create_day);
////                list.add(blog);
////
////            }
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////
////        return list;
////    }
////
////    public List<Blog> getListBlogForSellerRight(int user_id) {
////        List<Blog> list = new ArrayList<>();
////        String sql = "DECLARE @user_id INT = ?;\n"
////                + "\n"
////                + "DECLARE @total_blogs INT;\n"
////                + "\n"
////                + "SELECT @total_blogs = COUNT(*) FROM Blog WHERE user_id = @user_id;\n"
////                + "\n"
////                + "DECLARE @half_blogs INT;\n"
////                + "SET @half_blogs = CEILING(@total_blogs / 2.0);\n"
////                + "\n"
////                + "\n"
////                + "WITH Blog_CTE AS (\n"
////                + "    SELECT *, ROW_NUMBER() OVER (ORDER BY blog_create_day DESC) AS RowNum\n"
////                + "    FROM Blog\n"
////                + "    WHERE user_id = @user_id\n"
////                + ")\n"
////                + "SELECT * \n"
////                + "FROM Blog_CTE\n"
////                + "WHERE RowNum > @half_blogs;";
////        try {
////            PreparedStatement ps = con.prepareStatement(sql);
////            ps.setInt(1, user_id);
////            ResultSet rs = ps.executeQuery();
////            while (rs.next()) {
////                int blog_id = rs.getInt(1);
////                int category_id = rs.getInt(3);
////                String blog_title = rs.getString(4);
////                String blog_content = rs.getString(5);
////                String blog_image = rs.getString(6);
////                Date blog_create_day = rs.getDate(7);
////                Blog blog = new Blog(blog_id, user_id, category_id, blog_title, blog_content, blog_image, blog_create_day);
////                list.add(blog);
////            }
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////        return list;
////    }
////
////    public List<Blog> getListLatestBlogForSeller(int user_id) {
////        List<Blog> list = new ArrayList<>();
////        String sql = "SELECT Top 3 [blog_id]  \n"
////                + "                        ,[user_id]  \n"
////                + "                        ,[category_id]  \n"
////                + "                        ,[blog_title]  \n"
////                + "                        ,[blog_content]  \n"
////                + "                        ,[blog_image]  \n"
////                + "                        ,[blog_create_day]  \n"
////                + "                    FROM [Tikilazapee].[dbo].[Blog]   \n"
////                + "                    Where user_id = ?\n"
////                + "                    order by blog_create_day desc";
////        try {
////            PreparedStatement ps = con.prepareStatement(sql);
////            ps.setInt(1, user_id);
////            ResultSet rs = ps.executeQuery();
////            while (rs.next()) {
////                int blog_id = rs.getInt(1);
////                int category_id = rs.getInt(3);
////                String blog_title = rs.getString(4);
////                String blog_content = rs.getString(5);
////                String blog_image = rs.getString(6);
////                Date blog_create_day = rs.getDate(7);
////                Blog blog = new Blog(blog_id, user_id, category_id, blog_title, blog_content, blog_image, blog_create_day);
////                list.add(blog);
////            }
////        } catch (Exception ex) {
////            ex.printStackTrace();
////        }
////        return list;
////
////    }
////
////    public void AddBlogForSeller(Blog blog) {
////        try {
////            String sql = "INSERT INTO [dbo].[Blog]\n"
////                    + "           ([user_id]\n"
////                    + "           ,[category_id]\n"
////                    + "           ,[blog_title]\n"
////                    + "           ,[blog_content]\n"
////                    + "           ,[blog_image]\n"
////                    + "           )\n"
////                    + "     VALUES\n"
////                    + "           (?\n"
////                    + "           ,?\n"
////                    + "           ,?\n"
////                    + "           ,?\n"
////                    + "           ,?\n"
////                    + "           )";
////            PreparedStatement ps = con.prepareStatement(sql);
////
////            ps.setInt(1, blog.getUser_id());
////            ps.setInt(2, blog.getCategory_id());
////            ps.setString(3, blog.getBlog_title());
////            ps.setString(4, blog.getBlog_content());
////            ps.setString(5, blog.getBlog_image());
////            int x = ps.executeUpdate();
////
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////    }
////
////    // hung 
////    public void editBlogForSeller(Blog blog, int blog_id) {
////        try {
////            String sql = "UPDATE Blog \n"
////                    + "set category_id=?, blog_title = ?, blog_content= ?, blog_image= ?\n"
////                    + "where blog_id = ?";
////            PreparedStatement ps = con.prepareStatement(sql);
////
////            ps.setInt(1, blog.getCategory_id());
////            ps.setString(2, blog.getBlog_title());
////            ps.setString(3, blog.getBlog_content());
////            ps.setString(4, blog.getBlog_image());
////            ps.setInt(5, blog_id);
////            int x = ps.executeUpdate();
////
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////    }
////
////    public void deleteBlogForSeller(int blog_id) {
////        try {
////            String sql = "DELETE FROM [dbo].[Blog]\n"
////                    + "      WHERE blog_id = ?";
////            PreparedStatement ps = con.prepareStatement(sql);
////            ps.setInt(1, blog_id);
////            int x = ps.executeUpdate();
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////
////    }
////
////    public List<Blog> searchBlogForSeller(String text, int user_id) {
////        List<Blog> list = new ArrayList<>();
////        String sql = "Select b.blog_id, b.user_id, c.category_id, b.blog_title ,b.blog_content, b.blog_image, b.blog_create_day \n"
////                + "	from Blog b join Categories c on b.category_id = c.category_id\n"
////                + "	where b.user_id = ? and b.blog_title like ? or c.name like ?";
////        try {
////            PreparedStatement ps = con.prepareStatement(sql);
////            ps.setInt(1, user_id);
////            ps.setString(2, "%" + text + "%");
////            ps.setString(3, "%" + text + "%");
////            ResultSet rs = ps.executeQuery();
////            while (rs.next()) {
////                int blog_id = rs.getInt(1);
////                int category_id = rs.getInt(3);
////                String blog_title = rs.getString(4);
////                String blog_content = rs.getString(5);
////                String blog_image = rs.getString(6);
////                Date blog_create_day = rs.getDate(7);
////                Blog blog = new Blog(blog_id, user_id, category_id, blog_title, blog_content, blog_image, blog_create_day);
////                list.add(blog);
////            }
////        } catch (Exception ex) {
////            ex.printStackTrace();
////        }
////        return list;
////    }
//
//    public List<Blog> searchBlogForCustomer(String text) {
//        List<Blog> list = new ArrayList<>();
//        String sql = "Select b.blog_id, b.user_id, c.category_id, b.blog_title ,b.blog_content, b.blog_image, b.blog_create_day \n"
//                + "	from Blog b join Categories c on b.category_id = c.category_id\n"
//                + "	where   b.blog_title like ? or c.name like ?";
//        try {
//            PreparedStatement ps = con.prepareStatement(sql);
//
//            ps.setString(2, "%" + text + "%");
//            ps.setString(3, "%" + text + "%");
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                int user_id = rs.getInt("user_id");
//                int blog_id = rs.getInt(1);
//                int category_id = rs.getInt(3);
//                String blog_title = rs.getString(4);
//                String blog_content = rs.getString(5);
//                String blog_image = rs.getString(6);
//                Date blog_create_day = rs.getDate(7);
//                Blog blog = new Blog(blog_id, user_id, category_id, blog_title, blog_content, blog_image, blog_create_day);
//                list.add(blog);
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return list;
//    }

}
