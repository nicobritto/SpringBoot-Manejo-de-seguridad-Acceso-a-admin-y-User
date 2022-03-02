package mx.com.gm.web;
import java.util.Locale;
import lombok.var;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
@Configuration
public class WebConfig implements WebMvcConfigurer{
    
    @Bean
    public LocaleResolver localeResolver(){
        var slr = new SessionLocaleResolver();
        slr.setDefaultLocale(new Locale("es"));
        return slr;
    }
     //configuramos otro interceptor para poder cambiar de idioma de manera dinamica
   
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor(){
        var lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }
     //por ultimo registramos este interceoptor
    @Override
    public void addInterceptors(InterceptorRegistry registro){
        registro.addInterceptor(localeChangeInterceptor());
    }
    
    //vamos a mappiar  de la url por defaul porque si no con la configuracion de seguridad no se puede acceder
    @Override
    public void addViewControllers(ViewControllerRegistry registro){
       registro.addViewController("/").setViewName("index");
       registro.addViewController("/login");
        //registramos la pagina de error
       registro.addViewController("/errores/403").setViewName("/errores/403");
    }
    
}

