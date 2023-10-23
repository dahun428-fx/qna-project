package com.qna.project.qnaproject.model;

import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@Alias(value = "ec_qna")
@NoArgsConstructor
public class EcQna {
    private int qnaId;
    private String title;
    private String content;
    private String partNo;
    private String regId;
    private String seriesCode;
    private int contactId;
    private String isDelete;
    private String deletedUserId;
    private Timestamp deletedDate;
    private String isBlind;
}

/*
 * var qnaParams = {
    // use_purpose: vona2.functions.htmlEscape($("#qna_input_purpose").val()),
        content: vona2.functions.htmlEscape($("#qna_input_question").val()),
        part_no : vona2.functions.htmlEscape($("#qna_input_part_no").val()),
        reg_id: vona2.userInfo.userCode,
        reg_name: vona2.userInfo.userName,
        reg_code: vona2.userInfo.customerCode ? vona2.userInfo.customerCode : '',
        reg_company: vona2.userInfo.customerName ? vona2.userInfo.customerName : '',
        reg_tel: userData ? userData.tel : '',
        reg_email: $("#customerEmail").val(),
        series_code: VNServerInfo.seriesData.seriesCode,
        series_name: VNServerInfo.seriesData.seriesName,
        category_code: VNServerInfo.seriesData.categoryCode,
        category_name: VNServerInfo.seriesData.categoryName,
        brand_code: VNServerInfo.seriesData.brandCode,
        brand_name: VNServerInfo.seriesData.brandName,
        contact_name: 'TS팀',
    //   contact_name: opener.VNServerInfo.seriesInfo.seriesList ? opener.VNServerInfo.seriesInfo.seriesList[0].contact.contactName ? opener.VNServerInfo.seriesInfo.seriesList[0].contact.contactName : '지정없음': '지정없음',
    };
 */