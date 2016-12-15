package software.dygzt.data.share.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import software.dygzt.data.share.dataobject.SjjzBdDO;

import java.util.List;

import static com.sun.tools.doclint.Entity.and;

/**
 * Created by Pzy on 12/14/16.
 */
@Repository
public class SjjzBdDAO extends HibernateDaoSupport {
    private static final Logger log = LoggerFactory.getLogger(SjjzBdDAO.class);

    protected void initDao() {
        // do nothing
    }

    /**
     * 通过 fybh 找相应的  数据集中报表对象集合(ok)
     *
     * @param fybh
     * @return
     */
    public List<SjjzBdDO> findSjjzBdDOByFybh(String fybh) {
        String hql = "FROM SjjzBdDO WHERE fybh = '" + fybh + "'";
        List<SjjzBdDO> list = getHibernateTemplate().find(hql);
        return list;//返回第一个结果就行
    }


    /**
     * 查询条件: 法院编号，时间，可以不用案件状态关键字
     */
    public SjjzBdDO findSjjzBdDO(String fybh, String kssj, String jssj) {
        //kssj 和 jssj 格式: 2016-10-12
        String[] time = kssj.split("-");
        int KSND = Integer.valueOf(time[0]);
        int KSYF = Integer.valueOf(time[1]);
        int KSTS = Integer.valueOf(time[2]);

        String[] timeJssj = jssj.split("-");
        int JSND = Integer.valueOf(timeJssj[0]);
        int JSYF = Integer.valueOf(timeJssj[1]);
        int JSTS = Integer.valueOf(timeJssj[2]);


        /*private String fybh;
        private Integer ksNd;
        private Integer ksYf;
        private Integer ksTs;
        private Integer jsNd;
        private Integer jsYf;
        private Integer jsTs;*/

        String queryStr = "FROM SjjzBdDO WHERE fybh = '" + fybh + "' and ksNd = " + KSND + " and ksYf = " + KSYF + " and ksTs = "
                + KSTS + " and jsNd = " + JSND + " and jsYf = " + JSYF + " and jsTs = " + JSTS;

        List<SjjzBdDO> list = getHibernateTemplate().find(queryStr);
        if(list != null && list.size() != 0){
            return list.get(0);
        }else{
            return null;
        }
    }

}
