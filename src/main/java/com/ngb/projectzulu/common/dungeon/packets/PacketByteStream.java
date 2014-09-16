package com.ngb.projectzulu.common.dungeon.packets;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.ngb.projectzulu.common.core.PZPacket;
import com.ngb.projectzulu.common.core.PZPacketBase;
import com.ngb.projectzulu.common.core.ProjectZuluLog;

//import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
//import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

/**
 * Wraps ByteBuf into ByteStreams for additional read/write features such as writeString
 */
public abstract class PacketByteStream extends PZPacketBase {

    @Override
    public void toBytes(ByteBuf buffer) {
        ByteBufOutputStream data = new ByteBufOutputStream(buffer);
        try {
            writeData(data);
        } catch (Exception e) {
            ProjectZuluLog.severe("Error writing packet %s to ByteBufOutputStream", this);
            e.printStackTrace();
        }
    }

    protected abstract void writeData(ByteBufOutputStream buffer) throws IOException;

    @Override
    public void fromBytes(ByteBuf buffer) {
        ByteBufInputStream byteStream = new ByteBufInputStream(buffer);
        try {
            readData(byteStream);
        } catch (Exception e) {
            ProjectZuluLog.severe("Error reading packet %s from ByteBufInputStream", this);
            e.printStackTrace();
        }
    }

    protected abstract void readData(ByteBufInputStream buffer) throws IOException;
}
