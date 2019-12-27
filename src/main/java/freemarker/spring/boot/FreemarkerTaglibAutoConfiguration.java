package freemarker.spring.boot;

import java.util.List;

import javax.annotation.PostConstruct;

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
 * 
 * @className	： FreemarkerTaglibAutoConfiguration
 * @description	： TODO(描述这个类的作用)
 * @author 		： <a href="https://github.com/hiwepy">hiwepy</a>
 * @date		： 2018年3月8日 上午10:34:09
 * @version 	V1.0
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

	@PostConstruct
	public void loadClassPathTlds() {

		List<String> classPathTlds = properties.getClassPathTlds();
		if (!CollectionUtils.isEmpty(classPathTlds)) {
			freeMarkerConfigurer.getTaglibFactory().setClasspathTlds(classPathTlds);
		}

	}

}
