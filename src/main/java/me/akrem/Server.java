package me.akrem;

import java.io.IOException;
import java.io.OutputStream;
import java.net.*;
public class Server {
    private final int port;
    private final byte[] address;
    private boolean created;
    private boolean closed;
    private boolean accept = false;
    private boolean isBound = false;

    private boolean listen = false;

    private Socket socket;

    public Server(byte[] address, int port) throws IOException {
        this.address = address;
        this.port = port;
    }

    private boolean isCreated() {
        return this.created;
    }

    private boolean isClosed() {
        return this.closed;
    }
    private boolean isBound() {
        return this.isBound;
    }

    private boolean isListening() {
        return this.listen;
    }

    private void setAccept() {
        this.accept = true;
    }

    private void setClosed() {
        this.closed = true;
    }


    public void initSocket(){
        this.socket = new Socket();
    }

    public void bind() throws IOException {
        InetAddress inetAddress = InetAddress.getByAddress(this.address);
        SocketAddress socketAddress = new InetSocketAddress(inetAddress, this.port);
        this.socket.bind(socketAddress);
        this.isBound = true;
    }

    public Socket getServerSocket() throws SocketException {
        if ( socket.isClosed() ) {
            throw new SocketException("Server is closed");
        }

        if ( !socket.isBound() )
            throw new SocketException("Server is not in place");

        return this.socket;
    }

    public void startListening() {
        this.listen = true;
    }
    public void startServer() {
        this.setAccept();
    }

    public Socket connect() throws SocketException {
        socket = this.getServerSocket();
        if ( !this.isListening() )
            throw new SocketException("Server is not ready");

        while ( !accept ){
            System.out.println("Coming soon!");
        }

        return socket;
    }

    public OutputStream getData() throws IOException {
        // if everything is set!
        socket = this.connect();
        return socket.getOutputStream();
    }
}
