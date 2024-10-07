package ch.heigvd.dai.commands;

import ch.heigvd.dai.mode.Encrypt;
import ch.heigvd.dai.algorithm.CodeCesarEncode;
import java.util.concurrent.Callable;
import picocli.CommandLine;

@CommandLine.Command(name = "encode", description = "Encode the content of a file.")
public class Encode implements Callable<Integer> {
    @CommandLine.ParentCommand
    protected Root parent;

    @Override
    public Integer call() {
        Encrypt encrypt =
                switch (parent.getAlgorithm()) {
                    case CODE_CESAR -> new CodeCesarEncode();
                };

        System.out.println(
                "Encoding from "
                        + parent.getFilename()
                        + " using "
                        + parent.getAlgorithm()
                        + " algorithm.");

        encrypt.encode(parent.getFilename());
        return 0;
    }
}
