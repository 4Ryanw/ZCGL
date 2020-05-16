/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : zcgl

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 15/05/2020 18:17:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_account
-- ----------------------------
DROP TABLE IF EXISTS `t_account`;
CREATE TABLE `t_account`  (
  `uuid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '随机唯一id,作为主键',
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账号',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `user_role` tinyint(2) NOT NULL COMMENT '角色,对应角色表中的id',
  `user_status` tinyint(1) NULL DEFAULT 1 COMMENT '账户状态,0代表停用,1代表启用',
  PRIMARY KEY (`uuid`) USING BTREE,
  UNIQUE INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `role_roleid`(`user_role`) USING BTREE,
  CONSTRAINT `role_roleid` FOREIGN KEY (`user_role`) REFERENCES `t_role` (`role_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_account
-- ----------------------------
INSERT INTO `t_account` VALUES ('1', 'lmz_954186', '123456', '刘明珠', 1, 1);
INSERT INTO `t_account` VALUES ('2fbbff5c-8e1d-11ea-bcc8-b82a72aaba54', 'timeManager_Luo', '123456', '罗志祥', 3, 1);
INSERT INTO `t_account` VALUES ('4e2d2647-7f2c-11ea-a038-00ffe0cb5768', '1109357', '123456', '张三', 3, 1);
INSERT INTO `t_account` VALUES ('586c4218-8e1d-11ea-bcc8-b82a72aaba54', 'dddsx_NMSL', '123456', '孙笑川', 3, 1);
INSERT INTO `t_account` VALUES ('737f5968-8e1d-11ea-bcc8-b82a72aaba54', 'yellowSkirt', '123456', '秦牛正威', 3, 1);
INSERT INTO `t_account` VALUES ('913ed87d-769d-11ea-b59e-00ffe0cb5768', 'xxlisi', '123456', '李四', 3, 1);

-- ----------------------------
-- Table structure for t_device
-- ----------------------------
DROP TABLE IF EXISTS `t_device`;
CREATE TABLE `t_device`  (
  `dev_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '设备标识',
  `type_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '设备类型id',
  `brand_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '设备品牌id',
  `dev_model` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '设备型号',
  `purchase_time` date NOT NULL COMMENT '购置时间',
  `erp_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ERP资产编码',
  `last_Upate` datetime(0) NULL DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`dev_id`) USING BTREE,
  INDEX `type`(`type_id`) USING BTREE,
  INDEX `brand`(`brand_id`) USING BTREE,
  CONSTRAINT `brand` FOREIGN KEY (`brand_id`) REFERENCES `t_device_brand` (`brand_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `type` FOREIGN KEY (`type_id`) REFERENCES `t_device_type` (`type_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '设备基础信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_device
-- ----------------------------
INSERT INTO `t_device` VALUES ('C001', '1', '1', 'mate book14', '2020-04-02', '12578DF', '2020-05-08 23:08:45');
INSERT INTO `t_device` VALUES ('C78E74', '2', '3', 'Mac Pro（MD770CH/A）', '2020-05-01', 'C6687EZ', '2020-05-08 23:08:37');

-- ----------------------------
-- Table structure for t_device_brand
-- ----------------------------
DROP TABLE IF EXISTS `t_device_brand`;
CREATE TABLE `t_device_brand`  (
  `brand_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '品牌id',
  `brand` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '品牌',
  PRIMARY KEY (`brand_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '设备品牌表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_device_brand
-- ----------------------------
INSERT INTO `t_device_brand` VALUES ('1', '华为');
INSERT INTO `t_device_brand` VALUES ('2', '小米');
INSERT INTO `t_device_brand` VALUES ('3', '苹果');

-- ----------------------------
-- Table structure for t_device_maintain
-- ----------------------------
DROP TABLE IF EXISTS `t_device_maintain`;
CREATE TABLE `t_device_maintain`  (
  `dev_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `maintain_info` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `maintain_date` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`dev_id`) USING BTREE,
  CONSTRAINT `devi` FOREIGN KEY (`dev_id`) REFERENCES `t_device` (`dev_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '设备维护信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_device_running
-- ----------------------------
DROP TABLE IF EXISTS `t_device_running`;
CREATE TABLE `t_device_running`  (
  `dev_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '设备标识',
  `mac_address` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物理地址 mac',
  `ip_address` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'IP地址 ip',
  `system_version` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '系统版本',
  `hd_size` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '硬盘容量',
  `cpu_info` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'cpu信息',
  `memory_Info` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内存',
  PRIMARY KEY (`dev_id`) USING BTREE,
  CONSTRAINT `devid2` FOREIGN KEY (`dev_id`) REFERENCES `t_device` (`dev_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '设备运行信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_device_running
-- ----------------------------
INSERT INTO `t_device_running` VALUES ('C001', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_device_running` VALUES ('C78E74', NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_device_type
-- ----------------------------
DROP TABLE IF EXISTS `t_device_type`;
CREATE TABLE `t_device_type`  (
  `type_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '型号id',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '型号',
  PRIMARY KEY (`type_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '设备类型表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_device_type
-- ----------------------------
INSERT INTO `t_device_type` VALUES ('1', '笔记本');
INSERT INTO `t_device_type` VALUES ('2', '服务器');
INSERT INTO `t_device_type` VALUES ('3 ', '台式机');
INSERT INTO `t_device_type` VALUES ('c191a5b1-83ed-11ea-b7f9-00ffe0cb5768', 'V5机架服务器');

-- ----------------------------
-- Table structure for t_device_usage
-- ----------------------------
DROP TABLE IF EXISTS `t_device_usage`;
CREATE TABLE `t_device_usage`  (
  `dev_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '设备标识',
  `dev_status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '使用状态,1代表停用,2代表启用',
  `domain_state` tinyint(1) NOT NULL DEFAULT 0 COMMENT '域管理状态,1代表未加域,2代表已加域',
  `dep_fri` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '一级部门的id',
  `dep_sec` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '二级部门的id',
  `dep_thir` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '三级部门',
  `network` tinyint(1) NULL DEFAULT 0 COMMENT '所属网络(0代表未入网,1内网,2外网)',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物理位置 id',
  PRIMARY KEY (`dev_id`) USING BTREE,
  INDEX `1st_org_id`(`dep_fri`) USING BTREE,
  INDEX `2sec_org_id`(`dep_sec`) USING BTREE,
  INDEX `mac_org_id`(`address`) USING BTREE,
  CONSTRAINT `1st_org_id` FOREIGN KEY (`dep_fri`) REFERENCES `t_organization` (`org_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `2sec_org_id` FOREIGN KEY (`dep_sec`) REFERENCES `t_organization` (`org_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `dev_id` FOREIGN KEY (`dev_id`) REFERENCES `t_device` (`dev_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `mac_org_id` FOREIGN KEY (`address`) REFERENCES `t_organization` (`org_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '设备使用信息表\r\n(可在设备添加之后补充录入)' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_device_usage
-- ----------------------------
INSERT INTO `t_device_usage` VALUES ('C001', 2, 0, '58cec0ac-7f2d-11ea-a038-00ffe0cb5768', '984b0e90-7f2d-11ea-a038-00ffe0cb5768', NULL, 0, NULL);
INSERT INTO `t_device_usage` VALUES ('C78E74', 2, 0, '447ad9a7-7f2d-11ea-a038-00ffe0cb5768', '68a01b73-7f2d-11ea-a038-00ffe0cb5768', NULL, 0, '55d6c36d-945d-11ea-b7f9-00ffe0cb5768');

-- ----------------------------
-- Table structure for t_device_user
-- ----------------------------
DROP TABLE IF EXISTS `t_device_user`;
CREATE TABLE `t_device_user`  (
  `dev_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '设备标识',
  `user_uuid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户唯一id',
  INDEX `usr`(`user_uuid`) USING BTREE,
  INDEX `dev`(`dev_id`) USING BTREE,
  CONSTRAINT `dev` FOREIGN KEY (`dev_id`) REFERENCES `t_device` (`dev_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `usr` FOREIGN KEY (`user_uuid`) REFERENCES `t_account` (`uuid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_device_user
-- ----------------------------
INSERT INTO `t_device_user` VALUES ('C78E74', '2fbbff5c-8e1d-11ea-bcc8-b82a72aaba54');
INSERT INTO `t_device_user` VALUES ('C001', '737f5968-8e1d-11ea-bcc8-b82a72aaba54');
INSERT INTO `t_device_user` VALUES ('C001', '1');
INSERT INTO `t_device_user` VALUES ('C001', '4e2d2647-7f2c-11ea-a038-00ffe0cb5768');
INSERT INTO `t_device_user` VALUES ('C001', '586c4218-8e1d-11ea-bcc8-b82a72aaba54');

-- ----------------------------
-- Table structure for t_organization
-- ----------------------------
DROP TABLE IF EXISTS `t_organization`;
CREATE TABLE `t_organization`  (
  `org_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `org_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `org_parent_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `org_level` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`org_id`) USING BTREE,
  INDEX `parent`(`org_parent_id`) USING BTREE,
  CONSTRAINT `parent` FOREIGN KEY (`org_parent_id`) REFERENCES `t_organization` (`org_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_organization
-- ----------------------------
INSERT INTO `t_organization` VALUES ('0', '虚拟节点', NULL, 0);
INSERT INTO `t_organization` VALUES ('393f7d95-91cd-11ea-b7f9-00ffe0cb5768', 'test', '7f584666-7f2d-11ea-a038-00ffe0cb5768', 3);
INSERT INTO `t_organization` VALUES ('447ad9a7-7f2d-11ea-a038-00ffe0cb5768', '人力部', '0', 1);
INSERT INTO `t_organization` VALUES ('5110606c-7f2d-11ea-a038-00ffe0cb5768', '行政部', '0', 1);
INSERT INTO `t_organization` VALUES ('54161256-7f2d-11ea-a038-00ffe0cb5768', '财务部', '0', 1);
INSERT INTO `t_organization` VALUES ('55d6c36d-945d-11ea-b7f9-00ffe0cb5768', '但是', '68a01b73-7f2d-11ea-a038-00ffe0cb5768', 3);
INSERT INTO `t_organization` VALUES ('57607053-91e5-11ea-b7f9-00ffe0cb5768', ' 人力资源办公室 202', '68a01b73-7f2d-11ea-a038-00ffe0cb5768', 3);
INSERT INTO `t_organization` VALUES ('58cec0ac-7f2d-11ea-a038-00ffe0cb5768', '运营部', '0', 1);
INSERT INTO `t_organization` VALUES ('5dd2ae83-7f2d-11ea-a038-00ffe0cb5768', '市场部', '0', 1);
INSERT INTO `t_organization` VALUES ('68a01b73-7f2d-11ea-a038-00ffe0cb5768', '人事专员', '447ad9a7-7f2d-11ea-a038-00ffe0cb5768', 2);
INSERT INTO `t_organization` VALUES ('6c389fe0-945f-11ea-b7f9-00ffe0cb5768', 'testdd', '68a01b73-7f2d-11ea-a038-00ffe0cb5768', 3);
INSERT INTO `t_organization` VALUES ('7f584666-7f2d-11ea-a038-00ffe0cb5768', '行政专员', '5110606c-7f2d-11ea-a038-00ffe0cb5768', 2);
INSERT INTO `t_organization` VALUES ('849fb024-7f2d-11ea-a038-00ffe0cb5768', '前台', '5110606c-7f2d-11ea-a038-00ffe0cb5768', 2);
INSERT INTO `t_organization` VALUES ('8b1940bc-7f2d-11ea-a038-00ffe0cb5768', '会计', '54161256-7f2d-11ea-a038-00ffe0cb5768', 2);
INSERT INTO `t_organization` VALUES ('8f3214f1-7f2d-11ea-a038-00ffe0cb5768', '出纳', '54161256-7f2d-11ea-a038-00ffe0cb5768', 2);
INSERT INTO `t_organization` VALUES ('984b0e90-7f2d-11ea-a038-00ffe0cb5768', '商品专员', '58cec0ac-7f2d-11ea-a038-00ffe0cb5768', 2);
INSERT INTO `t_organization` VALUES ('9c8e50d1-7f2d-11ea-a038-00ffe0cb5768', '数据分析', '58cec0ac-7f2d-11ea-a038-00ffe0cb5768', 2);
INSERT INTO `t_organization` VALUES ('9fb3f2e5-7f2d-11ea-a038-00ffe0cb5768', '采购', '58cec0ac-7f2d-11ea-a038-00ffe0cb5768', 2);
INSERT INTO `t_organization` VALUES ('af7f04b1-7f2d-11ea-a038-00ffe0cb5768', '	商务拓展BD', '5dd2ae83-7f2d-11ea-a038-00ffe0cb5768', 2);
INSERT INTO `t_organization` VALUES ('b8402cba-7f2d-11ea-a038-00ffe0cb5768', '公关PR', '5dd2ae83-7f2d-11ea-a038-00ffe0cb5768', 2);
INSERT INTO `t_organization` VALUES ('bed858bc-7f2d-11ea-a038-00ffe0cb5768', '搜索营销SEM', '5dd2ae83-7f2d-11ea-a038-00ffe0cb5768', 2);

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `role_id` tinyint(2) NOT NULL AUTO_INCREMENT COMMENT '角色的唯一编码,主键',
  `role_name` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES (1, 'superAdmin');
INSERT INTO `t_role` VALUES (2, 'admin');
INSERT INTO `t_role` VALUES (3, 'user');

-- ----------------------------
-- Procedure structure for tett
-- ----------------------------
DROP PROCEDURE IF EXISTS `tett`;
delimiter ;;
CREATE DEFINER=`root`@`%` PROCEDURE `tett`()
BEGIN 
    DECLARE age VARCHAR(64);
SELECT
	dev_id INTO age 
FROM
	t_device
ORDER BY
 last_Upate
 LIMIT 1;

INSERT into t_device_usage (dev_id)   VALUES (age);

END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table t_device
-- ----------------------------
DROP TRIGGER IF EXISTS `device_insert_triger`;
delimiter ;;
CREATE DEFINER = `root`@`%` TRIGGER `device_insert_triger` AFTER INSERT ON `t_device` FOR EACH ROW BEGIN 
    DECLARE devId VARCHAR(64);
SELECT
	dev_id INTO devId 
FROM
	t_device
ORDER BY
 last_Upate DESC
 LIMIT 1;

INSERT into t_device_usage (dev_id)   VALUES (devId);
INSERT into t_device_running (dev_id)   VALUES (devId);

END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table t_device
-- ----------------------------
DROP TRIGGER IF EXISTS `device_delete_triger`;
delimiter ;;
CREATE DEFINER = `root`@`%` TRIGGER `device_delete_triger` BEFORE DELETE ON `t_device` FOR EACH ROW BEGIN
DELETE FROM t_device_usage WHERE dev_id =OLD.dev_id;
DELETE FROM t_device_running WHERE dev_id =OLD.dev_id;
DELETE FROM t_device_user WHERE dev_id =OLD.dev_id;
DELETE FROM t_device_maintain WHERE dev_id =OLD.dev_id;
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
