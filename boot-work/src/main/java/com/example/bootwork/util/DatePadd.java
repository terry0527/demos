package com.example.bootwork.util;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.google.common.collect.Lists;
import org.springframework.util.Assert;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 *  自动补充缺省的日期
 * @param <T>
 */
public abstract class DatePadd<T> {

    protected abstract T paddStrategy();

    /**
     *  填充日期  要保证时间正序排序
     * @param startDate  开始时间
     * @param endDate    结束时间
     * @param data       需要填充的数据
     * @param clazz      数据类型
     * @param property   日期字段名
     * @return
     * @throws Exception
     */
    public List<T> padd(Date startDate,Date endDate,List<T> data,Class<T> clazz, String property) throws Exception{
        Long size = DateUtil.between(startDate,endDate, DateUnit.DAY);
        List<T> result = Lists.newArrayListWithExpectedSize(size.intValue());
        Date temp = startDate;

        PropertyDescriptor pd = new PropertyDescriptor(property,clazz);
        Method writeMethod = pd.getWriteMethod();
        Method readMethod = pd.getReadMethod();

        Assert.isTrue(writeMethod != null && readMethod != null, "ERROR FIELD" + property);

        for (int i = 0; !temp.after(endDate); ) {
            T t;
            if (data.size() > 1 && ((Date) readMethod.invoke(data.get(i))).equals(temp)) {
                t = data.get(i++);
            } else {
                t = paddStrategy();
                //fix NPE
                writeMethod.invoke(t, temp);
            }
            result.add(t);
            temp = DateUtil.offsetDay(temp, 1);
        }
        return result;
    }

    /**
     *  填充日期  要保证时间正序排序
     * @param startDate  开始时间
     * @param endDate    结束时间
     * @param data       需要填充的数据
     * @param clazz      数据类型
     * @param property   日期字段名
     * @return
     * @throws Exception
     */
    public List<T> paddLocalDate(LocalDate startDate, LocalDate endDate, List<T> data, Class<T> clazz, String property) throws Exception{
        Assert.notNull(startDate,"startDate is null");
        Assert.notNull(endDate,"endDate is null");
        Long size = endDate.toEpochDay() - startDate.toEpochDay();
        List<T> result = Lists.newArrayListWithExpectedSize(size.intValue());
        LocalDate temp = startDate;

        PropertyDescriptor pd = new PropertyDescriptor(property,clazz);
        Method writeMethod = pd.getWriteMethod();
        Method readMethod = pd.getReadMethod();

        Assert.isTrue(writeMethod != null && readMethod != null, "ERROR FIELD" + property);

        for (int i = 0; !temp.isAfter(endDate); ) {
            T t;
            if (data.size() > 1 && ((LocalDate) readMethod.invoke(data.get(i))).equals(temp)) {
                t = data.get(i++);
            } else {
                t = paddStrategy();
                //fix NPE
                writeMethod.invoke(t, temp);
            }
            result.add(t);
            temp = temp.plusDays(1);
        }
        return result;
    }

}
