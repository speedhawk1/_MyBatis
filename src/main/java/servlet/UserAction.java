package servlet;

import model.User;
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
@WebServlet(urlPatterns = "/user")
public class UserAction extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action.equals("register")) {
            register(req, resp);
        }
        if (action.equals("login")) {
            login(req, resp);
        }
        if (action.equals("logout")) {
            logout(req, resp);
        }
    }

    private void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.getSession().invalidate();
        resp.sendRedirect("index.jsp");
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String username = req.getParameter("username").trim();
        String password = req.getParameter("password");
        try (SqlSession sqlSession = MyBatisSession.getSqlSession(false)) {
            List<User> users = sqlSession.selectList("user.login", new User(null, username, password));
            if (users.size() > 0) {
                req.getSession().setAttribute("username", username);
                resp.sendRedirect("/book?action=query");
            } else {
                req.setAttribute("message", "invalid username or password.");
                req.getRequestDispatcher("index.jsp").forward(req, resp);
            }
        }
    }

    private void register(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username").trim();
        String password = req.getParameter("password");
        try (SqlSession sqlSession = MyBatisSession.getSqlSession(true)) {
            sqlSession.insert("user.create", new User(null, username, password));
        }
        resp.sendRedirect("index.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
