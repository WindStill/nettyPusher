package com.four.dake.server;
import static org.jboss.netty.channel.Channels.*;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;

import org.jboss.netty.handler.codec.frame.LengthFieldBasedFrameDecoder;
import org.jboss.netty.handler.codec.frame.LengthFieldPrepender;

/**
 * Creates a newly configured {@link ChannelPipeline} for a new channel.
 */
public class ServerPipelineFactory implements ChannelPipelineFactory {

    public ChannelPipeline getPipeline() throws Exception {
        
        ChannelPipeline pipeline = pipeline();
        
        pipeline.addLast("UP_FRAME_HANDLER", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
        
        pipeline.addLast("DOWN_FRAME_HANDLER", new LengthFieldPrepender(4, false));
        
        pipeline.addLast("SERVER_HANDLER", new ServerHandler());

        return pipeline;
    }
}