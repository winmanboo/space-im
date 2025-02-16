// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: im-server/im-server-api/src/main/java/com/winmanboo/space/im/server/api/protocol/message/message.proto
// Protobuf Java Version: 4.27.1

package com.winmanboo.space.im.server.api.protocol.message;

public interface HeaderOrBuilder extends
    // @@protoc_insertion_point(interface_extends:com.winmanboo.space.im.core.codec.protocol.Header)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 消息魔数，固定值
   * </pre>
   *
   * <code>int32 magic = 1;</code>
   * @return The magic.
   */
  int getMagic();

  /**
   * <pre>
   * 命令 id
   * </pre>
   *
   * <code>int32 cmdId = 2;</code>
   * @return The cmdId.
   */
  int getCmdId();

  /**
   * <pre>
   * 消息体长度
   * </pre>
   *
   * <code>int32 bodyLength = 3;</code>
   * @return The bodyLength.
   */
  int getBodyLength();

  /**
   * <pre>
   * 版本号
   * </pre>
   *
   * <code>int32 version = 4;</code>
   * @return The version.
   */
  int getVersion();

  /**
   * <pre>
   * 数据解析类型，json/protobuf/...
   * </pre>
   *
   * <code>int32 objectType = 5;</code>
   * @return The objectType.
   */
  int getObjectType();

  /**
   * <pre>
   * 客户但类型
   * </pre>
   *
   * <code>int32 clientType = 6;</code>
   * @return The clientType.
   */
  int getClientType();
}
