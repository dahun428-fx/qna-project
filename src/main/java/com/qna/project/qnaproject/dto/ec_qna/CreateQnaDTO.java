package com.qna.project.qnaproject.dto.ec_qna;

import java.sql.Timestamp;

import com.qna.project.qnaproject.model.Series;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateQnaDTO {
    private int qnaId;
    private String title;
    private String content;
    private String partNo;
    private String regId;
    private String seriesCode;
    private String seriesName;
    private String categoryCode;
    private String categoryName;
    private String brandCode;
    private String brandName;
    private int contactId;
    private String contactName;
    private String isDelete;
    private String deletedUserId;
    private Timestamp deletedDate;
    private String isBlind;
}
