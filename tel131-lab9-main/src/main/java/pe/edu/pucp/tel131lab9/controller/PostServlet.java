package pe.edu.pucp.tel131lab9.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.pucp.tel131lab9.bean.Post;
import pe.edu.pucp.tel131lab9.dao.CommentDao;
import pe.edu.pucp.tel131lab9.dao.PostDao;

import java.io.IOException;
import java.sql.Timestamp;

@WebServlet(name = "PostServlet", urlPatterns = {"/PostServlet"})
public class PostServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action") != null ? req.getParameter("action") : "";


        if (action.equals("comment")) {

        }else{
            if(action.equals("new")){
                PostDao postDao = new PostDao();
                Post post = parsePost(req);
                postDao.guardarPost(post);

                resp.sendRedirect(req.getContextPath() + "/HomeServlet");
            }
        }







    }
    public Post parsePost(HttpServletRequest request){

        Post post = new Post();

        String tituloPost = request.getParameter("TituloPost");
        String comentarioPost = request.getParameter("Comentario");
        String id3 = request.getParameter("id");

        try{

            int idEmployee = Integer.parseInt(id3);

            post.setTitle(tituloPost);
            post.setContent(comentarioPost);
            post.setEmployeeId(idEmployee);

            return post;
        }catch (NumberFormatException e){

        }

        return post;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher view;
        String action = req.getParameter("action") != null ? req.getParameter("action") : "";

        if (action.equals("new")) {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("post/newPost.jsp");
            requestDispatcher.forward(req,resp);
        }
        else if (action.equals("view")) {
            String id = req.getParameter("id") != null ? req.getParameter("id") : "";
            System.out.println(id);
            PostDao postDao = new PostDao();
            CommentDao commentDao = new CommentDao();
            req.setAttribute("post", postDao.getPost(Integer.parseInt(id)));
            req.setAttribute("comments", commentDao.listCommentsByPostId(Integer.parseInt(id)));
            view = req.getRequestDispatcher("post/viewPost.jsp");
            view.forward(req, resp);
        }

    }
}
