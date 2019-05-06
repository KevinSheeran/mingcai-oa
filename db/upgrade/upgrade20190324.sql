CREATE TABLE `oa_eos_flow` (
  `id` varchar(64) COLLATE utf8_bin NOT NULL COMMENT 'id',
  `eos_id` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '创建项id',
  `user_id` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '审批者id',
  `status` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '审批状态(0,1完成,-1驳回)',
  `content` varchar(3000) COLLATE utf8_bin DEFAULT NULL COMMENT '审批者意见，建议',
  `order` int(6) DEFAULT NULL COMMENT '流程排序',
  `serial_number` int(6) DEFAULT NULL COMMENT '流程序号',
  `create_by` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `send_status` int(10) DEFAULT NULL COMMENT '消息推送状态 0未推送，1送达',
  `send_content` varchar(5000) COLLATE utf8_bin DEFAULT NULL COMMENT '推送文本内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='流程';
CREATE TABLE `oa_eos_flow_item` (
  `id` varchar(64) COLLATE utf8_bin NOT NULL COMMENT 'id',
  `flow_id` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '创建项id',
  `user_id` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '审批者id',
  `status` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '审批状态(0,1完成,-1驳回)',
  `content` varchar(3000) COLLATE utf8_bin DEFAULT NULL COMMENT '审批者意见，建议',
  `order` int(6) DEFAULT NULL COMMENT '流程排序',
  `serial_number` int(6) DEFAULT NULL COMMENT '流程序号',
  `create_by` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `send_status` int(10) DEFAULT NULL COMMENT '消息推送状态 0未推送，1送达',
  `send_content` varchar(5000) COLLATE utf8_bin DEFAULT NULL COMMENT '推送文本内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='流程项';
CREATE TABLE `oa_eos_flow_logo` (
  `id` varchar(64) COLLATE utf8_bin NOT NULL COMMENT 'id',
  `item_id` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '流程项id',
  `user_id` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '审批者id',
  `status` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '审批状态(0,1完成,-1驳回)',
  `content` varchar(3000) COLLATE utf8_bin DEFAULT NULL COMMENT '审批者意见，建议',
  `create_by` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `send_status` int(10) DEFAULT NULL COMMENT '消息推送状态 0未推送，1送达',
  `send_content` varchar(5000) COLLATE utf8_bin DEFAULT NULL COMMENT '推送文本内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='流程操作日志';
CREATE TABLE `oa_eos_flows` (
  `id` varchar(64) COLLATE utf8_bin NOT NULL COMMENT 'id',
  `name` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '流程名',
  `type` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '类型，1.预立项，2.报销。3.立项，4.',
  `status` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '审批状态(0,1完成,-1驳回)',
  `serial_number` int(6) DEFAULT NULL COMMENT '流程数',
  `create_by` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `is_send_user` bigint(2) DEFAULT NULL COMMENT '是否发送微信通知',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='流程';
CREATE TABLE `oa_eos_pro` (
  `id` varchar(64) COLLATE utf8_bin NOT NULL COMMENT 'id',
  `name` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '项目名称',
  `flow_id` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '流程id',
  `pa_number` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '预立项编号',
  `pro_number` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '立项编号',
  `person_liable_user` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '责任销售',
  `customer_name` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '客户名称',
  `customer_user` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '客户方负责人',
  `customer_contact_information` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '客户负责人联系方式',
  `status` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '立项状态（-2，申请驳回，-1，立项申请取消，0，预申请取消，1.预立项，2.预立项申请，3.预立项申请完成，4.立项申请审批，5.立项完成）',
  `pro_type` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '项目类型',
  `pro_location` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '客户/项目定位(客户是否为行业领先地位，项目是否为标杆项目）',
  `pro_capital_source` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '资金来源（1.全部落实，2.部分落实，3.未落实）',
  `pro_budget` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '资金预算（1.全部落实，2.部分落实，3.未落实）',
  `pro_cycle` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '周期（1.已确定，2.不确定）',
  `pro_content` varchar(2000) COLLATE utf8_bin DEFAULT NULL COMMENT '项目主要交付内容',
  `estimated_amount` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '项目预估金额',
  `gross_profit_margin` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '毛利率百分比',
  `input_estimation` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '前期投入估算',
  `workload` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '前期预估工作量（人/天）',
  `user_ids` varchar(3000) COLLATE utf8_bin DEFAULT NULL COMMENT '支持人员ids',
  `to_examine` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '管理，财务审核状态0,1完成',
  `approval` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '审批状态0,1完成',
  `create_by` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='销售项目立项';
CREATE TABLE `oa_eos_pro_cplan` (
  `id` varchar(64) COLLATE utf8_bin NOT NULL COMMENT 'id',
  `eos_id` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '项目id',
  `stage` int(10) DEFAULT NULL COMMENT '阶段',
  `cplan_content` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '收款类型计划',
  `receivables_date` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '收款时间计划',
  `finish_date` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '实际收款时间',
  `proportion` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '收款比例',
  `status` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '是否完成默认0,1.完成,-1.延期完成',
  `create_by` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` char(1) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='项目收款计划';
CREATE TABLE `oa_eos_pro_item` (
  `id` varchar(64) COLLATE utf8_bin NOT NULL COMMENT 'id',
  `eos_id` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '项目id',
  `eos_z_id` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '子项目id',
  `name` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '名称',
  `pro_cycle` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '周期（1.已确定，2.不确定）',
  `person_liable_user` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '负责人',
  `pro_content` varchar(2000) COLLATE utf8_bin DEFAULT NULL COMMENT '项目主要交付内容',
  `estimated_amount` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '预估金额',
  `gross_profit_margin` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '毛利率百分比',
  `input_estimation` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '投入估算',
  `workload_time` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '预估完成时间',
  `user_ids` varchar(3000) COLLATE utf8_bin DEFAULT NULL COMMENT '支持人员ids',
  `create_by` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` char(1) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='销售项目子项';

CREATE TABLE `oa_eos_pro_presentation` (
  `id` varchar(64) COLLATE utf8_bin NOT NULL COMMENT 'id',
  `eos_id` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '项目id',
  `summary` varchar(5000) COLLATE utf8_bin DEFAULT NULL COMMENT '总结',
  `next_plan` varchar(5000) COLLATE utf8_bin DEFAULT NULL COMMENT '下周计划',
  `problem_handle` varchar(5000) COLLATE utf8_bin DEFAULT NULL COMMENT '问题处理',
  `proposal` varchar(5000) COLLATE utf8_bin DEFAULT NULL COMMENT '建议',
  `start_end` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '时间段',
  `create_by` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` char(1) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='项目报告';
CREATE TABLE `oa_eos_pro_start` (
  `id` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '项目id',
  `flow_id` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '流程id',
  `person_liable_user` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '项目经理',
  `contract_amount` decimal(19,2) DEFAULT NULL COMMENT '合同金额',
  `estimated_amount` decimal(19,2) DEFAULT NULL COMMENT '预算金额',
  `gross_profit_margin` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '利润%',
  `status` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '立项状态(默认0,1.申请中，2.通过.-1驳回)',
  `user_ids` varchar(3000) COLLATE utf8_bin DEFAULT NULL COMMENT '支持人员ids',
  `budgetary` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '预算变更默认0,1.变更',
  `deferred` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '延期变更默认0,1.变更',
  `progress` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '项目进展，0.按计划进行，1.比计划提前，2.落后计划',
  `create_by` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` char(1) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='项目启动';
CREATE TABLE `oa_eos_pro_start_item` (
  `id` varchar(64) COLLATE utf8_bin NOT NULL COMMENT 'id',
  `eos_id` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '项目id',
  `code` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '编号',
  `name` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '名称',
  `person_liable_user` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '负责人',
  `pro_content` varchar(2000) COLLATE utf8_bin DEFAULT NULL COMMENT '项目主要交付内容',
  `estimated_amount` decimal(19,2) DEFAULT NULL COMMENT '预算金额',
  `input_estimation` decimal(19,2) DEFAULT NULL COMMENT '投入金额',
  `workload_time` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '预估完成时间',
  `deviation_time` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '预估偏差时间(天)',
  `actual_time` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '实际完成时间',
  `status` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '完成状态默认0,1.完成',
  `create_by` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` char(1) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='立项子项目';
CREATE TABLE `oa_wx_extended` (
  `id` varchar(64) COLLATE utf8_bin NOT NULL COMMENT 'id',
  `cost` decimal(10,2) DEFAULT NULL COMMENT '报销金额',
  `state` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '状态(-1已驳回，0默认(未审核)，1审核通过)',
  `exte_super` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '主表id',
  `content` varchar(1000) COLLATE utf8_bin DEFAULT NULL COMMENT '报销内容',
  `pro_id` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '子项id',
  `create_by` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '描述信息',
  `del_flag` char(1) COLLATE utf8_bin DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='报销详情表';
CREATE TABLE `oa_wx_extended_super` (
  `id` varchar(64) COLLATE utf8_bin NOT NULL COMMENT 'id',
  `state` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '状态(-1已驳回，0默认(未审核)，1审核通过)',
  `flow_id` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '流程id',
  `pro_id` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '项目',
  `appropriation` varchar(100) COLLATE utf8_bin DEFAULT '0' COMMENT '确定财务是否拨款（-1审核未通过，0未拨款,1已拨款）',
  `enclosure` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '附件',
  `create_by` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '描述信息',
  `del_flag` char(1) COLLATE utf8_bin DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='报销表';
CREATE TABLE `unity_log` (
  `id` varchar(64) COLLATE utf8_bin NOT NULL,
  `unityTag` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '数据库表名',
  `unityTagId` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '表id',
  `unityOperate` varchar(40) COLLATE utf8_bin DEFAULT NULL COMMENT '操作类型',
  `unityMatter` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '操作事项',
  `unityOperator` varchar(200) COLLATE utf8_bin NOT NULL COMMENT '操作人',
  `unityOperateTime` datetime NOT NULL COMMENT '操作时间',
  `unityContent` varchar(2000) COLLATE utf8_bin DEFAULT NULL COMMENT '操作内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='表操作日志';
INSERT INTO `mingcai-oa`.`sys_dict` (`id`,`value`,`label`,`type`,`description`,`sort`,`parent_id`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`) VALUES('1001','1', '预立项', 'eos_status','项目状态','10','0','1','2019-03-07 16:19:21','1','2019-03-07 16:19:21','','0') ;
INSERT INTO `mingcai-oa`.`sys_dict` (`id`,`value`,`label`,`type`,`description`,`sort`,`parent_id`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`) VALUES('1002','2', '预立项申请', 'eos_status','项目状态','20','0','1','2019-03-07 16:19:21','1','2019-03-07 16:19:21','','0') ;
INSERT INTO `mingcai-oa`.`sys_dict` (`id`,`value`,`label`,`type`,`description`,`sort`,`parent_id`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`) VALUES('1003','3', '预立项申请完成', 'eos_status','项目状态','30','0','1','2019-03-07 16:19:21','1','2019-03-07 16:19:21','','0') ;
INSERT INTO `mingcai-oa`.`sys_dict` (`id`,`value`,`label`,`type`,`description`,`sort`,`parent_id`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`) VALUES('1004','4', '立项申请审核', 'eos_status','项目状态','40','0','1','2019-03-07 16:19:21','1','2019-03-07 16:19:21','','0') ;
INSERT INTO `mingcai-oa`.`sys_dict` (`id`,`value`,`label`,`type`,`description`,`sort`,`parent_id`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`) VALUES('1005','5', '立项申请审批', 'eos_status','项目状态','50','0','1','2019-03-07 16:19:21','1','2019-03-07 16:19:21','','0') ;
INSERT INTO `mingcai-oa`.`sys_dict` (`id`,`value`,`label`,`type`,`description`,`sort`,`parent_id`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`) VALUES('1006','6', '立项完成', 'eos_status','项目状态','60','0','1','2019-03-07 16:19:21','1','2019-03-07 16:19:21','','0') ;
INSERT INTO `mingcai-oa`.`sys_dict` (`id`,`value`,`label`,`type`,`description`,`sort`,`parent_id`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`) VALUES('1007','1', '大型项目（500万以上）', 'eos_type','项目类型','10','0','1','2019-03-07 16:19:21','1','2019-03-07 16:19:21','','0') ;
INSERT INTO `mingcai-oa`.`sys_dict` (`id`,`value`,`label`,`type`,`description`,`sort`,`parent_id`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`) VALUES('1008','2', '中型项目（100万-500万）', 'eos_type','项目类型','20','0','1','2019-03-07 16:19:21','1','2019-03-07 16:19:21','','0') ;
INSERT INTO `mingcai-oa`.`sys_dict` (`id`,`value`,`label`,`type`,`description`,`sort`,`parent_id`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`) VALUES('1009','3', '大型项目（100万以下）', 'eos_type','项目类型','30','0','1','2019-03-07 16:19:21','1','2019-03-07 16:19:21','','0') ;

INSERT INTO `mingcai-oa`.`sys_dict` (`id`,`value`,`label`,`type`,`description`,`sort`,`parent_id`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`) VALUES('1010','1', '全部落实', 'eos_source','资金来源','10','0','1','2019-03-07 16:19:21','1','2019-03-07 16:19:21','','0') ;
INSERT INTO `mingcai-oa`.`sys_dict` (`id`,`value`,`label`,`type`,`description`,`sort`,`parent_id`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`) VALUES('1011','2', '部分落实', 'eos_source','资金来源','20','0','1','2019-03-07 16:19:21','1','2019-03-07 16:19:21','','0') ;
INSERT INTO `mingcai-oa`.`sys_dict` (`id`,`value`,`label`,`type`,`description`,`sort`,`parent_id`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`) VALUES('1012','3', '未落实', 'eos_source','资金来源','30','0','1','2019-03-07 16:19:21','1','2019-03-07 16:19:21','','0') ;

INSERT INTO `mingcai-oa`.`sys_dict` (`id`,`value`,`label`,`type`,`description`,`sort`,`parent_id`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`) VALUES('1013','1', '全部落实', 'eos_budget','资金预算','10','0','1','2019-03-07 16:19:21','1','2019-03-07 16:19:21','','0') ;
INSERT INTO `mingcai-oa`.`sys_dict` (`id`,`value`,`label`,`type`,`description`,`sort`,`parent_id`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`) VALUES('1014','2', '部分落实', 'eos_budget','资金预算','20','0','1','2019-03-07 16:19:21','1','2019-03-07 16:19:21','','0') ;
INSERT INTO `mingcai-oa`.`sys_dict` (`id`,`value`,`label`,`type`,`description`,`sort`,`parent_id`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`) VALUES('1015','3', '未落实', 'eos_budget','资金预算','30','0','1','2019-03-07 16:19:21','1','2019-03-07 16:19:21','','0') ;

INSERT INTO `mingcai-oa`.`sys_dict` (`id`,`value`,`label`,`type`,`description`,`sort`,`parent_id`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`) VALUES('1016','1', '已确定', 'eos_cycle','项目周期','10','0','1','2019-03-07 16:19:21','1','2019-03-07 16:19:21','','0') ;
INSERT INTO `mingcai-oa`.`sys_dict` (`id`,`value`,`label`,`type`,`description`,`sort`,`parent_id`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`) VALUES('1017','2', '不确定', 'eos_cycle','项目周期','20','0','1','2019-03-07 16:19:21','1','2019-03-07 16:19:21','','0') ;


INSERT INTO `mingcai-oa`.`sys_dict` (`id`,`value`,`label`,`type`,`description`,`sort`,`parent_id`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`) VALUES('1018','1', '未立项', 'oa_eos_pro_status','立项目状态','10','0','1','2019-03-07 16:19:21','1','2019-03-07 16:19:21','','0') ;
INSERT INTO `mingcai-oa`.`sys_dict` (`id`,`value`,`label`,`type`,`description`,`sort`,`parent_id`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`) VALUES('1019','2', '预立项申请中', 'oa_eos_pro_status','立项目状态','20','0','1','2019-03-07 16:19:21','1','2019-03-07 16:19:21','','0') ;
INSERT INTO `mingcai-oa`.`sys_dict` (`id`,`value`,`label`,`type`,`description`,`sort`,`parent_id`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`) VALUES('1020','3', '预立项完成', 'oa_eos_pro_status','立项目状态','30','0','1','2019-03-07 16:19:21','1','2019-03-07 16:19:21','','0') ;
INSERT INTO `mingcai-oa`.`sys_dict` (`id`,`value`,`label`,`type`,`description`,`sort`,`parent_id`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`) VALUES('1021','4', '立项审批中', 'oa_eos_pro_status','立项目状态','40','0','1','2019-03-07 16:19:21','1','2019-03-07 16:19:21','','0') ;
INSERT INTO `mingcai-oa`.`sys_dict` (`id`,`value`,`label`,`type`,`description`,`sort`,`parent_id`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`) VALUES('1022','5', '立项完成', 'oa_eos_pro_status','立项目状态','50','0','1','2019-03-07 16:19:21','1','2019-03-07 16:19:21','','0') ;
INSERT INTO `mingcai-oa`.`sys_dict` (`id`,`value`,`label`,`type`,`description`,`sort`,`parent_id`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`) VALUES('1023','0', '预立项申请取消', 'oa_eos_pro_status','立项目状态','60','0','1','2019-03-07 16:19:21','1','2019-03-07 16:19:21','','0') ;
INSERT INTO `mingcai-oa`.`sys_dict` (`id`,`value`,`label`,`type`,`description`,`sort`,`parent_id`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`) VALUES('1024','-1', '立项申请取消', 'oa_eos_pro_status','立项目状态','70','0','1','2019-03-07 16:19:21','1','2019-03-07 16:19:21','','0') ;
INSERT INTO `mingcai-oa`.`sys_dict` (`id`,`value`,`label`,`type`,`description`,`sort`,`parent_id`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`) VALUES('1025','-2', '立项申请驳回', 'oa_eos_pro_status','立项目状态','80','0','1','2019-03-07 16:19:21','1','2019-03-07 16:19:21','','0') ;


INSERT INTO `mingcai-oa`.`sys_dict` (`id`,`value`,`label`,`type`,`description`,`sort`,`parent_id`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`) VALUES('1026','0', '未立项', 'pro_start_status','立项目状态','60','0','1','2019-03-07 16:19:21','1','2019-03-07 16:19:21','','0') ;
INSERT INTO `mingcai-oa`.`sys_dict` (`id`,`value`,`label`,`type`,`description`,`sort`,`parent_id`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`) VALUES('1027','-1', '申请驳回', 'pro_start_status','立项目状态','70','0','1','2019-03-07 16:19:21','1','2019-03-07 16:19:21','','0') ;
INSERT INTO `mingcai-oa`.`sys_dict` (`id`,`value`,`label`,`type`,`description`,`sort`,`parent_id`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`) VALUES('1028','1', '立项申请中', 'pro_start_status','立项目状态','80','0','1','2019-03-07 16:19:21','1','2019-03-07 16:19:21','','0') ;
INSERT INTO `mingcai-oa`.`sys_dict` (`id`,`value`,`label`,`type`,`description`,`sort`,`parent_id`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`) VALUES('1029','2', '立项完成', 'pro_start_status','立项目状态','90','0','1','2019-03-07 16:19:21','1','2019-03-07 16:19:21','','0') ;

INSERT INTO `mingcai-oa`.`sys_dict` (`id`,`value`,`label`,`type`,`description`,`sort`,`parent_id`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`) VALUES('1030','0', '按计划进行', 'pro_start_progress','项目进展','10','0','1','2019-03-07 16:19:21','1','2019-03-07 16:19:21','','0') ;
INSERT INTO `mingcai-oa`.`sys_dict` (`id`,`value`,`label`,`type`,`description`,`sort`,`parent_id`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`) VALUES('1031','1', '比计划提前', 'pro_start_progress','项目进展','20','0','1','2019-03-07 16:19:21','1','2019-03-07 16:19:21','','0') ;
INSERT INTO `mingcai-oa`.`sys_dict` (`id`,`value`,`label`,`type`,`description`,`sort`,`parent_id`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`) VALUES('1032','2', '落后计划', 'pro_start_progress','项目进展','30','0','1','2019-03-07 16:19:21','1','2019-03-07 16:19:21','','0') ;

INSERT INTO `mingcai-oa`.`sys_dict` (`id`,`value`,`label`,`type`,`description`,`sort`,`parent_id`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`) VALUES('1033','0', '收款中', 'pro_plan_start','收款状态','10','0','1','2019-03-07 16:19:21','1','2019-03-07 16:19:21','','0') ;
INSERT INTO `mingcai-oa`.`sys_dict` (`id`,`value`,`label`,`type`,`description`,`sort`,`parent_id`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`) VALUES('1034','1', '收款完成', 'pro_plan_start','收款状态','20','0','1','2019-03-07 16:19:21','1','2019-03-07 16:19:21','','0') ;
INSERT INTO `mingcai-oa`.`sys_dict` (`id`,`value`,`label`,`type`,`description`,`sort`,`parent_id`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`) VALUES('1035','-1', '延期完成', 'pro_plan_start','收款状态','30','0','1','2019-03-07 16:19:21','1','2019-03-07 16:19:21','','0') ;