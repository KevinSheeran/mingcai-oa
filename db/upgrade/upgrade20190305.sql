CREATE TABLE `oa_wx_department` (
  `id` VARCHAR(64) COLLATE utf8_bin NOT NULL COMMENT 'id',
  `name` VARCHAR(100) COLLATE utf8_bin DEFAULT NULL COMMENT '部门名称',
  `parentid` VARCHAR(64) COLLATE utf8_bin DEFAULT NULL COMMENT '父节点',
  `order` VARCHAR(64) COLLATE utf8_bin DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='微信部门';

CREATE TABLE `oa_wx_users` (
  `id` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '主键',
  `userid` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT 'id成员UserID。对应管理端的帐号，企业内必须唯一。不区分大小写，长度为1~64个字节',
  `name` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '成员名称',
  `mobile` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '手机号码，第三方仅通讯录应用可获取',
  `department` varchar(2000) COLLATE utf8_bin DEFAULT NULL COMMENT '成员所属部门id列表，仅返回该应用有查看权限的部门id',
  `order` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '部门内的排序值，默认为0。数量必须和department一致',
  `position` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '职务信息；第三方仅通讯录应用可获取',
  `gender` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '性别。0表示未定义，1表示男性，2表示女性',
  `email` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '邮箱，第三方仅通讯录应用可获取',
  `is_leader_in_dept` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '表示在所在的部门内是否为上级。；第三方仅通讯录应用可获取',
  `avatar` varchar(1000) COLLATE utf8_bin DEFAULT NULL COMMENT '头像url。注：如果要获取小图将url最后的”/0”改成”/100”即可。第三方仅通讯录应用可获取',
  `telephone` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '座机。第三方仅通讯录应用可获取',
  `enable` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '成员启用状态。1表示启用的成员，0表示被禁用。注意，服务商调用接口不会返回此字段',
  `alias` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '别名；第三方仅通讯录应用可获取',
  `status` varchar(6) COLLATE utf8_bin DEFAULT NULL COMMENT '激活状态: 1=已激活，2=已禁用，4=未激活。已激活代表已激活企业微信或已关注微工作台（原企业号）。未激活代表既未激活企业微信又未关注微工作台（原企业号）。',
  `qr_code` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '员工个人二维码，扫描可添加为外部联系人；第三方仅通讯录应用可获取',
  `external_position` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '对外职务',
  `address` varchar(300) COLLATE utf8_bin DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='微信用户';
CREATE TABLE `oa_wx_department_users` (
  `userid` VARCHAR(64) COLLATE utf8_bin NOT NULL COMMENT '用户id',
  `id` VARCHAR(100) COLLATE utf8_bin DEFAULT NULL COMMENT '部门id',
  `is_leader_in_dept` INT(4) COLLATE utf8_bin DEFAULT NULL COMMENT '表示在所在的部门内是否为上级。；第三方仅通讯录应用可获取',
  `order` INT(4) COLLATE utf8_bin DEFAULT NULL COMMENT '排序'
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='微信部门用户';