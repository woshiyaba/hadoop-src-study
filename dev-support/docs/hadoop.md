### hadoop源码分析

#### namenode

#### datanode

```
1、datanode磁盘上维护了一个block的数据 定期上报给namenode
2、namenode不会主动调用datanode的接口，datanode会定期发送心跳指令给namenode，询问namenode是否有任务，namenode给datanode返回一个指令 （删除数据|复制blcok等等）
3、如果client与datanode交互不是直接和datanode交互的 是datanode将自己的监听的host和端口号发送给namenode，client与datanode交互会先从namenode获取到对应datanode的host和port然后进行通信
```

##### DataXceiverServer

```
DataXceiverServer 类是 Apache Hadoop 中 DataNode 组件的一部分，主要用于处理来自客户端或其他 DataNode 的数据传输请求。它的任务是管理 DataNode 上的数据传输线程，并且确保数据块的接收和发送过程能够高效且安全地进行。它与 PeerServer 交互，用于接收来自远程节点的连接，并且在接收到连接时创建数据传输线程（DataXceiver）。这个类是 Hadoop 分布式文件系统（HDFS）中重要的组件之一，专门用于处理数据块的接收和传输。
```

InfoServer

```
监听处理httpserver，初始化httpserver的类
org/apache/hadoop/hdfs/server/datanode/DataNode.java:1299
```

##### IpcServer

```
org.apache.hadoop.hdfs.server.datanode.DataNode#initIpcServer

```

