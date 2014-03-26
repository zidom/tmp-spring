package org.demo;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
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
		
		
		
		ServletRegistration.Dynamic registration = container.addServlet(
				"dispatcher", new DispatcherServlet());
		registration.setLoadOnStartup(1);
		registration.addMapping("/*");
	}
}