
DROP TABLE IF EXISTS TEST_MODEL;
CREATE TABLE TEST_MODEL (
    	id char(36)  COMMENT 'id' DEFAULT '1'  	,
    	name varchar  COMMENT '名称'   	,
    	code varchar(100)  COMMENT '编码'   	,
    
  PRIMARY KEY (id)
  
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;