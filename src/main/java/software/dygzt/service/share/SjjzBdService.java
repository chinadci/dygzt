package software.dygzt.service.share;

import software.dygzt.service.share.model.CreditNumVO;
import software.dygzt.service.share.model.SjjzBdModel;

import java.util.List;


/**
 * Created by Pzy on 12/14/16.
 */
public interface SjjzBdService {
    List getSjjzBdByFybh(List<String> fybh);

    List<SjjzBdModel> getSjjzBdB(List<String> fybhList, String kssj, String jssj);
    SjjzBdModel getSjjzBdModelOfAllSelectFY(List<SjjzBdModel> sjjzBdModelList);
    CreditNumVO calculateCredit(SjjzBdModel sjjzBdModelOfAllSelectFY,String[] ajztArray);

}
