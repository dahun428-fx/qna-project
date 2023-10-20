package com.qna.project.qnaproject.dto.ec_qna;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
@Data
@ToString
@Builder
public class CreateQnaDTO {
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
