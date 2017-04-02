package osocket.sync.server;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class Server {

    private final EventLoopGroup bossGroup;
    private final EventLoopGroup workerGroup;
    private final int port;

    public ChannelFuture serverChannelFuture;

    public Server(int port) {
        this.port = port;
        this.bossGroup = new NioEventLoopGroup();
        this.workerGroup = new NioEventLoopGroup();
    }

    public void run() throws InterruptedException {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup);
        serverBootstrap.channel(NioServerSocketChannel.class);
        serverBootstrap.childHandler(new ServerInitializer());
        serverBootstrap.option(ChannelOption.SO_BACKLOG, 128);
        serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);

        serverChannelFuture = serverBootstrap.bind(port).sync();
        //channelFuture.channel().closeFuture().sync();
    }

    public void stop() {
        workerGroup.shutdownGracefully();
        bossGroup.shutdownGracefully();
    }


}

