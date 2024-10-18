package ch.heigvd.dai.commands;

import ch.heigvd.dai.algorithm.AesEncode;
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
                    case AES_ECB -> new AesEncode();
                };

        System.out.println(
                "Encoding from "
                        + parent.getFilenameInput()
                        + " to "
                        + parent.getFilenameOutput()
                        + " using "
                        + parent.getAlgorithm()
                        + " algorithm.");

        encrypt.encode(parent.getFilenameInput(), parent.getFilenameOutput(), parent.getKey());
        return 0;
    }
}
