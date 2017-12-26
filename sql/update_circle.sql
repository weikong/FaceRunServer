-- account表增加字段
ALTER TABLE `circle` ADD COLUMN `thumbs` VARCHAR(1000) NULL COMMENT '缩略图集' AFTER `photos`;
