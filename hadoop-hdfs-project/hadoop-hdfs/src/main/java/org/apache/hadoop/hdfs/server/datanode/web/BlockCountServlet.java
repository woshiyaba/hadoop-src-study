package org.apache.hadoop.hdfs.server.datanode.web;

import java.io.IOException;
import java.io.Serializable;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;

import org.apache.hadoop.hdfs.server.datanode.DataNode;

public class BlockCountServlet extends SimpleChannelInboundHandler<HttpRequest>
    implements Serializable {

  private static final long serialVersionUID = 1L;
  private DataNode datanode;

  public BlockCountServlet(DataNode datanode) {
    this.datanode = datanode;
  }

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, HttpRequest request) throws Exception {
    if (!"/blockcount".equals(request.uri())) {
      ctx.fireChannelRead(request); // 传递给后续处理器
      return;
    }

    // 获取块数量

    long blockCount = datanode.getFSDataset().getNumBlocksCached();

    // 构建响应
    FullHttpResponse response =
        new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK,
            Unpooled.wrappedBuffer(("Current Block Count: " + blockCount).getBytes()));
    response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
    response.headers().set(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());

    ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
  }
}
