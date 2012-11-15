package com.four.dake.server;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

/**
 * The Push Server
 */
public class Server {

    private final int port;

    public Server(int port) {
        this.port = port;
    }

    public void run() {
        // Configure the server.
    	ServerBootstrap bootstrap = new ServerBootstrap(
    			new NioServerSocketChannelFactory(
    					Executors.newCachedThreadPool(),
    					Executors.newCachedThreadPool()));

    	// Configure the pipeline factory.
    	bootstrap.setPipelineFactory(new ServerPipelineFactory());
    	
    	bootstrap.setOption("child.tcpNoDelay", true);
        bootstrap.setOption("child.keepAlive", true);

        // Bind and start to accept incoming connections.
        bootstrap.bind(new InetSocketAddress(port));
    }

    public static void main(String[] args) throws Exception {
        new Server(8080).run();
    }
}  