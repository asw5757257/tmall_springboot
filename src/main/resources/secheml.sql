create table user(
	id int(11) not null auto_increment,
	name varchar(255) default null,
	password varchar(255) default null,
	salt varchar(255) default null,
	primary key(id)
	)ENGINE=InnoDB DEFAULT CHARSET=utf8;
	
create table category(
	id int(11) not null auto_increment,
	name varchar(255) default null,
	primary key(id)
	)ENGINE=InnoDB DEFAULT CHARSET=utf8;
	
create table property(
	id int(11) not null auto_increment,
	cid int(11) default null,
	name varchar(255) default null,
	primary key (id),
	CONSTRAINT fk_property_category FOREIGN KEY(cid) REFERENCES category(id)
	)ENGINE=InnoDB DEFAULT CHARSET=utf8;
	
create table product(
	id int(11) not null auto_increment,
	name varchar(255) default null comment '产品名称',
	subTitle varchar(255) default null comment '小标题',
	originalPrice float default null comment '原始价格',
	promotePrice float default null comment '优惠价格',
	stock int(11) default null comment '库存',
	cid int(11) default null,
	createData datetime default null,
	primary key(id),
	CONSTRAINT fk_product_category FOREIGN KEY (cid) REFERENCES category (id)
	)ENGINE=InnoDB  DEFAULT CHARSET=utf8;

create table propertyvalue(
	id int(11) not null auto_increment,
	pid int(11) default null comment '指向产品表',
	ptid int(11) default null comment '指向属性表',
	value varchar(255) default null,
	primary key(id),
	CONSTRAINT fk_propertyvalue_property FOREIGN KEY (ptid) REFERENCES property (id),
	CONSTRAINT fk_propertyvalue_product FOREIGN KEY (pid) REFERENCES product (id)
	)ENGINE=InnoDB  DEFAULT CHARSET=utf8;
	
create table producttimage(
	id int(11) NOT NULL AUTO_INCREMENT,
	pid int(11) DEFAULT NULL comment '指向产品',
	type varchar(255) DEFAULT NULL,
	PRIMARY KEY (id),
	CONSTRAINT fk_productimage_product FOREIGN KEY (pid) REFERENCES product (id)
	) ENGINE=InnoDB DEFAULT CHARSET=utf8;
	
create table review(
	id int(11) NOT NULL AUTO_INCREMENT,
	content varchar(4000) DEFAULT NULL,
	uid int(11) DEFAULT NULL comment '指向用户表',
	pid int(11) DEFAULT NULL comment '指向产品表',
	createDate datetime DEFAULT NULL,
	PRIMARY KEY (id),
	CONSTRAINT fk_review_product FOREIGN KEY (pid) REFERENCES product (id),
    CONSTRAINT fk_review_user FOREIGN KEY (uid) REFERENCES user (id)
	)ENGINE=InnoDB  DEFAULT CHARSET=utf8;
	
create table order_(
	id int(11) not null auto_increment,
	orderCode varchar(255) default null comment '订单号',
	address varchar(255) default null comment '收货地址',
	post varchar(255) default null comment '邮编',
	receiver varchar(255) default null comment '收货人信息',
	mobile varchar(255) default null,
	userMessage varchar(255) default null comment '用户备注信息',
	createDate datetime default null,
	payDate datetime default null,
	deliveryDate datetime default null comment '发货日期',
	confirmDate datetime default null comment '确认收货日期',
	uid int(11) default null comment '指向用户表',
	status varchar(255) default null,
	primary key(id),
	CONSTRAINT fk_order_user FOREIGN KEY (uid) REFERENCES user (id)
	) ENGINE=InnoDB  DEFAULT CHARSET=utf8;
	
create table orderitem(
	id int(11) NOT NULL AUTO_INCREMENT,
	pid int(11) DEFAULT NULL comment '指向产品表',
	oid int(11) DEFAULT NULL comment '指向订单表',
	uid int(11) DEFAULT NULL comment '指向用户表',
	number int(11) DEFAULT NULL,
	PRIMARY KEY (id),
	CONSTRAINT fk_orderitem_user FOREIGN KEY (uid) REFERENCES user (id),
	CONSTRAINT fk_orderitem_product FOREIGN KEY (pid) REFERENCES product (id),
	CONSTRAINT fk_orderitem_order FOREIGN KEY (oid) REFERENCES order_ (id)
	) ENGINE=InnoDB  DEFAULT CHARSET=utf8;
	
	
	
	
	
	
