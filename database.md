

## 数据库表

### user用户表

| 字段名     | 类型                 | 描述             | 备注             | 索引     |
| ---------- | -------------------- | ---------------- | ---------------- | -------- |
| id         | int                  |                  | 主键约束、自增   | 主键索引 |
| username   | varchar(50)          | 用户名           | 唯一约束         | 唯一索引 |
| password   | varchar(255)         | 密码             |                  |          |
| avatar     | varchar(200)         | 头像存储路径     |                  |          |
| role       | enum('user','admin') | 用户类型         |                  |          |
| created_at | datetime             | 该数据创建的时间 | CURRENT_TIMESAMP |          |
| updated_at | datetime             | 该数据更新的时间 | CURRENT_TIMESAMP |          |



### music音乐表

| 字段名      | 类型         | 描述              | 备注             | 索引     |
| ----------- | ------------ | ----------------- | ---------------- | -------- |
| id          | int          |                   | 主键约束、自增   | 主键索引 |
| title       | varchar(100) | 歌曲名称          |                  |          |
| singer      | varchar(100) | 歌手              |                  |          |
| lyric       | varchar(100) | 作词              |                  |          |
| composition | varchar(100) | 作曲              |                  |          |
| producer    | varchar(100) | 制作人            |                  |          |
| company     | varchar(100) | 公司              |                  |          |
| album       | VARCHAR(50)  | 专辑              |                  |          |
| lyric_data  | JSON         | 歌词文件路径      |                  |          |
| duration    | INT          | 秒数              |                  |          |
| file_path   | VARCHAR(500) | MinIO对象存储路径 |                  |          |
| uploader_id | INT          | 绑定用户id        |                  |          |
| bitrate     | INT          | 码率(kbps)        |                  |          |
| created_at  | datetime     | 该数据创建的时间  | CURRENT_TIMESAMP |          |
| updated_at  | datetime     | 该数据更新的时间  | CURRENT_TIMESAMP |          |



### play_history播放记录表

| 字段名     | 类型     | 描述             | 备注             | 索引     |
| ---------- | -------- | ---------------- | ---------------- | -------- |
| id         | int      |                  | 主键约束、自增   | 主键索引 |
| user_id    | int      | 用户id           |                  |          |
| music_id   | int      | 音乐id           |                  |          |
| play_time  | int      | 播放进度         |                  |          |
| created_at | datetime | 该数据创建时间   | CURRENT_TIMESAMP |          |
| updated_at | datetime | 该数据更新的时间 | CURRENT_TIMESAMP |          |

