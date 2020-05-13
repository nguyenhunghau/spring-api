import config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.web.WebAppConfiguration;
import service.StudentService;

/**
 * Created by CoT on 7/29/18.
 */
//@WebAppConfiguration
public class Main {

    public static void main(String[] args){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);

        context.refresh();

        StudentService studentService = context.getBean(StudentService.class);

        System.out.println(studentService.findStudents("Student"));
    }
}
