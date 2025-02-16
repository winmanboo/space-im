// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: im-server/im-server-api/src/main/java/com/winmanboo/space/im/server/api/protocol/message/message.proto
// Protobuf Java Version: 4.27.1

package com.winmanboo.space.im.server.api.protocol.message;

/**
 * Protobuf type {@code com.winmanboo.space.im.core.codec.protocol.Header}
 */
public final class Header extends
    com.google.protobuf.GeneratedMessage implements
    // @@protoc_insertion_point(message_implements:com.winmanboo.space.im.core.codec.protocol.Header)
    HeaderOrBuilder {
private static final long serialVersionUID = 0L;
  static {
    com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
      com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
      /* major= */ 4,
      /* minor= */ 27,
      /* patch= */ 1,
      /* suffix= */ "",
      Header.class.getName());
  }
  // Use Header.newBuilder() to construct.
  private Header(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
    super(builder);
  }
  private Header() {
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.winmanboo.space.im.server.api.protocol.message.MessageProto.internal_static_com_winmanboo_space_im_core_codec_protocol_Header_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.winmanboo.space.im.server.api.protocol.message.MessageProto.internal_static_com_winmanboo_space_im_core_codec_protocol_Header_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.winmanboo.space.im.server.api.protocol.message.Header.class, com.winmanboo.space.im.server.api.protocol.message.Header.Builder.class);
  }

  public static final int MAGIC_FIELD_NUMBER = 1;
  private int magic_ = 0;
  /**
   * <pre>
   * 消息魔数，固定值
   * </pre>
   *
   * <code>int32 magic = 1;</code>
   * @return The magic.
   */
  @java.lang.Override
  public int getMagic() {
    return magic_;
  }

  public static final int CMDID_FIELD_NUMBER = 2;
  private int cmdId_ = 0;
  /**
   * <pre>
   * 命令 id
   * </pre>
   *
   * <code>int32 cmdId = 2;</code>
   * @return The cmdId.
   */
  @java.lang.Override
  public int getCmdId() {
    return cmdId_;
  }

  public static final int BODYLENGTH_FIELD_NUMBER = 3;
  private int bodyLength_ = 0;
  /**
   * <pre>
   * 消息体长度
   * </pre>
   *
   * <code>int32 bodyLength = 3;</code>
   * @return The bodyLength.
   */
  @java.lang.Override
  public int getBodyLength() {
    return bodyLength_;
  }

  public static final int VERSION_FIELD_NUMBER = 4;
  private int version_ = 0;
  /**
   * <pre>
   * 版本号
   * </pre>
   *
   * <code>int32 version = 4;</code>
   * @return The version.
   */
  @java.lang.Override
  public int getVersion() {
    return version_;
  }

  public static final int OBJECTTYPE_FIELD_NUMBER = 5;
  private int objectType_ = 0;
  /**
   * <pre>
   * 数据解析类型，json/protobuf/...
   * </pre>
   *
   * <code>int32 objectType = 5;</code>
   * @return The objectType.
   */
  @java.lang.Override
  public int getObjectType() {
    return objectType_;
  }

  public static final int CLIENTTYPE_FIELD_NUMBER = 6;
  private int clientType_ = 0;
  /**
   * <pre>
   * 客户但类型
   * </pre>
   *
   * <code>int32 clientType = 6;</code>
   * @return The clientType.
   */
  @java.lang.Override
  public int getClientType() {
    return clientType_;
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (magic_ != 0) {
      output.writeInt32(1, magic_);
    }
    if (cmdId_ != 0) {
      output.writeInt32(2, cmdId_);
    }
    if (bodyLength_ != 0) {
      output.writeInt32(3, bodyLength_);
    }
    if (version_ != 0) {
      output.writeInt32(4, version_);
    }
    if (objectType_ != 0) {
      output.writeInt32(5, objectType_);
    }
    if (clientType_ != 0) {
      output.writeInt32(6, clientType_);
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (magic_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, magic_);
    }
    if (cmdId_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, cmdId_);
    }
    if (bodyLength_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, bodyLength_);
    }
    if (version_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(4, version_);
    }
    if (objectType_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(5, objectType_);
    }
    if (clientType_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(6, clientType_);
    }
    size += getUnknownFields().getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.winmanboo.space.im.server.api.protocol.message.Header)) {
      return super.equals(obj);
    }
    com.winmanboo.space.im.server.api.protocol.message.Header other = (com.winmanboo.space.im.server.api.protocol.message.Header) obj;

    if (getMagic()
        != other.getMagic()) return false;
    if (getCmdId()
        != other.getCmdId()) return false;
    if (getBodyLength()
        != other.getBodyLength()) return false;
    if (getVersion()
        != other.getVersion()) return false;
    if (getObjectType()
        != other.getObjectType()) return false;
    if (getClientType()
        != other.getClientType()) return false;
    if (!getUnknownFields().equals(other.getUnknownFields())) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + MAGIC_FIELD_NUMBER;
    hash = (53 * hash) + getMagic();
    hash = (37 * hash) + CMDID_FIELD_NUMBER;
    hash = (53 * hash) + getCmdId();
    hash = (37 * hash) + BODYLENGTH_FIELD_NUMBER;
    hash = (53 * hash) + getBodyLength();
    hash = (37 * hash) + VERSION_FIELD_NUMBER;
    hash = (53 * hash) + getVersion();
    hash = (37 * hash) + OBJECTTYPE_FIELD_NUMBER;
    hash = (53 * hash) + getObjectType();
    hash = (37 * hash) + CLIENTTYPE_FIELD_NUMBER;
    hash = (53 * hash) + getClientType();
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.winmanboo.space.im.server.api.protocol.message.Header parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.winmanboo.space.im.server.api.protocol.message.Header parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.winmanboo.space.im.server.api.protocol.message.Header parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.winmanboo.space.im.server.api.protocol.message.Header parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.winmanboo.space.im.server.api.protocol.message.Header parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.winmanboo.space.im.server.api.protocol.message.Header parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.winmanboo.space.im.server.api.protocol.message.Header parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input);
  }
  public static com.winmanboo.space.im.server.api.protocol.message.Header parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static com.winmanboo.space.im.server.api.protocol.message.Header parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static com.winmanboo.space.im.server.api.protocol.message.Header parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.winmanboo.space.im.server.api.protocol.message.Header parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input);
  }
  public static com.winmanboo.space.im.server.api.protocol.message.Header parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.winmanboo.space.im.server.api.protocol.message.Header prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessage.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code com.winmanboo.space.im.core.codec.protocol.Header}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessage.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:com.winmanboo.space.im.core.codec.protocol.Header)
      com.winmanboo.space.im.server.api.protocol.message.HeaderOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.winmanboo.space.im.server.api.protocol.message.MessageProto.internal_static_com_winmanboo_space_im_core_codec_protocol_Header_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.winmanboo.space.im.server.api.protocol.message.MessageProto.internal_static_com_winmanboo_space_im_core_codec_protocol_Header_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.winmanboo.space.im.server.api.protocol.message.Header.class, com.winmanboo.space.im.server.api.protocol.message.Header.Builder.class);
    }

    // Construct using com.winmanboo.space.im.server.api.protocol.message.Header.newBuilder()
    private Builder() {

    }

    private Builder(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      super(parent);

    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      bitField0_ = 0;
      magic_ = 0;
      cmdId_ = 0;
      bodyLength_ = 0;
      version_ = 0;
      objectType_ = 0;
      clientType_ = 0;
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.winmanboo.space.im.server.api.protocol.message.MessageProto.internal_static_com_winmanboo_space_im_core_codec_protocol_Header_descriptor;
    }

    @java.lang.Override
    public com.winmanboo.space.im.server.api.protocol.message.Header getDefaultInstanceForType() {
      return com.winmanboo.space.im.server.api.protocol.message.Header.getDefaultInstance();
    }

    @java.lang.Override
    public com.winmanboo.space.im.server.api.protocol.message.Header build() {
      com.winmanboo.space.im.server.api.protocol.message.Header result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.winmanboo.space.im.server.api.protocol.message.Header buildPartial() {
      com.winmanboo.space.im.server.api.protocol.message.Header result = new com.winmanboo.space.im.server.api.protocol.message.Header(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(com.winmanboo.space.im.server.api.protocol.message.Header result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.magic_ = magic_;
      }
      if (((from_bitField0_ & 0x00000002) != 0)) {
        result.cmdId_ = cmdId_;
      }
      if (((from_bitField0_ & 0x00000004) != 0)) {
        result.bodyLength_ = bodyLength_;
      }
      if (((from_bitField0_ & 0x00000008) != 0)) {
        result.version_ = version_;
      }
      if (((from_bitField0_ & 0x00000010) != 0)) {
        result.objectType_ = objectType_;
      }
      if (((from_bitField0_ & 0x00000020) != 0)) {
        result.clientType_ = clientType_;
      }
    }

    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.winmanboo.space.im.server.api.protocol.message.Header) {
        return mergeFrom((com.winmanboo.space.im.server.api.protocol.message.Header)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.winmanboo.space.im.server.api.protocol.message.Header other) {
      if (other == com.winmanboo.space.im.server.api.protocol.message.Header.getDefaultInstance()) return this;
      if (other.getMagic() != 0) {
        setMagic(other.getMagic());
      }
      if (other.getCmdId() != 0) {
        setCmdId(other.getCmdId());
      }
      if (other.getBodyLength() != 0) {
        setBodyLength(other.getBodyLength());
      }
      if (other.getVersion() != 0) {
        setVersion(other.getVersion());
      }
      if (other.getObjectType() != 0) {
        setObjectType(other.getObjectType());
      }
      if (other.getClientType() != 0) {
        setClientType(other.getClientType());
      }
      this.mergeUnknownFields(other.getUnknownFields());
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 8: {
              magic_ = input.readInt32();
              bitField0_ |= 0x00000001;
              break;
            } // case 8
            case 16: {
              cmdId_ = input.readInt32();
              bitField0_ |= 0x00000002;
              break;
            } // case 16
            case 24: {
              bodyLength_ = input.readInt32();
              bitField0_ |= 0x00000004;
              break;
            } // case 24
            case 32: {
              version_ = input.readInt32();
              bitField0_ |= 0x00000008;
              break;
            } // case 32
            case 40: {
              objectType_ = input.readInt32();
              bitField0_ |= 0x00000010;
              break;
            } // case 40
            case 48: {
              clientType_ = input.readInt32();
              bitField0_ |= 0x00000020;
              break;
            } // case 48
            default: {
              if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                done = true; // was an endgroup tag
              }
              break;
            } // default:
          } // switch (tag)
        } // while (!done)
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.unwrapIOException();
      } finally {
        onChanged();
      } // finally
      return this;
    }
    private int bitField0_;

    private int magic_ ;
    /**
     * <pre>
     * 消息魔数，固定值
     * </pre>
     *
     * <code>int32 magic = 1;</code>
     * @return The magic.
     */
    @java.lang.Override
    public int getMagic() {
      return magic_;
    }
    /**
     * <pre>
     * 消息魔数，固定值
     * </pre>
     *
     * <code>int32 magic = 1;</code>
     * @param value The magic to set.
     * @return This builder for chaining.
     */
    public Builder setMagic(int value) {

      magic_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 消息魔数，固定值
     * </pre>
     *
     * <code>int32 magic = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearMagic() {
      bitField0_ = (bitField0_ & ~0x00000001);
      magic_ = 0;
      onChanged();
      return this;
    }

    private int cmdId_ ;
    /**
     * <pre>
     * 命令 id
     * </pre>
     *
     * <code>int32 cmdId = 2;</code>
     * @return The cmdId.
     */
    @java.lang.Override
    public int getCmdId() {
      return cmdId_;
    }
    /**
     * <pre>
     * 命令 id
     * </pre>
     *
     * <code>int32 cmdId = 2;</code>
     * @param value The cmdId to set.
     * @return This builder for chaining.
     */
    public Builder setCmdId(int value) {

      cmdId_ = value;
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 命令 id
     * </pre>
     *
     * <code>int32 cmdId = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearCmdId() {
      bitField0_ = (bitField0_ & ~0x00000002);
      cmdId_ = 0;
      onChanged();
      return this;
    }

    private int bodyLength_ ;
    /**
     * <pre>
     * 消息体长度
     * </pre>
     *
     * <code>int32 bodyLength = 3;</code>
     * @return The bodyLength.
     */
    @java.lang.Override
    public int getBodyLength() {
      return bodyLength_;
    }
    /**
     * <pre>
     * 消息体长度
     * </pre>
     *
     * <code>int32 bodyLength = 3;</code>
     * @param value The bodyLength to set.
     * @return This builder for chaining.
     */
    public Builder setBodyLength(int value) {

      bodyLength_ = value;
      bitField0_ |= 0x00000004;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 消息体长度
     * </pre>
     *
     * <code>int32 bodyLength = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearBodyLength() {
      bitField0_ = (bitField0_ & ~0x00000004);
      bodyLength_ = 0;
      onChanged();
      return this;
    }

    private int version_ ;
    /**
     * <pre>
     * 版本号
     * </pre>
     *
     * <code>int32 version = 4;</code>
     * @return The version.
     */
    @java.lang.Override
    public int getVersion() {
      return version_;
    }
    /**
     * <pre>
     * 版本号
     * </pre>
     *
     * <code>int32 version = 4;</code>
     * @param value The version to set.
     * @return This builder for chaining.
     */
    public Builder setVersion(int value) {

      version_ = value;
      bitField0_ |= 0x00000008;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 版本号
     * </pre>
     *
     * <code>int32 version = 4;</code>
     * @return This builder for chaining.
     */
    public Builder clearVersion() {
      bitField0_ = (bitField0_ & ~0x00000008);
      version_ = 0;
      onChanged();
      return this;
    }

    private int objectType_ ;
    /**
     * <pre>
     * 数据解析类型，json/protobuf/...
     * </pre>
     *
     * <code>int32 objectType = 5;</code>
     * @return The objectType.
     */
    @java.lang.Override
    public int getObjectType() {
      return objectType_;
    }
    /**
     * <pre>
     * 数据解析类型，json/protobuf/...
     * </pre>
     *
     * <code>int32 objectType = 5;</code>
     * @param value The objectType to set.
     * @return This builder for chaining.
     */
    public Builder setObjectType(int value) {

      objectType_ = value;
      bitField0_ |= 0x00000010;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 数据解析类型，json/protobuf/...
     * </pre>
     *
     * <code>int32 objectType = 5;</code>
     * @return This builder for chaining.
     */
    public Builder clearObjectType() {
      bitField0_ = (bitField0_ & ~0x00000010);
      objectType_ = 0;
      onChanged();
      return this;
    }

    private int clientType_ ;
    /**
     * <pre>
     * 客户但类型
     * </pre>
     *
     * <code>int32 clientType = 6;</code>
     * @return The clientType.
     */
    @java.lang.Override
    public int getClientType() {
      return clientType_;
    }
    /**
     * <pre>
     * 客户但类型
     * </pre>
     *
     * <code>int32 clientType = 6;</code>
     * @param value The clientType to set.
     * @return This builder for chaining.
     */
    public Builder setClientType(int value) {

      clientType_ = value;
      bitField0_ |= 0x00000020;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 客户但类型
     * </pre>
     *
     * <code>int32 clientType = 6;</code>
     * @return This builder for chaining.
     */
    public Builder clearClientType() {
      bitField0_ = (bitField0_ & ~0x00000020);
      clientType_ = 0;
      onChanged();
      return this;
    }

    // @@protoc_insertion_point(builder_scope:com.winmanboo.space.im.core.codec.protocol.Header)
  }

  // @@protoc_insertion_point(class_scope:com.winmanboo.space.im.core.codec.protocol.Header)
  private static final com.winmanboo.space.im.server.api.protocol.message.Header DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.winmanboo.space.im.server.api.protocol.message.Header();
  }

  public static com.winmanboo.space.im.server.api.protocol.message.Header getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<Header>
      PARSER = new com.google.protobuf.AbstractParser<Header>() {
    @java.lang.Override
    public Header parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      Builder builder = newBuilder();
      try {
        builder.mergeFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(builder.buildPartial());
      } catch (com.google.protobuf.UninitializedMessageException e) {
        throw e.asInvalidProtocolBufferException().setUnfinishedMessage(builder.buildPartial());
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(e)
            .setUnfinishedMessage(builder.buildPartial());
      }
      return builder.buildPartial();
    }
  };

  public static com.google.protobuf.Parser<Header> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<Header> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.winmanboo.space.im.server.api.protocol.message.Header getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

