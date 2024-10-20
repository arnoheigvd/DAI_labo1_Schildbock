package ch.heigvd.dai.commands;

import static ch.heigvd.dai.terminal.Color.*;
import ch.heigvd.dai.algorithm.AesDecode;
import ch.heigvd.dai.algorithm.CodeCesarDecode;
import ch.heigvd.dai.mode.Decrypt;

import java.util.Objects;
import java.util.concurrent.Callable;
import picocli.CommandLine;

@CommandLine.Command(name = "decode", description = "Decode the content of a file.")
public class Decode implements Callable<Integer> {
    @CommandLine.ParentCommand
    protected Root parent;

    @Override
    public Integer call() {

        /* Checking file names */
        if (Objects.equals(parent.getFilenameInput(), parent.getFilenameOutput())) {
            System.err.println(RED + "Input and Output are the same file !\nLook at -h to have help." + RESET);
            return 1;
        }

        Decrypt decrypt =
                switch (parent.getAlgorithm()) {
                    case CODE_CESAR -> new CodeCesarDecode();
                    case AES_ECB -> new AesDecode();
                };

        if (parent.getChoiceColor()){
            System.out.println(
                    BOLD + "Decoding" + RESET + " from "
                            + MAGENTA + parent.getFilenameInput() + RESET
                            + " to "
                            + MAGENTA + parent.getFilenameOutput() + RESET
                            + " using "
                            + GREEN + parent.getAlgorithm() + RESET
                            + " algorithm.");
        } else {
            System.out.println(
                     "Decoding from "
                            + parent.getFilenameInput()
                            + " to "
                            + parent.getFilenameOutput()
                            + " using "
                            + parent.getAlgorithm()
                            + " algorithm.");
        }

        decrypt.decode(parent.getFilenameInput(), parent.getFilenameOutput(), parent.getKey());
        return 0;
    }
}