package org.demo;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * @author zidom
 * 
 */
public class MyWebApplicationInitializer implements WebApplicationInitializer {
	/**
	 * @see org.springframework.web.WebApplicationInitializer#onStartup(javax.servlet.ServletContext)
	 */
	@Override
	public void onStartup(ServletContext container) {

		XmlWebApplicationContext applicationContext = new XmlWebApplicationContext();
		applicationContext
				.setConfigLocation("classpath:spring/application-config.xml");

		DispatcherServlet dispatcher = new DispatcherServlet(applicationContext);
		dispatcher.setContextConfigLocation("/WEB-INF/mvc-config.xml");

		ServletRegistration.Dynamic registration = container.addServlet(
				"dispatcher", dispatcher);
		registration.setLoadOnStartup(1);
		registration.addMapping("/*");
	}
}