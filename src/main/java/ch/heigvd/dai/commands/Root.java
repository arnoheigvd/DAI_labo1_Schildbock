package ch.heigvd.dai.commands;

import picocli.CommandLine;

@CommandLine.Command(
        description = "A CLI to encrypt/decrypt files using different algorithm.",
        version = "1.0.0",
        subcommands = {
                Decode.class,
                Encode.class,
        },
        scope = CommandLine.ScopeType.INHERIT,
        mixinStandardHelpOptions = true)
public class Root {
    public enum AvailableAlgorithm {
        CODE_CESAR
    }

    @CommandLine.Parameters(index = "0", description = "The name of the file.")
    protected String filename;

    @CommandLine.Option(
            names = {"-a", "--algorithm"},
            description = "The algorithm to use (possible values: ${COMPLETION-CANDIDATES}).",
            required = true)
    protected AvailableAlgorithm algorithm;

    /*@CommandLine.Option(
            names = {"-k", "--key"},
            description = "The algorithm to use (possible values: ${COMPLETION-CANDIDATES}).",
            required = true)
    protected AvailableInputOutputKey key;*/

    public String getFilename() {
        return filename;
    }

    public AvailableAlgorithm getAlgorithm() {
        return algorithm;
    }
}
