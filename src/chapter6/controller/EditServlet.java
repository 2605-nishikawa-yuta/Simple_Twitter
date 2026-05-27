package chapter6.controller;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import chapter6.beans.Message;
import chapter6.logging.InitApplication;
import chapter6.service.MessageService;

@WebServlet(urlPatterns = { "/edit" })
public class EditServlet extends HttpServlet {


    /**
    * ロガーインスタンスの生成
    */
    Logger log = Logger.getLogger("twitter");

    /**
    * デフォルトコンストラクタ
    * アプリケーションの初期化を実施する。
    */
    public EditServlet() {
        InitApplication application = InitApplication.getInstance();
        application.init();

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        log.info(new Object(){}.getClass().getEnclosingClass().getName() +
        " : " + new Object(){}.getClass().getEnclosingMethod().getName());

        String text = request.getParameter("text");
        int id = Integer.parseInt(request.getParameter("id"));

        java.util.List<String> errorMessages = new java.util.ArrayList<String>();

        if (text == null || text.trim().replaceAll("^[\\s\\p{Z}]+$", "").isEmpty()) {
            errorMessages.add("入力してください");
        } else if (text.length() > 140) {
            errorMessages.add("140文字以下で入力してください");
        }

        if (!errorMessages.isEmpty()) {
            Message message = new Message();
            message.setId(id);
            message.setText(text);

            request.setAttribute("errorMessages", errorMessages);
            request.setAttribute("message", message);
            request.getRequestDispatcher("edit.jsp").forward(request, response);
            return;
        }

        Message message = new Message();
        message.setId(id);
        message.setText(text);

        new MessageService().update(message);

        response.sendRedirect("./");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        log.info(new Object(){}.getClass().getEnclosingClass().getName() +
        " : " + new Object(){}.getClass().getEnclosingMethod().getName());

        javax.servlet.http.HttpSession session = request.getSession();
        java.util.List<String> errorMessages = new java.util.ArrayList<String>();

        try {
            String idParam = request.getParameter("id");
            if (idParam == null || idParam.isEmpty()) {
                throw new NumberFormatException();
            }

            int id = Integer.parseInt(idParam);

            Message message = new MessageService().select(id);
            if (message == null) {
                errorMessages.add("不正なパラメータが入力されました");
                session.setAttribute("errorMessages", errorMessages);
                response.sendRedirect("./");
                return;
            }

            request.setAttribute("message", message);
            request.getRequestDispatcher("edit.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            errorMessages.add("不正なパラメータが入力されました");
            session.setAttribute("errorMessages", errorMessages);
            response.sendRedirect("./");
        }
    }
}

