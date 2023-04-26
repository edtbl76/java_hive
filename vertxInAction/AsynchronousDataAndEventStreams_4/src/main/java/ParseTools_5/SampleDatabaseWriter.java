package ParseTools_5;

import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.file.AsyncFile;
import io.vertx.core.file.OpenOptions;

public class SampleDatabaseWriter {

    public static void main(String[] args) {
        // set up a vertx instance and build an AsyncFile (blocking IO, set to write/create)
        Vertx vertx = Vertx.vertx();
        AsyncFile file = vertx.fileSystem().openBlocking("sample.db", new OpenOptions().setWrite(true).setCreate(true));

        // Set up a write buffer.
        Buffer buffer = Buffer.buffer();

        // Magic number
        buffer.appendBytes(new byte[]{1, 2, 3, 4});

        // Version
        buffer.appendInt(2);

        // DB name
        buffer.appendString("Sample Database\n");

        // Entry 1
        String key = "abc";
        String value = "123456-abcdef";
        buffer
                .appendInt(key.length())
                .appendString(key)
                .appendInt(value.length())
                .appendString(value);

        // Entry 2
        key = "foo@bar";
        value = "Foo Bar Baz";
        buffer
                .appendInt(key.length())
                .appendString(key)
                .appendInt(value.length())
                .appendString(value);

        // Write the buffer to the file. Once that's done, close up vertx.
        file.write(buffer).close(voidAsyncResult -> vertx.close());
    }

}
