package ch.heigvd.dai.commands;

import static ch.heigvd.dai.terminal.Color.*;
import ch.heigvd.dai.algorithm.AesEncode;
import ch.heigvd.dai.mode.Encrypt;
import ch.heigvd.dai.algorithm.CodeCesarEncode;

import java.util.Objects;
import java.util.concurrent.Callable;
import picocli.CommandLine;

@CommandLine.Command(name = "encode", description = "Encode the content of a file.")
public class Encode implements Callable<Integer> {
    @CommandLine.ParentCommand
    protected Root parent;

    @Override
    public Integer call() {

        /* Checking file names */
        if (Objects.equals(parent.getFilenameInput(), parent.getFilenameOutput())) {
            System.err.println(RED + "Input and Output are the same file !\nLook at -h to have help." + RESET);
            return 1;
        }

        Encrypt encrypt =
                switch (parent.getAlgorithm()) {
                    case CODE_CESAR -> new CodeCesarEncode();
                    case AES_ECB -> new AesEncode();
                };

        if (parent.getChoiceColor()){
            System.out.println(
                    BOLD + "Encoding" + RESET + " from "
                            + MAGENTA + parent.getFilenameInput() + RESET
                            + " to "
                            + MAGENTA + parent.getFilenameOutput() + RESET
                            + " using "
                            + GREEN + parent.getAlgorithm() + RESET
                            + " algorithm.");
        } else {
            System.out.println(
                    "Encoding from "
                            + parent.getFilenameInput()
                            + " to "
                            + parent.getFilenameOutput()
                            + " using "
                            + parent.getAlgorithm()
                            + " algorithm.");
        }

        encrypt.encode(parent.getFilenameInput(), parent.getFilenameOutput(), parent.getKey());
        return 0;
    }
}
