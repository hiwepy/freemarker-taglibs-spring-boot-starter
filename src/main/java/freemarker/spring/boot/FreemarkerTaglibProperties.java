package freemarker.spring.boot;

import java.util.Collections;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

@SuppressWarnings("unchecked")
@ConfigurationProperties(FreemarkerTaglibProperties.PREFIX)
public class FreemarkerTaglibProperties {

	public static final String PREFIX = "spring.freemarker.taglibs";

	/** 标签库的Classpath路径，例如：/META-INF/taglib.tld */
	private List<String> classPathTlds = Collections.EMPTY_LIST;

	public List<String> getClassPathTlds() {
		return classPathTlds;
	}

	public void setClassPathTlds(List<String> classPathTlds) {
		this.classPathTlds = classPathTlds;
	}

}