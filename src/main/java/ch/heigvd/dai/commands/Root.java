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

    @CommandLine.Option(
            names = {"-i", "--input"},
            description = "The input file to use",
            required = true)
    protected String filename_input;

    @CommandLine.Option(
            names = {"-o", "--output"},
            description = "The output file to use, default value is output.txt",
            defaultValue = "output.txt",
            required = true)
    protected String filename_output;

    @CommandLine.Option(
            names = {"-a", "--algorithm"},
            description = "The algorithm to use (possible values: ${COMPLETION-CANDIDATES}).",
            required = true)
    protected AvailableAlgorithm algorithm;

    @CommandLine.Option(
            names = {"-k", "--key"},
            description = "The key to use for the algorithm.",
            required = true)
    protected String key;

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
