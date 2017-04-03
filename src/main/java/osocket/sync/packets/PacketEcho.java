package osocket.sync.packets;

import osocket.sync.Packet;
import io.netty.channel.ChannelHandlerContext;


public class PacketEcho extends Packet {

    private String text;

    public PacketEcho(String text) {
        this.text = text;
    }

    @Override
    public void executeOnServer(ChannelHandlerContext ctx) {
        System.out.println("От клинта: " + text + " | ");
    }

    @Override
    public void executeOnClient(ChannelHandlerContext ctx) {
        System.out.println("От cервера: " + text + " | ");
    }

}
