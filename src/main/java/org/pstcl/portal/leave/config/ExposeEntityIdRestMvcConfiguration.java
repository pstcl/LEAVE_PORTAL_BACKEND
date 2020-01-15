package org.pstcl.portal.leave.config;

import java.util.Set;

import org.pstcl.portal.leave.config.id.exposure.IdExposable;
import org.pstcl.portal.leave.mvc.model.Employee;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.stereotype.Component;

@Component
public class ExposeEntityIdRestMvcConfiguration extends RepositoryRestConfigurerAdapter {

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {


		//config.exposeIdsFor(LeaveType.class);
		//config.exposeIdsFor(Employee.class);
		ClassPathScanningCandidateComponentProvider provider= new ClassPathScanningCandidateComponentProvider(false);
		provider.addIncludeFilter(new AnnotationTypeFilter(IdExposable.class));
		Set<BeanDefinition> beanDefinitions=  provider.findCandidateComponents("org.pstcl");

		for (BeanDefinition beanDefinition : beanDefinitions) {
			Class<?> cl = null;
			try {
				cl = Class.forName(beanDefinition.getBeanClassName());
				IdExposable findable = cl.getAnnotation(IdExposable.class);
				config.exposeIdsFor(Class.forName(beanDefinition.getBeanClassName()));
				System.out.printf("Found class: %s, with meta name: %s%n",
						cl.getSimpleName(), Class.forName(beanDefinition.getBeanClassName()));
				
			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			}
		}
	}


}