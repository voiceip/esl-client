package org.robotninjas.esl.inbound;

import com.google.inject.PrivateModule;
import org.freeswitch.esl.client.EventSocketConnection;
import org.freeswitch.esl.client.EventSocketConnectionImpl;
import org.jboss.netty.channel.socket.ClientSocketChannelFactory;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

import java.util.concurrent.Executors;

public class InboundEventSocketModule extends PrivateModule {

  private final ClientSocketChannelFactory channelFactory;

  public InboundEventSocketModule() {
    this.channelFactory = new NioClientSocketChannelFactory(
      Executors.newCachedThreadPool(),
      Executors.newCachedThreadPool()
    );
  }

  public InboundEventSocketModule(ClientSocketChannelFactory channelFactory) {
    this.channelFactory = channelFactory;
  }

  @Override
  protected void configure() {

    bind(ClientSocketChannelFactory.class).toInstance(channelFactory);
    bind(EventSocketConnection.class).to(EventSocketConnectionImpl.class);
    bind(InboundEventSocket.class).to(InboundEventSocketImpl.class);
    expose(InboundEventSocket.class);

  }

}