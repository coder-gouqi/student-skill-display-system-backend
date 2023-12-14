/*
 Navicat Premium Data Transfer

 Source Server         : 本地数据库
 Source Server Type    : MySQL
 Source Server Version : 80032
 Source Host           : localhost:3306
 Source Schema         : system

 Target Server Type    : MySQL
 Target Server Version : 80032
 File Encoding         : 65001

 Date: 13/12/2023 18:43:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for academy
-- ----------------------------
DROP TABLE IF EXISTS `academy`;
CREATE TABLE `academy`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '学院id',
  `academy_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '学院名称',
  `academy_info` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '学院简介',
  `academy_photo` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '学院头像',
  `is_delete` tinyint(1) UNSIGNED ZEROFILL NULL DEFAULT NULL COMMENT '逻辑删除，0未删除，1已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of academy
-- ----------------------------
INSERT INTO `academy` VALUES ('9388994a33a94719bbd1a9677f4ac391', '自动化学院', '自动化学院成立于2004年，2022年由控制工程学院更名为自动化学院。学院是我校信息学科群和电子信息专业群的重要组成部分，位于我校信息产业人才培养链中的关键节点“设备与装备”。学院既是顺应国家人工智能、智能制造、能源等战略紧需人才培养而成立和发展，也始终致力于积极服务国家和区域经济社会发展。\r\n\r\n自动化学院的一级学科为“控制科学与工程”，依托2019年获批的四川省无人系统智能感知控制技术工程实验室、2020年获批的四川省机器人与智能系统国际联合研究中心和2022年获批的成都信息工程大学自主智能技术与系统研究院。学院于2018年培养“智能机器人技术及应用”方向学术型硕士研究生，2020年开始培养“交通运输”专业硕士研究生，2021年开始培养“控制工程”领域专业型硕士研究生，2023年开始培养“控制科学与工程”学术型硕士研究生。目前，该一级学科下设“自主智能技术与系统”、“辐照环境智能装备”、“特种智能机器人”、“智能电网”、 “智能检测系统”、“先进控制理论及应用”和“特殊机电装备”7个研究方向。', 'L3KuV', 0);
INSERT INTO `academy` VALUES ('b2739ce2bafd406bbc1c16744326a5c5', '软件工程学院', ' 软件工程学院是顺应国家和四川省大力发展软件产业的大背景而成立的。现拥有软件工程一级学科硕士学位点和电子信息、农业工程与信息技术2个专业硕士学位点，开设有软件工程、空间信息与数字技术和数据科学与大数据技术3个本科专业，现有在校研究生和本科学生2000余人。\n\n学院通过内培外引，不断加强人才队伍建设，拥有一支行业背景深厚、工程能力强、师德优良的专业教师队伍。专任教师中教授11人、副教授25人，博士率70%；硕士生导师25人、博士生导师4人。有享受国务院政府特殊津贴专家2人、四川省学术和技术带头人1人、四川省有突出贡献专家1人、四川省千人和万人计划专家3人、四川省学术和技术带头人后备人选4人，中国科学院“西部之光”人才计划1人、1人入选国家标准委员会TC240委员。一大批专业教师获得全国和省级各类教学竞赛奖，以及学校“优秀教师”“最受欢迎教师”等称号。辅导员中先后涌现出第六届四川省高校辅导员年度人物、学校“十佳辅导员”等先进个人等典型。', 'Uj5', 0);
INSERT INTO `academy` VALUES ('c9863f8b58cd4e5a87fa912580d16d9a', '计算机学院', '计算机学院立足服务四川和西部地区的信息产业发展和地方经济建设，支持我国气象行业信息化和现代化；培养在四川省和我国气象行业具有领先水平的计算机创新团队；构建高层次应用型信息技术人才的培养基地。计算机学院高度重视师资队伍建设，大力引进和培养优秀人才。现有专任教师95人，其中高级职称比例39%，博士学位比例62%，享受国务院政府津贴专家1人，长江学者讲席教授1人，四川省学术和技术带头人2人，四川省有突出贡献专家2人，“天府青城计划”领军人才2人，“天府峨眉计划”青年人才2人，成都市人才计划专家1人，四川省学术和技术带头人后备人选5人，硕士生导师31人。', 'MIpi', 0);
INSERT INTO `academy` VALUES ('e075055ed8064385b23a73e638909dd4', '大气科学学院', '成都信息工程大学大气科学学院源于1951年成立的中国人民解放军西南军区气象干部训练大队，1979年开始大气科学本科专业招生，2004-2017年开展国防生培养，2008年设立应用气象学专业，2020年大气科学专业和应用气象学专业分别入选国家级和省级一流本科专业建设点。2003年学院开始硕士研究生培养，“气象学”获批二级学科硕士授权点和四川省重点建设学科，2006年获批“大气科学”一级学科硕士授权点，2016年获批四川省“双一流”建设学科，2023年获批四川省“双一流建设贡嘎计划建设学科”。在近年来的“软科中国最好学科排名”中，均进入大气科学前50%。以大气科学为主要支撑的地球科学学科和环境生态学科均进入ESI全球排名前1%，体现出大气科学学科较强的学术影响力。', '', 0);
INSERT INTO `academy` VALUES ('e655d637ed11458ca4a70fe394440138', '网络空间安全学院', '学院源于2005年1月成立的网络工程系，2008年10月更名为网络工程学院，2013年12月更名为信息安全工程学院，2017年3月更名为网络空间安全学院。学院现有教职员工共80人，专任教师64人，专任教师中有四川省有突出贡献的优秀专家2人，四川省学术与技术带头人后备人选5人，教育部“新世纪优秀人才支持计划”1人，博士占比52%，高级职称占比48%。\r\n\r\n学院设有信息安全（国家一流专业建设点）、网络工程（省级一流专业建设点）、物联网工程（省级一流专业建设点）和网络空间安全（2022年招生）四个本科专业，三个硕士学位授权点（网络空间安全一级学科（学术型硕士）、网络与信息安全（专业学位）和农业工程与信息技术（专业学位）），目前在校硕士研究生229人，本科生2024人。', 'li0e', 0);

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课程id',
  `course_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '课程名称',
  `course_skill_index_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '课程所属技能指标id',
  `course_weight` double NULL DEFAULT NULL COMMENT '权重',
  `is_delete` tinyint(1) UNSIGNED ZEROFILL NULL DEFAULT NULL COMMENT '逻辑删除，0未删除，1已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('04d903524bdc4ed89962c56a6c9bcc97', '计算机操作系统', 'e655d637ed11458ca4a70fe394440138', 12, 0);
INSERT INTO `course` VALUES ('7d204bc537984bcd91ee36f885e5a853', '大学美育', 'e655d637ed11458ca4a70fe394440138', 22, 0);
INSERT INTO `course` VALUES ('8506b11481174fcd975e12919cad6155', '计算机网络', 'e655d637ed11458ca4a70fe394440138', 12, 0);
INSERT INTO `course` VALUES ('b2739ce2bafd406bbc1c16744326a5c5', '计算机组成原理', 'e655d637ed11458ca4a70fe394440138', 32, 0);
INSERT INTO `course` VALUES ('e655d637ed11458ca4a70fe394440138', '艺术与审美', 'e655d637ed11458ca4a70fe394440138', 56, 0);
INSERT INTO `course` VALUES ('f2b3d1e559b64a03a9b6a0d4bbc41d48', '科技英语', 'e655d637ed11458ca4a70fe394440138', 45, 0);

-- ----------------------------
-- Table structure for score
-- ----------------------------
DROP TABLE IF EXISTS `score`;
CREATE TABLE `score`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '成绩id',
  `student_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '学生id',
  `course_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '课程id',
  `student_score` double NULL DEFAULT NULL COMMENT '学生成绩',
  `is_delete` tinyint(1) UNSIGNED ZEROFILL NULL DEFAULT NULL COMMENT '逻辑删除，0未删除，1已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of score
-- ----------------------------
INSERT INTO `score` VALUES ('0949f23535ad4ca88392b118bdd8976e', '04d903524bdc4ed89962c56a6c9bcc97', 'e655d637ed11458ca4a70fe394440138', 98, 0);
INSERT INTO `score` VALUES ('e655d637ed11458ca4a70fe394440138', '04d903524bdc4ed89962c56a6c9bcc97', '04d903524bdc4ed89962c56a6c9bcc97', 82, 0);

-- ----------------------------
-- Table structure for skill
-- ----------------------------
DROP TABLE IF EXISTS `skill`;
CREATE TABLE `skill`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '技能展示id',
  `student_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '学生姓名',
  `student_skill_score` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '学生技能成绩（json）',
  `is_delete` tinyint(1) UNSIGNED ZEROFILL NULL DEFAULT NULL COMMENT '逻辑删除，0未删除，1已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of skill
-- ----------------------------
INSERT INTO `skill` VALUES ('f2b3d1e559b64a03a9b6a0d4bbc41d48', '04d903524bdc4ed89962c56a6c9bcc97', '{\"编程能力\":\"90.0\"}', 0);

-- ----------------------------
-- Table structure for skill_index
-- ----------------------------
DROP TABLE IF EXISTS `skill_index`;
CREATE TABLE `skill_index`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '技能指标id',
  `skill_index_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '技能指标名称',
  `is_delete` tinyint(1) UNSIGNED ZEROFILL NULL DEFAULT NULL COMMENT '逻辑删除，0未删除，1已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of skill_index
-- ----------------------------
INSERT INTO `skill_index` VALUES ('e655d637ed11458ca4a70fe394440138', '编程能力', 0);

-- ----------------------------
-- Table structure for skill_level
-- ----------------------------
DROP TABLE IF EXISTS `skill_level`;
CREATE TABLE `skill_level`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '技能等级id',
  `skill_level` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '技能等级',
  `end` int NULL DEFAULT NULL COMMENT '结束值',
  `start` int NULL DEFAULT NULL COMMENT '开始值',
  `is_delete` tinyint(1) UNSIGNED ZEROFILL NULL DEFAULT NULL COMMENT '逻辑删除，0未删除，1已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of skill_level
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户id',
  `user_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户姓名',
  `user_account` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户账号',
  `user_password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户密码',
  `user_role` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户角色',
  `student_number` int NULL DEFAULT NULL COMMENT '学生学号',
  `student_academy_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '学生学院',
  `student_class` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '学生班级',
  `student_grade` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '学生年级',
  `student_photo` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '学生头像',
  `is_delete` tinyint(1) UNSIGNED ZEROFILL NULL DEFAULT NULL COMMENT '逻辑删除，0未删除，1已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('04d903524bdc4ed89962c56a6c9bcc97', '金涛', '2jIc', 'UB', 'student', 702626, '212648dd-f050-49bf-a0bb-71688035be7a', 'uh', 'DnGsq', 'iD37a', 0);
INSERT INTO `user` VALUES ('1', '程序员枸杞', '123', '123', 'admin', 2021121133, '网络空间安全学院', '网络214', '2021级', 'fedsdf', 0);
INSERT INTO `user` VALUES ('9388994a-33a9-4719-bbd1-a9677f4ac391', '朱俊驰', 'mE', 'bz0Z', 'student', 52562, 'f2b3d1e5-59b6-4a03-a9b6-a0d4bbc41d48', 'De2F', '3To7N', 'pgmMN', 0);

SET FOREIGN_KEY_CHECKS = 1;
