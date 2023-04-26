package NonBlockingAPI_2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Pattern;

public class AsynchronousEcho {


    /*
        1 - "SOCKET CHANNEL PREP CODE"
            - opens server socket channel, sets it to non-blocking mode.
            - registers NIO selector for processing events received on the socket channel
            - create a DISPATCH loop to iterate over the selector keys that have events ready for doing stuff.
            - dispatches to specialized methods depending on the event type
                - new connection
                - arrived data
                - ready for more!
     */
    public static void main(String[] args) throws Throwable {

        // Create a Selector. This is used to select objects on the SocketChannel we will create.
        Selector selector = Selector.open();

        // Build a socketChannel and bind it to a TCP port.
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(3000));

        // enable Non-Blocking mode.
        serverSocketChannel.configureBlocking(false);

        // register the channel to notify() on incoming connections.
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            // this will collect all of the non-blocking notifications (from previous setp)
            selector.select();

            // converts the collection of notifications into an iterator.
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

            // iterate as long as we got sumpthin!
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();

                if (key.isAcceptable()) {               // this is a new connection
                    newConnection(selector, key);
                } else if (key.isReadable()) {          // this means that the socket has data to process
                    echo(key);
                } else if (key.isWritable()) {          // this means the socket is ready for us to write.
                    continueEcho(selector, key);
                }

                // The iterator persists the data, so we have to remove the Selection keys as they are processed.
                iterator.remove();

            }
        }
    }

    /*
        2 - "Handling New TCP Connections
        - create a non-blocking socket channel for new TCP connection
        - create a context object that stores state of the connection (depends on the state of the connection)
        - we store both in a HashMap where the socket channel is the key and the context object is the value.
     */

    // Context class is a just storage for connection state.
    private static class Context {
        private final ByteBuffer nioBuffer = ByteBuffer.allocate(512);
        private String currentLine = "";
        private boolean terminating = false;
    }

    private static HashMap<SocketChannel, Context> contexts = new HashMap<>();

    private static void newConnection(Selector selector, SelectionKey key) throws IOException {

        // creates a socket channel for the new TCP connection
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
        SocketChannel socketChannel = serverSocketChannel.accept();

        // configures the channel to Non-Blocking, and sets Selector to filter on read operations.
        socketChannel
                .configureBlocking(false)
                .register(selector, SelectionKey.OP_READ);

        // stores connection state of in a HashMap.
        contexts.put(socketChannel, new Context());
    }

    /*
        3 - Business Logic (Echo Method)

        - here we handled the case for shutting down our connection
        - reading/writing back (echo)
     */
    private static final Pattern QUIT = Pattern.compile("(\\r)?(\\n)?/quit$");

    private static void echo(SelectionKey key) throws IOException {
        // create a socketChannel
        SocketChannel socketChannel = (SocketChannel) key.channel();
        Context context = contexts.get(socketChannel);

        try {
            // read what is in the buffer.
            socketChannel.read(context.nioBuffer);

            // this is an NIO-specific requirement. NIO Buffers are manipulated by position. flip() returns it to the
            // start position.
            context.nioBuffer.flip();

            // append to the current Line from the decoded bytes into the charset.
            context.currentLine = context.currentLine + Charset.defaultCharset().decode(context.nioBuffer);

            // if the line matches the /quit signal, then we set terminating to true, which leads to shutting down the socket
            if (QUIT.matcher(context.currentLine).find()){
                context.terminating = true;
            // if the line is longer than 16 characters, break it up into smaller bits.
            } else if (context.currentLine.length() > 16) {
                context.currentLine = context.currentLine.substring(8);
            }
            // set us back to start position and get the number of bits we write into the buffer.
            context.nioBuffer.flip();
            int count = socketChannel.write(context.nioBuffer);
            // if the write was partial, we stop reading and set the channel to listen to for a writable state.
            if (count < context.nioBuffer.limit()) {
                key.cancel();
                socketChannel.register(key.selector(), SelectionKey.OP_WRITE);

            // if the write was full, clear the buffer
            // also if we are terminating (i,.e. done), cleanup our socket.
            } else {
                context.nioBuffer.clear();
                if (context.terminating) {
                    cleanup(socketChannel);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            cleanup(socketChannel);
        }
    }

    /*
        Methods for
            - Closing Connection (and cleaning up its state)
            - finishing writing a buffer

     */
    private static void cleanup(SocketChannel socketChannel) throws IOException {
        // should be obvious. Close the connection and then remove its entry in our HM.
        socketChannel.close();
        contexts.remove(socketChannel);
    }

    private static void continueEcho(Selector selector, SelectionKey key) throws IOException {

        // build a connection from the key and then get its state.
        SocketChannel socketChannel = (SocketChannel) key.channel();
        Context context = contexts.get(socketChannel);


        try {

            // determine how much data is remaining in the buffer, write it out and get the # of bytes written
            int remainingBytes = context.nioBuffer.limit() - context.nioBuffer.position();
            int count = socketChannel.write(context.nioBuffer);

            // If the amount is equal, then clear the buffer
            if (count == remainingBytes) {
                context.nioBuffer.clear();
                key.cancel();

                // if we're done, close the connection otherwise set the channel back to "ready to read!" mode.
                if (context.terminating) {
                    cleanup(socketChannel);
                } else {
                    socketChannel.register(selector, SelectionKey.OP_READ);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            cleanup(socketChannel);
        }
    }
}