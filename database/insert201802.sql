#=CONCATENATE("SELECT null,'2018','02','",A3,"','现金','私人','",B3,"','1','",C3,"',null",CHAR(10),"UNION ALL")
INSERT INTO accounts (orderid,year,month,day,paymethod,isprivate,itemname,itemnumber,price,remarks)
SELECT null,'2018','02','25','现金','私人','刘群3月养老费','1','4101.3',null
UNION ALL
SELECT null,'2018','02','25','现金','私人','平型关路2月水费','1','41.4',null
UNION ALL
SELECT null,'2018','02','25','现金','私人','平型关路2月电费','1','276.5',null
UNION ALL
SELECT null,'2018','02','25','现金','私人','平型关路2月燃气费','1','111',null
UNION ALL
SELECT null,'2018','02','25','现金','私人','12201 2月水费','1','58.7',null
UNION ALL
SELECT null,'2018','02','25','现金','私人','12201 2月电费','1','507.2',null
UNION ALL
SELECT null,'2018','02','25','现金','私人','12201 2月燃气费','1','75',null
UNION ALL
SELECT null,'2018','02','25','现金','私人','邢坤1月手机费','1','350',null
UNION ALL
SELECT null,'2018','02','01','信用卡','店内','网尚脆皮炸鸡粉','1','79.5',null
UNION ALL
SELECT null,'2018','02','02','信用卡','店内','折叠推拉门','1','493',null
UNION ALL
SELECT null,'2018','02','02','信用卡','店内','杰卡斯梅洛干红12瓶','1','830',null
UNION ALL
SELECT null,'2018','02','02','信用卡','店内','铂帝赤霞珠干红6瓶','1','1188',null
UNION ALL
SELECT null,'2018','02','02','信用卡','店内','复古led触摸充电台灯','1','155',null
UNION ALL
SELECT null,'2018','02','04','信用卡','店内','磁铁自吸软门帘','1','183',null
UNION ALL
SELECT null,'2018','02','06','信用卡','店内',' xo酱烤肉粒','1','211',null
UNION ALL
SELECT null,'2018','02','24','信用卡','店内','扑克','1','75',null
UNION ALL
SELECT null,'2018','02','07','信用卡','店内','黄飞鸿花生','1','620',null
UNION ALL
SELECT null,'2018','02','02','信用卡','私人','发饰头饰','1','54.8',null;






