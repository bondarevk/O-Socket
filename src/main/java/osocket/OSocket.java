package osocket;


import osocket.sync.client.Client;
import osocket.sync.packets.PacketEcho;
import osocket.sync.server.Server;

public class OSocket {

    public static void main(String[] args) throws InterruptedException {

        Server server = new Server(26000);
        server.run();

        Client client = new Client("localhost", 26000);
        client.connect();
        client.send(new PacketEcho("client"));
        server.sendToAll(new PacketEcho("server"));

        Thread.sleep(1000);
        client.disconnect();

        client.clientChannelFuture.channel().closeFuture().sync();
    }

}
