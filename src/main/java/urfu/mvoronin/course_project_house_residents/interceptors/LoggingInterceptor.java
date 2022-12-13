package urfu.mvoronin.course_project_house_residents.interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import urfu.mvoronin.course_project_house_residents.entity.UserAction;
import urfu.mvoronin.course_project_house_residents.repository.UserActionRepository;
import urfu.mvoronin.course_project_house_residents.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.OffsetDateTime;

@Component
public class LoggingInterceptor implements HandlerInterceptor {

    private UserActionRepository userActionRepository;
    private UserRepository userRepository;

    @Autowired
    public LoggingInterceptor(UserActionRepository userActionRepository, UserRepository userRepository) {
        this.userActionRepository = userActionRepository;
        this.userRepository = userRepository;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        return true;
    }

    @Override
    public void postHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            ModelAndView modelAndView) {

        String controllerName;
        String actionName;

        if (handler instanceof HandlerMethod) {
            var handlerMethod = (HandlerMethod) handler;
            controllerName = handlerMethod.getBean().getClass().getSimpleName().replace("Controller", "");
            actionName = handlerMethod.getMethod().getName();

            var auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null && auth.isAuthenticated()) {
                var principal = auth.getPrincipal();

                if (principal == null) {
                    return;
                }

                if (principal instanceof org.springframework.security.core.userdetails.User springUser) {
                    var action = new UserAction();

                    var user = userRepository.findByName(springUser.getUsername());

                    if (user == null) {
                        return;
                    }

                    action.setUser(user);
                    action.setActionDate(OffsetDateTime.now());
                    action.setDescription("Action " + actionName + " in controller " + controllerName);

                    userActionRepository.save(action);
                }
            }
        }
    }
}
