package SimplifyingTestingWithAdvancedJUnitFeatures_4.examples;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class CustomArgumentsProvider1 implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
        System.out.println("Arguments provider to test " + extensionContext.getTestMethod().get().getName());
        return Stream.of(Arguments.of("en", 1), Arguments.of("to", 2));
    }
}
