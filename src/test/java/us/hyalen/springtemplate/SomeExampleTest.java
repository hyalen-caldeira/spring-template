package us.hyalen.springtemplate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import us.hyalen.springtemplate.core.service.CompanyService;
import us.hyalen.springtemplate.core.service.CompanyServiceImpl;
import us.hyalen.springtemplate.core.web.CompanyController;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.mock;

public class SomeExampleTest {
    @ParameterizedTest
    @CsvSource({
            "java,      4",
            "clojure,   7",
            "python,    6"
    })
    void test_csv(String str, int length) {
        assertEquals(length, str.length());
    }

    @ParameterizedTest(name = "#{index} - Run test with args = STR {0} - LENGTH {1} - LIST {2}")
    @MethodSource("stringIntAndListProvider")
    public void testWithMultiArgMethodSource(String str, int length, List<String> list) {
        assertTrue(str.length() > 0);
        assertEquals(length, list.size());
    }

    static Stream<Arguments> stringIntAndListProvider() {
        return Stream.of(
                arguments("abc", 3, Arrays.asList("a", "b", "c")),
                arguments("lemon", 2, Arrays.asList("x", "y"))
        );
    }

    // Reflection method used to manually mock an object
    // Should be put in a TestUtils class
    public static void injectObjects(Object target, String fieldName, Object toInject) {
        boolean wasPrivate = false;

        try {
            Field field = target.getClass().getDeclaredField(fieldName);

            if (!field.canAccess(target)) {
                field.setAccessible(true);
                wasPrivate = true;
            }

            field.set(target, toInject);

            if (wasPrivate)
                field.setAccessible(false);

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void exampleOfInjectionUsingReflection() {
        CompanyService service = mock(CompanyService.class);
        CompanyController controller = new CompanyController(new CompanyServiceImpl());
        injectObjects(controller, "service", service);
    }
}
