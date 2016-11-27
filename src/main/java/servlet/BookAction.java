package servlet;

import model.Book;
import org.apache.ibatis.session.SqlSession;
import util.MyBatisSession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2016/11/27.
 */
@WebServlet(urlPatterns = "/book")
public class BookAction extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action.equals("create")) {
            create(req, resp);
        }
        if (action.equals("query")) {
            query(req, resp);
        }
        if (action.equals("search")) {
            search(req, resp);
        }
        if (action.equals("update")) {
            update(req, resp);
        }
        if (action.equals("remove")) {
            remove(req, resp);
        }
    }

    private void remove(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.valueOf(req.getParameter("id"));
        try (SqlSession sqlSession = MyBatisSession.getSqlSession(true)) {
            sqlSession.delete("book.remove", id);
        }
        resp.sendRedirect("/book?action=query");
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.valueOf(req.getParameter("id"));
        String title = req.getParameter("title").trim();
        try (SqlSession sqlSession = MyBatisSession.getSqlSession(true)) {
            sqlSession.update("book.update", new Book(id, title));
        }
        resp.sendRedirect("/book?action=query");
    }

    private void search(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.valueOf(req.getParameter("id"));
        try (SqlSession sqlSession = MyBatisSession.getSqlSession(false)) {
            req.getSession().setAttribute("book", sqlSession.selectOne("book.search", id));
        }
        resp.sendRedirect("edit.jsp");
    }

    private void query(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try (SqlSession sqlSession = MyBatisSession.getSqlSession(false)) {
            req.getSession().setAttribute("books", sqlSession.selectList("book.query"));
        }
        resp.sendRedirect("home.jsp");
    }

    private void create(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String title = req.getParameter("title");
        try (SqlSession sqlSession = MyBatisSession.getSqlSession(true)) {
            sqlSession.insert("book.create", new Book(null, title));
        }
        resp.sendRedirect("/book?action=query");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
