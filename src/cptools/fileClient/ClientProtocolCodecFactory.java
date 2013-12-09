/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cptools.fileClient;

import cptools.common.Message;
import org.apache.mina.filter.codec.demux.DemuxingProtocolCodecFactory;

public class ClientProtocolCodecFactory extends DemuxingProtocolCodecFactory {

    public ClientProtocolCodecFactory() {

        super.addMessageEncoder(Message.class, ClientMessageEncoder.class);
    }
}
