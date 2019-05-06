alter table oa_contract_supplier_product add column `enclosure_path` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '附件地址';
alter table oa_contract_supplier_product add column `labels` varchar(2000) COLLATE utf8_bin DEFAULT NULL COMMENT '标签id';
CREATE TABLE `oa_contract_supplier_product_type` (
  `id` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '编号',
  `name` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '名称',
  `create_by` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='供应商产品标签';
CREATE TABLE `oa_contract_supplier_files` (
  `id` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '编号',
  `name` varchar(200) COLLATE utf8_bin NOT NULL COMMENT '文件名称',
  `parent_id` varchar(200) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '父节点',
  `path` varchar(200) COLLATE utf8_bin NOT NULL COMMENT '文件路径',
  `type` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '文件类型 （供应商1，产品2）',
  `format` varchar(64) COLLATE utf8_bin DEFAULT '' COMMENT '文件格式',
  `fileSize` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '文件大小',
  `create_by` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='供应商,产品附件';