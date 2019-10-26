
CREATE TABLE `sys_log`  (
  `id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作用户',
  `operation` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '操作',
  `method` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '执行方法',
  `params` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '请求参数',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `operate_time` datetime(0) NULL DEFAULT NULL COMMENT '最后一次更新时间',
  `operate_ip` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最后一次更新者的ip地址',
  `time` int(11) NULL DEFAULT NULL COMMENT '耗时',
  `location` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作地点',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;




-- 多数据源

--oracle Create table
create table M_COST
(
  id    VARCHAR2(36) not null,
  money VARCHAR2(36)
)
-- Create/Recreate primary, unique and foreign key constraints
alter table M_COST
  add constraint PK_COST primary key (ID)
  using index
  ;
-----
-- ！oracle Create table