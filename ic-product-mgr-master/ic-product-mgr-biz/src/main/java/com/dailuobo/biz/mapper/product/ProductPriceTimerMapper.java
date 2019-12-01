package com.dailuobo.biz.mapper.product;

import com.dailuobo.api.domain.newmgr.MultiBargainTask;
import com.dailuobo.api.domain.entity.BargainTaskByMulti;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * ...
 *
 * @author lihf@mallcai.com <lihaifeng>
 * @date 2019-05-09 17:39
 * @since Jdk 1.8
 */
public interface ProductPriceTimerMapper {
    @Insert("INSERT INTO tbl_multi_bargain_task_mgr (execute_time, city_product_id, product_no, show_name, city_id, store_ids, store_names, icon_tip, keyword, avg_price, real_price, threshold_for_purchase, create_user_id)" +
            "            VALUE (" +
            "            #{executeTime}," +
            "            #{cityProductId}," +
            "            #{productNo}," +
            "            #{showName}," +
            "            #{cityId}," +
            "            #{storeIds}," +
            "            #{storeNames}," +
            "            #{iconTip}," +
            "            #{keyword}," +
            "            #{avgPrice}," +
            "            #{realPrice}," +
            "            #{thresholdForPurchase}," +
            "            #{createUserId}" +
            "        )")
    void addTask(MultiBargainTask task);

    @Select("<script>" +
            "SELECT" +
            "        t.id," +
            "        t.execute_time," +
            "        t.city_product_id," +
            "        t.product_no," +
            "        t.show_name," +
            "        t.city_id," +
            "        t.store_ids," +
            "        t.store_names," +
            "        t.icon_tip," +
            "        t.avg_price as store_avg_price," +
            "        t.real_price as store_real_price," +
            "        t.threshold_for_purchase," +
            "        t.status," +
            "        t.create_time," +
            "        t.update_time," +
            "        t.create_user_id" +
            "        FROM tbl_multi_bargain_task_mgr t " +
            "        WHERE t.del_flag = 1 " +
            "        <if test=\"cityId != null\">" +
            "            AND t.city_id = #{cityId}" +
            "        </if>" +
            "        <if test=\"userId != null\">" +
            "            AND t.create_user_id = #{userId}" +
            "        </if>" +
            "        <if test=\"startTime != null\">" +
            "            AND t.execute_time &gt;= #{startTime}" +
            "        </if>" +
            "        <if test=\"endTime != null\">" +
            "            AND t.execute_time &lt;= #{endTime}" +
            "        </if>" +
            "        <if test=\"status != null\">" +
            "            AND t.status = #{status}" +
            "        </if>" +
            "        <if test=\"productNo != null and productNo != ''\">" +
            "            AND (t.show_name like CONCAT('%',#{productNo},'%') OR t.product_no like CONCAT('%',#{productNo},'%'))" +
            "        </if>" +
            "        ORDER BY t.create_time DESC, t.product_no" +
            "</script>")
    List<MultiBargainTask> selectAll(Map<String, Object> param);

    @Update("UPDATE tbl_multi_bargain_task_mgr t" +
            "        SET t.del_flag = 0" +
            "        WHERE t.status = 0" +
            "              AND t.del_flag = 1" +
            "              AND t.id = #{id}")
    int delete(@Param("id") Integer id);

    @Select( "SELECT" +
            "        t.id," +
            "        t.execute_time," +
            "        t.city_product_id," +
            "        t.product_no," +
            "        t.show_name," +
            "        t.city_id," +
            "        t.store_ids," +
            "        t.store_names," +
            "        t.icon_tip," +
            "        t.avg_price," +
            "        t.real_price," +
            "        t.threshold_for_purchase," +
            "        t.status," +
            "        t.create_time," +
            "        t.update_time," +
            "        t.create_user_id" +
            "        FROM tbl_multi_bargain_task_mgr t" +
            "        WHERE t.del_flag = 1" +
            "        AND t.`status` = 0" +
            "        and t.execute_time <= NOW()")
    List<MultiBargainTask> getNonExecutionTask();

    @Update("INSERT INTO tbl_store_product_info (city_product_id, store_id, icon_tip)" +
            "            VALUE (#{cityProductId}, #{storeId}, #{iconTip})" +
            "        ON DUPLICATE KEY UPDATE" +
            "            icon_tip = #{iconTip};" +
            "" +
            "        INSERT INTO tbl_sales_spec (city_product_id," +
            "                                    real_price," +
            "                                    avg_price," +
            "                                    avg_unit," +
            "                                    unit_type," +
            "                                    spec_name," +
            "                                    create_user_id," +
            "                                    threshold," +
            "                                    package_max_weight," +
            "                                    change_flag," +
            "                                    package_quantity," +
            "                                    store_id," +
            "                                    city_id," +
            "                                    vip_count," +
            "                                    vip_product_price)" +
            "            SELECT" +
            "                city_product_id," +
            "                #{realPrice}," +
            "                #{avgPrice}," +
            "                avg_unit," +
            "                unit_type," +
            "                spec_name," +
            "                #{createUserId}," +
            "                #{thresholdForPurchase}," +
            "                package_max_weight," +
            "                change_flag," +
            "                package_quantity," +
            "                #{storeId}," +
            "                #{cityId}," +
            "                vip_count," +
            "                vip_product_price" +
            "            FROM tbl_sales_spec" +
            "            WHERE city_product_id = #{cityProductId}" +
            "                  AND store_id = 0" +
            "        ON DUPLICATE KEY UPDATE" +
            "            real_price     = #{realPrice}," +
            "            avg_price      = #{avgPrice}," +
            "            threshold      = #{thresholdForPurchase}," +
            "            update_user_id = #{createUserId};")
    void runTask(BargainTaskByMulti task);

    @Update("<script>UPDATE tbl_multi_bargain_task_mgr t" +
            "        <set>" +
            "            t.status = #{status}," +
            "            <if test=\"exception != null\">" +
            "                t.exception = #{exception}" +
            "            </if>" +
            "        </set>" +
            "        WHERE t.del_flag = 1" +
            "        AND t.id = #{id}</script>")
    int updateTaskStatus(Map<String, Object> param);

    @Select("<script>" +
            " select product_no,hq_product_icon" +
            " from tbl_hq_product" +
            " where product_no in" +
            " <foreach collection=\"productNoList\" item=\"productNo\" separator=\",\" open=\"(\" close=\");\">" +
            "     #{productNo}" +
            " </foreach>" +
            "</script>")
    List<MultiBargainTask> selectProductIconByProductNos(@Param("productNoList") List<String> productNoList);

    @Select("<script>" +
            " select user_id create_user_id,real_name create_real_name" +
            " from tbl_user" +
            " where user_id in" +
            " <foreach collection=\"userIdList\" item=\"userId\" separator=\",\" open=\"(\" close=\");\">" +
            "   #{userId}" +
            " </foreach>" +
            "</script>")
    List<MultiBargainTask> selectUserNameByUserIds(@Param("userIdList") List<Integer> userIdList);

}
