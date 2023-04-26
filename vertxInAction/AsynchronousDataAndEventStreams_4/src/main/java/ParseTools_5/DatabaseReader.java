package ParseTools_5;

import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.file.AsyncFile;
import io.vertx.core.file.OpenOptions;
import io.vertx.core.parsetools.RecordParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DatabaseReader {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseReader.class);

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();

        AsyncFile file = vertx.fileSystem().openBlocking("sample.db", new OpenOptions().setRead(true));

        // This parser is reading 4 bytes at a time.
        RecordParser parser = RecordParser.newFixed(4, file);

        // This is the beginning of a serialized pipeline of callbacks, but the parser is being carried through the
        // pipeline.
        parser.handler(header -> readMagicNumber(header, parser));

        // This ends the file reader once the pipeline is completed.
        parser.endHandler(v -> vertx.close());
    }

    // First stop.. get the Magic Number
    private static void readMagicNumber(Buffer buffer, RecordParser parser) {
        logger.info("Magic number: {}:{}:{}:{}",
                buffer.getByte(0),
                buffer.getByte(1),
                buffer.getByte(2),
                buffer.getByte(3));
        parser.handler(version -> readVersion(version, parser));
    }

    private static void readVersion(Buffer buffer, RecordParser parser) {
        logger.info("Version: {}", buffer.getInt(0));

        // we've switched this into delimited mode after reading in the version number (previous step in the log
        // event)
        parser.delimitedMode("\n");
        parser.handler(name -> readName(name, parser));
    }

    private static void readName(Buffer buffer, RecordParser parser) {
        logger.info("Name: {}", buffer.toString());
        // Fixed Size Mode swaps us back to reading 4 bytes at a time.
        parser.fixedSizeMode(4);
        parser.handler(keyLength -> readKey(keyLength, parser));
    }

    private static void readKey(Buffer buffer, RecordParser parser) {
        // we adjust the size of the parser to the size of the key (this tells us when to stop reading the key)
        parser.fixedSizeMode(buffer.getInt(0));

        // NOTE: we hand the key off as a String here. This forces us to use a key as a string. Bad example IN my opinion
        parser.handler(key -> readValue(key.toString(), parser));
    }

    private static void readValue(String key, RecordParser parser) {
        // we read just 4 bytes to get the valueLength
        parser.fixedSizeMode(4);
        parser.handler(valueLength -> finishEntry(key, valueLength, parser));
    }

    private static void finishEntry(String key, Buffer buffer, RecordParser parser) {
        // This sets the buffer size to the length of the "value" so we know when to stop.
        parser.fixedSizeMode(buffer.getInt(0));

        // this handler is slightly  more special. We carried the Key value through so we could output it here.
        // value is the buffer contents.
        parser.handler(value -> {
            logger.info ("Key: {} / Value: {}", key, value);

            // Swap back to 4 bytes to get the length of the next key and we keep reading keys/values/finishEntries
            // until the DB contents are gone.
            parser.fixedSizeMode(4);
            parser.handler(keyLength -> readKey(keyLength, parser));
        });
    }
}
