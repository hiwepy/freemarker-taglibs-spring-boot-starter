package freemarker.spring.boot;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockServletContext;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link FreemarkerTaglibAutoConfiguration}.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
class FreemarkerTaglibAutoConfigurationTest {

    @Test
    void loadClassPathTlds_withNonEmptyList_setsTlds() throws Exception {
        FreemarkerTaglibAutoConfiguration config = new FreemarkerTaglibAutoConfiguration();

        FreemarkerTaglibProperties properties = new FreemarkerTaglibProperties();
        List<String> tlds = Arrays.asList("/META-INF/taglib.tld");
        properties.setClassPathTlds(tlds);

        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
        configurer.setServletContext(new MockServletContext());
        configurer.afterPropertiesSet();

        setField(config, "properties", properties);
        setField(config, "freeMarkerConfigurer", configurer);

        config.loadClassPathTlds();

        assertThat(configurer.getTaglibFactory().getClasspathTlds()).isEqualTo(tlds);
    }

    @Test
    void loadClassPathTlds_withEmptyList_doesNotSetTlds() throws Exception {
        FreemarkerTaglibAutoConfiguration config = new FreemarkerTaglibAutoConfiguration();

        FreemarkerTaglibProperties properties = new FreemarkerTaglibProperties();

        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();

        setField(config, "properties", properties);
        setField(config, "freeMarkerConfigurer", configurer);

        config.loadClassPathTlds();

        assertThat(properties.getClassPathTlds()).isEmpty();
    }

    @Test
    void loadClassPathTlds_withNullList_doesNotSetTlds() throws Exception {
        FreemarkerTaglibAutoConfiguration config = new FreemarkerTaglibAutoConfiguration();

        FreemarkerTaglibProperties properties = new FreemarkerTaglibProperties();
        properties.setClassPathTlds(null);

        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();

        setField(config, "properties", properties);
        setField(config, "freeMarkerConfigurer", configurer);

        config.loadClassPathTlds();

        assertThat(properties.getClassPathTlds()).isNull();
    }

    @Test
    void classExists() {
        assertThat(FreemarkerTaglibAutoConfiguration.class).isNotNull();
    }

    private static void setField(Object target, String fieldName, Object value) throws Exception {
        Field field = target.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(target, value);
    }
}
