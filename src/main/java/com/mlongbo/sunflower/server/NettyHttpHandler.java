package com.mlongbo.sunflower.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import static io.netty.buffer.Unpooled.copiedBuffer;

/**
 * @author malongbo
 */
public class NettyHttpHandler extends SimpleChannelInboundHandler<DefaultHttpRequest> {
    protected void channelRead0(ChannelHandlerContext ctx, DefaultHttpRequest msg) throws Exception {
            ByteBuf buf = copiedBuffer("Hello Netty HTTP.", CharsetUtil.UTF_8);
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                    HttpResponseStatus.OK, buf);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html; charset=UTF-8");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, buf.readableBytes());
            response.headers().set(HttpHeaderNames.SERVER, "Simple Netty Http Server");
            ctx.writeAndFlush(response);
    }
}
