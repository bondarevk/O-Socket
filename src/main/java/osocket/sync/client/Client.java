package osocket.sync.client;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;


public class Client {

    private final EventLoopGroup workerGroup;
    private final int port;
    private final String host;

    public ChannelFuture clientChannelFuture;

    public Client(String host, int port) {
        this.workerGroup = new NioEventLoopGroup();
        this.port = port;
        this.host = host;
    }

    public void connect() throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workerGroup);
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
        bootstrap.handler(new ClientInitializer());
        clientChannelFuture = bootstrap.connect(host, port).sync();
    }

    public void disconnect() {
        workerGroup.shutdownGracefully();
    }
}
