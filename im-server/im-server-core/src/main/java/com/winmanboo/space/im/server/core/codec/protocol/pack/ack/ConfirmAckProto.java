// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: im-server/im-server-core/src/main/java/com/winmanboo/space/im/server/core/codec/protocol/pack/ack/confirm_ack.proto
// Protobuf Java Version: 4.27.1

package com.winmanboo.space.im.server.core.codec.protocol.pack.ack;

public final class ConfirmAckProto {
  private ConfirmAckProto() {}
  static {
    com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
      com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
      /* major= */ 4,
      /* minor= */ 27,
      /* patch= */ 1,
      /* suffix= */ "",
      ConfirmAckProto.class.getName());
  }
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_winmanboo_space_im_core_codec_protocol_pack_ack_ConfirmAck_descriptor;
  static final 
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_com_winmanboo_space_im_core_codec_protocol_pack_ack_ConfirmAck_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_winmanboo_space_im_core_codec_protocol_pack_ack_MessagePack_descriptor;
  static final 
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_com_winmanboo_space_im_core_codec_protocol_pack_ack_MessagePack_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\nsim-server/im-server-core/src/main/java" +
      "/com/winmanboo/space/im/server/core/code" +
      "c/protocol/pack/ack/confirm_ack.proto\0223c" +
      "om.winmanboo.space.im.core.codec.protoco" +
      "l.pack.ack\"\215\002\n\nConfirmAck\022\013\n\003ack\030\001 \001(\005\022\021" +
      "\n\tmessageId\030\002 \001(\t\022\022\n\nresultCode\030\003 \001(\005\022N\n" +
      "\004data\030\004 \001(\0132@.com.winmanboo.space.im.cor" +
      "e.codec.protocol.pack.ack.MessagePack\"\036\n" +
      "\nResultCode\022\010\n\004FAIL\020\000\022\006\n\002OK\020\001\"[\n\003Ack\022\007\n\003" +
      "ACK\020\000\022\010\n\004SYNC\020\001\022\013\n\007MESSAGE\020\002\022\020\n\014FRIEND_A" +
      "PPLY\020\004\022\021\n\rSYSTEM_NOTIFY\020\005\022\017\n\013GROUP_APPLY" +
      "\020\006\"Y\n\013MessagePack\022\016\n\006userId\030\001 \001(\t\022\014\n\004toI" +
      "d\030\002 \001(\t\022\r\n\005msgId\030\003 \001(\t\022\017\n\007msgType\030\004 \001(\005\022" +
      "\014\n\004data\030\005 \001(\tBO\n:com.winmanboo.space.im." +
      "server.core.codec.protocol.pack.ackB\017Con" +
      "firmAckProtoP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_com_winmanboo_space_im_core_codec_protocol_pack_ack_ConfirmAck_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_com_winmanboo_space_im_core_codec_protocol_pack_ack_ConfirmAck_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_com_winmanboo_space_im_core_codec_protocol_pack_ack_ConfirmAck_descriptor,
        new java.lang.String[] { "Ack", "MessageId", "ResultCode", "Data", });
    internal_static_com_winmanboo_space_im_core_codec_protocol_pack_ack_MessagePack_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_com_winmanboo_space_im_core_codec_protocol_pack_ack_MessagePack_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_com_winmanboo_space_im_core_codec_protocol_pack_ack_MessagePack_descriptor,
        new java.lang.String[] { "UserId", "ToId", "MsgId", "MsgType", "Data", });
    descriptor.resolveAllFeaturesImmutable();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
