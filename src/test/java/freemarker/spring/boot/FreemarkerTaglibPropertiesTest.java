package freemarker.spring.boot;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link FreemarkerTaglibProperties}.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
class FreemarkerTaglibPropertiesTest {

    @Test
    void prefix_isCorrect() {
        assertThat(FreemarkerTaglibProperties.PREFIX).isEqualTo("spring.freemarker.taglibs");
    }

    @Test
    void defaultClassPathTlds_isEmptyList() {
        FreemarkerTaglibProperties props = new FreemarkerTaglibProperties();
        assertThat(props.getClassPathTlds()).isNotNull().isEmpty();
    }

    @Test
    void setClassPathTlds_updatesValue() {
        FreemarkerTaglibProperties props = new FreemarkerTaglibProperties();
        List<String> tlds = Arrays.asList("/META-INF/taglib1.tld", "/META-INF/taglib2.tld");
        props.setClassPathTlds(tlds);
        assertThat(props.getClassPathTlds()).containsExactly("/META-INF/taglib1.tld", "/META-INF/taglib2.tld");
    }

    @Test
    void setClassPathTlds_toEmptyList() {
        FreemarkerTaglibProperties props = new FreemarkerTaglibProperties();
        props.setClassPathTlds(Collections.emptyList());
        assertThat(props.getClassPathTlds()).isEmpty();
    }
}
