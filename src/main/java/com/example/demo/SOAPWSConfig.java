package com.example.demo;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@Configuration
@EnableWs
public class SOAPWSConfig extends WsConfigurerAdapter
{

    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet (ApplicationContext context)
    {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(context);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<MessageDispatcherServlet>(servlet,"/service/*");
    }

    @Bean(name = "loan")
    public DefaultWsdl11Definition defaultWsdl11Definition (XsdSchema loanSchema)
    {
        DefaultWsdl11Definition defaultWsdl11Definition = new DefaultWsdl11Definition();
        defaultWsdl11Definition.setPortTypeName("LoanEligibility");
        defaultWsdl11Definition.setTargetNamespace("http://in28minutes.com/students");
        defaultWsdl11Definition.setLocationUri("/ws");
        defaultWsdl11Definition.setSchema(loanSchema);
        return defaultWsdl11Definition;
    }

    @Bean
    public XsdSchema loanSchema ()
    {
        return new SimpleXsdSchema(new ClassPathResource("loaneligibility.xsd"));
    }


}
