package com.techieonthenet.interceptors;

import com.techieonthenet.entity.User;
import com.techieonthenet.entity.common.TaskStatus;
import com.techieonthenet.service.ShoppingCartService;
import com.techieonthenet.service.TaskService;
import com.techieonthenet.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.Principal;

public class SessionAttributesInterceptor implements HandlerInterceptor {

    private static Logger logger = LoggerFactory.getLogger(SessionAttributesInterceptor.class);
    @Autowired
    TaskService taskService;
    @Autowired
    private ShoppingCartService scs;
    @Autowired
    private UserService us;

    @Override
    public boolean preHandle(HttpServletRequest requestServlet, HttpServletResponse responseServlet, Object handler) throws Exception {
        HttpSession session = requestServlet.getSession();
        if (!requestServlet.getRequestURI().contains("/webjars")
                && !requestServlet.getRequestURI().equals("/login")
                && !requestServlet.getRequestURI().equals("/") && !requestServlet.getRequestURI().equals("/error")
                && !requestServlet.getRequestURI().contains("/css")
                && !requestServlet.getRequestURI().contains("/images")
                && !requestServlet.getRequestURI().contains("/js")
                && !requestServlet.getRequestURI().equals("/user/forgotPwd")
                && session.getAttribute("user") == null) {

            loadSessionAttributes(requestServlet.getUserPrincipal(), session);
            logger.info("Session Attributes Loaded");
        }
        return true;
    }

    public void loadSessionAttributes(Principal principal, HttpSession session) {
        logger.info("User - " + principal.getName());
        User user = us.findByUsernameAndEnabled(principal.getName());
        if (user != null) {
            if ((scs.findByUserId(user.getId()) != null))
                session.setAttribute("cartSize", scs.findByUserId(us.findByUsernameAndEnabled(principal.getName()).getId()).getTotalItems());
            session.setAttribute("user", user);
            session.setAttribute("tasks", taskService.findByUserAndTaskStatus(user, TaskStatus.PENDING_APPROVAL));
        }
    }

}
