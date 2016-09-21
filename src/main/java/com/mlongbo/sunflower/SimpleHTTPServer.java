package com.mlongbo.sunflower;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.CharsetUtil;

import static io.netty.buffer.Unpooled.copiedBuffer;


/**
 * @author malongbo
 */
public class SimpleHTTPServer {

    // 服务端口
    private  final String address = "0.0.0.0";
    private final int port = 3002;

    public void run() throws Exception{
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                .handler(new LoggingHandler(LogLevel.DEBUG))
                .childHandler(new SimpleHttpInitializer());

        try {
            Channel ch = b.bind(address, port).sync().channel();
            System.out.println("HTTP Server Serve at " + address + ":" + port);
            ch.closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }

    public static void main( String[] args ) {
        try {
            new SimpleHTTPServer().run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class SimpleHttpInitializer extends ChannelInitializer<SocketChannel> {
        protected void initChannel(SocketChannel ch) throws Exception {
            ch.pipeline().addLast(new HttpServerCodec(), new SimpleHttpHandler());
        }
    }

    private class SimpleHttpHandler extends SimpleChannelInboundHandler<DefaultHttpRequest>{

        protected void channelRead0(ChannelHandlerContext ctx, DefaultHttpRequest msg) throws Exception {
            System.out.println("request uri: " + msg.uri());
            ByteBuf buf = copiedBuffer("Hello Netty HTTP.", CharsetUtil.UTF_8);
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                    HttpResponseStatus.OK, buf);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html; charset=UTF-8");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, buf.readableBytes());
            response.headers().set(HttpHeaderNames.SERVER, "Simple Netty Http Server");
            ctx.writeAndFlush(response);
        }

    }
}
