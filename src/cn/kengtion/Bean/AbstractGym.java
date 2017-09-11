package cn.kengtion.Bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by 洪坤峰 on 2017/9/9.
 * 体育馆抽象类
 */
public abstract class AbstractGym {


    /**
     * 执行预定命令.
     *
     * @param order 订单
     * @return 执行状态 true为成功，false为失败
     */
    public abstract boolean bookGym(AbstractOrder order) ;


    /**
     * 执行取消预定命令
     *
     * @param order 待取消的订单信息
     * @return 执行结果 true为成功，false为不存在该订单，失败
     */
    public abstract boolean cancelBook(AbstractOrder order) ;

    /**
     * 获取体育馆的收入输出字符串
     *
     * @return the output
     */
    public abstract String getOutput() ;

    /**
     * 设置体育馆编号
     *
     * @param tag the tag
     */
    public abstract void setTag(char tag);


    /**
     * 获得总收入
     *
     * @return the total income
     */
    public abstract int getTotalIncome();

}
