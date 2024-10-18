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
        CODE_CESAR,
        AES_ECB
    }

    @CommandLine.Parameters(index = "0", description = "The name of the file.")
    protected String filename_input;

    @CommandLine.Option(
            names = {"-a", "--algorithm"},
            description = "The algorithm to use (possible values: ${COMPLETION-CANDIDATES}).",
            required = true)
    protected AvailableAlgorithm algorithm;

    @CommandLine.Option(
            names = {"-k", "--key"},
            description = "The key to use.",
            required = true)
    protected String key;

    @CommandLine.Option(
            names = {"-o", "--output"},
            description = "The output file to use",
            required = true)
    protected String filename_output;

    public String getFilenameInput() {
        return filename_input;
    }

    public AvailableAlgorithm getAlgorithm() {
        return algorithm;
    }

    public String getKey() {
        return key;
    }

    public String getFilenameOutput() {
        return filename_output;
    }
}
