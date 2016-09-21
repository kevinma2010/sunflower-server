package com.mlongbo.sunflower.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author malongbo
 */
public class DefaultServer  implements Server{
    private Config cfg;
    private Logger log;

    public DefaultServer(Config cfg) {
        this.cfg = cfg;
        this.log = LoggerFactory.getLogger("");
    }

    public void setLogger(Logger logger) {
        this.log = logger;
    }

    public void register(String uriPrefix, Class<? extends Controller> controller) {

    }

    public void run() {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new HttpServerCodec(), new NettyHttpHandler());
                    }
                });
        try {
            Channel ch = b.bind(cfg.getHost(), Integer.parseInt(cfg.getPort())).sync().channel();
            log.info(String.format("%s start at %s", cfg.get(ConfigKey.Name), cfg.get(ConfigKey.Address)));
            ch.closeFuture().sync();
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }
}
