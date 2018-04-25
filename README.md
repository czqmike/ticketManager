# ticketManager
A simple system simulate the real ticket-book system.Based on JavaFX-MySQL-JDBC.

运行环境：
  Eclipse 带e(fx)clipse插件
  MySQL
  JDBC连接的jar包:mysql-connector-java-5.1.5-bin.jar
  JavaFX连接的jar包:controlsfx-8.0.6_20.jar

说明：
  简易的练习系统，模拟购票退票买票等操作。
  
功能：
  现在需要建立一个火车票售票系统，以完成在众多的车次中较容易地选出自己需要的车次这一功能。此系统应该可以实现始发站，终点站及目的站的查询，并且在查询后可以订票，并且可以退票。
  1.	注册及登录功能
  用户一开始打开的窗口应该是初始窗口，可以完成注册及登录功能，在登录后方可进入系统。
  2.	查询功能
  a)	站站查询：起始站到终点站的车票查询
  b)	车次查询：通过输入的车次查询
  c)	车票查询：通过输入的车票编号查询
  d)	已定车票查询
  3.	订票功能
  在查询车票后显示车票的价格，有无空位等信息，然后提供订票窗口，让用户决定是否订这张车票。
  4.	退票功能
  在查询了自己的已定车票后可以选择退票

运行截图：

![Image](https://github.com/czqmike/ticketManager/blob/master/screenshots/SignIn.png)
![Image](https://github.com/czqmike/ticketManager/blob/master/screenshots/SignUp.png)
![Image](https://github.com/czqmike/ticketManager/blob/master/screenshots/PrimaryWindow.png)
![Image](https://github.com/czqmike/ticketManager/blob/master/screenshots/SelectDialog1.png)
![Image](https://github.com/czqmike/ticketManager/blob/master/screenshots/SelectDialog2.png)
![Image](https://github.com/czqmike/ticketManager/blob/master/screenshots/MyTicketDialog1.png)
![Image](https://github.com/czqmike/ticketManager/blob/master/screenshots/MyTicketDialog2.png)

