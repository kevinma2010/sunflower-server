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
import sun.misc.Signal;
import sun.misc.SignalHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * @author malongbo
 */
public class DefaultServer  implements Server{
    private Config cfg;
    private Logger log;
    private Map<String, SignalHandler> signalHandlers;

    public DefaultServer(Config cfg) {
        this.cfg = cfg;
        this.log = LoggerFactory.getLogger("");
        signalHandlers = new HashMap<String, SignalHandler>();
    }

    public void setLogger(Logger logger) {
        this.log = logger;
    }

    public void register(String uriPrefix, Class<? extends Controller> controller) {

    }

    public void handleSignal(String signal, SignalHandler handler) {
        signalHandlers.put(signal, handler);
    }

    private void registerSignals() {
        Signal.handle(new Signal("USR2"), new SignalHandler() {
            public void handle(Signal signal) {
                if (signalHandlers.get("USR2") != null) {
                    signalHandlers.get("USR2").handle(signal);
                }
            }
        });
    }

    public void run() {
        // handle signal
        registerSignals();

        log.debug(String.format("pid is %d", Util.getPID()));

        // parse mapping

        // serve
        serve();
    }

    /**
     * 启动 HTTP 服务
     */
    private void serve() {
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
