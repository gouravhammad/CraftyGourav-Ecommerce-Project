package com.Gourav.configuration;

import java.beans.PropertyVetoException;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages="com.Gourav")
public class MyDispatcherServlet implements WebMvcConfigurer           // webMVCConfigure implemented for static images and CSS
{
	
	@Bean
	public JavaMailSenderImpl mailSender(){
		JavaMailSenderImpl jms=new JavaMailSenderImpl();
		jms.setHost("smtp.gmail.com");
		jms.setPort(587);
		jms.setUsername("craftygourav@gmail.com");
		jms.setPassword("craftygourav");
		Properties properties=new Properties();
		properties.setProperty("mail.transport.protocol", "smtp");
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.starttls.enable", "true");
		jms.setJavaMailProperties(properties);
		return jms;
	}
	
	@Bean
	public CommonsMultipartResolver multipartResolver() throws Exception
	{
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setMaxUploadSize(3145728);  // = 3MB -->> (3*1024)*1024KB = 3145728
		
		return resolver;
	}
	
	
	@Bean
	public ComboPooledDataSource myDataSource() throws PropertyVetoException
	{
		ComboPooledDataSource cpdObject = new ComboPooledDataSource();
		
		cpdObject.setDriverClass("com.mysql.jdbc.Driver");
		cpdObject.setJdbcUrl("jdbc:mysql://localhost:3306/craftygourav");
		cpdObject.setUser("root");
		cpdObject.setPassword("password");
		
		return cpdObject;
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFacotory() throws PropertyVetoException
	{
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		
		sessionFactory.setDataSource(myDataSource());
		sessionFactory.setPackagesToScan("com.Gourav");
		
		Properties properties = new Properties();
		properties.put("hibernate.dialect","org.hibernate.dialect.MySQL5InnoDBDialect");
		properties.put("hibernate.show_sql","true");
		properties.put("hibernate.hbm2ddl.auto","update");
		
		sessionFactory.setHibernateProperties(properties);
		
		return sessionFactory;
	}
	
	@Bean
	public InternalResourceViewResolver viewResolver()
	{
		InternalResourceViewResolver IRVObject = new InternalResourceViewResolver();
		IRVObject.setSuffix(".jsp");
        IRVObject.setPrefix("/WEB-INF/views/"); 
		
		return IRVObject; 
	} 
	
	
     @Override
	 public void addResourceHandlers(ResourceHandlerRegistry registry) 
	 {
	      
	      // Register resource handler for images
	      registry.addResourceHandler("/images/**").addResourceLocations("/WEB-INF/images/");
	      
	      // Register resource handler for CSS
	      registry.addResourceHandler("/css/**").addResourceLocations("/WEB-INF/css/");
	      
	      //Register resource handler for Custom CSS
	      registry.addResourceHandler("/customCSS/**").addResourceLocations("/WEB-INF/customCSS/");
	      
	      //Register resource handler for JS
	      registry.addResourceHandler("/js/**").addResourceLocations("/WEB-INF/js/");
	            
	  }
}
