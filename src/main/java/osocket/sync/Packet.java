package osocket.sync;


import io.netty.channel.ChannelHandlerContext;

import java.io.Serializable;

public abstract class Packet implements Serializable {

    public void executeOnClient(ChannelHandlerContext ctx) {
        throw new UnsupportedOperationException("This packet [" + this.getClass().getName() + "] does not implement a client side handler.");
    }

    public void executeOnServer(ChannelHandlerContext ctx){
        throw new UnsupportedOperationException("This packet [" + this.getClass().getName() + "] does not implement a server side handler.");
    }

}
