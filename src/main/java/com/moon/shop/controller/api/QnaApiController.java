package com.moon.shop.controller.api;


import com.moon.shop.config.auth.PrincipalDetail;
import com.moon.shop.domain.QnaResponse;
import com.moon.shop.domain.Qna;

import com.moon.shop.dto.ResponseDto;
import com.moon.shop.service.QnaService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class QnaApiController {

    private final QnaService qnaService;

    /**
     * 문의글
     */
    //문의글 작성
    @PostMapping("/api/user/qna")
    public ResponseDto<Integer> save(@RequestBody Qna qna, @AuthenticationPrincipal PrincipalDetail principal){
        qnaService.saveQna(qna, principal.getMember());
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    //문의글 삭제
    @DeleteMapping("/api/user/qna/{id}")
    public ResponseDto<Integer> deleteById(@PathVariable int id){
        qnaService.deleteById(id);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    //문의글 수정
    @PutMapping("/api/user/qna/{id}")
    public ResponseDto<Integer> update(@PathVariable int id, @RequestBody Qna qna){
        qnaService.updateQna(id,qna);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    //문의글 리스트 조회
    @GetMapping("/api/user/qna/qnaList")
    public List<Qna> list(){
        List<Qna> qnaList = qnaService.qnaList();
        return qnaList;
    }

    //문의글 상세 조회
    @GetMapping("/api/user/qna/{id}")
    public Qna findById(@PathVariable int id){
        Qna qnaDetail = qnaService.qnaDetail(id);
        return qnaDetail;
    }


    /**
     * 문의글 답변
     */
    //문의글 답변 작성
    @PostMapping("/api/admin/qna/{qnaId}/response")
    public ResponseDto<Integer> responseSave(@PathVariable int qnaId, @RequestBody QnaResponse qnaResponse, @AuthenticationPrincipal PrincipalDetail principal){
        qnaService.saveResponse(principal.getMember(), qnaId, qnaResponse);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    //문의글 답변 삭제
    @DeleteMapping("/api/user/qna/{qnaResponseId}/delete")
    public ResponseDto<Integer> responseSave(@PathVariable int qnaResponseId){
        qnaService.deleteResponse(qnaResponseId);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }


}
