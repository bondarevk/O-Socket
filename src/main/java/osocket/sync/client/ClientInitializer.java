package osocket.sync.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;


public class ClientInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(
                new ObjectEncoder(),
                new ObjectDecoder(ClassResolvers.cacheDisabled(null)),
                new ClientHandler());
    }
}
