package com.framework.service;

import com.framework.dto.FishDTO;
import com.framework.utils.ReturnData;

/**
 * @author ChenDang
 * @date 2019/12/17 0017
 */
public interface RestfulService {

    public ReturnData index(FishDTO dto);

    public ReturnData getNewsList(FishDTO dto);

    public ReturnData getPersonData(FishDTO dto);

    public ReturnData getCheckCode(FishDTO dto);

    public ReturnData register(FishDTO dto);

    public ReturnData login(FishDTO dto);

    public ReturnData logout(FishDTO dto);

    public ReturnData modifyPwd(FishDTO dto);

    public ReturnData saveForgetPwd(FishDTO dto);

    public ReturnData getMallIndex(FishDTO dto);

    public ReturnData getMallProductDetail(FishDTO dto);

    public ReturnData exchangePoints(FishDTO dto);

    public ReturnData getExchangeRecords(FishDTO dto);

    public ReturnData getPointRecords(FishDTO dto);

    public ReturnData getExchangeDetail(FishDTO dto);

    public ReturnData modifyNickName(FishDTO dto);

    public ReturnData saveFeedback(FishDTO dto);

    public ReturnData getFinanceList(FishDTO dto);

    public ReturnData getFinanceDetail(FishDTO dto);

    public ReturnData applyFinance(FishDTO dto);

    public ReturnData getInsuranceList(FishDTO dto);

    public ReturnData getInsuranceDetail(FishDTO dto);

    public ReturnData applyInsurance(FishDTO dto);

    public ReturnData getAppConstantDatas(FishDTO dto);

    public ReturnData saveSupplyInfo(FishDTO dto);

    public ReturnData querySupplyList(FishDTO dto);

    public ReturnData querySupplyDetail(FishDTO dto);

    public ReturnData saveSupplyOrder(FishDTO dto);

    public ReturnData saveBuyInfo(FishDTO dto);

    public ReturnData queryBuyList(FishDTO dto);

    public ReturnData queryBuyDetail(FishDTO dto);

    public ReturnData queryMySupplyList(FishDTO dto);

    public ReturnData queryMyBuyList(FishDTO dto);

    public ReturnData queryMarketPriceList(FishDTO dto);

    public ReturnData getSupplyOrderList(FishDTO dto);

    public ReturnData getSupplyOrderDetail(FishDTO dto);

    public ReturnData cancleOrder(FishDTO dto);

    public ReturnData setOutlinePay(FishDTO dto);

    public ReturnData confirmReceived(FishDTO dto);

    public ReturnData queryBankInfo(FishDTO dto);

    public ReturnData saveEvaluation(FishDTO dto);

}
