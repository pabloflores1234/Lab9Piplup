package pe.edu.pucp.tel131lab9.dao;

import pe.edu.pucp.tel131lab9.bean.Employee;
import pe.edu.pucp.tel131lab9.bean.Post;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PostDao extends DaoBase{

    public ArrayList<Post> listPosts() {

        ArrayList<Post> posts = new ArrayList<>();

        String sql = "SELECT p.post_id,title,concat(first_name,' ',last_name) as empleado ,p.datetime,content,count(comment_id) as comentarios FROM post p left join employees e on e.employee_id = p.employee_id\n" +
                "left join comments c on c.post_id=p.post_id\n" +
                "group by p.post_id;";

        try (Connection conn = this.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Post post = new Post();
                //fetchPostData(post, rs);
                post.setPostId(rs.getInt(1));
                post.setTitle(rs.getString(2));
                post.setNombrecompleto(rs.getString("empleado"));
                post.setDatetime(rs.getTimestamp(4));
                post.setContent(rs.getString(5));
                post.setNumerocomentarios(rs.getInt("comentarios"));

                /*Employee employee= new Employee();
                employee.setEmployeeId(rs.getInt(2));
                employee.setFirstName(rs.getString(3));
                employee.setLastName(rs.getString(4));
                post.setEmployee(employee);
                post.setDatetime(rs.getTimestamp(5));*/

                posts.add(post);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return posts;
    }

    public Post getPost(int id) {

        Post post = null;

        String sql = "SELECT * FROM post p left join employees e on p.employee_id = e.employee_id "+
                "where p.post_id = ?";

        try (Connection conn = this.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setInt(1, id);

            try (ResultSet rs = statement.executeQuery()) {

                if (rs.next()) {
                    post = new Post();
                    fetchPostData(post, rs);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return post;
    }

    public Post savePost(Post post) {

        return post;
    }

    private void fetchPostData(Post post, ResultSet rs) throws SQLException {
        post.setPostId(rs.getInt(1));
        post.setTitle(rs.getString(2));
        post.setContent(rs.getString(3));
        post.setEmployeeId(rs.getInt(4));

        Employee employee = new Employee();
        employee.setEmployeeId(rs.getInt("e.employee_id"));
        employee.setFirstName(rs.getString("e.first_name"));
        employee.setLastName(rs.getString("e.last_name"));
        post.setEmployee(employee);
    }

}
