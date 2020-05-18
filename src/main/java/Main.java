import config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.StaffService;

/**
 * Created by CoT on 7/29/18.
 */
//@WebAppConfiguration
public class Main {

    public static void main(String[] args){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);

        context.refresh();

        StaffService staffService = context.getBean(StaffService.class);

        System.out.println(staffService.findAll(1, 10));
    }
}
