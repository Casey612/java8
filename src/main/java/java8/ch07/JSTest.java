package java8.ch07;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * @author yuzhe
 * @since 2/5/18
 */
public class JSTest {

    public static void main(String[] args) throws ScriptException, IOException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("nashorn");
        Object result = engine.eval("'hello, world!'.length");
        System.out.println(result);


        File jsFile = new File("/home/yuzhe/IdeaProjects/Test/src/main/java/java8/ch07/JsFile.js");
        Object re = engine.eval(Files.newBufferedReader(jsFile.toPath()));
    }

}
