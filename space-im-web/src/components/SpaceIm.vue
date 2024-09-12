<template>
  <div>
    <el-row>
      <el-col :span="12">
        <div class="chat-box" style="width: 100%; height: 700px; background-color: darkgray;" v-html="chatContent">
        </div>
        <el-row>
          <el-col :span="20">
            <el-input v-model="chatInput" placeholder="请输入聊天内容"></el-input>
          </el-col>
          <el-rol :span="4">
            <el-button v-on:click="sendData">发送</el-button>
          </el-rol>
        </el-row>
      </el-col>
      <el-col :span="12">
        <div class="log-box" style="height: 400px; background-color: cadetblue;" v-html="logContent"></div>
        <div class="operation-box" style="height: 300px;">
          <el-input v-model="userId" placeholder="发送方用户 id" />
          <el-input v-model="toId" placeholder="接收方用户 id" />
          <el-button v-on:click="login">登录</el-button>
          <el-button v-on:click="connect">连接</el-button>
          <el-button v-on:click="disconnect">断开连接</el-button>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
var messageProto = require('@/js/message')
var confirmAck = require('@/js/confirmAck')

export default {
  name: 'SpaceIm',
  data() {
    return {
      websocket: undefined,
      chatContent: '',
      logContent: '',
      chatInput: '',
      userId: '',
      toId: '',
      statusArr: [
        { state: 0, value: '正在连接' },
        { state: 1, value: '已建立连接' },
        { state: 2, value: '正在关闭连接' },
        { state: 3, value: '已关闭连接' },
      ],
    }
  },
  methods: {
    connect() {
      this.websocket = new WebSocket('ws://localhost:8085/space-ws')
      this.websocket.binaryType = 'arrayBuffer'

      // 开启状态监听
      this.websocket.onopen = (event) => this.socketChange()

      // 接收消息
      this.websocket.onmessage = (res) => {
        const reader = new FileReader()
        reader.readAsArrayBuffer(res.data)
        reader.onload = () => {
          const arrayBuffer = reader.result
          const buffer = new Uint8Array(arrayBuffer)
          const ack = confirmAck.decodeConfirmAck(buffer)
          const ackResult = ack.resultCode == 1 ? "success" : "fail";
          if (ack.ack == 0) { // "普通ack"
          } else if (ack.ack == 1) { // "消息同步ack"
            this.chatContent += `${this.userId}: ${ackResult}<br>`
          } else if (ack.ack == 2) { // "业务消息 ack"
            const messagePack = ack.data
            this.chatContent += `${messagePack.userId}: ${messagePack.data}<br>`
          }
        }
      }

      // 监听关闭
      this.websocket.onclose = (event) => this.socketChange()
    },
    login() {
      let body = {
        "userId": this.userId,
        "appId": 1,
        "msgId": this.generateMsgId().toString(),
      }
      let header = {
        "magic": 0x722819,
        "cmdId": 0x01,
        "bodyLength": new Blob([body]).size,
        "version": 1,
        "objectType": 2,
        "clientType": 1
      }

      let message = {
        "header": header,
        "body": body
      }
      console.log(message);
      const byteBuf = messageProto.encodeMessage(message)
      this.logContent += `${this.userId}: ${JSON.stringify(message)}<br>`

      this.websocket.send(byteBuf);
    },
    disconnect() {
      this.websocket.close()
    },
    sendData() {
      let textEncoder = new TextEncoder();
      const bodyData = textEncoder.encode(this.chatInput)

      let body = {
        "userId": this.userId,
        "toId": this.toId,
        "appId": 1,
        "msgId": this.generateMsgId().toString(),
        "cmdId": 0x00,
        "data": bodyData
      }

      let header = {
        "magic": 0x722819,
        "cmdId": 0x04,
        "bodyLength": new Blob([body]).size,
        "version": 1,
        "objectType": 2,
        "clientType": 1
      }

      let message = {
        "header": header,
        "body": body
      }
      const byteBuf = messageProto.encodeMessage(message)
      this.logContent += `${this.userId}: ${message}\n`

      this.websocket.send(byteBuf);
      this.chatContent += `${this.userId}: ${this.chatInput}<br>`
      this.chatInput = ""
    },
    socketChange() {
      let state = this.websocket.readyState;
      let val = this.statusArr.map((item) => {
        if (item.state == state) {
          return item.value
        }
      });

      // 实时显示状态的变化
      this.logContent += `当前的socket连接状态是: ${val}<br>`
    },
    generateMsgId() {
      return Math.floor(100000 + Math.random() * 900000)
    }
  }
}
</script>

<style></style>