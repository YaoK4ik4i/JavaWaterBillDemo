#=CONCATENATE("SELECT null,'2017','12','",A2,"','现金','私人','",B2,"','1','",C2,"',null",CHAR(10),"UNION ALL")
INSERT INTO accounts (orderid,year,month,day,paymethod,isprivate,itemname,itemnumber,price,remarks)
SELECT null,'2017','12','22','现金','私人','平型关路电费12月','1','3.9',null
UNION ALL
SELECT null,'2017','12','22','现金','私人','12201电费12月','1','107.6',null
UNION ALL
SELECT null,'2017','12','22','现金','私人','刘群201801养老费','1','4101.3',null
UNION ALL
SELECT null,'2017','12','22','现金','私人','手机费11月','1','227',null
UNION ALL
SELECT null,'2017','12','22','现金','私人','平型关路燃气费12月','1','27',null
UNION ALL
SELECT null,'2017','12','22','现金','私人','平型关路水费12月','1','13.8',null
UNION ALL
SELECT null,'2017','12','22','现金','私人','12201燃气费12月','1','69',null
UNION ALL
SELECT null,'2017','12','31','现金','店内','馒头','1','50',null  
UNION ALL
SELECT null,'2017','12','01','信用卡','店内','不锈钢角码L型','1','52.8',null
UNION ALL
SELECT null,'2017','12','03','信用卡','店内','LED防水铜丝灯','1','96',null
UNION ALL
SELECT null,'2017','12','03','信用卡','店内','硅胶地面刮水器','1','25.5',null
UNION ALL
SELECT null,'2017','12','03','信用卡','店内','酒精棉签','1','25.8',null
UNION ALL
SELECT null,'2017','12','05','信用卡','店内','圣诞节装饰品','1','706.8',null
UNION ALL
SELECT null,'2017','12','06','信用卡','店内','套装工具','1','17.7',null
UNION ALL
SELECT null,'2017','12','07','信用卡','店内','法式羊排','1','234',null
UNION ALL
SELECT null,'2017','12','08','信用卡','店内','led小彩灯','1','158.8',null
UNION ALL
SELECT null,'2017','12','08','信用卡','店内','电池','1','64.9',null
UNION ALL
SELECT null,'2017','12','09','信用卡','店内','旺旺 小小酥','1','96.2',null
UNION ALL
SELECT null,'2017','12','09','信用卡','店内','微波炉爆米花','1','85',null
UNION ALL
SELECT null,'2017','12','11','信用卡','店内','越南无籽新鲜青，黄柠檬','1','40.7',null
UNION ALL
SELECT null,'2017','12','11','信用卡','店内','500只商务水杯','1','53.6',null
UNION ALL
SELECT null,'2017','12','12','信用卡','店内','牛皮纸','1','28.9',null
UNION ALL
SELECT null,'2017','12','12','信用卡','店内','安岳黄柠檬','1','19.7',null
UNION ALL
SELECT null,'2017','12','12','信用卡','店内','led星星灯小彩灯','1','244.8',null
UNION ALL
SELECT null,'2017','12','12','信用卡','店内','圣诞节装饰拉花','1','45.9',null
UNION ALL
SELECT null,'2017','12','12','信用卡','店内','一次性船盒','1','121.2',null
UNION ALL
SELECT null,'2017','12','12','信用卡','店内','玉米片等小零食','1','172.4',null
UNION ALL
SELECT null,'2017','12','13','信用卡','店内','龙虾片','1','124',null
UNION ALL
SELECT null,'2017','12','14','信用卡','店内','红薯粉宽粉','1','25',null
UNION ALL
SELECT null,'2017','12','14','信用卡','店内','电池','1','64.5',null
UNION ALL
SELECT null,'2017','12','15','信用卡','店内','智利进口火鸡腿','1','1160',null
UNION ALL
SELECT null,'2017','12','16','信用卡','店内','口水娃豆','1','74',null
UNION ALL
SELECT null,'2017','12','17','信用卡','店内','巧克力华夫饼粉','1','32',null
UNION ALL
SELECT null,'2017','12','18','信用卡','店内',' 门缝密封条','1','37.6',null
UNION ALL
SELECT null,'2017','12','18','信用卡','店内','净水器滤芯','1','85',null
UNION ALL
SELECT null,'2017','12','18','信用卡','店内','一次性杯子','1','28.9',null
UNION ALL
SELECT null,'2017','12','19','信用卡','店内','圣诞节装饰用品','1','89.3',null
UNION ALL
SELECT null,'2017','12','19','信用卡','店内','牛排煎锅','1','39',null
UNION ALL
SELECT null,'2017','12','19','信用卡','店内','里脊肉腌料','1','38.6',null
UNION ALL
SELECT null,'2017','12','19','信用卡','店内','菲娜虾味木薯片','1','154.8',null
UNION ALL
SELECT null,'2017','12','21','信用卡','店内','天猫超市物品','1','636.5',null
UNION ALL
SELECT null,'2017','12','22','信用卡','店内','天猫超市物品','1','193.5',null
UNION ALL
SELECT null,'2017','12','26','信用卡','店内','xo酱烤肉粒','1','168.6',null
UNION ALL
SELECT null,'2017','12','27','信用卡','店内','海天味极鲜，午餐肉罐头','1','89.2',null
UNION ALL
SELECT null,'2017','12','31','信用卡','店内','越南无籽新鲜青柠檬','1','57.8',null
UNION ALL
SELECT null,'2017','12','01','信用卡','私人','羽绒马甲女装','1','66',null
UNION ALL
SELECT null,'2017','12','01','信用卡','私人','野生罗布麻茶叶','1','29.9',null
UNION ALL
SELECT null,'2017','12','03','信用卡','私人','一次性内裤女士','1','33.8',null
UNION ALL
SELECT null,'2017','12','03','信用卡','私人','日本IQOS电子烟','1','39.8',null
UNION ALL
SELECT null,'2017','12','11','信用卡','私人','无痕挂钩强力粘','1','27.6',null
UNION ALL
SELECT null,'2017','12','12','信用卡','私人','中老年人保暖内','1','66.8',null
UNION ALL
SELECT null,'2017','12','12','信用卡','私人','迷你榨汁机','1','58',null
UNION ALL
SELECT null,'2017','12','12','信用卡','私人','全棉磨毛四件套','1','624.1',null
UNION ALL
SELECT null,'2017','12','12','信用卡','私人','猫砂','1','143.1',null
UNION ALL
SELECT null,'2017','12','12','信用卡','私人','天然矿泉水','1','238.2',null
UNION ALL
SELECT null,'2017','12','12','信用卡','私人','猫粮','1','38.6',null
UNION ALL
SELECT null,'2017','12','12','信用卡','私人','一次性旅行用品','1','185.6',null
UNION ALL
SELECT null,'2017','12','12','信用卡','私人','折叠儿童三轮车','1','298',null
UNION ALL
SELECT null,'2017','12','12','信用卡','私人','抽屉垫橱柜垫铝箔纸','1','31.5',null
UNION ALL
SELECT null,'2017','12','14','信用卡','私人','秋冬天兔毛帽子 ','1','32',null
UNION ALL
SELECT null,'2017','12','16','信用卡','私人','百草味-牛扎奶芙160gx2袋','1','18.9',null
UNION ALL
SELECT null,'2017','12','16','信用卡','私人','GP超霸23A 12V电池','1','6.9',null
UNION ALL
SELECT null,'2017','12','18','信用卡','私人','黑苦荞茶','1','38.6',null
UNION ALL
SELECT null,'2017','12','20','信用卡','私人','宝宝湿巾纸','1','19.9',null
UNION ALL
SELECT null,'2017','12','22','信用卡','私人','法兰绒床笠','1','149',null
UNION ALL
SELECT null,'2017','12','22','信用卡','私人','医级用甘油','1','20',null
UNION ALL
SELECT null,'2017','12','26','信用卡','私人','高弹力打底裤','1','49.9',null
UNION ALL
SELECT null,'2017','12','27','信用卡','私人','针织打底衫','1','158',null
UNION ALL
SELECT null,'2017','12','30','信用卡','私人','猫咪用品','1','47.4',null
UNION ALL
SELECT null,'2017','12','30','信用卡','私人','加绒加厚打底裤','1','49',null
UNION ALL
SELECT null,'2017','12','30','信用卡','私人','被子被套固定器','1','60.5',null;








