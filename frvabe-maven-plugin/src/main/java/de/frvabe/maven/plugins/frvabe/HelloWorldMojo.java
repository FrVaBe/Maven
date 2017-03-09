package de.frvabe.maven.plugins.frvabe;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

/**
 * This goal writes a 'Hello World!' messages to the standard output.
 */
@Mojo(name = "hello-world", requiresProject = false, defaultPhase = LifecyclePhase.GENERATE_SOURCES)
public class HelloWorldMojo extends AbstractMojo {

    private static final String DEFAULT_MESSAGE = "Hello World!";
    private static final String DEAFULT_MESSAGE_COUNT = "1";

    /**
     * A custom message that will be printed instead of the default {@value #DEFAULT_MESSAGE}
     * message.
     */
    @Parameter(defaultValue = DEFAULT_MESSAGE, property = "frvabe.message")
    private String message;

    /**
     * The number of times the message gets printed. The number is expected to be between 1 and 100.
     */
    @Parameter(defaultValue = DEAFULT_MESSAGE_COUNT, property = "frvabe.count")
    private int count;

    /**
     * Execute the plugin goal.
     * 
     * @throws MojoExecutionException in case of invalid parameter values.
     * @throws MojoFailureException will never happen
     */
    public void execute() throws MojoExecutionException, MojoFailureException {

        // use the LOGGER provided by the Maven infrastructure to log messages
        getLog().debug("Entering HelloWorldMojo.execute()...");

        if (count < 0 || count > 100) {
            throw new MojoExecutionException(
                    "Count is expected to be between 1 and 100 but is: " + count);
        }

        if (message != null && message.isEmpty()) {
            throw new MojoExecutionException("Message must not be empty!");
        }

        for (int i = 1; i <= count; i++) {
            System.out.println(message);
        }

        getLog().debug("Exiting HelloWorldMojo.execute()...");

    }


}
