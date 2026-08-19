package freemarker.spring.boot;

import java.util.Collections;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties bound to the {@value #PREFIX} namespace.
 * <p>
 * Holds the user-supplied list of tag-library descriptor (TLD) locations that
 * should be registered with the FreeMarker tag library factory so that
 * templates can reference JSTL-style tags.
 * </p>
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
@SuppressWarnings("unchecked")
@ConfigurationProperties(FreemarkerTaglibProperties.PREFIX)
/**
 * <p>Auto-configuration for FreemarkerTaglibProperties.</p>
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public class FreemarkerTaglibProperties {

	/**
	 * Property prefix under which FreeMarker taglib options live.
	 */
	public static final String PREFIX = "spring.freemarker.taglibs";

	/**
	 * Classpath locations of the tag-library descriptors to register,
	 * for example {@code /META-INF/taglib.tld}.
	 */
	private List<String> classPathTlds = Collections.EMPTY_LIST;

	/**
	 * Returns the configured classpath TLD locations.
	 *
	 * @return list of classpath TLD paths, never {@code null}
	 */
	public List<String> getClassPathTlds() {
		return classPathTlds;
	}

	/**
	 * Sets the classpath TLD locations to be registered with the taglib factory.
	 *
	 * @param classPathTlds list of classpath TLD paths
	 */
	public void setClassPathTlds(List<String> classPathTlds) {
		this.classPathTlds = classPathTlds;
	}

}