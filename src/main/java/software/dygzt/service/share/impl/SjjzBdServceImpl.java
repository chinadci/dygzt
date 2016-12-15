package software.dygzt.service.share.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.dygzt.data.share.dao.SjjzBdDAO;
import software.dygzt.data.share.dataobject.SjjzBdDO;
import software.dygzt.service.bm.impl.BmbServiceImpl;
import software.dygzt.service.share.Convertor;
import software.dygzt.service.share.SjjzBdService;
import software.dygzt.service.share.model.CreditNumVO;
import software.dygzt.service.share.model.SjjzBdModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pzy on 12/14/16.
 */
@Service
public class SjjzBdServceImpl implements SjjzBdService {
    @Autowired
    SjjzBdDAO sjjzBdDAO;

    static final Logger log = Logger.getLogger(BmbServiceImpl.class);


    /**
     * 根据法院编号list，取得 sjjzBdModelList
     * */
    public List<SjjzBdModel> getSjjzBdByFybh(List<String> fybhList) {
        List<SjjzBdModel> sjjzBdModelList = new ArrayList();
        for(int i = 0; i< fybhList.size();i++){
            String fybh = fybhList.get(i);
            SjjzBdDO sjjzBdDO =  sjjzBdDAO.findSjjzBdDOByFybh(fybh).get(0); //只取第一个
            SjjzBdModel sjjzBdModel = Convertor.doToSjjzBdModel(sjjzBdDO); //转为 model
            sjjzBdModelList.add(sjjzBdModel);
        }
        return sjjzBdModelList;
    }

    /**
     * 根据法院编号list，取得 sjjzBdModelList
     * */
    public List<SjjzBdModel> getSjjzBdB(List<String> fybhList, String kssj, String jssj) {

        List<SjjzBdModel> sjjzBdModelList = new ArrayList();
        for(int i = 0; i< fybhList.size();i++){
            SjjzBdDO sjjzBdDO =  sjjzBdDAO.findSjjzBdDO(fybhList.get(i),kssj,jssj);
            SjjzBdModel sjjzBdModel = Convertor.doToSjjzBdModel(sjjzBdDO); //转为 model
            if(sjjzBdModel != null){
                sjjzBdModelList.add(sjjzBdModel);
            }
        }
        return sjjzBdModelList;
    }

    public SjjzBdModel getSjjzBdModelOfAllSelectFY(List<SjjzBdModel> sjjzBdModelList) {
        SjjzBdModel sjjzBdModelOfAllSelectFY = new SjjzBdModel();
        for (SjjzBdModel item : sjjzBdModelList) {
            /*CY 相关值*/
            sjjzBdModelOfAllSelectFY.setCyJc(sjjzBdModelOfAllSelectFY.getCyJc() + item.getCyJc()); //在原来基础上加上 item 的值
            sjjzBdModelOfAllSelectFY.setCyXs(sjjzBdModelOfAllSelectFY.getCyXs() + item.getCyXs()); //在原来基础上加上 item 的值
            sjjzBdModelOfAllSelectFY.setCyWj(sjjzBdModelOfAllSelectFY.getCyWj() + item.getCyWj()); //在原来基础上加上 item 的值
            sjjzBdModelOfAllSelectFY.setCyYj(sjjzBdModelOfAllSelectFY.getCyYj() + item.getCyYj()); //在原来基础上加上 item 的值

            /*BD 相关值*/
            sjjzBdModelOfAllSelectFY.setBdJc(sjjzBdModelOfAllSelectFY.getBdJc() + item.getBdJc()); //在原来基础上加上 item 的值
            sjjzBdModelOfAllSelectFY.setBdXs(sjjzBdModelOfAllSelectFY.getBdXs() + item.getBdXs()); //在原来基础上加上 item 的值
            sjjzBdModelOfAllSelectFY.setBdWj(sjjzBdModelOfAllSelectFY.getBdWj() + item.getBdWj()); //在原来基础上加上 item 的值
            sjjzBdModelOfAllSelectFY.setBdYj(sjjzBdModelOfAllSelectFY.getBdYj() + item.getBdYj()); //在原来基础上加上 item 的值

            /*JZRH 相关值*/
            sjjzBdModelOfAllSelectFY.setJzrhJc(sjjzBdModelOfAllSelectFY.getJzrhJc() + item.getJzrhJc()); //在原来基础上加上 item 的值
            sjjzBdModelOfAllSelectFY.setJzrhXs(sjjzBdModelOfAllSelectFY.getJzrhXs() + item.getJzrhXs()); //在原来基础上加上 item 的值
            sjjzBdModelOfAllSelectFY.setJzrhWj(sjjzBdModelOfAllSelectFY.getJzrhWj() + item.getJzrhWj()); //在原来基础上加上 item 的值
            sjjzBdModelOfAllSelectFY.setJzrhYj(sjjzBdModelOfAllSelectFY.getJzrhYj() + item.getJzrhYj()); //在原来基础上加上 item 的值
        }
        return sjjzBdModelOfAllSelectFY;
    }

        /**
         * 一个 yjConditon 可能要计算多个案件状态的数据
         * 如果这个 sjjzBdModelOfAllSelectFY 中的值都为空，那么则不做计算
         * 置信度计算公式为 1-( CY_前缀/MAX(BD or JZRH) )
         * */
    public CreditNumVO calculateCredit( SjjzBdModel sjjzBdModelOfAllSelectFY, String[] ajztArray) {
        if (!sjjzBdModelOfAllSelectFY.equals(new SjjzBdModel())) {
            CreditNumVO creditNumVO = new CreditNumVO();
            /*把表格中所以的案件状态的置信度计算出来*/
            for (String ajzt : ajztArray) {
                if (ajzt.contains("新收")) {
                    double XSCredit = 1 - sjjzBdModelOfAllSelectFY.getCyXs() / (float)Math.max(sjjzBdModelOfAllSelectFY.getBdXs(), sjjzBdModelOfAllSelectFY.getJzrhXs());
                    creditNumVO.setXSCredit(XSCredit);
                } else if (ajzt.contains("旧存")) {
                    double JCCredit = 1 - sjjzBdModelOfAllSelectFY.getCyJc() / (float)Math.max(sjjzBdModelOfAllSelectFY.getBdJc(), sjjzBdModelOfAllSelectFY.getJzrhJc());
                    creditNumVO.setJCCredit(JCCredit);
                } else if (ajzt.contains("已结")) {
                    double YJCredit = 1 - sjjzBdModelOfAllSelectFY.getCyYj() / (float)Math.max(sjjzBdModelOfAllSelectFY.getBdYj(), sjjzBdModelOfAllSelectFY.getJzrhYj());
                    creditNumVO.setYJCredit(YJCredit);
                } else if (ajzt.contains("未结")) { //未结
                    double WJcredit = 1 - sjjzBdModelOfAllSelectFY.getCyWj() / (float)Math.max(sjjzBdModelOfAllSelectFY.getBdWj(), sjjzBdModelOfAllSelectFY.getJzrhWj());
                    creditNumVO.setWJcredit(WJcredit);
                }
            }
            return creditNumVO;
        }
        return null; //如果传入的 SjjzBdModel 的内容指都为0 则返回空
    }


}
