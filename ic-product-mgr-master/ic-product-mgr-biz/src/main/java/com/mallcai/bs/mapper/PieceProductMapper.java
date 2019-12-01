package com.mallcai.bs.mapper;

import com.dailuobo.api.domain.entity.PieceEvent;
import com.dailuobo.api.domain.entity.PieceGroup;
import com.dailuobo.api.domain.entity.PieceProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface PieceProductMapper {

	List<Map> selectAll(Map<String, Object> param);

	/**
	 * Description:  返回符合添加条件的拼团商品列表
	 *
	 * @author liuwei
	 * @param
	 * @CreateDate 2019/1/4 9:34
	 */
	List<Map> getAddPieceProduct(@Param("cityId") Integer cityId);

	/**
	 * Description:  添加商品，查看该商品是否共享库存
	 *
	 * @author liuwei
	 * @param
	 * @CreateDate 2019/1/11 13:41
	 */
	int getShareCounts(int cityProductId);




	void add(PieceProduct pieceProduct);

	int update(PieceProduct pieceProduct);

	/**
	 * Description: 萝卜拼商品有无有效记录
	 *
	 * @author liuwei
	 * @param
	 * @CreateDate 2019/1/2 20:26
	 */
	int getPieceProductCount(Map param);

	/**
	 * Description:  团拼商品删除
	 *
	 * @author liuwei
	 * @param
	 * @CreateDate 2019/1/3 10:39
	 */
	void deletePieceProduct(Map param);

	/**
	 * Description:判断开启，当前有效时间内的团
	 *
	 * @author liuwei
	 * @param
	 * @CreateDate 2019/1/3 10:52
	 */
	int getPieceByProduct(Map param);

	/**
	 * Description:  开启拼团判断商品是不是全是全民拼
	 *
	 * @author liuwei
	 * @param
	 * @CreateDate 2019/1/3 15:31
	 */
	int checkPieceProduct(Map param);

	/**
	 * Description:拼团商品信息
	 *
	 * @author liuwei
	 * @param
	 * @CreateDate 2019/1/4 13:49
	 */
	List<PieceProduct> getProductByIds(@Param("pieceIds") List<Integer> pieceIds);

	/**
	 * Description:  查看商品是否全为共享库存
	 *
	 * @author liuwei
	 * @param
	 * @CreateDate 2019/1/8 10:43
	 */
	List<Map> getIsShareByIds(@Param("cityId") Integer cityId,@Param("productIds") List<Integer> productIds);

	/**
	 * Description:  批量开团
	 *
	 * @author liuwei
	 * @param
	 * @CreateDate 2019/1/3 17:01
	 */
	int insertPieceGroup(@Param("pieceGroups") List<PieceGroup> pieceGroups);

	/**
	 * Description:根据id查询拼团信息
	 *
	 * @author liuwei
	 * @param
	 * @CreateDate 2019/1/3 19:43
	 */
	Map getPieceGroupById(Integer id);

	/**
	 * Description:  已经存在的同类型的活动
	 *
	 * @author liuwei
	 * @param
	 * @CreateDate 2019/1/8 16:32
	 */
	int getSameEvent(Map param);

	/**
	 * Description:不同类型，重复时间区间内的piece
	 *
	 * @author liuwei
	 * @param
	 * @CreateDate 2019/1/16 15:06
	 */
	List<Integer> getPieceByType(Map param);


	/**
	 * Description:根据商品查找当前时间有效期内的拼团数
	 *
	 * @author liuwei
	 * @param
	 * @CreateDate 2019/1/4 10:30
	 */
	int getPieceGroupByProduct(Integer pieceProductId);


	/**
	 * Description:拼团编辑
	 *
	 * @author liuwei
	 * @param
	 * @CreateDate 2019/1/3 20:29
	 */
	void updatePieceGroup(PieceGroup pieceGroup);

	/**
	 * Description:  拼团删除
	 *
	 * @author liuwei
	 * @param
	 * @CreateDate 2019/1/7 19:29
	 */
	void deletePieceGroup(@Param("id") Integer id,@Param("deleteUserId") Integer deleteUserId );

	void lockPiece(@Param("id") Integer id,@Param("updateUserId") Integer updateUserId );

	/**
	 * Description: 新建活动
	 *
	 * @author liuwei
	 * @param
	 * @CreateDate 2019/1/4 17:40
	 */
	int insertPieceEvent(PieceEvent pieceEvent);

	/**
	 * Description:  编辑活动
	 *
	 * @author liuwei
	 * @param
	 * @CreateDate 2019/1/5 17:08
	 */
	void updatePieceEvent(PieceEvent pieceEvent);

	/**
	 * Description:编辑活动，同时编辑活动下面的所有团
	 *
	 * @author liuwei
	 * @param
	 * @CreateDate 2019/1/4 20:43
	 */
	void updatePieceByEvent(PieceEvent pieceEvent);

	/**
	 * Description:  活动删除
	 *
	 * @author liuwei
	 * @param
	 * @CreateDate 2019/1/7 18:20
	 */
	void deleteEvent(Integer eventId);

	/**
	 * Description:查询活动
	 *
	 * @author liuwei
	 * @param
	 * @CreateDate 2019/1/5 9:32
	 */
	List<Map> getPieceEvent(Map param);



	/**
	 * Description:根据活动展开拼团列表
	 *
	 * @author liuwei
	 * @param
	 * @CreateDate 2019/1/5 10:04
	 */
	List<Map> getPieceGroupByEvent(Integer eventId);

	/**
	 * Description:  正在有效期内的，已经开启的活动
	 *
	 * @author liuwei
	 * @param
	 * @CreateDate 2019/1/5 16:50
	 */
	Map getRightEvent(Integer id);

	/**
	 * Description:拼团置顶
	 *
	 * @author liuwei
	 * @param
	 * @CreateDate 2019/1/7 9:49
	 */
	int stickPiece(Integer pieceId);

	/**
	 * Description:根据cityProductId 查找团
	 *
	 * @author liuwei
	 * @param
	 * @CreateDate 2019/1/17 14:55
	 */
	List<Map> getPieceByCityProductId(Integer cityProductId);

	void  updatePieceAvaible(@Param("delta") Integer delta,@Param("ids") List<Integer> ids);

	//活动下面未删除的商品
	List<Integer> getPieceByEventNoDel(Integer eventId);

	void delete(@Param("topId") Integer topId);

	/**
	 * Description:查询活动
	 *
	 * @author liuwei
	 * @param
	 * @CreateDate 2019/1/5 9:32
	 */
	PieceGroup getPieceInfoById(Map param);

	/**
	 * Description:查询活动
	 *
	 * @author liuwei
	 * @param
	 * @CreateDate 2019/1/5 9:32
	 */
	PieceGroup getPieceGroupInfoById(Map param);

}
