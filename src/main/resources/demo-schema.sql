CREATE SCHEMA IF NOT EXISTS demo;
SET SCHEMA demo;

-- DROP TABLE sys_user;
CREATE TABLE IF NOT EXISTS sys_user (
	id             INTEGER NOT NULL AUTO_INCREMENT,
	name           VARCHAR(64) NOT NULL,
	pwd            VARCHAR(64),
	phone          VARCHAR(11),
	create_date    TIMESTAMP
);
COMMENT ON COLUMN sys_user.id IS '用户ID';
COMMENT ON COLUMN sys_user.name IS '用户名称';
COMMENT ON COLUMN sys_user.pwd IS '用户密码';
COMMENT ON COLUMN sys_user.phone IS '用户手机号码';
COMMENT ON COLUMN sys_user.create_date IS '用户创建时间';




