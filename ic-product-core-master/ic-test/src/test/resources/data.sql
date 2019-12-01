INSERT INTO `ic_back_category` (`id`, `pid`, `name`, `level`, `status`, `has_children`, `has_spu`, `create_time`, `update_time`, `updated_by`)
VALUES (1, 0, '一级类目', 1, 1, 1, 0, now(), now(), 1),
       (2, 1, '二级类目', 2, 1, 1, 0, now(), now(), 1),
       (3, 2, '叶子类目', 3, 1, 0, 0, now(), now(), 1);
