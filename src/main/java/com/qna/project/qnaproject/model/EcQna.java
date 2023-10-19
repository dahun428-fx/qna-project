package com.qna.project.qnaproject.model;

import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
@Alias(value = "ec_qna")
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
