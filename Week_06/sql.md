 CREATE TABLE tbl_order_master(
	orderID bigint(20) NOT NULL AUTO_INCREMENT COMMENT '订单ID', 
	orderKey varchar(50) NOT NULL DEFAULT '' COMMENT '订单号',
	goodsAmount decimal(12,2) NOT NULL DEFAULT '0.00' COMMENT '商品金额',
	orderStatus int NOT NULL DEFAULT 0 COMMENT '订单状态',
	userID bigint(20) NOT NULL DEFAULT 0 COMMENT '用户ID',
	shopID bigint(20) NOT NULL DEFAULT 0 COMMNET '店铺ID',
	deliveryAdress varchar(100) NOT NULL DEFAULT '' COMMENT '收货地址',
	deliveryName varchar(20) NOT NULL DEFAULT '' COMMENT '收货人',
	deliveryTel varchar(11) NOT NULL DEFAULT '' COMMENT '收货人手机号',
	expressAmount decimal(12,2) NOT NULL DEFAULT '0.00' COMMENT '快递费', 
	orderTotalAmount decimal(12,2) NOT NULL DEFAULT '0.00' COMMENT '订单总金额',
	creatTime bigint(20) NOT NULL DEFAULT 0 COMMENT '创建时间',
	actionTime bigint(20) NOT NULL DEFAULT 0 COMMENT '修改时间',
	PRIMARY KEY (`orderID`),
	KEY `idx_userID` (`userID`)
) CHARSET=utf8mb4 COMMENT='订单主表';

CREATE TABLE tbl_order_item(
	itemID bigint(20) NOT NULL AUTO_INCREMENT COMMENT '明细ID', 
	orderKey varchar(50) NOT NULL DEFAULT '' COMMENT '订单号',
	goodsID bigint(20) NOT NULL DEFAULT 0 COMMNET '商品ID',
	goodsName varchar(50) NOT NULL DEFAULT 0 COMMNET '商品名称',
	price  decimal(12,2) NOT NULL DEFAULT '0.00' COMMENT '单价',
	goodsCount int DEFAULT 0 COMMENT '商品数量',
	amount(12,2) NOT NULL DEFAULT '0.00' COMMENT '总金额',
    creatTime bigint(20) NOT NULL DEFAULT 0 COMMENT '创建时间',
	actionTime bigint(20) NOT NULL DEFAULT 0 COMMENT '修改时间',
	PRIMARY KEY (`itemID`),
	KEY `idx_orderKey` (`orderKey`),
	KEY `idx_goodsID` (`goodsID`)
) CHARSET=utf8mb4 COMMENT='订单明细表';


CREATE TABLE tbl_user(
	userID bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID', 
	userName varchar(50) NOT NULL DEFAULT '' COMMENT '用户名称',
	userTel varchar(11) NOT NULL DEFAULT '' COMMENT '手机号',
	nickName varchar(50) NOT NULL DEFAULT '' COMMENT '昵称',
  creatTime bigint(20) NOT NULL DEFAULT 0 COMMENT '创建时间',
	actionTime bigint(20) NOT NULL DEFAULT 0 COMMENT '修改时间',
	PRIMARY KEY (`userID`)
)CHARSET=utf8mb4 COMMENT='用户表';


CREATE TABLE tbl_goods(
	goodsID bigint(20) NOT NULL DEFAULT 0 COMMNET '商品ID',
	goodsName varchar(50) NOT NULL DEFAULT 0 COMMNET '商品名称',
	costPrice decimal(12,2) NOT NULL DEFAULT '0.00' COMMENT '成本价',
	salePrice decimal(12,2) NOT NULL DEFAULT '0.00' COMMENT '售价',
	description varchar(200) NOT NULL DEFAULT '' COMMENT '商品描述'
	creatTime bigint(20) NOT NULL DEFAULT 0 COMMENT '创建时间',
	actionTime bigint(20) NOT NULL DEFAULT 0 COMMENT '修改时间',
	PRIMARY KEY (`goodsID`),
)CHARSET=utf8mb4 COMMENT='商品表';