package com.four.dake.server;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.sf.json.JSONObject;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

public class ServerHandler extends SimpleChannelUpstreamHandler {
	private static final Logger logger = Logger.getLogger(
			ServerHandler.class.getName());
	
	private static Map<String, Channel> allChannels = Collections.synchronizedMap(new HashMap<String, Channel>());

	public static Logger getLogger() {
		return logger;
	}
	
	@Override
	public void	channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
		System.out.println("有一个新连接");
	}
	
	@Override
	public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
		System.out.println("有一个连接断开………………………………");
	}

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws UnsupportedEncodingException {
//		String message = ((ChannelBuffer) e.getMessage()).toString(Charset.forName( "UTF-8" ));
//		JSONObject parsed = JSONObject.fromObject(message);
//		JSONObject user = (JSONObject)parsed.get("user");
//		String user_id = (String)user.get("username");
//	    System.out.println(user_id);
//        e.getChannel().write(e.getMessage());
        
		String recievedDataStr = ((ChannelBuffer) e.getMessage()).toString(Charset.forName( "UTF-8" ));
		System.out.println(recievedDataStr);
		JSONObject recievedData = JSONObject.fromObject(recievedDataStr);
		String type = recievedData.getString("type");
		if (type.equals("login")) {
			System.out.println(type);
			String userId = recievedData.getString("id");
			Channel channel = e.getChannel();
			channel.setAttachment(userId);
			allChannels.put(userId, channel);
		} else if (type.equals("chat")) {
			JSONObject sender = recievedData.getJSONObject("user");
			JSONObject recievedMsg = recievedData.getJSONObject("message");
			String friendId = recievedData.getString("friendId");
			JSONObject sendingData = new JSONObject();
			sendingData.put("sender", sender);
			sendingData.put("type", type);
			sendingData.put("message", recievedMsg);
			byte[] dataByte = sendingData.toString().getBytes("UTF-8");
			ChannelBuffer channelBuffer = ChannelBuffers.buffer(dataByte.length); 
			channelBuffer.writeBytes(dataByte);
			if (allChannels.containsKey(friendId)) {
				System.out.println("sended……");
				Channel friendChannel = allChannels.get(friendId);
				friendChannel.write(channelBuffer);
			}
		} else if (type.equals("join")){
			JSONObject user = recievedData.getJSONObject("user");
			String friendId = recievedData.getString("friendId");
			JSONObject sendingData = new JSONObject();
			sendingData.put("user", user);
			sendingData.put("type", type);
			byte[] dataByte = sendingData.toString().getBytes("UTF-8");
			ChannelBuffer channelBuffer = ChannelBuffers.buffer(dataByte.length); 
			channelBuffer.writeBytes(dataByte);
			if (allChannels.containsKey(friendId)) {
				System.out.println("sended……");
				Channel friendChannel = allChannels.get(friendId);
				friendChannel.write(channelBuffer);
			}
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
		// Close the connection when an exception is raised.
		logger.log(
				Level.WARNING,
				"Unexpected exception from downstream.",
				e.getCause());
		e.getChannel().close();
	}
}
