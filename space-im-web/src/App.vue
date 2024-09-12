<template>
  <div id="app">
    <!-- <h1>websockets简单示例</h1><br>
    <div id="message">{{ message }}</div>
    <div>
      <input type="text" v-model="text" id="sendText">
      <button id="connect" v-on:click="connect">建立连接</button>
      <button id="sendData" v-on:click="sendData">发送数据</button>
      <button id="closeConnect" v-on:click="closeConnect">关闭连接</button>
    </div> -->

    <space-im />
  </div>
</template>

<script>
import HelloWorld from './components/HelloWorld.vue'
import SpaceIm from './components/SpaceIm.vue'
import protobuf from 'protobufjs'
import Long from 'long'
var messageProto = require('@/js/message')

export default {
  name: 'App',
  data() {
    return {
      websockets: undefined,
      statusArr: [
        { state: 0, value: '正在连接' },
        { state: 1, value: '已建立连接' },
        { state: 2, value: '正在关闭连接' },
        { state: 3, value: '已关闭连接' },
      ],
      message: "",
      text: undefined
    }
  },
  components: {
    HelloWorld,
    SpaceIm
  },
  methods: {
    connect() {
      // 1. 创建websockets对象，参数为服务器websockets地址
      this.websockets = new WebSocket("ws://localhost:8085/space-ws");
      this.websockets.binaryType = 'arrayBuffer'

      // 2.监听websocket的状态变化，接收的信息，关闭时的状态

      //监听连接状态的变化
      this.websockets.onopen = (event) => this.socketChange();

      //监听接收消息的情况
      this.websockets.onmessage = (res) => {
        this.message += `<p>接收数据: ${res.data}</p>`
      }

      //监听关闭时的状态变化
      this.websockets.onclose = (event) => this.socketChange();
    },
    sendData() {
      //1. 首先获取输入的信息，判断信息是否可以发送
      if (this.text == "" || this.text == undefined) {
        this.message += "<p>发送数据为空，请填写完成后再发送！</p>";
        return;
      }

      let textEncoder = new TextEncoder();
      const bodyData = textEncoder.encode(this.text)

      let body = {
        "userId": "1",
        "appId": 123,
        "toId": "1",
        "cmdId": 1,
        "data": bodyData,
      }

      const bodyLength = new Blob([body]).size
      console.log(bodyLength)

      let header = {
        "cmdId": 0x01,
        "magic": 0x722819,
        "bodyLength": bodyLength,
        "version": 1,
        "objectType": 0x01,
        "clientType": 1
      }

      const headerLength = new Blob([header]).size
      console.log(headerLength)

      let messageReq = {
        "header": header,
        "body": body
      }
      console.log(body)

      const byteBuf = messageProto.encodeMessage(messageReq)
      console.log(byteBuf.length);

      this.websockets.send(byteBuf);
      this.message += `<p>发送数据:${this.text}</p>`;
    },
    closeConnect() {
      this.websockets.close();
    },
    socketChange() {
      let state = this.websockets.readyState;
      let val = this.statusArr.map((item) => {
        if (item.state == state) {
          return item.value
        }
      });

      //实时显示状态的变化
      this.message += `<p>当前的socket连接状态是: ${val}</p>`
    }
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
