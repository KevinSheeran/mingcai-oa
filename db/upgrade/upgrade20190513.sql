CREATE TABLE `oa_proc_inventory` (
  `id` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '编号',
  `name` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '名称',
  `specifications` varchar(1000) COLLATE utf8_bin DEFAULT NULL COMMENT '规格',
  `type` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '产品类型，自有，开发，采购',
  `apply_id` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '申请单ID',
  `price` decimal(19,2) DEFAULT NULL COMMENT '单价',
  `num` double(10,2) DEFAULT NULL COMMENT '数量',
  `pro_id` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '项目ID',
  `pro_item_id` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '子项目ID',
  `create_by` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='采购清单表'



CREATE TABLE `oa_proc_applylist` (
  `id` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '编号',
  `pro_id` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '项目ID',
  `f_flow_id` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '第一个流程ID',
  `s_flow_id` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '第二个流程ID',
  `apply_user_id` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '请购人ID',
  `f_status` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '第一个人审批状态(默认0,1.申请中，2.通过.-1驳回)',
  `s_status` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '第二个人审批状态(默认0,1.申请中，2.通过.-1驳回)',
  `finance_status` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '财务审批状态(默认0,1.申请中，2.通过.-1驳回)',
  `create_by` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='采购申请单'
