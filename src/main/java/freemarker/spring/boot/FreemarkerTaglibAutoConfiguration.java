package freemarker.spring.boot;

import java.util.List;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

/**
 * Spring Boot auto-configuration that registers JSP tag-library descriptor (TLD)
 * locations with the FreeMarker {@code TaglibFactory}.
 * <p>
 * Activates after Spring Boot's built-in {@code FreeMarkerAutoConfiguration},
 * only when a {@link FreeMarkerConfigurer} bean exists and
 * {@code spring.freemarker.enabled=true}. The configured classpath TLDs are
 * applied to the shared tag library factory so FreeMarker templates can use
 * JSTL-style tags through the FreeMarker/Spring taglib bridge.
 * </p>
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */
@Configuration
@ConditionalOnClass({ freemarker.template.Configuration.class, FreeMarkerConfigurer.class })
@ConditionalOnBean({ FreeMarkerConfigurer.class })
@ConditionalOnProperty(prefix = "spring.freemarker", value = "enabled", havingValue = "true")
@AutoConfigureAfter({ org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration.class })
@EnableConfigurationProperties({ FreemarkerTaglibProperties.class })
public class FreemarkerTaglibAutoConfiguration {

	@Autowired
	private FreemarkerTaglibProperties properties;
	@Autowired
	private FreeMarkerConfigurer freeMarkerConfigurer;

	/**
	 * Registers the classpath TLDs declared through {@link FreemarkerTaglibProperties}
	 * with the FreeMarker {@code TaglibFactory}, making them resolvable by templates.
	 * <p>No-op when no TLDs are configured.</p>
	 */
	@PostConstruct
	public void loadClassPathTlds() {

		List<String> classPathTlds = properties.getClassPathTlds();
		if (!CollectionUtils.isEmpty(classPathTlds)) {
			freeMarkerConfigurer.getTaglibFactory().setClasspathTlds(classPathTlds);
		}

	}

}
