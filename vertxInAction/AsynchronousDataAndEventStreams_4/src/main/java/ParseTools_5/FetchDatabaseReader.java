package ParseTools_5;


import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.file.AsyncFile;
import io.vertx.core.file.OpenOptions;
import io.vertx.core.parsetools.RecordParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/*
    This is the same code as DatabaseReader class, but its using fetch() mode from the ReadStream.

    FETCH MODE
    - this allows a stream consumer to REQUEST an amount of data items, rather than the stream PUSHING data
    items to the consumer.

    we have to pause() the stream, then fetch x number of items as data is needed.

 */
public class FetchDatabaseReader {
    private static final Logger logger = LoggerFactory.getLogger(FetchDatabaseReader.class);

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();

        AsyncFile file = vertx.fileSystem().openBlocking("sample.db", new OpenOptions().setRead(true));

        RecordParser parser = RecordParser.newFixed(4, file);
        parser.pause();
        parser.fetch(1);
        parser.handler(header -> readMagicNumber(header, parser));
        parser.endHandler(v -> vertx.close());
    }

    private static void readMagicNumber(Buffer buffer, RecordParser parser) {
        logger.info("Magic Number: {}:{}:{}:{}", buffer.getByte(0), buffer.getByte(1), buffer.getByte(2), buffer.getByte(3));
        parser.handler(version -> readVersion(version, parser));
        parser.fetch(1);
    }

    private static void readVersion(Buffer buffer, RecordParser parser) {
        logger.info("Version: {}", buffer.getInt(1));
        parser.delimitedMode("\n");
        parser.handler(name -> readName(name, parser));
        parser.fetch(1);
    }

    private static void readName(Buffer buffer, RecordParser parser) {
        logger.info("Name: {}", buffer.toString());
        parser.fixedSizeMode(4);
        parser.handler(keyLength -> readKey(keyLength, parser));
        parser.fetch(1);
    }

    private static void readKey(Buffer buffer, RecordParser parser) {
        parser.fixedSizeMode(buffer.getInt(0));
        parser.handler(key -> readValue(key.toString(), parser));
        parser.fetch(1);
    }

    private static void readValue(String key, RecordParser parser) {
        parser.fixedSizeMode(4);
        parser.handler(valueLength -> finishEntry(key, valueLength, parser));
        parser.fetch(1);
    }

    private static void finishEntry(String key, Buffer buffer, RecordParser parser) {
        parser.fixedSizeMode(buffer.getInt(0));
        parser.handler(value -> {
            logger.info("Key: {} / Value: {}", key, value);
            parser.fixedSizeMode(4);
            parser.handler(keyLength -> readKey(keyLength, parser));
            parser.fetch(1);
        });
        parser.fetch(1);
    }
}
