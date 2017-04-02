package osocket.sync.client;


import osocket.sync.Packet;
import osocket.sync.packets.PacketEcho;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ClientHandler extends SimpleChannelInboundHandler<Packet> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println("Клиент подключлся к серверу.");
        ctx.writeAndFlush(new PacketEcho("Приветик :3 От клиента"));
    }

    @Override
    public void channelInactive(final ChannelHandlerContext ctx) {
        System.out.println("Клиент отключился от сервера.");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Packet packet) throws Exception {
        packet.executeOnClient(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}