package osocket.sync.server;

import io.netty.channel.group.ChannelGroup;
import osocket.sync.Packet;
import io.netty.channel.*;
import osocket.sync.packets.PacketEcho;


public class ServerHandler extends SimpleChannelInboundHandler<Packet> {

    private final ChannelGroup channels;

    ServerHandler(ChannelGroup channels) {
        this.channels = channels;
    }

    @Override
    public void channelActive(final ChannelHandlerContext ctx) {
        System.out.println("Клиент подключился: ");
        channels.add(ctx.channel());
    }

    @Override
    public void channelInactive(final ChannelHandlerContext ctx) {
        System.out.println("Клиент отключился: ");
    }

    @Override
    protected void channelRead0(final ChannelHandlerContext ctx, Packet packet) throws Exception {
        packet.executeOnServer(ctx);
    }

    @Override
    public void exceptionCaught(final ChannelHandlerContext ctx, final Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
