alter table oa_finance_company column `area_id` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '区域id';
CREATE TABLE `oa_crm_customer` (
  `id` varchar(64) COLLATE utf8_bin NOT NULL COMMENT 'id',
  `name` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '单位名称',
  `phone` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '联系电话',
  `post` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '职务',
  `create_by` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '描述信息',
  `del_flag` char(1) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '删除标记',
  `company_id` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '单位',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='联系人';
CREATE TABLE `oa_crm_product` (
      `id` VARCHAR(64) COLLATE utf8_bin NOT NULL COMMENT 'id',
      `name` VARCHAR(100) COLLATE utf8_bin NOT NULL COMMENT '名称',
      `customer_ids` VARCHAR(2000) COLLATE utf8_bin NOT NULL COMMENT '客户ids',
      `status` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '状态：1.未跟进，2.跟进中，3.公开，4.申请跟进中,5.申请立项中,6.立项',
      `create_by` VARCHAR(64) COLLATE utf8_bin NOT NULL COMMENT '创建者',
      `create_date` DATETIME NOT NULL COMMENT '创建时间',
      `update_by` VARCHAR(64) COLLATE utf8_bin NOT NULL COMMENT '更新者',
      `update_date` DATETIME NOT NULL COMMENT '更新时间',
      `remarks` VARCHAR(255) COLLATE utf8_bin DEFAULT NULL COMMENT '描述信息',
      `del_flag` CHAR(1) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '删除标记' ,
      PRIMARY KEY (`id`)
    ) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='项目报备' ;
CREATE TABLE `oa_crm_product_ing` (
    `id` varchar(64) COLLATE utf8_bin NOT NULL COMMENT 'id',
    `type` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '拜访方式：1.电话，2.面见，视频',
    `create_by` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '创建者',
    `create_date` datetime NOT NULL COMMENT '创建时间',
    `update_date` datetime NOT NULL COMMENT '拜访时间',
    `remarks` varchar(3000) COLLATE utf8_bin DEFAULT NULL COMMENT '拜访内容',
    `del_flag` char(1) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '删除标记',
    `pid` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '项目id',
    `visit_time` datetime DEFAULT NULL COMMENT '拜访时间',
    PRIMARY KEY (`id`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='项目跟进情况';


insert into `sys_menu` (`id`, `parent_id`, `parent_ids`, `name`, `sort`, `href`, `target`, `icon`, `is_show`, `permission`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`, `css_style`, `css_color`, `menu_type`) values('0fe5159cca9848599b67fb86358d8403','4f718ea1c5de41cd91f92e489bd99b73','0,1,1c6f58f2c0364db3b86df1e223fdfdf4,4f718ea1c5de41cd91f92e489bd99b73,','审核权限','30','','','','0','oa:crm:oaCrmProduct:apply','1','2018-02-08 17:35:47','1','2018-02-08 17:35:47','','0','','','1');
insert into `sys_menu` (`id`, `parent_id`, `parent_ids`, `name`, `sort`, `href`, `target`, `icon`, `is_show`, `permission`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`, `css_style`, `css_color`, `menu_type`) values('1c6f58f2c0364db3b86df1e223fdfdf4','1','0,1,','CRM','5270','','','','1','','1','2018-02-07 15:55:17','1','2018-02-07 15:55:17','','0','','','4');
insert into `sys_menu` (`id`, `parent_id`, `parent_ids`, `name`, `sort`, `href`, `target`, `icon`, `is_show`, `permission`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`, `css_style`, `css_color`, `menu_type`) values('386a40e0c35d4519bd0269373346fa58','1c6f58f2c0364db3b86df1e223fdfdf4','0,1,1c6f58f2c0364db3b86df1e223fdfdf4,','单位联系人','4','/oa/crm/oaCrmCustomer','','/static/windowsstyle/menuIcon/icon3.png','1','oa:crm:oaCrmCustomer:view','1','2018-02-07 15:59:58','1','2018-02-07 16:33:47','','0','g150x75','bg2','4');
insert into `sys_menu` (`id`, `parent_id`, `parent_ids`, `name`, `sort`, `href`, `target`, `icon`, `is_show`, `permission`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`, `css_style`, `css_color`, `menu_type`) values('3d9962dd32844cebbac4817da5a4ebf6','1c6f58f2c0364db3b86df1e223fdfdf4','0,1,1c6f58f2c0364db3b86df1e223fdfdf4,','项目报备','1','/oa/crm/oaCrmProduct','','/static/windowsstyle/menuIcon/icon9.png','1','oa:crm:oaCrmProduct:view','1','2018-02-07 16:01:38','1','2018-02-07 16:32:34','','0','g150x75','bg1','4');
insert into `sys_menu` (`id`, `parent_id`, `parent_ids`, `name`, `sort`, `href`, `target`, `icon`, `is_show`, `permission`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`, `css_style`, `css_color`, `menu_type`) values('4f718ea1c5de41cd91f92e489bd99b73','1c6f58f2c0364db3b86df1e223fdfdf4','0,1,1c6f58f2c0364db3b86df1e223fdfdf4,','申请审批','5','/oa/crm/oaCrmProduct/applyList','','/static/windowsstyle/menuIcon/icon2.png','1','oa:crm:oaCrmProduct:view','1','2018-02-08 17:13:36','1','2018-02-08 17:13:56','','0','g150x75','bg4','4');
insert into `sys_menu` (`id`, `parent_id`, `parent_ids`, `name`, `sort`, `href`, `target`, `icon`, `is_show`, `permission`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`, `css_style`, `css_color`, `menu_type`) values('6542b0987530435aab40952e24de7081','1c6f58f2c0364db3b86df1e223fdfdf4','0,1,1c6f58f2c0364db3b86df1e223fdfdf4,','项目跟进','1','/oa/crm/oaCrmProductIng','','','0','oa:crm:oaCrmProductIng:view','1','2018-02-07 16:03:11','1','2018-02-07 16:33:18','','0','','','1');
insert into `sys_menu` (`id`, `parent_id`, `parent_ids`, `name`, `sort`, `href`, `target`, `icon`, `is_show`, `permission`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`, `css_style`, `css_color`, `menu_type`) values('6d1635b27d1e4ea9ab09d54e89017fc7','6542b0987530435aab40952e24de7081','0,1,1c6f58f2c0364db3b86df1e223fdfdf4,6542b0987530435aab40952e24de7081,','查看','60','','','','0','oa:crm:oaCrmProductIng:view','1','2018-02-07 16:06:02','1','2018-02-07 16:33:37','','0','','','1');
insert into `sys_menu` (`id`, `parent_id`, `parent_ids`, `name`, `sort`, `href`, `target`, `icon`, `is_show`, `permission`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`, `css_style`, `css_color`, `menu_type`) values('6f667fd4ef674cc6934257352d74ced2','386a40e0c35d4519bd0269373346fa58','0,1,1c6f58f2c0364db3b86df1e223fdfdf4,386a40e0c35d4519bd0269373346fa58,','修改','30','','','','0','oa:crm:oaCrmCustomer:edit','1','2018-02-07 16:00:51','1','2018-02-08 17:00:11','','0','','','1');
insert into `sys_menu` (`id`, `parent_id`, `parent_ids`, `name`, `sort`, `href`, `target`, `icon`, `is_show`, `permission`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`, `css_style`, `css_color`, `menu_type`) values('7356987e67e24977a84c2fa409dba59c','3d9962dd32844cebbac4817da5a4ebf6','0,1,1c6f58f2c0364db3b86df1e223fdfdf4,3d9962dd32844cebbac4817da5a4ebf6,','修改','30','','','','0','oa:crm:oaCrmProduct:edit','1','2018-02-07 16:02:14','1','2018-02-07 16:32:52','','0','','','4');
insert into `sys_menu` (`id`, `parent_id`, `parent_ids`, `name`, `sort`, `href`, `target`, `icon`, `is_show`, `permission`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`, `css_style`, `css_color`, `menu_type`) values('a54bc913a75c4d3490f388bfcb7bd096','6542b0987530435aab40952e24de7081','0,1,1c6f58f2c0364db3b86df1e223fdfdf4,6542b0987530435aab40952e24de7081,','修改','30','','','','0','oa:crm:oaCrmProductIng:edit','1','2018-02-07 16:03:31','1','2018-02-07 16:33:27','','0','','','1');
insert into `sys_menu` (`id`, `parent_id`, `parent_ids`, `name`, `sort`, `href`, `target`, `icon`, `is_show`, `permission`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`, `css_style`, `css_color`, `menu_type`) values('e3dae9d421904e8ca6c8833815b7fcb9','386a40e0c35d4519bd0269373346fa58','0,1,1c6f58f2c0364db3b86df1e223fdfdf4,386a40e0c35d4519bd0269373346fa58,','查看','60','','','','0','oa:crm:oaCrmCustomer:view','1','2018-02-07 16:06:28','1','2018-02-07 16:34:03','','0','','','1');
insert into `sys_menu` (`id`, `parent_id`, `parent_ids`, `name`, `sort`, `href`, `target`, `icon`, `is_show`, `permission`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`, `css_style`, `css_color`, `menu_type`) values('ec6f8556384b4fa883e973075d65f571','3d9962dd32844cebbac4817da5a4ebf6','0,1,1c6f58f2c0364db3b86df1e223fdfdf4,3d9962dd32844cebbac4817da5a4ebf6,','查看','60','','','','0','oa:crm:oaCrmProduct:view','1','2018-02-07 16:05:38','1','2018-02-07 16:33:02','','0','','','1');
