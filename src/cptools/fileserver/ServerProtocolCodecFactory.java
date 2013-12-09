/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cptools.fileserver;

import org.apache.mina.filter.codec.demux.DemuxingProtocolCodecFactory;

/**
 *
 * @author zhangwei <laketea@163.com>
 */

public class ServerProtocolCodecFactory extends DemuxingProtocolCodecFactory{
	
	public ServerProtocolCodecFactory(){
			super.addMessageDecoder(ServerMessgeDecoder.class);
	}
}
