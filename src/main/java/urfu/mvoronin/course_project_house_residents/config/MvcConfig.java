package urfu.mvoronin.course_project_house_residents.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import urfu.mvoronin.course_project_house_residents.interceptors.LoggingInterceptor;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    private LoggingInterceptor loggingInterceptor;

    public MvcConfig(LoggingInterceptor loggingInterceptor)
    {
        this.loggingInterceptor = loggingInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loggingInterceptor);
    }

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("list-residents");
    }
}
