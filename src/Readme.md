
boolean autoAck = false;
channel.basicConsume(QUEUE_NAME,autoAck,defaultConsumer);

boolean autoAck = true ;(自动确认模式) 一旦Rabbitmq将消息发给消费者,就会从内从中删除,这种情况kill掉正在执行的消费者,就会丢失正在处理的消息

boolean autoAck = false;(手动模式) 消费者接收到消息后会 发送一个应答给生产者

消息的持久化:
    boolean durable = true;
    channel.queueDeclare(QUEUE_NAME,durable,false,false,null);



订阅模式:
                |------------------ C1
        交换机   |
   P----->x---->|
                |
                |------------------ C2


    一个生产者,多个消费者,每个消费者都有自己的队列,生产者没有将消息发送到队列,而是发送到交换机/转发器 exchange上,
    每个队列都绑定到交换机上, 生产者发送的消息,经过交换机,到达队列,就实现了一个消息被多个消费者消费

          |------邮件
    注册---|
          |------短信


    交换机没有存储能力,rabbitmq里面只有队列有存储能力,当没有队列绑定到这个交换机时,数据会丢失

    交换机 : 接收生产者的消息 ,向队列推送消息

    fanout(不处理路邮键)
    
    direct exchange()
    
    
路由模式:
                            key1
                      |-------------------->  C1 - key1
                      |     key2
    p------>x---------|-------------------->  C2 - key2
                      |     key3
                      |-------------------->  C3 - key3
                
                
   路邮到指定的key
   
   
   
   
   Topic exchange                将主题归类
   将路由键和某模式匹配
   
        # 匹配一个
        * 匹配多个
        
        Goods.#  如Goods.insert
   
   
   
                               Q1 *.orange.*
                         |-------------------->  C1
           type=topic    |     Q2 *.*.rabbit
       p------>x---------|-------------------->  C2
                         |     Q3 lazy.#
                         |-------------------->  C3
       
       商品: 发布 删除  修改 查询
           
           
Rabbitmq 的消息确认机制

   在rabbitmq中我们可以通过持久化 解决rabbitmq服务器异常的数据丢失问题
        
-

生产端 comfirm 模式的实现原理
    
    confirm最大的好处在与异步
    
    开启confirm模式
    channel.confirmSelect()
    
    1, waitForConfirms()
    2, 批量 发一批 waitForConfirms
    3, 异步 comfirm 模式,提供一个回调方法
    
    