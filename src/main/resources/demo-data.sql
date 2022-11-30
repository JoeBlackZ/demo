-- 保存默认用户（若不存在插入）
MERGE INTO demo.sys_user (id, name, pwd, phone, create_date) KEY(id) VALUES(1, 'admin', '123456', '13112345678', CURRENT_TIMESTAMP())
