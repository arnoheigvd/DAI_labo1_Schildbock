package ch.heigvd.dai.commands;

import ch.heigvd.dai.algorithm.CodeCesarDecode;
import ch.heigvd.dai.mode.Decrypt;
import java.util.concurrent.Callable;
import picocli.CommandLine;

@CommandLine.Command(name = "decode", description = "Decode the content of a file.")
public class Decode implements Callable<Integer> {
    @CommandLine.ParentCommand
    protected Root parent;

    @Override
    public Integer call() {
        Decrypt decrypt =
                switch (parent.getAlgorithm()) {
                    case CODE_CESAR -> new CodeCesarDecode();
                };

        System.out.println(
                "Decoding from "
                        + parent.getFilenameInput()
                        + " using "
                        + parent.getAlgorithm()
                        + " algorithm.");

        decrypt.decode(parent.getFilenameInput(), parent.getFilenameOutput(), parent.getKey());
        return 0;
    }
}