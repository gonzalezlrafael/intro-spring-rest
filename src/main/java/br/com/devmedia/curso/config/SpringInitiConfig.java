package br.com.devmedia.curso.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Substitui o uso do arquivo web.xml porem tem que estender a classe {@link AbstractAnnotationConfigDispatcherServletInitializer}
 */
public class SpringInitiConfig extends AbstractAnnotationConfigDispatcherServletInitializer implements WebApplicationInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        //Determina que a primeira classe que deve ser rodada quando for iniciada a app, será a classe de configuração do Spring
        return new Class[] {SpringRootConfig.class};
    }

    /**
     * Por enquanto não existe a necessidade de mexer. So quando for utilizado mais recursos.
     * @return
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[0];
    }

    @Override
    protected String[] getServletMappings() {
        //Determina que sempre após a "/" após o contexto da aplicaçãp, ele tem uma uri para acessar.
        return new String[] {"/"};
    }
}
