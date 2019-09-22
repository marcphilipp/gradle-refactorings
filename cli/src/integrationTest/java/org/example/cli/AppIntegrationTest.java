package org.example.cli;

import de.sormuras.bartholdy.Configuration;
import de.sormuras.bartholdy.Result;
import de.sormuras.bartholdy.tool.Java;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class AppIntegrationTest {
    @Test
    void mainClassCanBeExecutedByJava() {
        var configuration = Configuration.of("--class-path", System.getProperty("main.class.path"), App.class.getName());

        Result result = new Java().run(configuration);
        String output = result.getOutput("out");
        System.out.println(output);

        assertTrue(output.contains("[foo, bar]"), output);
    }
}
